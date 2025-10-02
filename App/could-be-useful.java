import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PaymentExample {
    public static void main(String[] args) {
        try {
            // Example endpoint (replace with real one, e.g. Stripe or Razorpay)
            URL url = new URL("https://api.payment-gateway.com/v1/payments");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            
            // Replace with your gateway’s API key
            conn.setRequestProperty("Authorization", "Bearer YOUR_SECRET_KEY");
            conn.setDoOutput(true);

            // Example payment data (amount in paise/cents, currency, etc.)
            String jsonInput = """
                {
                    "amount": 50000,
                    "currency": "INR",
                    "description": "Test Payment",
                    "receipt": "rcptid_11"
                }
            """;

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            int code = conn.getResponseCode();
            System.out.println("Response Code: " + code);

            if (code == HttpURLConnection.HTTP_OK) {
                System.out.println("Payment request sent successfully!");
            } else {
                System.out.println("Payment request failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

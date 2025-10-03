import random

def play_game():
    # Pick a random number between 1 and 100
    number = random.randint(1, 100)
    attempts = 0

    print("\n🎲 Welcome to the Number Guessing Game!")
    print("I'm thinking of a number between 1 and 100.")

    while True:
        try:
            guess = int(input("👉 Enter your guess: "))
            attempts += 1

            if guess < number:
                print("📉 Too low! Try again.")
            elif guess > number:
                print("📈 Too high! Try again.")
            else:
                print(f"🎉 Correct! You guessed it in {attempts} tries.")
                break
        except ValueError:
            print("⚠ Please enter numbers only.")

def main():
    while True:
        play_game()
        again = input("Play again? (y/n): ").strip().lower()
        if again != "y":
            print("👋 Thanks for playing, see you next time!")
            break

if __name__ == "__main__":
    main()

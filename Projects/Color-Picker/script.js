const colorInput = document.getElementById("colorPicker");
const colorBox = document.getElementById("colorBox");
const hexValue = document.getElementById("hexValue");

colorBox.style.backgroundColor = colorInput.value;

colorInput.addEventListener("input", function () {
    colorBox.style.backgroundColor = this.value;
    hexValue.textContent = this.value;
});
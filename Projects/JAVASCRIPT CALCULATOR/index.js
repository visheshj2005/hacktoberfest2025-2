let display = document.getElementById('display');
let currentInput = '';
let shouldResetDisplay = false;

function appendNumber(num) {
  if (display.textContent === '0' || shouldResetDisplay) {
    display.textContent = num;
    shouldResetDisplay = false;
  } else {
    display.textContent += num;
  }
  currentInput = display.textContent;
}

function appendOperator(op) {
  if (/[+\-*/]$/.test(display.textContent)) {
    display.textContent = display.textContent.slice(0, -1) + op;
  } else {
    display.textContent += op;
  }
  shouldResetDisplay = false;
}

function appendDecimal(dot) {
  const parts = display.textContent.split(/[\+\-\*\/]/);
  const lastPart = parts[parts.length - 1];
  if (!lastPart.includes(dot)) {
    display.textContent += dot;
  }
}

function clearDisplay() {
  display.textContent = '0';
  currentInput = '';
}

function calculate() {
  try {
    const result = eval(display.textContent);
    display.textContent = result;
    currentInput = result;
    shouldResetDisplay = true;
  } catch (e) {
    display.textContent = 'Error';
    shouldResetDisplay = true;
  }
}
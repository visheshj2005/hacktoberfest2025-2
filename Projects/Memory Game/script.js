const emojis = ["🐶", "🐱", "🐰", "🐻", "🐼", "🐸", "🦁", "🐵"];
let cardsArray = [...emojis, ...emojis];

const gameBoard = document.getElementById("gameBoard");
const resetBtn = document.getElementById("resetBtn");
const celebration = document.getElementById("celebration");
const flowersContainer = document.getElementById("flowers");

let flippedCards = [];
let matchedCards = [];

// Shuffle cards
function shuffle(array) {
  return array.sort(() => 0.5 - Math.random());
}

// Create game board
function createBoard() {
  celebration.classList.add("hidden");
  flowersContainer.innerHTML = "";
  gameBoard.innerHTML = "";
  flippedCards = [];
  matchedCards = [];
  cardsArray = shuffle(cardsArray);

  cardsArray.forEach((emoji) => {
    const card = document.createElement("div");
    card.classList.add("card");
    card.dataset.emoji = emoji;
    card.textContent = emoji;

    card.addEventListener("click", flipCard);
    gameBoard.appendChild(card);
  });
}

// Flip card function
function flipCard() {
  if (
    flippedCards.length === 2 ||
    this.classList.contains("flipped") ||
    this.classList.contains("matched")
  ) {
    return;
  }

  this.classList.add("flipped");
  flippedCards.push(this);

  if (flippedCards.length === 2) {
    checkMatch();
  }
}

// Check if cards match
function checkMatch() {
  const [card1, card2] = flippedCards;

  if (card1.dataset.emoji === card2.dataset.emoji) {
    card1.classList.add("matched");
    card2.classList.add("matched");
    matchedCards.push(card1, card2);
  } else {
    setTimeout(() => {
      card1.classList.remove("flipped");
      card2.classList.remove("flipped");
    }, 800);
  }

  flippedCards = [];

  if (matchedCards.length === cardsArray.length) {
    setTimeout(showCelebration, 600);
  }
}

// Celebration animation
function showCelebration() {
  celebration.classList.remove("hidden");
  for (let i = 0; i < 40; i++) {
    const flower = document.createElement("div");
    flower.classList.add("flower");
    flower.textContent = ["🌸", "🌼", "🌺", "🌷", "🌻"][Math.floor(Math.random() * 5)];
    flower.style.left = Math.random() * 100 + "vw";
    flower.style.animationDuration = 3 + Math.random() * 3 + "s";
    flowersContainer.appendChild(flower);
  }

  setTimeout(() => {
    celebration.classList.add("hidden");
    createBoard();
  }, 6000);
}

// Reset game
resetBtn.addEventListener("click", createBoard);

// Initialize
createBoard();

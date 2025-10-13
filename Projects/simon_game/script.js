let gameSeq = [];
let userSeq = [];
let btns = ["green" , "red" , "yellow" , "blue"];

let started = false;
let level = 0;
let h2 = document.querySelector("h2");

document.addEventListener("keypress" , function() {
    if (!started) {
        console.log("game is started");
        started = true;
        // If game is started call the level up function
        levelUp();
    }
});

function btnFlash(btn) {
    btn.classList.add("flash");
    setTimeout(function () {
        btn.classList.remove("flash");
    }, 200);
}

function userFlash(btn) {
    btn.classList.add("userFlash");
    setTimeout(function () {
        btn.classList.remove("userFlash");
    }, 200);
}

function levelUp() {
    userSeq = [];
    level++;
    h2.innerText = `Level ${level}`;

    let randIdx = Math.floor(Math.random() * 3);
    let randColor = btns[randIdx];
    let randBtn = document.querySelector(`.${randColor}`);
    gameSeq.push(randColor);
    console.log(gameSeq);
    btnFlash(randBtn);
    // For mobile version
    document.querySelector(".mobile").style.display = "none";
};

// Creating function to check the answer
function checkAns(idx) {
    // let idx = level - 1;
    if(userSeq[idx] === gameSeq[idx]){
        if (userSeq.length === gameSeq.length){
            setTimeout(levelUp , 1000);
        }
    }
    else {
        h2.innerHTML = `Game Over &#x1F47B Your Score was <b>${level}</b> 
                        Press any key to Restart`;
        document.querySelector("body").style.backgroundColor = "#AB0003";
        setTimeout(function() {
        document.querySelector("body").style.backgroundColor = "#3c2717";
        }, 150);
        highestScore();
        resetGame();
    }
}

// Button press
function btnPress() {
    let btn = this;
    userFlash(btn);

    userColor = btn.getAttribute("id");
    userSeq.push(userColor);

    checkAns(userSeq.length - 1);
}

let allBtns = document.querySelectorAll(".btn");
for (btn of allBtns) {
    btn.addEventListener("click" , btnPress);
}

// Creating function to reset the game
function resetGame () {
    started = false;
    level = 0;
    gameSeq = [];
    userSeq = [];
    document.querySelector("#mobile").innerText = "RESTART"; // show restart
    document.querySelector(".mobile").style.display = "flex";
}

// Creating function to print highest score
let h3 = document.querySelector("#highest");
let hScore = 0;
function highestScore() {
    if(level > hScore){
        hScore = level;
        h3.innerHTML = `Highest Score: ${hScore}`;
    }
}

// Game will start on clicking the start button
let mStart = document.querySelector("#mobile");
document.addEventListener("click" , function(e) {
    if (!started && e.target.id == "mobile") {
        started = true;
        levelUp();
    }
});

if(window.innerWidth <= 400) {
    document.querySelector("h2").innerText = 
    "Press Start button to begin the game"
}
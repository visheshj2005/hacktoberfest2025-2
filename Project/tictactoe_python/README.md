# 🎮 Tic-Tac-Toe (Python)

A **command-line Tic-Tac-Toe game** between a human player (`X`) and the computer (`O`), written entirely in Python.  
This version includes **logging of all moves**, a **scoreboard**, and an option to **play multiple rounds**.

---

## 📋 Features

- 🧑 Human (X) vs 🤖 Computer (O)
- 🗂️ Logs every move to a file (`log.txt`)
- 🧾 Scoreboard to keep track of wins
- 🔁 Option to replay and reset scores
- ❌ Detects wins, draws, and invalid moves
- 🎲 Computer makes random valid moves

---

## 🧠 Game Rules

1. The game is played on a **3×3 grid**.  
2. The **human (X)** always makes the **first move**.  
3. The **computer (O)** randomly picks from available empty spots.  
4. The first to align **3 symbols in a row, column, or diagonal** wins.  
5. If the board fills up with no winner, it’s a **draw**.

---

## 🕹️ How to Play

### 🧩 Step 1 — Run the program

```bash
python tictactoe.py
```

### 🧩 Step 2 — Follow on-screen instructions

- Enter the **row (1–3)** and **column (1–3)** to place your move.
- Watch as the computer plays its move.
- The game will display the winner or declare a tie.
- After each match, you’ll be asked if you want to **play again** or **reset the score**.

---

## 📘 Log File (`log.txt`)

Every move in the game is recorded in a text file named **`log.txt`** for reference.

| Move Count | Player (H/C) | Row | Column | Piece (X/O) |
|-------------|---------------|-----|--------|-------------|
| 1 | H | 1 | 2 | X |
| 2 | C | 2 | 3 | O |
| ... | ... | ... | ... | ... |

> 💡 The file is reset each time you start a new game session.

---

## 🧩 Example Gameplay

```
------------------------
---   TIC TAC TOE   ---
Human (X) vs Computer (O)
------------------------

     1     2     3
1    X  |     |  
    ---+-----+---
2       |  O  |  
    ---+-----+---
3       |     |  

Your turn!
Enter row number: 2
Enter column number: 2
```

---

## 🧮 Scoreboard Example

```
--------------------------------
            SCOREBOARD       
--------------------------------
      Human : 2 points
      Computer : 1 points
--------------------------------
```

---

## 🧰 Code Overview

| Function | Description |
|-----------|--------------|
| `main()` | Runs a single game session. |
| `main_menu()` | Displays title and instructions. |
| `display_board()` | Prints the current game board. |
| `user_move()` | Handles player input and validation. |
| `comp_move()` | Randomly selects a move for the computer. |
| `check_row()`, `check_column()`, `check_diagonals()` | Check for winning conditions. |
| `check_win()` | Determines if someone has won. |
| `check_draw()` | Determines if the board is full. |
| `log_info()` | Writes move details to `log.txt`. |
| `score_board()` | Displays current scores. |
| `play_again()` | Asks to replay or reset the game. |

---

## 🧑‍💻 Requirements

- Python 3.x  
- No external libraries required (`random` module is built-in)

---

## 🚀 How to Run on Any System

1. Save the script as `tictactoe.py`.
2. Open a terminal or command prompt.
3. Run:
   ```bash
   python tictactoe.py
   ```
4. Enjoy the game!

---

## 📄 License

This project is open-source and free to use under the **MIT License**.  
Feel free to modify and improve it!

---

## 💡 Future Improvements

- Add a smarter AI using the **Minimax algorithm**
- Add **GUI** using `tkinter` or `pygame`
- Save **multiple game logs** instead of overwriting
- Implement **leaderboard** persistence

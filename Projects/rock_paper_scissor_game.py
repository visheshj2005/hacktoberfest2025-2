import random

# -----------------------------------------
# 🎮 ROCK, PAPER, SCISSORS GAME
# -----------------------------------------
# The user plays against the computer.
# Computer randomly chooses rock, paper, or scissors.
# Rules:
# Rock beats Scissors 🪨
# Scissors beats Paper 
# Paper beats Rock 
# -----------------------------------------

print(" Welcome to Rock, Paper, Scissors! ")
print("Type 'rock', 'paper', or 'scissors' to play. Type 'exit' to quit the game.\n")

# List of valid choices
choices = ["rock", "paper", "scissors"]

while True:
    # Step 1: Ask user for input
    user_choice = input("Your choice: ").lower()

    # Step 2: Option to exit the game
    if user_choice == "exit":
        print(" Thanks for playing! Goodbye!")
        break

    # Step 3: Validate user input
    if user_choice not in choices:
        print(" Invalid choice! Please enter rock, paper, or scissors.")
        continue

    # Step 4: Computer makes a random choice
    computer_choice = random.choice(choices)
    print(f"🤖 Computer chose: {computer_choice}")

    # Step 5: Compare both choices and decide winner
    if user_choice == computer_choice:
        print(" It's a tie!")
    elif (user_choice == "rock" and computer_choice == "scissors") or \
         (user_choice == "scissors" and computer_choice == "paper") or \
         (user_choice == "paper" and computer_choice == "rock"):
        print(" You win!")
    else:
        print("💻 Computer wins!")


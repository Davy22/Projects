import random
colors = ['Red','Yellow', 'Blue', 'Green', 'Orange', 'Pink', 'Purple', 'Cyan', 'Silver', 'Teal']
sequence  = []
for i in colors:
  sequence.append(i)
  sequence = random.sample(colors, 4)
sequence2 =[]
for x in sequence:
  sequence2.append(x.lower())
print(sequence2)
player = input("What is your name? ")
print(f"Welcome to Master Mind {player}!")
print("The code maker is here. Available colors are\nRed, Yellow, Blue, Green, Orange, Pink, Purple, Cyan, Silver, Teal")
print("You have 15 guesses, total of 5 penalties are allowed but avoid penalties.\nThe code maker selected 4 colors")
print(f"You can start guessing {player}.")
count = 0
num_guess = 0
while num_guess < 15:
  penalties = 0
  black = 0
  white = 0 
  count +=1
  num_guess += 1
  guess = input(f"Enter guess number {count}: ").lower().split()
  print(guess)
  if guess == sequence2:
    game = True
    break
  else:
    for i, j in zip(guess, sequence2):
      if i == j:
        black +=1
    for i in range(0,4):
      if i in sequence2:
        white +=1
    for i,j in zip(guess, sequence2):
      if i not in colors:
        penalties +=1
  print(f'{black} blacks, {white} whites')
  num_guess -=1
  if penalties == 5:
    print("you've exceeded the number of penalties")
    break

if game == True:
  print(f"You won the game with {count} guesses and {penalties} penalties, Congratulations")
else:
  print(f"Sorry {player}, you ran out of guesses and you lost the game with {penalties} penalties")

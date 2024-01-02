# Board Game

A simple Java board game, where 2 players compete against each other.
Each player starts with 3 lives, but they lose lives after landing on an enemy's square.
A player wins after reaching the exit square or if the other player loses all their lives.

## Usage

Clone the repo and run the `Main` class from your IDE or download the latest release and execute the .jar file: 

```zsh
java -jar prog.jar
```

## How to play

At first, players must choose the dimensions of the board, from 6x6 to 10x10 and the difficulty: easy, medium or hard. Higher difficulty means more enemies on the board.
Then, each player gets to choose their character and after that Player1 will be prompted to insert his move. After every move, the game updates the board's state and then prompts the next player for a move.

### Moves
The movement, like many keyboard games is 'wasd', but before inserting the direction character (w for example), the player must first introduce a number from 1 to 3 that indicates how many squares to move in said direction.

Valid examples: 

```zsh
3w > Moves 3 squares up.
1d > Moves 1 squares to the right.
2d > Moves 2 squares to the left.
```

### Cheats
Each player can use the command `1t` to visualize the board with enemies included, however this command can only be used once and it will consume 1 life.

## PSA
This is just a school project. Chill.

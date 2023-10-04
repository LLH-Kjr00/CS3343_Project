# CS3343_Project: Shou Dou Qi (The Jungle Chess) Game
CityU HK CS3343 Software Engineering Practices Course-work project

# Description of the application
This application utilizes Java and a web browser to provide an environment for players to play Shou Dou Qi (or the Jungle Chess) through a computer. 
Shou Dou Qi is an ancient Chinese board game that was popular among people of all ages, and we wish to bring this game alive through this project.

# Basic Guide (How to Play)
1) A player can move one pawn in each turn, choosing whether to go up, down, left, or right, in a 7*9 rectangular board.
2) If a player's pawn has greater than or equal strength when compared to the opponent's pawn, then the player can kill it by stepping on its place in a turn.
3) The strength of the pawns arranged in descending order is Elephant > Lion > Tiger > Leopard > Wolf > Dog > Cat > Mouse (except for Mouse, which is allowed to kill Elephant).
4) Also, if the opponent's pawn steps into a trap, the player can use any pawn to kill it by basically neglecting its strength.
5) To win the game, the player has to either kill all the pawns that the opponent has or reach the opponent's den.

# Special rules 
1) The Red Player makes the first move.
2) There is limited time for each player's turn, a player will skip the turn if no move has been committed.
3) There are two rivers near the middle of the board. Most pawns cannot interact with the rivers.
4) Only Mouse can submerge in the river by walking into it.
5) Only Lion and Tiger can jump across a river once if there is no pawn in between the starting point and the landing point (i.e.: a mouse in between)
6) Lion and Tiger are allowed to kill an opponent's pawn on the landing spot after they jump
7) Mouse can only kill when it is in the same medium as the opponent's pawn (i.e.: cannot kill a mouse/elephant when it exits a river)

# Installation

# Libraries used 
JRE System Library (for basic Java functionalities)
JUnit (for testing)

# Versions and corresponding Bug Fixes

# List of Contributors:
Alex
Ken 
Landon
Michael
Pedro
Wing

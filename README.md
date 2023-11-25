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
5) Only Lion and Tiger can jump across a river once if there is no pawn in between the starting point and the landing point

   (i.e.: a mouse in between)
7) Lion and Tiger are allowed to kill an opponent's pawn on the landing spot after they jump
8) Mouse can only kill when it is in the same medium as the opponent's pawn (i.e.: cannot kill a mouse/elephant when it exits a river)

# Installation and Running
- Using the ZIP file from GitHub's source code (link: https://github.com/LLH-Kjr00/CS3343_Project)
1) If the current branch is not "main", switch to "main" by clicking the drop-down catalog near the top-left corner
2) Press the "Code" button which is in green
3) Press "Download ZIP" to download the ZIP file
4) Decompress the ZIP file to open it
5) Open the source code folder in an IDE such as Microsoft Visual Studio or IntelliJ
6) Go to App in the directory "app\src\main\java\animalchess\App.java"
7) Run the Application

- Using the JAR file from the project folder
1) Go to the directory "Release"
2) Run the JAR file "JungleChessV1.0.jar"

# Libraries used 
- JRE System Library (for basic Java functionalities)
- JUnit (for testing)
- JSwing (for making the GUI)

# Versions and Achieved task(s)
- V0.1: Setup GitHub and made the basic file structure
- V0.2: Finished GameUI's implementation
- V0.3: Finished Animal and its Children classes' basic implementation, including exception handling related to basic and special rules
- V0.4: Finished Board's basic implementation 
- V0.5: Refactored Board's implementation, adding Tile and its Children classes to the Board
- V0.6: Fixed bugs related to the Animal (moving diagonally, Mouse could eat other animals in a different medium)
- V0.7: Refactored GUI's implementation, adding JPanel classes and JBox classes to the GameUI
- V0.8: Tested Animal and Board; Fixed bugs discovered during testing (Lion jumping horizontally)
- V0.9: Added documentation to the source code
- V1.0: Released to the public
# List of Contributors:
Alex
Ken 
Landon
Michael
Pedro
Wing

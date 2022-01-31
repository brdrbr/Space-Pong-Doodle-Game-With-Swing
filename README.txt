The Instructions File is Listed in the Instructions section.
Classes and functions:
Main Class:
-I created a new instance of the ballgame in order to initialize the program and then I called the init function of the ball game. The init function and what it does wil
be explained below.
BallGame Class:
First, the dimensions of the shapes are entered and the initial random x and y locations of the shapes are created.
Then, the wrapper shapes of each type of shape is formed around them. They are created in order to detect when the ball hits the shapes. Since these rectangular shapes
are always in the same spot as their corresponding shapes and they have the same height, this logic works.
A new instance of the ball class gets created.
The run function gets executed when the project runs.
Run Function:
The function enters a while true statement and breaks out of it if and only if when the total lives reach zero.
In this function, whether or not the ball currently hits the wrappers of the shapes gets tested by the if statements. If one of these of statements is true, 
the ball respawns to its beginning position and whatever is supposed to happen when the particular shape is hit happens. 
After these if statements, the program checks if the ball hit the paddle and if this is true, the direction of the y-coordinate of the ball is reversed.
After this if, the program checks whether or not the ball has hit the right, left or the top side of the game and if this is the case, either the x and y coordinate
is reversed depending on the side that the ball has hit.
Then during the time that the program checks whether or not the ball hit the top, it also checks whether or not it hit the bottom. If this is the case this means that the 
user hasn't been able to catch the ball, one life is taken off and a new ball is formed in the starting position in order for the user to continue the game.
After this there is an if statement that checks whether or not the game is paused and if this is the case then the new ball gets respawned to the initial location and
its speed is back to the starting speed of the level.
After, there are if statements that control whether or not the amountr and amountl flags are 1.
These flags become one later in the code when the right and left keys are pressed. This is done by using the built in keypressed. If the right key is pressed,
the paddle moves 30 units to the right and if left is pressed, the paddle moves 30 units to the left. The keypressed is used in the init function which will be
mentioned later.
Then the program checks if the program is currently being played and not paused or the life count is not zero. If this is the case, the time remaining is checked.
If it is zero, the level and speed is increased and the remaining time is reset to 60.
Then the gui is updated by being repainted.
The following try and catch statement updates the GUI every 5 milliseconds.
RespawnWrapper Function:
This is called during the run method when the wrappers are hit. It randomly assigns a new location to the wrappers.
ReadImages Function:
This function gets the images of the stellar objects from the computer and sets them and their y values up for the game.
paintComponent Function:
Paints all of the components (paddle, stellar objects and ball) onto the GUI.
drawPaddle Function:
Draws a paddle by setting the graphics2d object to black and fills it as a rectangle with the desired length and location.
init Function:
Sets up the start button and the key listeners in order to catch the arrow movements for the paddle.
Setup Class:
This class provides the information that is located at the top left of the gui such as lives, score, level and time remaining.
init Function:
Sets up the GUI's 3 sections and the game information.
Ball Class:
The ball is created in this class with its desired coordinates and radius. 
Move Function:
Makes the ball move in the desired gravitational movement.
Sources Used:
https://www.cs.utexas.edu/~mitra/csSpring2007/cs313/lectures/threads/anim.html
https://www.codejava.net/java-se/swing/jpanel-basic-tutorial-and-examples
https://stackoverflow.com/
https://stackoverflow.com/questions/19695620/bouncing-ball-off-of-paddle-in-breakout-game-java
https://zetcode.com/javagames/breakout/

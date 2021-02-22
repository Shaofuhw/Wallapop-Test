# MY SOLUTION

My solution is build with Kotlin as the reduced boilerplate needed makes
working with it quite more pleasant than vanilla Java.

I decided to divide the classes by their own functionality as much as possible.

There are 4 main things in the program:
 - Rover:
    - Holds movement logic
 - Mars Map:
    - Holds Map size and list of obstacles
 - Coordinates:
    - Holds current position of the rover
 - ConsoleUtils:
    - Helps with the retrieval of the data from the console
    
Both Rover and Map have a static method "Builder.setup()" that will
ask all the required data from the console.

For the obstacles, there is a "generateRandomObstacles" inside the Map class
as asking for this data from the console is cumbersome. There is a "setObstacles"
so the data can be imported from different places like files or APIs.

There are a few Enums that list the types of ConsoleInputs, Rover
Orientations and Rover Commands to make the conditionals easier to handle.

As for the console input, there is a check for valid data, like typing characters
or negative numbers on mapSize, so it will keep asking the user for valid data.

Lastly, there are unit tests for most classes and methods.
 - ConsoleUtilsTests checks the console input and output values
 - CoordinatesTests checks that values are properly updated in the case the value
 is over the mapSize
 - MapTest checks the Setup and obstacle detection
 - RoverTest checks all the commands. 
 
I did not include the compiled self-executable file, but it should be
as simple as compile the project to create a .jar file.
# MY SOLUTION

For my solution, I kept the input from the console, although this could be change to retrieve the data either from a 
file or an API

The "Map" class contains a static Builder. Calling the setup will ask for the data from the console,
both X and Y size.

Same goes for the Rover. Calling the setup will ask for the initial position and orientation.

There are 3 enums that list all the possible options for Orientation, ConsoleInputTypes and RoverCommands.
Having this in an enum reduces the chances of typing an option wrong.

All methods are Unit Tested. Including console input and output data.


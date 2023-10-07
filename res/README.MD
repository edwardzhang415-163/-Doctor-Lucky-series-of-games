
# MyWorld

MyWorld is a game world where spaces, items, and characters are placed. It is implemented in Java and uses Swing for the graphical user interface.

## Getting Started


### Usage

When you run the project, you will see the game world displayed in a window. You can move the character around the world using the arrow keys. You can pick up items by moving onto them, and you can use items by pressing the space bar.

### File Format

The game world is loaded from a text file with the following format:

```
<numRows> <numCols> <name>
<character_health> <character_name> <row1> <col1> <row2> <col2>
<space_0_0> <space_0_1> ... <space_0_numCols-1>
<space_1_0> <space_1_1> ... <space_1_numCols-1>
...
<space_numRows-1_0> <space_numRows-1_1> ... <space_numRows-1_numCols-1>
<numItems>
<item_0_roomIndex> <item_0_damage> <item_0_name>
<item_1_roomIndex> <item_1_damage> <item_1_name>
...
```

- `<numRows>` and `<numCols>` are the number of rows and columns in the game world, respectively.
- `<name>` is the name of the game world.
- `<character_health>` is the starting health of the character.
- `<character_name>` is the name of the character.
- `<row1>` and `<col1>` are the row and column of the upper left corner of the character's starting position.
- `<row2>` and `<col2>` are the row and column of the lower right corner of the character's starting position.
- `<space_i_j>` is the type of space at row `i` and column `j`.
- `<numItems>` is the number of items in the game world.
- `<item_i_roomIndex>` is the index of the room where the `i`th item is located.
- `<item_i_damage>` is the amount of damage the `i`th item can do if used to attack the character.
- `<item_i_name>` is the name of the `i`th item.

## Jar Instruction
 -Please create res directory in the same directory as the .jar file.
 -And put the mansion.txt  in the res directory.
 -See examplerun.txt.




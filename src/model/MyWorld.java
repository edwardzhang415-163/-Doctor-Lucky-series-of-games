package src.model;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

/**
 * Represents the game src.world where spaces, items, and characters are placed.
 */
public class MyWorld implements World, ViewWorld {
  private int numRows;
  private int numCols;
  private String name;
  private List<Space> spaces; // Stores all the space information
  private Character character; // Stores character information
  private MyWorldPet pet;
  private List<Item> items;
  private List<Player> players;

  /**
   * Constructs the game src.world with the provided spaces, items, and character.
   *
   * @param filename The name of the file to parse.
   */

  public MyWorld(String filename) {
    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(filename));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    String line;
    int lineNumber = 0;
    int numRowsN = 0;
    int numColsN = 0;
    String worldName = "";
    int spaceNumber = 0;
    int health = 0;
    String characterName = "";
    String petName = "";
    int numItems = 0;
    List<Space> spacesN = new ArrayList<>();
    List<Item> itemsN = new ArrayList<>();
    try {
      while ((line = br.readLine()) != null) {
        if (lineNumber == 0) {
          String[] tokens = line.trim().split("\\s+", 3);
          numRowsN = Integer.parseInt(tokens[0]);
          numColsN = Integer.parseInt(tokens[1]);
          worldName = tokens[2];
        } else if (lineNumber == 1) {
          String[] tokens = line.trim().split("\\s+", 2);
          health = Integer.parseInt(tokens[0]);
          characterName = tokens[1];
        } else if (lineNumber == 2) {
          petName = line;
        } else if (lineNumber == 3) {
          spaceNumber = Integer.parseInt(line);
        } else if (lineNumber <= spaceNumber + 3) {
          String[] tokens = line.trim().split("\\s+", 5);
          spacesN.add(new MyWorldSpace(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                  Integer.parseInt(tokens[2]) + 1, Integer.parseInt(tokens[3]) + 1, tokens[4]));
        } else if (lineNumber == spaceNumber + 4) {
          numItems = Integer.parseInt(line);
        }  else if (lineNumber <= spaceNumber + 4 + numItems) {
          String[] tokens = line.split(" ", 3);
          itemsN.add(new MyWorldItem(Integer.parseInt(tokens[0]),
                  Integer.parseInt(tokens[1]), tokens[2]));
        }
        lineNumber++;
      }
      this.numRows = numRowsN;
      this.numCols = numColsN;
      this.name = worldName;
      this.spaces = spacesN;
      this.character = new MyWorldCharacter(health, characterName, 0, spaceNumber - 1);
      this.pet = new MyWorldPet(petName, spacesN.get(0), spaceNumber - 1);
      this.items = itemsN;
      spaces.get(0).addCharacter(character);
      spaces.get(0).addPet(pet);
    } catch (IOException e) {
      System.out.println(lineNumber);
      e.printStackTrace();
    }
    validateSpaces(this.spaces);
    // Add items
    deployItems();
    // Connect adjacent spaces
    connectAdjacentSpaces();
    this.players = new ArrayList<>();
  }

  @Override
  public int getNumRows() {
    return numRows;
  }

  @Override
  public int getNumCols() {
    return numCols;
  }

  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets all the spaces in the game src.world.
   *
   * @return List of spaces.
   */
  @Override
  public List<Space> getSpaces() {
    return spaces;
  }

  /**
   * Gets the character in the game src.world.
   *
   * @return The character.
   */
  @Override
  public Character getCharacter() {
    return character;
  }

  @Override
  public MyWorldPet getPet() {
    return pet;
  }

  @Override
  public List<Item> getItems() {
    return items;
  }

  /**
   * Creates a graphical representation of the src.world map with space names.
   *
   * @return BufferedImage representing the src.world map with space names.
   */
  @Override
  public BufferedImage renderWorldImage() {
    int numRowsN = getNumRows(); // Get the number of rows in the src.world
    int numColsN = getNumCols(); // Get the number of columns in the src.world
    int cellSize = 50; // Size of each cell in pixels

    int mapWidth = numColsN * cellSize;
    int mapHeight = numRowsN * cellSize;

    BufferedImage mapImage = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = mapImage.createGraphics();
    g2d.setBackground(Color.WHITE);

    List<Space> spacesN = getSpaces();

    // Draw spaces and their names
    for (Space space : spacesN) {
      int upperLeftX = space.getUpperLeftCol() * cellSize;
      int upperLeftY = space.getUpperLeftRow() * cellSize;
      int width = space.getLowerRightCol() * cellSize - upperLeftX - 1;
      int height = space.getLowerRightRow() * cellSize - upperLeftY - 1;
      String spaceName = space.getName();


      // Draw space as a rectangle with black border and white fill color
      g2d.setColor(Color.BLACK);
      g2d.drawRect(upperLeftX, upperLeftY, width, height); // Draw black border
      g2d.setColor(Color.WHITE);
      g2d.fillRect(upperLeftX + 1, upperLeftY + 1, width - 1, height - 1); // Draw white fill color

      // Draw space name
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Arial", Font.BOLD, 16)); // You can adjust font size if needed
      g2d.drawString(spaceName, upperLeftX + 10, upperLeftY + 30); // Adjust text position as needed
    }

    g2d.dispose(); // Release resources

    // print out the map image in res package
    try {
      ImageIO.write(mapImage, "png", new File("res/world-map.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return mapImage;
  }

  @Override
  public String moveCharacter() {
    spaces.get(character.getCurrentRoomIndex()).removeCharacter();
    character.moveSpace();
    spaces.get(character.getCurrentRoomIndex()).addCharacter(character);
    return String.format("Target moved to %s",
            spaces.get(character.getCurrentRoomIndex()).getName());
  }

  @Override
  public String movePet(String spaceName) {
    for (Space space : spaces) {
      if (space.getName().equals(spaceName)) {
        pet.setCurrentSpace(space);
        pet.refresh();
        return String.format("Pet teleported to %s", spaceName);
      }
    }
    return "There is no such space in this world";
  }

  @Override
  public Space getSpace(int spaceIndex) {
    return spaces.get(spaceIndex);
  }

  @Override
  public String addPlayer(String playerName, int initialSpaceIndex, boolean isBot) {
    if (initialSpaceIndex >= 0 && initialSpaceIndex < spaces.size()) {
      Player player;
      Space initialSpace = spaces.get(initialSpaceIndex);
      if (isBot) {
        player = new BotPlayer(playerName, initialSpace, this);
      } else {
        player = new MyWorldPlayer(playerName, initialSpace, this);
      }
      for (Player p : players) {
        if (p.getName().equals(playerName)) {
          return ("Player name already exists");
        }
      }
      players.add(player);
      initialSpace.addPlayer(player);
      return String.format("Player %s is added to %s", playerName, initialSpace.getName());
    } else {
      return String.format("Invalid space index: %d", initialSpaceIndex);
    }
  }

  @Override
  public String playerInfo(String playerName) {
    for (Player player : players) {
      if (player.getName().equals(playerName)) {
        return player.toString();
      }
    }
    return "there is no such player in this world\n";
  }

  @Override
  public String targetInfo() {
    return String.format("Target %s is in %s", character.getName(),
        spaces.get(character.getCurrentRoomIndex()).getName());
  }

  @Override
  public List<Player> getPlayers() {
    return players;
  }


  /**
   *  Add items to corresponding spaces based on room index, handling invalid room indices.
   *
   */
  private void deployItems() {
    for (Item item : items) {
      int roomIndex = item.getRoomIndex();
      if (roomIndex >= 0 && roomIndex < spaces.size()) {
        spaces.get(roomIndex).addItem(item);
      } else {
        throw new IllegalArgumentException("Invalid room index for item: " + item.getName());
      }
    }
  }

  /**
   * Validates the spaces to ensure there are no overlaps.
   *
   * @param spacesN List of spaces to validate.
   */
  private void validateSpaces(List<Space> spacesN) {
    for (int i = 0; i < spacesN.size(); i++) {
      Space spaceA = spacesN.get(i);
      if (spaceA.getLowerRightRow() - 1 > numRows || spaceA.getLowerRightCol() - 1 > numCols
              || spaceA.getUpperLeftRow() < 0 || spaceA.getUpperLeftCol() < 0) {
        throw new IllegalArgumentException("Spaces overflow: " + spaceA.getName());
      }
      for (int j = i + 1; j < spacesN.size(); j++) {
        Space spaceB = spacesN.get(j);
        if (isOverlap(spaceA, spaceB)) {
          throw new IllegalArgumentException("Spaces overlap: " + spaceA.getName()
                  + " and " + spaceB.getName());
        }
      }
    }
  }

  /**
   * Checks if two spaces overlap.
   *
   * @param spaceA First space.
   * @param spaceB Second space.
   * @return True if spaces overlap, false otherwise.
   */
  private boolean isOverlap(Space spaceA, Space spaceB) {
    int upperLeftRowA = spaceA.getUpperLeftRow();
    int upperLeftColA = spaceA.getUpperLeftCol();
    int lowerRightRowA = spaceA.getLowerRightRow();
    int lowerRightColA = spaceA.getLowerRightCol();

    int upperLeftRowB = spaceB.getUpperLeftRow();
    int upperLeftColB = spaceB.getUpperLeftCol();
    int lowerRightRowB = spaceB.getLowerRightRow();
    int lowerRightColB = spaceB.getLowerRightCol();

    return (upperLeftRowA > upperLeftRowB && upperLeftRowA < lowerRightRowB
            && upperLeftColA > upperLeftColB && upperLeftColA < lowerRightColB)
            || (lowerRightRowA > upperLeftRowB && lowerRightRowA < lowerRightRowB
            && lowerRightColA > upperLeftColB && lowerRightColA < lowerRightColB)
            || (upperLeftRowA > upperLeftRowB && upperLeftRowA < lowerRightRowB
            && lowerRightColA > upperLeftColB && lowerRightColA < lowerRightColB)
            || (lowerRightRowA > upperLeftRowB && lowerRightRowA < lowerRightRowB
            && upperLeftColA > upperLeftColB && upperLeftColA < lowerRightColB);
  }

  /**
   * Connects adjacent spaces in the game src.world.
   */
  private void connectAdjacentSpaces() {
    for (Space space : spaces) {
      for (Space otherSpace : spaces) {
        if (space.isNeighbor(otherSpace)) {
          space.addNeighbor(otherSpace);
        }
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyWorld myWorld = (MyWorld) o;
    return numRows == myWorld.numRows
            && numCols == myWorld.numCols
            && Objects.equals(spaces, myWorld.spaces)
            && Objects.equals(character, myWorld.character)
            && Objects.equals(items, myWorld.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numRows, numCols, spaces, character, items);
  }

  @Override
  public String toString() {
    String spacesList = spaces.stream()
        .map(Space::getName)
        .collect(Collectors.joining(", "));

    String itemsList = items.stream()
        .map(Item::getName)
        .collect(Collectors.joining(", "));

    String playersList = players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(", "));

    return String.format("%s{\ntarget=%s\npet=%s\nspaces=\n%s\nitems=\n%s\nplayers=\n%s}",
        name, character.getName(), pet.getName(), spacesList, itemsList, playersList);
  }
}


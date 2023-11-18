package test;

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
import src.model.BotPlayer;
import src.model.Character;
import src.model.Item;
import src.model.MyWorldCharacter;
import src.model.MyWorldItem;
import src.model.MyWorldPet;
import src.model.MyWorldPlayer;
import src.model.MyWorldSpace;
import src.model.Player;
import src.model.Space;
import src.model.World;


/**
 * tset the game src.world where spaces, items, and characters are placed.
 */
public class MockWorld implements World {
  private int numRows;
  private int numCols;
  private String name;
  private List<Space> spaces; // Stores all the space information
  private Character character; // Stores character information
  private List<Item> items;
  private StringBuilder log;
  private MyWorldPet pet;


  private List<Player> players;

  /**
   * Constructs the game src.world with the provided spaces, items, and character.
   *
   * @param filename The name of the file to parse.
   */

  public MockWorld(String filename) {
    this.log = new StringBuilder();
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
    this.log.append("getNumRows\n");
    return numRows;
  }

  @Override
  public int getNumCols() {
    this.log.append("getNumCols\n");
    return numCols;
  }

  @Override
  public String getName() {
    this.log.append("getName\n");
    return name;
  }

  /**
   * Gets all the spaces in the game src.world.
   *
   * @return List of spaces.
   */
  @Override
  public List<Space> getSpaces() {
    this.log.append("getSpaces\n");
    return spaces;
  }

  /**
   * Gets the character in the game src.world.
   *
   * @return The character.
   */
  @Override
  public Character getCharacter() {
    this.log.append("getCharacter\n");
    return character;
  }

  @Override
  public List<Item> getItems() {
    this.log.append("getItems\n");
    return items;
  }

  /**
   * Creates a graphical representation of the src.world map with space names.
   *
   * @return BufferedImage representing the src.world map with space names.
   */
  @Override
  public BufferedImage renderWorldImage() {
    this.log.append("renderWorldImage\n");
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
    this.log.append("moveCharacter\n");
    character.moveSpace();
    return String.format("Character moved to %s",
        spaces.get(character.getCurrentRoomIndex()).getName());
  }

  @Override
  public Space getSpace(int spaceIndex) {
    this.log.append("getSpace\n");
    return spaces.get(spaceIndex);
  }


  @Override
  public String movePet(String spaceName) {
    for (Space space : spaces) {
      if (space.getName().equals(spaceName)) {
        pet.setCurrentSpace(space);
        return String.format("Pet is now in %s", spaceName);
      }
    }
    return "There is no such space in this world";
  }

  @Override
  public MyWorldPet getPet() {
    return pet;
  }

  @Override
  public String addPlayer(String playerName, int initialSpaceIndex, boolean isBot) {
    this.log.append("addPlayer\n");
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
    this.log.append("playerInfo\n");
    for (Player player : players) {
      if (player.getName().equals(playerName)) {
        return player.toString();
      }
    }
    return "there is no such player in this src.world";
  }

  @Override
  public List<Player> getPlayers() {
    this.log.append("getPlayers\n");
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
  public String toString() {
    this.log.append("toString\n");
    String spacesList = spaces.stream()
        .map(Space::getName)
        .collect(Collectors.joining(",\n"));

    String itemsList = items.stream()
        .map(Item::getName)
        .collect(Collectors.joining(",\n"));

    String playersList = players.stream()
        .map(Player::getName)
        .collect(Collectors.joining(",\n"));

    return String.format("%s{\nspaces=\n%s\ntarget=%s\nitems=\n%s\nplayers=\n%s\n}",
        name, spacesList, character.getName(), itemsList, playersList);
  }
}
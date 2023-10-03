package world;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Represents the game world where spaces, items, and characters are placed.
 */
public class MyWorld implements World {
  private int numRows;
  private int numCols;
  private List<Space> spaces; // Stores all the space information
  private Character character; // Stores character information
  private List<Item> items;
  /**
   * Constructs the game world with the provided spaces, items, and character.
   *
   * @param numRows
   * @param numCols
   * @param spacesN    List of spaces in the game world.
   * @param itemsN     List of items in the game world.
   * @param characterN The character in the game world.
   */
  public MyWorld(int numRows, int numCols, List<Space> spacesN, List<Item> itemsN, Character characterN) {
    validateSpaces(spaces);
    this.numRows = numRows;
    this.numCols = numCols;
    this.spaces = spacesN;
    this.character = characterN;
    this.items = itemsN;
    // Add items
    deployItems();
    // Connect adjacent spaces
    connectAdjacentSpaces();
  }

  @Override
  public int getNumRows() {
    return numRows;
  }

  @Override
  public int getNumCols() {
    return numCols;
  }

  /**
   * Gets all the spaces in the game world.
   *
   * @return List of spaces.
   */
  @Override
  public List<Space> getSpaces() {
    return spaces;
  }

  /**
   * Gets the character in the game world.
   *
   * @return The character.
   */
  @Override
  public Character getCharacter() {
    return character;
  }

  @Override
  public List<Item> getItems() {
    return items;
  }

  /**
   * Creates a graphical representation of the world map with space names.
   *
   * @return BufferedImage representing the world map with space names.
   */
  @Override
  public BufferedImage createWorldMapImage() {
    int numRows = getNumRows(); // Get the number of rows in the world
    int numCols = getNumCols(); // Get the number of columns in the world
    int cellSize = 50; // Size of each cell in pixels

    int mapWidth = numCols * cellSize;
    int mapHeight = numRows * cellSize;

    BufferedImage mapImage = new BufferedImage(mapWidth, mapHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = mapImage.createGraphics();

    List<Space> spaces = getSpaces();

    // Draw spaces and their names
    for (Space space : spaces) {
      int upperLeftX = space.getUpperLeftCol() * cellSize;
      int upperLeftY = space.getUpperLeftRow() * cellSize;
      String spaceName = space.getName();

      // Draw space as a rectangle
      g2d.setColor(Color.BLUE);
      g2d.fillRect(upperLeftX, upperLeftY, cellSize, cellSize);

      // Draw space name
      g2d.setColor(Color.BLACK);
      g2d.setFont(new Font("Arial", Font.BOLD, 16)); // You can adjust font size if needed
      g2d.drawString(spaceName, upperLeftX + 10, upperLeftY + 30); // Adjust text position as needed
    }

    g2d.dispose(); // Release resources

    return mapImage;
  }


  /**
   *  Add items to corresponding spaces based on room index, handling invalid room indices
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
   * @param spaces List of spaces to validate.
   */
  private void validateSpaces(List<Space> spaces) {
    for (int i = 0; i < spaces.size(); i++) {
      Space spaceA = spaces.get(i);
      if (spaceA.getUpperLeftRow() > numRows || spaceA.getLowerRightCol() > numCols) {
        throw new IllegalArgumentException("Spaces overflow: " + spaceA.getName());
      }
      for (int j = i + 1; j < spaces.size(); j++) {
        Space spaceB = spaces.get(j);
        if (isOverlap(spaceA, spaceB)) {
          throw new IllegalArgumentException("Spaces overlap: " + spaceA.getName() + " and " + spaceB.getName());
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
    return doRectanglesOverlap(spaceA, spaceB) || doRectanglesOverlap(spaceB, spaceA);
  }

  /**
   * Checks if two rectangles overlap.
   *
   * @param spaceA First space.
   * @param spaceB Second space.
   * @return True if rectangles overlap, false otherwise.
   */
  private boolean doRectanglesOverlap(Space spaceA, Space spaceB) {
    return spaceA.getUpperLeftCol() < spaceB.getLowerRightCol() &&
            spaceA.getLowerRightCol() > spaceB.getUpperLeftCol() &&
            spaceA.getUpperLeftRow() < spaceB.getLowerRightRow() &&
            spaceA.getLowerRightRow() > spaceB.getUpperLeftRow();
  }

  /**
   * Connects adjacent spaces in the game world.
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
}


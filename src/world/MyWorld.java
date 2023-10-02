package world;

import java.util.List;

/**
 * Represents the game world where spaces, items, and characters are placed.
 */
public class MyWorld implements WorldInterface {
  private List<SpaceInterface> spaces; // Stores all the space information
  private CharacterInterface character; // Stores character information
  private List<ItemInterface> items;
  /**
   * Constructs the game world with the provided spaces, items, and character.
   *
   * @param spacesN    List of spaces in the game world.
   * @param itemsN     List of items in the game world.
   * @param characterN The character in the game world.
   */
  public MyWorld(List<SpaceInterface> spacesN, List<ItemInterface> itemsN, CharacterInterface characterN) {
    validateSpaces(spaces);
    this.spaces = spacesN;
    this.character = characterN;
    this.items = itemsN;
    // Add items
    deployItems();
    // Connect adjacent spaces
    connectAdjacentSpaces();
  }

  /**
   * Gets all the spaces in the game world.
   *
   * @return List of spaces.
   */
  @Override
  public List<SpaceInterface> getSpaces() {
    return spaces;
  }

  /**
   * Gets the character in the game world.
   *
   * @return The character.
   */
  @Override
  public CharacterInterface getCharacter() {
    return character;
  }

  @Override
  public List<ItemInterface> getItems() {
    return items;
  }

  /**
   *  Add items to corresponding spaces based on room index, handling invalid room indices
   *
   */
  private void deployItems() {
    for (ItemInterface item : items) {
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
  private void validateSpaces(List<SpaceInterface> spaces) {
    for (int i = 0; i < spaces.size(); i++) {
      SpaceInterface spaceA = spaces.get(i);
      for (int j = i + 1; j < spaces.size(); j++) {
        SpaceInterface spaceB = spaces.get(j);
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
  private boolean isOverlap(SpaceInterface spaceA, SpaceInterface spaceB) {
    return doRectanglesOverlap(spaceA, spaceB) || doRectanglesOverlap(spaceB, spaceA);
  }

  /**
   * Checks if two rectangles overlap.
   *
   * @param spaceA First space.
   * @param spaceB Second space.
   * @return True if rectangles overlap, false otherwise.
   */
  private boolean doRectanglesOverlap(SpaceInterface spaceA, SpaceInterface spaceB) {
    return spaceA.getUpperLeftCol() < spaceB.getLowerRightCol() &&
            spaceA.getLowerRightCol() > spaceB.getUpperLeftCol() &&
            spaceA.getUpperLeftRow() < spaceB.getLowerRightRow() &&
            spaceA.getLowerRightRow() > spaceB.getUpperLeftRow();
  }

  /**
   * Connects adjacent spaces in the game world.
   */
  private void connectAdjacentSpaces() {
    for (SpaceInterface space : spaces) {
      for (SpaceInterface otherSpace : spaces) {
        if (space.isNeighbor(otherSpace)) {
          space.addNeighbor(otherSpace);
        }
      }
    }
  }
}


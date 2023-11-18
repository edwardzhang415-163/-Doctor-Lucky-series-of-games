package src.model;

import java.util.List;

/**
 * This interface represents a space in the game src.world. Spaces have coordinates,
 * a name, neighboring spaces, and items that can be found in them.
 */
public interface Space {

  /**
   * Gets the name of the space.
   *
   * @return The name of the space.
   */
  String getName();

  /**
   * Gets the row coordinate of the upper left corner of the space.
   *
   * @return The row coordinate of the upper left corner.
   */
  int getUpperLeftRow();

  /**
   * Gets the column coordinate of the upper left corner of the space.
   *
   * @return The column coordinate of the upper left corner.
   */
  int getUpperLeftCol();

  /**
   * Gets the row coordinate of the lower right corner of the space.
   *
   * @return The row coordinate of the lower right corner.
   */
  int getLowerRightRow();

  /**
   * Gets the column coordinate of the lower right corner of the space.
   *
   * @return The column coordinate of the lower right corner.
   */
  int getLowerRightCol();

  /**
   * Gets the list of neighboring spaces.
   *
   * @return A list of neighboring spaces.
   */
  List<Space> getNeighbors();

  /**
   * Gets the list of items in the space.
   *
   * @return A list of items in the space.
   */
  List<Item> getItems();

  /**
   * Checks if this space is a neighbor of another space.
   *
   * @param other The space to check for adjacency.
   * @return True if this space is a neighbor of the other space, false otherwise.
   */
  boolean isNeighbor(Space other);

  /**
   * Adds a neighboring space to this space.
   *
   * @param neighbor The neighboring space to add.
   */
  void addNeighbor(Space neighbor);

  /**
   * Adds an item to this space.
   *
   * @param item The item to add to the space.
   */
  void addItem(Item item);

  /**
   * Adds an item to this space.
   *
   * @param item The item to add to the space.
   */
  String revomeItem(Item item);

  /**
   * Adds an item to this space.
   *
   * @param player The item to add to the space.
   */
  String removePlayer(Player player);

  /**
   * Adds an item to this space.
   *
   * @param player The item to add to the space.
   */
  String addPlayer(Player player);

  /**
   * Gets the character in the space.
   *
   * @return The character in the space.
   */
  List<Player> getPlayers();

  /**
   * Gets the character in the space.
   *
   * @return The character in the space.
   */
  String scope();

  /**
   * Gets the character in the space.
   *
   * @return The character in the space.
   */
  void addCharacter(Character character);

  /**
   * Gets the character in the space.
   *
   * @return The character in the space.
   */
  void removeCharacter();

  /**
   * Gets the character in the space.
   *
   * @return The character in the space.
   */
  void addPet(Pet pet);

  /**
   * Gets the character in the space.
   *
   * @return The character in the space.
   */
  void removePet();

  /**
   * Gets the character in the space.
   *
   * @return The character in the space.
   */
  String getCharacter();
}

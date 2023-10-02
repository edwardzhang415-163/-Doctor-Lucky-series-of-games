package world;

import java.util.List;

/**
 * This interface represents a space in the game world. Spaces have coordinates,
 * a name, neighboring spaces, and items that can be found in them.
 */
public interface SpaceInterface {

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
  List<SpaceInterface> getNeighbors();

  /**
   * Gets the list of items in the space.
   *
   * @return A list of items in the space.
   */
  List<ItemInterface> getItems();

  /**
   * Checks if this space is a neighbor of another space.
   *
   * @param other The space to check for adjacency.
   * @return True if this space is a neighbor of the other space, false otherwise.
   */
  boolean isNeighbor(SpaceInterface other);

  /**
   * Adds a neighboring space to this space.
   *
   * @param neighbor The neighboring space to add.
   */
  void addNeighbor(SpaceInterface neighbor);

  /**
   * Adds an item to this space.
   *
   * @param item The item to add to the space.
   */
  void addItem(ItemInterface item);

  // Other possible space-related interface methods can be documented here.
}

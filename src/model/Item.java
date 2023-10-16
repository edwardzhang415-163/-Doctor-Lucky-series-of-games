package src.model;

/**
 * This interface represents an item that can be found in a space.
 */
public interface Item {

  /**
   * Gets the index of the room where the item can be found.
   *
   * @return The index of the room.
   */
  int getRoomIndex();

  /**
   * Gets the damage amount the item can inflict if used in an attack.
   *
   * @return The damage amount of the item.
   */
  int getDamage();

  /**
   * Gets the name of the item.
   *
   * @return The name of the item.
   */
  String getName();
}

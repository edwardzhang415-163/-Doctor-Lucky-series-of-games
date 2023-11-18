package src.model;

/**
 * This interface represents a character in the game.
 */
public interface Character {

  /**
   * Gets the health of the character.
   *
   * @return The health of the character.
   */
  int getHealth();

  /**
   * Gets the name of the character.
   *
   * @return The name of the character.
   */
  String getName();

  /**
   * Gets the current room index where the character is located.
   *
   * @return The current room index.
   */
  int getCurrentRoomIndex();

  /**
   * Sets the current room index where the character is located.
   *
   * @param roomIndex The new room index.
   */
  void setCurrentRoomIndex(int roomIndex);

  /**
   * Gets the current space where the character is located.
   *
   * @return The current space.
   */
  int getMaxIndex();

  /**
   * Sets the maximum room index allowed for the character.
   *
   * @param maxIndex The maximum room index.
   * @throws IllegalArgumentException if maxIndex is less than startIndex.
   */
  void setMaxIndex(int maxIndex);

  /**
   * Gets the current space where the character is located.
   *
   * @return The current space.
   */
  int moveSpace();

  /**
   * Gets the current space where the character is located.
   *
   * @return The current space.
   */
  int hurt(int damage);
}


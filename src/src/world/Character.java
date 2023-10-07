package src.world;

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

  public int getMaxIndex();

  public void setMaxIndex(int maxIndex);

  public int moveSpace();
}


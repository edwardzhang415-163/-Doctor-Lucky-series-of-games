package src.model;

public interface Pet {

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


}

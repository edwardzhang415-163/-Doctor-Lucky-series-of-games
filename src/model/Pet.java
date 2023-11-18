package src.model;

interface Pet {

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
  Space getCurrentSpace();

  /**
   * Sets the current room index where the character is located.
   *
   * @param currentSpaceN The current room index.
   */
  void setCurrentSpace(Space currentSpaceN);

  /**
   * Gets the current space where the character is located.
   *
   * @return The current space.
   */
  String wandering();

  /**
   * Gets the current space where the character is located.
   *
   * @return The current space.
   */
  void refresh();


}

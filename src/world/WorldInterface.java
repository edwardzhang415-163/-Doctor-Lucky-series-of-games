package world;

import java.util.List;

/**
 * Represents the game world interface where spaces, items, and characters are placed.
 */
public interface WorldInterface {
  /**
   * Get all the spaces in the game world.
   *
   * @return List of spaces.
   */
  List<SpaceInterface> getSpaces();

  /**
   * Get the character in the game world.
   *
   * @return The character.
   */
  CharacterInterface getCharacter();

  public List<ItemInterface> getItems();

}


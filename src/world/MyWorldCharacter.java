package world;

import java.util.Objects;

/**
 * Represents a character in the game.
 */
public class MyWorldCharacter implements Character {
  private static final int startIndex = 0; // 常量 startIndex
  private int maxIndex; // 字段 maxIndex
  private int health;
  private String name;
  private int currentRoomIndex;

  /**
   * Creates a new character with the specified health, name, current room index, and maximum room index.
   *
   * @param health          The health of the character.
   * @param name            The name of the character.
   * @param currentRoomIndex The initial room index where the character is located.
   * @param maxIndex        The maximum room index allowed for the character.
   * @throws IllegalArgumentException if maxIndex is less than startIndex.
   */
  public MyWorldCharacter(int health, String name, int currentRoomIndex, int maxIndex) {
    if (maxIndex < startIndex) {
      throw new IllegalArgumentException("maxIndex must be greater than or equal to startIndex.");
    }
    this.health = health;
    this.name = name;
    this.currentRoomIndex = currentRoomIndex;
    this.maxIndex = maxIndex;
  }

  /**
   * Gets the maximum room index allowed for the character.
   *
   * @return The maximum room index.
   */

  @Override
  public int getMaxIndex() {
    return maxIndex;
  }

  /**
   * Sets the maximum room index allowed for the character.
   *
   * @param maxIndex The maximum room index.
   * @throws IllegalArgumentException if maxIndex is less than startIndex.
   */
  @Override
  public void setMaxIndex(int maxIndex) {
    if (maxIndex < startIndex) {
      throw new IllegalArgumentException("maxIndex must be greater than or equal to startIndex.");
    }
    this.maxIndex = maxIndex;
  }

  @Override
  public int getHealth() {
    return health;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  @Override
  public void setCurrentRoomIndex(int roomIndex) {
    if (roomIndex < startIndex || roomIndex > maxIndex) {
      throw new IllegalArgumentException("Invalid room index.");
    }
    this.currentRoomIndex = roomIndex;
  }

  /**
   * Moves the character's current room index by the specified delta.
   *
   * @param delta The change in room index (can be positive or negative).
   * @throws IllegalArgumentException if the resulting room index is less than startIndex or greater than maxIndex.
   */
  @Override
  public void moveSpace(int delta) {
    int newRoomIndex = currentRoomIndex + delta;
    if (newRoomIndex < startIndex || newRoomIndex > maxIndex) {
      throw new IllegalArgumentException("Invalid room index.");
    }
    currentRoomIndex = newRoomIndex;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MyWorldCharacter that = (MyWorldCharacter) o;
    return health == that.health &&
            currentRoomIndex == that.currentRoomIndex &&
            maxIndex == that.maxIndex &&
            Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(health, name, currentRoomIndex, maxIndex);
  }

  @Override
  public String toString() {
    return "MyWorldCharacter{" +
            "health=" + health +
            ", name='" + name + '\'' +
            ", currentRoomIndex=" + currentRoomIndex +
            ", maxIndex=" + maxIndex +
            '}';
  }
}


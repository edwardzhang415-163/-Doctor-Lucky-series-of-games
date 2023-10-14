package src.world;

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
   * Creates a new character with the specified health.
   *
   * @param healthN           The health of the character.
   * @param nameN            The name of the character.
   * @param currentRoomIndexN The initial room index where the character is located.
   * @param maxIndexN         The maximum room index allowed for the character.
   * @throws IllegalArgumentException if maxIndex is less than startIndex.
   */
  public MyWorldCharacter(int healthN, String nameN, int currentRoomIndexN, int maxIndexN) {
    if (maxIndex < startIndex) {
      throw new IllegalArgumentException("maxIndex must be greater than or equal to startIndex.");
    }
    this.health = healthN;
    this.name = nameN;
    this.currentRoomIndex = currentRoomIndexN;
    this.maxIndex = maxIndexN;
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
   * @param maxIndexN The maximum room index.
   * @throws IllegalArgumentException if maxIndex is less than startIndex.
   */
  @Override
  public void setMaxIndex(int maxIndexN) {
    if (maxIndexN < startIndex) {
      throw new IllegalArgumentException("maxIndex must be greater than or equal to startIndex.");
    }
    this.maxIndex = maxIndexN;
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
   *
   * @throws IllegalArgumentException if the resulting room index greater than maxIndex.
   */
  @Override
  public int moveSpace() {
    int newRoomIndex = currentRoomIndex + 1;
    if (newRoomIndex > maxIndex) {
      newRoomIndex = startIndex;
    }
    currentRoomIndex = newRoomIndex;
    return currentRoomIndex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyWorldCharacter that = (MyWorldCharacter) o;
    return health == that.health
            && currentRoomIndex == that.currentRoomIndex
            && maxIndex == that.maxIndex
            && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(health, name, currentRoomIndex, maxIndex);
  }

  @Override
  public String toString() {
    return String.format("MyWorldCharacter{health=%d, name='%s', currentRoomIndex=%d}",
        health, name, currentRoomIndex);
  }
}


package src.model;

import java.util.Objects;

/**
 * Represents an item that can be found in a space.
 */
public class MyWorldItem  implements Item {
  private final int roomIndex;
  private final int damage;
  private final String name;

  /**
   * Creates a new item with the specified room index, damage, and name.
   *
   * @param roomIndexN The index of the room where the item can be found.
   * @param damageN    The damage amount the item can inflict.
   * @param nameN      The name of the item.
   */
  public MyWorldItem(int roomIndexN, int damageN, String nameN) {
    this.roomIndex = roomIndexN;
    this.damage = damageN;
    this.name = nameN;
  }

  @Override
  public int getRoomIndex() {
    return roomIndex;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyWorldItem that = (MyWorldItem) o;
    return roomIndex == that.roomIndex
            && damage == that.damage
            && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(roomIndex, damage, name);
  }

  @Override
  public String toString() {
    return String.format("MyWorldItem{roomIndex=%d, damage=%d, name='%s'}",
        roomIndex, damage, name);
  }
}

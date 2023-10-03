package world;

/**
 * Represents an item that can be found in a space.
 */
public class MyWorldItem implements Item {
  private int roomIndex;
  private int damage;
  private String name;

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
}

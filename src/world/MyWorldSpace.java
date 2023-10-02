package world;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a space or room in the game world.
 */
public class MyWorldSpace implements SpaceInterface {

  private int upperLeftRow;
  private int upperLeftCol;
  private int lowerRightRow;
  private int lowerRightCol;
  private String name;
  private List<SpaceInterface> neighbors;
  private List<ItemInterface> items;

  /**
   * Creates a new space with the specified coordinates, name, neighbors, and items.
   *
   * @param upperLeftRowN  The row coordinate of the upper left corner.
   * @param upperLeftColN  The column coordinate of the upper left corner.
   * @param lowerRightRowN The row coordinate of the lower right corner.
   * @param lowerRightColN The column coordinate of the lower right corner.
   * @param nameN          The name of the space.
   */
  public MyWorldSpace(int upperLeftRowN, int upperLeftColN, int lowerRightRowN, int lowerRightColN, String nameN) {
    this.upperLeftRow = upperLeftRowN;
    this.upperLeftCol = upperLeftColN;
    this.lowerRightRow = lowerRightRowN;
    this.lowerRightCol = lowerRightColN;
    this.name = nameN;
    this.neighbors = new ArrayList<>();
    this.items = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getUpperLeftRow() {
    return upperLeftRow;
  }

  @Override
  public int getUpperLeftCol() {
    return upperLeftCol;
  }

  @Override
  public int getLowerRightRow() {
    return lowerRightRow;
  }

  @Override
  public int getLowerRightCol() {
    return lowerRightCol;
  }

  @Override
  public List<SpaceInterface> getNeighbors() {
    return neighbors;
  }

  @Override
  public List<ItemInterface> getItems() {
    return items;
  }

  @Override
  public boolean isNeighbor(SpaceInterface other) {
    // Implement the logic to check if this space is a neighbor of the other space.
    // ...
    return false;
  }

  /**
   * Adds a neighboring space to this space.
   *
   * @param neighbor The neighboring space to add.
   */
  @Override
  public void addNeighbor(SpaceInterface neighbor) {
    neighbors.add(neighbor);
  }

  /**
   * Adds an item to this space.
   *
   * @param item The item to add to the space.
   */
  @Override
  public void addItem(ItemInterface item) {
    items.add(item);
  }


}


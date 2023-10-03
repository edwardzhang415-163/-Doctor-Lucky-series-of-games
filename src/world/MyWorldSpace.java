package world;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a space or room in the game world.
 */
public class MyWorldSpace implements Space {

  private int upperLeftRow;
  private int upperLeftCol;
  private int lowerRightRow;
  private int lowerRightCol;
  private String name;
  private List<Space> neighbors;
  private List<Item> items;

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
    if (upperLeftRowN < lowerRightRowN || upperLeftColN > lowerRightColN) {
      throw new IllegalArgumentException("Invalid space coordinates.");
    }
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
  public List<Space> getNeighbors() {
    return neighbors;
  }

  @Override
  public List<Item> getItems() {
    return items;
  }

  @Override
  public boolean isNeighbor(Space other) {
    // Implement the logic to check if this space is a neighbor of the other space.
    boolean lowerNeighbor =this.getUpperLeftRow() == other.getLowerRightRow() &&
            this.getLowerRightCol() > this.getUpperLeftCol() && this.getUpperLeftCol() < this.getUpperLeftCol();
    boolean upperNeighbor =this.getLowerRightRow() == other.getUpperLeftRow() &&
            this.getLowerRightCol() > this.getUpperLeftCol() && this.getUpperLeftCol() < this.getUpperLeftCol();
    boolean leftNeighbor =this.getLowerRightCol() == other.getUpperLeftCol() &&
            this.getUpperLeftRow() > this.getLowerRightRow() && this.getLowerRightCol() < this.getUpperLeftRow();
    boolean rightNeighbor =this.getUpperLeftCol() == other.getLowerRightCol() &&
            this.getUpperLeftRow() > this.getLowerRightRow() && this.getLowerRightCol() < this.getUpperLeftRow();

    return lowerNeighbor || upperNeighbor || leftNeighbor || rightNeighbor;
  }

  /**
   * Adds a neighboring space to this space.
   *
   * @param neighbor The neighboring space to add.
   */
  @Override
  public void addNeighbor(Space neighbor) {
    neighbors.add(neighbor);
  }

  /**
   * Adds an item to this space.
   *
   * @param item The item to add to the space.
   */
  @Override
  public void addItem(Item item) {
    items.add(item);
  }


}


package src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Represents a space or room in the game src.world.
 */
public class MyWorldSpace  implements Space {

  private final int upperLeftRow;
  private final int upperLeftCol;
  private final int lowerRightRow;
  private final int lowerRightCol;
  private final String name;
  private  List<Space> neighbors;
  private  List<Item> items;
  private  List<Player> players;
  private String character;
  private String pet;
  /**
   * Creates a new space with the specified coordinates, name, neighbors, and items.
   *
   * @param upperLeftRowN  The row coordinate of the upper left corner.
   * @param upperLeftColN  The column coordinate of the upper left corner.
   * @param lowerRightRowN The row coordinate of the lower right corner.
   * @param lowerRightColN The column coordinate of the lower right corner.
   * @param nameN          The name of the space.
   */

  public MyWorldSpace(int upperLeftRowN, int upperLeftColN,
                      int lowerRightRowN, int lowerRightColN, String nameN) {
    if (upperLeftRowN > lowerRightRowN || upperLeftColN > lowerRightColN) {
      throw new IllegalArgumentException("Invalid space coordinates.");
    }
    this.upperLeftRow = upperLeftRowN;
    this.upperLeftCol = upperLeftColN;
    this.lowerRightRow = lowerRightRowN;
    this.lowerRightCol = lowerRightColN;
    this.name = nameN;
    this.neighbors = new ArrayList<>();
    this.items = new ArrayList<>();
    this.players = new ArrayList<>();
    this.character = "";
    this.pet = "";
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
  public List<Player> getPlayers() {
    return players;
  }

  @Override
  public boolean isNeighbor(Space other) {
    // Implement the logic to check if this space is a neighbor of the other space.
    boolean lowerNeighbor = this.getUpperLeftRow() == other.getLowerRightRow()
            && this.getLowerRightCol() > other.getUpperLeftCol()
            && this.getUpperLeftCol() < other.getLowerRightCol();
    boolean upperNeighbor = this.getLowerRightRow() == other.getUpperLeftRow()
            && this.getLowerRightCol() > other.getUpperLeftCol()
            && this.getUpperLeftCol() < other.getLowerRightCol();
    boolean leftNeighbor = this.getLowerRightCol() == other.getUpperLeftCol()
            && this.getUpperLeftRow() < other.getLowerRightRow()
            && this.getLowerRightRow() > other.getUpperLeftRow();
    boolean rightNeighbor = this.getUpperLeftCol() == other.getLowerRightCol()
            && this.getUpperLeftRow() < other.getLowerRightRow()
            && this.getLowerRightRow() > other.getUpperLeftRow();

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

  @Override
  public String getCharacter() {
    return character;
  }

  @Override
  public String revomeItem(Item item) {
    for (Item i : items) {
      if (i.getName().equals(item.getName())) {
        items.remove(i);
        return i.getName();
      }
    }
    return "there is no such item in this space";
  }

  @Override
  public String addPlayer(Player player) {
    players.add(player);
    return String.format("player %s is added to %s", player.getName(), this.getName());
  }

  @Override
  public String removePlayer(Player player) {
    for (Player p : players) {
      if (p.getName().equals(player.getName())) {
        players.remove(p);
        return String.format("player %s is removed from %s", player.getName(), this.getName());
      }
    }
    return String.format("player %s is removed from %s", player.getName(), this.getName());
  }

  @Override
  public void addCharacter(Character character) {
    this.character = character.getName();
  }

  @Override
  public void addPet(Pet pet) {
    this.pet = pet.getName();
  }

  @Override
  public void removeCharacter() {
    this.character = "";
  }

  @Override
  public void removePet() {
    this.pet = "";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyWorldSpace that = (MyWorldSpace) o;
    return upperLeftRow == that.upperLeftRow
            && upperLeftCol == that.upperLeftCol
            && lowerRightRow == that.lowerRightRow
            && lowerRightCol == that.lowerRightCol
            && Objects.equals(name, that.name);
  }

  @Override
  public String scope() {
    if (!"".equals(pet)) {
      return String.format("Space{\nname='%s', other invisible}",
          name);
    }
    String itemsList = items.stream()
        .map(Item::getName)
        .collect(Collectors.joining(", "));

    String playersList = players.stream()
        .map(Player::getName)
        .collect(Collectors.joining(", "));
    return String.format("Space{\nname='%s'\nitems=%s\nplayers=%s\nTarget=%s\n}",
        name, itemsList, playersList, character);
  }

  @Override
  public int hashCode() {
    return Objects.hash(upperLeftRow, upperLeftCol, lowerRightRow,
            lowerRightCol);
  }

  @Override
  public String toString() {

    String neighborsList = neighbors.stream()
        .map(Space::getName)
        .collect(Collectors.joining(", "));

    String itemsList = items.stream()
        .map(Item::getName)
        .collect(Collectors.joining(", "));

    String playersList = players.stream()
            .map(Player::getName)
            .collect(Collectors.joining(", "));

    return String.format("Space{\nname='%s'\nneighbors=%s\nitems=%s\nplayers=%s\nTarget=%s\nPet"
            + "=%s\n}", name, neighborsList, itemsList, playersList, character, pet);
  }
}


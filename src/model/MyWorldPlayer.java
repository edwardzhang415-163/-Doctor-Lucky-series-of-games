package src.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a player in the game.
 */
public class MyWorldPlayer implements Player {
  private final String name;
  private final List<Item> items;
  private Space currentSpace;
  private final int maxItem;
  private final World world;

  /**
   * Creates a new player with the specified name and current space.
   *
   * @param nameN         The name of the player.
   * @param currentSpaceN The current space of the player.
   */
  public MyWorldPlayer(String nameN, Space currentSpaceN, World myWorld) {
    this.name = nameN;
    this.currentSpace = currentSpaceN;
    this.maxItem = 5;
    this.items = new ArrayList<>();
    this.world = myWorld;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String pickupItems(String itemName) {
    if (items.size() < maxItem) {
      for (Item item : currentSpace.getItems()) {
        if (item.getName().equals(itemName)) {
          items.add(item);
          currentSpace.revomeItem(item);
          return String.format("Player %s picked up %s", name, itemName);
        }
      }
      return "There is no such item in this space";
    } else {
      return String.format("Player %s can't pick up more items", name);
    }

  }

  @Override
  public Space getCurrentSpace() {
    return currentSpace;
  }

  @Override
  public String moveTo(String nighbourName) {
    List<Space> nighbours = currentSpace.getNeighbors();
    for (Space nighbour : nighbours) {
      if (nighbour.getName().equals(nighbourName)) {
        currentSpace = nighbour;
        return String.format("Player %s moved to %s", name, nighbourName);
      }
    }
    return "There is no such nighbour";
  }

  @Override
  public String lookAround() {
    String items = currentSpace.getItems().stream().map(Item::getName)
        .collect(Collectors.joining(", "));
    String players = currentSpace.getPlayers().stream().map(Player::getName)
        .collect(Collectors.joining(", "));
    String nighbours = currentSpace.getNeighbors().stream().map(Space::scope)
            .collect(Collectors.joining(", "));
    return String.format("Player %s is in %s.\n space have items: %s\n space have playlers: %s\n"
            + "Player %s can go to: %s",
            name, currentSpace.getName(), items, players, name, nighbours);
  }

  @Override
  public List<Item> getItems() {
    return items;
  }

  @Override
  public String attackTarget() {
    int damage = 1;
    Item weapon = null;
    List<Player> nighbourPlayers;
    Character character = world.getCharacter();
    nighbourPlayers = currentSpace.getNeighbors().stream()
        .flatMap(space -> space.getPlayers().stream())
        .collect(Collectors.toList());
    if ("".equals(currentSpace.getCharacter())) {
      return "There is no target";
    }
    for (Item item : items) {
      if (item.getDamage() >= damage) {
        damage = item.getDamage();
        weapon = item;
      }
    }
    StringBuilder result = new StringBuilder();
    result.append(String.format("Player %s attacked %s ", name, currentSpace.getCharacter()));
    if (weapon != null) {
      result.append(String.format("with %s ", weapon.getName()));
      removeItem(weapon);
    }
    if (nighbourPlayers.size() != 0) {
      return result.append("but failed. Target damage : ").append(character.getHealth()).toString();
    }
    character.hurt(damage);
    return result.append(String.format("and damaged %s. Target damage : %s  ",
        damage, character.getHealth())).toString();
  }

  private String removeItem(Item item) {
    items.remove(item);
    world.getItems().remove(item);
    return String.format("Player %s removed %s", name, item.getName());
  }

  @Override
  public String doAction() {
    return "";
  }

  @Override
  public String toString() {
    String itemsList = items.stream()
        .map(Item::getName)
        .collect(Collectors.joining(", "));
    return String.format("player{\nname='%s'\nitems=%s\ncurrentSpace=%s\n}",
        name, itemsList, currentSpace.getName());
  }

  @Override
  public String movePet(String spaceName) {
    return world.movePet(spaceName);
  }
}


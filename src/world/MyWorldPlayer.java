package src.world;

import java.util.List;
import java.util.stream.Collectors;

public class MyWorldPlayer implements Player {
  private String name;
  private List<Item> items;
  private Space currentSpace;
  private int maxItem;


  public MyWorldPlayer(String nameN, Space currentSpaceN) {
    this.name = nameN;
    this.currentSpace = currentSpaceN;
    this.maxItem = 5;
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
  public String MoveTo(String nighbourName) {
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
    String nighbours = currentSpace.getNeighbors().stream().map(Space::getName)
            .collect(Collectors.joining(", "));
    return String.format("Player %s is in %s. You can go to: %s",
            name, currentSpace.getName(), nighbours);
  }

  @Override
  public String doAction() {
    return "";
  }
}


package src.model;

import java.util.Random;
import java.util.stream.Collectors;

public class BotPlayer extends MyWorldPlayer {

  public BotPlayer(String nameN, Space currentSpaceN) {
    super(nameN, currentSpaceN);
  }

  @Override
  public String doAction() {
    Random random = new Random();
    int action = random.nextInt(3);
    switch (action) {
      case 0:
        return pickupItems(getCurrentSpace().getItems().get(0).getName());
      case 1:
        return moveTo(getCurrentSpace().getNeighbors().get(0).getName());
      case 2:
        return lookAround();
      default: return "doNothing";
    }
  }
  @Override
  public String toString() {
    String itemsList = getItems().stream()
        .map(Item::getName)
        .collect(Collectors.joining(", "));
    return String.format("botplayer{\nname='%s'\nitems=%s\ncurrentSpace=%s\n}",
       getName(), itemsList, getCurrentSpace().getName());
  }
}

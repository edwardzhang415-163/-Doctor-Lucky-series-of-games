package src.model;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * A bot player in the game.
 */

public class BotPlayer extends MyWorldPlayer {

  /**
   * Constructor for a bot player.
   *
   * @param nameN the name of the bot player.
   * @param currentSpaceN the current space of the bot player.
   */

  public BotPlayer(String nameN, Space currentSpaceN, World myWorld) {
    super(nameN, currentSpaceN, myWorld);
  }

  @Override
  public String doAction() {
    Random random = new Random();
    int action = random.nextInt(4);
    if (getCurrentSpace().getItems().size() == 0) {
      action = random.nextInt(3) + 1;
    }
    if (!"".equals(getCurrentSpace().getCharacter())) {
      action = 4;
    }
    try {
      switch (action) {
        case 0:
          return pickupItems(getCurrentSpace().getItems().get(0).getName());
        case 1:
          return moveTo(getCurrentSpace().getNeighbors().get(0).getName());
        case 2:
          return lookAround();
        case 3:
          return movePet(getCurrentSpace().getNeighbors().get(0).getName());
        case 4:
          return attackTarget();
        default: return "doNothing";
      }
    } catch (IndexOutOfBoundsException e) {
      return lookAround();
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

package src.controller.command;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;
import src.model.Item;
import src.model.Player;
import src.model.World;

/**
 * Represents a command that can be executed on a game src.world.
 */
public class NextTurnCommand implements Command {

  public int turn;
  private final int maxTurn;

  /**
   * Creates a new command with the specified parameters.
   *
   * @param maxTurns The maximum number of turns in the game.
   */
  public NextTurnCommand(int maxTurns) {
    this.maxTurn = maxTurns;
    this.turn = 0;
  }

  /**
   * Executes this command on the given src.world.
   *
   * @param world   The src.world on which to execute this command.
   * @param scanner The scanner to use for user input.
   * @param out     The appendable to which to write output.
   * @throws IOException if an error occurs while reading user input or writing output.
   */
  public void execute(World world, Scanner scanner, Appendable out) throws IOException {
    if (turn < maxTurn) {
      turn++;
      out.append("turn ").append(String.valueOf(turn)).append("\n");
      for (Player player : world.getPlayers()) {
        out.append(String.format("Player %s turn", player.getName())).append("\n");
        if ('p' == player.toString().charAt(0)) {
          out.append(String.format("Select option:\n%s\n%s\n%s\n",
              "1.look around.",
              "2.move to neighbor space.",
              "3.pick up items."));
          String option = scanner.next();
          switch (option) {
            case "1":
              out.append(player.lookAround()).append("\n");
              break;
            case "2":
              out.append("please provide space name\n");
              scanner.nextLine();
              String spaceName = scanner.nextLine();
              out.append(player.moveTo(spaceName)).append("\n");
              break;
            case "3":
              String itemsList = player.getCurrentSpace().getItems().stream()
                  .map(Item::getName)
                  .collect(Collectors.joining(", "));
              out.append("space has item :").append(itemsList).append("\n");
              out.append("please provide item name\n");
              scanner.nextLine();
              String item = scanner.nextLine();
              out.append(player.pickupItems(item)).append("\n");
              break;
            default:
              out.append("Invalid option, please try again\n");
              break;
          }
        } else {
          out.append("botplayer do action automaticallly\n");
          out.append(player.doAction()).append("\n");
        }
      }
      out.append(world.moveCharacter()).append("\n");
      out.append("this turn is end\n");
      if (turn == maxTurn) {
        out.append("Max turn! game over\n");
      }
    } else {
      out.append("game is over\n");
    }
  }
}

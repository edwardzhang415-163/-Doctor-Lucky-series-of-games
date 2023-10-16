package src.controller.command;

import java.io.IOException;
import java.util.Scanner;
import src.model.Player;
import src.model.World;

public class NextTurnCommand implements Command {
  private final int maxTurn;
  public int turn;

  public NextTurnCommand( int maxTurns) {
    this.maxTurn = maxTurns;
    this.turn = 0;
  }

  public void execute(World world, Scanner scanner, Appendable out) throws IOException {
    if (turn < maxTurn) {
      turn++;
      out.append("turn ").append(String.valueOf(turn)).append("\n");
      for (Player player : world.getPlayers()) {
        out.append(String.format("Player %s turn",player.getName())).append("\n");
        if('p' == player.toString().charAt(0)){
          out.append(String.format("Select option:\n%s\n%s\n%s",
              "1.look around.",
              "2.move to neighbor space.",
              "3.pick up items."));
          String option = scanner.nextLine();
          switch (option) {
            case "1":
              out.append(player.lookAround()).append("\n");
              break;
            case "2":
              out.append("please provide space name\n");
              String spaceName = scanner.nextLine();
              out.append(player.moveTo(spaceName)).append("\n");
              break;
            case "3":
              out.append("please provide item name\n");
              String item = scanner.nextLine();
              out.append(player.pickupItems(item)).append("\n");
              break;
            default:
              out.append("Invalid option, please try again\n");
              break;
          }
        }else {
          out.append("botplayer do automaticallly\n");
          player.doAction();
        }
      }
      out.append(world.moveCharacter()).append("\n");
    } else {
      out.append("game over\n");
    }
  }
}

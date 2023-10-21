package src.controller.command;

import java.io.IOException;
import java.util.Scanner;
import src.model.World;

/**
 * Command to add a player to the game.
 */
public class AddPlayerCommand implements Command {
  @Override
  public void execute(World world, Scanner scanner, Appendable out) throws IOException {
    out.append("please provide player name\n");
    String playerNameN = scanner.nextLine();
    out.append("please provide initial space index\n");
    int initialSpaceIndex = scanner.nextInt();
    out.append("bot player chose true, human player chose false\n");
    boolean isBot = scanner.nextBoolean();
    out.append(world.addPlayer(playerNameN, initialSpaceIndex, isBot)).append("\n");
  }
}

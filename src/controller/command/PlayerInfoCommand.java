package src.controller.command;

import java.io.IOException;
import java.util.Scanner;
import src.model.World;

public class PlayerInfoCommand implements Command {
  @Override
  public void execute(World world, Scanner scanner, Appendable out) throws IOException {
    out.append("please provide player name\n");
    String playerName = scanner.nextLine();
    out.append(world.playerInfo(playerName)).append("\n");
  }
}

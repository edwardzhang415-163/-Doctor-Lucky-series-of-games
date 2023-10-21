package src.controller.command;

import java.io.IOException;
import java.util.Scanner;
import src.model.World;

/**
 * Represents a command that can be executed on a game src.world.
 */
public class WorldInfoCommand implements Command {
  @Override
  public void execute(World world, Scanner scanner, Appendable out) throws IOException {
    out.append(world.toString()).append("\n");
  }
}

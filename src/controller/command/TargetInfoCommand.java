package src.controller.command;

import java.io.IOException;
import java.util.Scanner;
import src.model.World;

public class TargetInfoCommand implements Command {
  @Override
  public void execute(World world, Scanner scanner, Appendable out) throws IOException {
    out.append(world.getCharacter().toString()).append("\n");
  }
}

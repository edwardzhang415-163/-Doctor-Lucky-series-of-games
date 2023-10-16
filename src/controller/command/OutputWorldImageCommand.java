package src.controller.command;


import java.io.IOException;
import java.util.Scanner;
import src.model.World;

public class OutputWorldImageCommand implements Command {
  @Override
  public void execute(World world, Scanner scanner, Appendable out) throws IOException {
    world.renderWorldImage();
    out.append("successfully output the world to an image file\n");
  }
}

package src.controller.command;

import src.model.World;
import java.io.IOException;
import java.util.Scanner;

public interface Command {
  void execute(World world, Scanner scanner, Appendable out) throws IOException;
}

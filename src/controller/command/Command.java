package src.controller.command;


import java.io.IOException;
import java.util.Scanner;
import src.model.World;

/**
 * Represents a command that can be executed on a game src.world.
 */
public interface Command {
  /**
   * Executes the command.
   * @param world the src.world to execute the command on
   * @param scanner the scanner to use to read user input
   * @param out the appendable to use to output results
   * @throws IOException if an IO error occurs
   */
  void execute(World world, Scanner scanner, Appendable out) throws IOException;
}

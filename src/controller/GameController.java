package src.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import src.controller.command.AddPlayerCommand;
import src.controller.command.Command;
import src.controller.command.MoveCharacterCommand;
import src.controller.command.NextTurnCommand;
import src.controller.command.OutputWorldImageCommand;
import src.controller.command.PlayerInfoCommand;
import src.controller.command.SpaceInfoCommand;
import src.controller.command.TargetInfoCommand;
import src.controller.command.WorldInfoCommand;
import src.model.World;

/**
 * Represents a controller that can be used to play a game.
 */
public class GameController implements Controller {

  private final Readable in;
  private final Appendable out;
  private final int maxTurn;
  private final Map<String, Command> commands;

  /**
   * Constructs a game controller.
   *
   * @param inN        the input source
   * @param outN       the output source
   * @param maxTurnsN  the maximum number of turns
   */
  public GameController(Readable inN, Appendable outN, int maxTurnsN) {
    this.in = inN;
    this.out = outN;
    this.maxTurn = maxTurnsN;
    // Initialize commands and map them to user input.
    commands = new HashMap<>();
    commands.put("1", new OutputWorldImageCommand());
    commands.put("2", new MoveCharacterCommand());
    commands.put("3", new SpaceInfoCommand());
    commands.put("4", new TargetInfoCommand());
    commands.put("5", new WorldInfoCommand());
    commands.put("6", new PlayerInfoCommand());
    commands.put("7", new AddPlayerCommand());
    commands.put("8", new NextTurnCommand(this.maxTurn));
  }

  @Override
  public void start(World world) {
    try (Scanner scanner = new Scanner(this.in)) {
      out.append("game start!");
      while (true) {
        scanner.nextLine();
        scanner.nextLine();
        out.append(String.format("Select option:\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
            "1.Output the world to an image file.",
            "2.Move target.",
            "3.Space info by index.",
            "4.Target info.",
            "5.World info.",
            "6.player info.",
            "7.add player.",
            "8.next turn.",
            "q.Exit game."));
        String option = scanner.next();
        // Execute the corresponding command.
        if (commands.containsKey(option)) {
          Command command = commands.get(option);
          try {
            command.execute(world, scanner, out);
          } catch (InputMismatchException e) {
            out.append("invalid writing try again\n");
          }
        } else {
          out.append("Invalid option, please try again\n");
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("An error occured writing the result");
    }
  }
}

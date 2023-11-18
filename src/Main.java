package src;

import java.io.InputStreamReader;
import java.util.Scanner;
import src.controller.GameController;
import src.model.MyWorld;
import src.model.World;


/**
 * Driver class for the game.
 */
public class Main {
  /**
   * main method.
   *
   *@param args arguments
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("please provide path to world file");
    String path = scanner.nextLine();
    World world = new MyWorld(path);
    System.out.println("Mansion load successfully");
    System.out.println(String.format("Masion name: %s ", world.getName()));
    System.out.println("Please provide max turn number.");
    int maxTurns = scanner.nextInt();
    //controller
    GameController
        controller = new GameController(new InputStreamReader(System.in), System.out, maxTurns);
    controller.start(world);
  }
}

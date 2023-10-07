package src.world;

import java.util.Scanner;

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
    System.out.println("please provide path to src.world file");
    String path = scanner.nextLine();
    World world = new MyWorld(path);
    System.out.println("Mansion load successfully");
    world.renderWorldImage();
    System.out.println("Masion name: " + world.getName());
    System.out.println("character name: " + world.getCharacter().getName());
    System.out.println("character health: " + world.getCharacter().getHealth());
    System.out.println("Select option");
    int option = 10;
    while (0 < option) {
      System.out.println("Select option: \n"
              + "1.Output the world to an image file. \n"
              + "2.Move character. \n"
              + "3.Space info by index.\n"
              + "4.Character info\n"
              + "5.World info\n"
              + "0.Exit game.");
      option = scanner.nextInt();
      switch (option) {
        case 1:
          world.renderWorldImage();
          System.out.println("successfully output the world to an image file");
          break;
        case 2:
          System.out.println(world.moveCharacter());
          break;
        case 3:
          System.out.println("please provide space index");
          int spaceIndex = scanner.nextInt();
          System.out.println(world.getSpace(spaceIndex).toString());
          break;
        case 4:
          System.out.println(world.getCharacter().toString());
          break;
        case 5:
          System.out.println(world.toString());
          break;
        case 0:
          System.out.println("Exit game");
          break;
        default:
          System.out.println("Invalid option");
      }
    }
  }
}

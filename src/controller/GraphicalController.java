package src.controller;


import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import src.model.Item;
import src.model.Player;
import src.model.Space;
import src.model.World;
import src.view.View;

/**
 * Represents a controller that can be executed on a game world.
 */
public class GraphicalController implements Controller, Feature {
  private World world;
  private View view;
  private int maxTurns;
  private int turn;

  /**
   * Constructs a controller object.
   *
   * @param maxTurn the max turns of the game
   */
  public GraphicalController(int maxTurn) {
    this.world = null;
    this.view = null;
    this.maxTurns = maxTurn;
    this.turn = 0;

  }

  @Override
  public void start(World world) {
    this.world = world;
    view.makeVisible();
    view.setFeatures(this);
    view.updateGameOutput("Welcome to the game!\n");
    view.updateGameOutput(String.format("Select option:\n%s\n%s\n%s\n%s\n",
        "1.add player.",
        "2.World info.",
        "3.next turn.",
        "0.Exit game."));
  }

  @Override
  public void setView(View view) {
    this.view = view;
  }

  @Override
  public void infoTarget() {
    String target = world.getCharacter().toString();
    String pet = world.getPet().toString();
    view.updateGameOutput(String.format("here is info of the target:\n%s\n%s", target, pet));
    view.refresh();
  }

  @Override
  public void infoPlayer(String name) {
    String player = world.playerInfo(name);
    view.updateGameOutput(String.format("here is info of the player:\n%s", player));
    view.refresh();
  }

  @Override
  public void infoWorld() {
    view.updateGameOutput(world.toString());
    view.refresh();
  }

  @Override
  public void infoSpace(String spaceName) {
    List<Space> spaces = world.getSpaces();
    for (Space space : spaces) {
      if (space.getName().equals(spaceName)) {
        view.updateGameOutput(space.toString());
        view.refresh();
      }
    }
    view.refresh();
  }

  @Override
  public void addPlayer() {
    String playerName = JOptionPane.showInputDialog("Please provide player name:");
    int initialSpaceIndex = Integer.parseInt(JOptionPane
        .showInputDialog("Please provide initial space index:"));
    boolean isBot = Boolean.parseBoolean(JOptionPane
        .showInputDialog("Bot player chose true, human player chose false:"));

    String result = world.addPlayer(playerName, initialSpaceIndex, isBot);
    view.getTextPanel().updateGameOutput(result);
    view.refresh();
  }

  @Override
  public void nextTurn() {
    if (turn < maxTurns) {
      turn++;
      view.getTextPanel().updateGameOutput(String.format("turn %s\n", turn));

      for (Player player : world.getPlayers()) {
        String playerTrun = String.format("Player %s turn\n", player.getName());
        JOptionPane.showMessageDialog(null, playerTrun);
        view.getTextPanel().updateGameOutput(playerTrun);
        String playerItems = String.format("Player %s have items: %s\n", player.getName(),
            player.getItems().stream().map(Item::getName)
                .collect(Collectors.joining(",")));
        JOptionPane.showMessageDialog(null, playerItems);
        view.getTextPanel().updateGameOutput(playerItems);
        if ('p' == player.toString().charAt(0)) {
          String option = JOptionPane.showInputDialog(String.format("Select "
                  + "option:\n%s\n%s\n%s\n%s\n%s\n",
              "1.look around.",
              "2.move to neighbor space.",
              "3.pick up items.",
              "4.teleport pet.",
              "5.attack target."));
          switch (option) {
            case "1":
              JOptionPane.showMessageDialog(null, String.format("%s\n", player.lookAround()));
              view.getTextPanel().updateGameOutput(String.format("%s\n", player.lookAround()));
              break;
            case "2":
              String spaceName = JOptionPane.showInputDialog("please provide space name");
              String moveResult = String.format("%s\n", player.moveTo(spaceName));
              JOptionPane.showMessageDialog(null, moveResult);
              view.getTextPanel().updateGameOutput(moveResult);
              view.refresh();
              break;
            case "3":
              String itemsList = player.getCurrentSpace().getItems().stream()
                  .map(Item::getName)
                  .collect(Collectors.joining(", "));
              JOptionPane.showMessageDialog(null, String.format("space has item :%s\n", itemsList));
              view.getTextPanel()
                  .updateGameOutput(String.format("space has item :%s\n", itemsList));
              String item = JOptionPane.showInputDialog("please provide item name");
              String pickupResult = String.format("%s\n", player.pickupItems(item));
              JOptionPane.showMessageDialog(null, pickupResult);
              view.getTextPanel().updateGameOutput(pickupResult);
              break;
            case "4":
              String teleportSpace = JOptionPane.showInputDialog("please provide space name");
              String teleportResult = String.format("%s\n", player.movePet(teleportSpace));
              JOptionPane.showMessageDialog(null, teleportResult);
              view.getTextPanel().updateGameOutput(teleportResult);
              break;
            case "5":
              String attackResult = String.format("%s\n", player.attackTarget());
              JOptionPane.showMessageDialog(null, attackResult);
              view.getTextPanel().updateGameOutput(attackResult);
              view.updateGameOutput(attackResult);
              if (world.getCharacter().getHealth() <= 0) {
                view.getTextPanel()
                    .updateGameOutput(String.format("%s win! game over\n", player.getName()));
                JOptionPane.showMessageDialog(null,
                    String.format("%s win! game over\n", player.getName()));
                turn = maxTurns + 1;
              }
              break;
            default:
              JOptionPane.showMessageDialog(null, "Invalid option, please try again\n");
              view.getTextPanel().updateGameOutput("Invalid option, please try again\n");
              break;
          }
        } else {
          JOptionPane.showMessageDialog(null, "\nbot player do action automatically\n");
          view.getTextPanel().updateGameOutput(player.doAction());
          if (world.getCharacter().getHealth() <= 0) {
            view.getTextPanel()
                .updateGameOutput(String.format("%s win! game over\n", player.getName()));
            JOptionPane.showMessageDialog(null,
                String.format("%s win! game over\n", player.getName()));
            turn = maxTurns + 1;
          }
          view.refresh();
        }
      }
      view.getTextPanel().updateGameOutput(world.moveCharacter() + "\n");
      view.getTextPanel().updateGameOutput(world.getPet().wandering() + "\n");
      view.getTextPanel().updateGameOutput("this turn is end\n");
      view.refresh();
      if (turn == maxTurns) {
        view.getTextPanel().updateGameOutput("Target escaped! game over\n");
      }
    } else {
      view.getTextPanel().updateGameOutput("game is over\n");
    }
  }

  @Override
  public void exitGame() {
    JOptionPane.showMessageDialog(null, "quit game");
    System.exit(0);
  }

}

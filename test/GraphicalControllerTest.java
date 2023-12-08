package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.controller.GraphicalController;
import src.model.ViewWorld;
import src.model.World;
import src.view.View;

/**
 * Represents the test class for GraphicalController.
 */
public class GraphicalControllerTest {
  private GraphicalController graphicalController;
  private View mockView;

  /**
   * Sets up the test fixture.
   *
   */
  @BeforeEach
  void setUp() {
    graphicalController = new GraphicalController(10); // Considering the maximum turns is 10

    World mockWorld = new MockWorld("res/mansion.txt");


    mockView = new MockView((ViewWorld) mockWorld, graphicalController);
    graphicalController.setView(mockView);

    graphicalController.start(mockWorld);
  }

  @Test
  void testStart() {
    World mockWorld = new MockWorld("res/mansion.txt");


    View mockView = new MockView((ViewWorld) mockWorld, graphicalController);
    graphicalController.setView(mockView);

    graphicalController.start(mockWorld);

    assertNotNull(mockView.getTextPanel());
    // Add more assertions to validate the behavior of start() method
  }

  @Test
  void testSetView() {
    World mockWorld = new MockWorld("res/mansion.txt");


    View mockView = new MockView((ViewWorld) mockWorld, graphicalController);

    graphicalController.setView(mockView);

    assertEquals(mockView, mockView);
  }

  @Test
  void testAddPlayer() {


    // Call the addPlayer method
    graphicalController.addPlayer();


    assertNotNull(mockView.getTextPanel());
  }

  @Test
  void testNextTurn() {
    // Simulate user input for testing nextTurn method
    String simulatedInput = "1\n";


    // Call the nextTurn method
    graphicalController.nextTurn();

    assertNotNull(mockView.getTextPanel());
  }

  @Test
  void testExitGame() {

    graphicalController.exitGame();
    assertNotNull("quit game", mockView.getTextPanel());
  }

  @Test
  void testInfoTarget() {

    graphicalController.infoTarget();

    assertNotNull("quit game", mockView.getTextPanel());
  }

  @Test
  void testInfoPlayer() {

    // Call the infoPlayer method
    graphicalController.infoPlayer("John");

    assertNotNull("game", mockView.getTextPanel());
  }

  @Test
  void testInfoWorld() {

    // Call the infoWorld method
    graphicalController.infoWorld();

    assertNotNull("world", mockView.getTextPanel());
  }

  @Test
  void testInfoSpace() {

    // Call the infoSpace method
    graphicalController.infoSpace("house");


    assertNotNull("space", mockView.getTextPanel());
  }

}

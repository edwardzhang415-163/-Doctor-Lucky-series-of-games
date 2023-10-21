package test;



import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.io.StringWriter;
import org.junit.Before;
import org.junit.Test;
import src.controller.Controller;
import src.controller.GameController;
import src.model.World;



/**
 * Tests the GameController.
 */
public class GameControllerTest {

  private Controller gameController;
  private World mockWorld;
  private StringWriter out;
  private StringReader in;

  /**
   * Sets up the test.
   */
  @Before
  public void setUp() {
    // Create a mock world for testing
    mockWorld = new MockWorld("res/mansion.txt");
    out = new StringWriter();
  }

  @Test
  public void testGameController() {
    // Set up the input (user commands) as a StringReader with sample commands
    String input = "1\n2\n3\n4\n5\n6\n7\n8\nq\n"; // Example commands for testing
    in = new StringReader(input);

    // Create a GameController with the mock world and the input/output streams
    gameController = new GameController(in, out, 40);

    // Start the game controller
    gameController.start(mockWorld);

    // Assert the output based on the expected behavior of the GameController
    String expectedOutput = String.format("Select option:\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
        "1.Output the world to an image file.",
        "2.Move character.",
        "3.Space info by index.",
        "4.Target info.",
        "5.World info.",
        "6.Player info.",
        "7.Add player.",
        "8.Next turn.",
        "q.Quit.");
    // Add more expected output based on the specific interactions with the game controller
    // The expected output should include the responses to user commands.

    assertEquals(expectedOutput, out.toString());
  }
}

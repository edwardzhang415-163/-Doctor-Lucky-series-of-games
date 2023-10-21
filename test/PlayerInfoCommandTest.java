package test;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import src.controller.command.PlayerInfoCommand;
import src.model.World;


/**
 * Tests the PlayerInfoCommand.
 */
public class PlayerInfoCommandTest {

  private PlayerInfoCommand playerInfoCommand;
  private World mockWorld;
  private StringWriter out;
  private Scanner scanner;

  /**
   * Sets up the test.
   *
   */
  @Before
  public void setUp() {
    // Create a mock world for testing
    mockWorld = new MockWorld("res/mansion.txt");
    playerInfoCommand = new PlayerInfoCommand();
    out = new StringWriter();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testPlayerInfoCommand() throws IOException {
    // Add some players to the mock world for testing
    ((MockWorld) mockWorld).addPlayer("q", 1, false);

    // Set up the scanner input for the test
    String input = "Player1";
    scanner = new Scanner(input);

    playerInfoCommand.execute(mockWorld, scanner, out);

    // Assuming that the execute method of PlayerInfoCommand does not return any output,
    // you can assert the Appendable output to check the message appended.
    assertEquals("please provide player name\nPlayer1\n", out.toString());
  }
}

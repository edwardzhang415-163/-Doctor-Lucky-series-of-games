package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import src.controller.command.NextTurnCommand;
import src.model.World;



/**
 * Tests the NextTurnCommand.
 */
public class NextTurnCommandTest {

  private NextTurnCommand nextTurnCommand;
  private World mockWorld;
  private StringWriter out;
  private Scanner scanner;

  /**
   * Sets up the test.
   */
  @Before
  public void setUp() {
    // Create a mock world for testing
    mockWorld = new MockWorld("res/mansion.txt");
    nextTurnCommand = new NextTurnCommand(40);
    out = new StringWriter();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testNextTurnCommand() throws IOException {

    // Assuming that the turn logic in NextTurnCommand is correct,
    // you can execute the command and assert the output.
    nextTurnCommand.execute(mockWorld, scanner, out);

    // Example assertions (update as needed):
    assertEquals("turn 1\n", out.toString()); // Check if the turn number is correctly incremented
    // Add more assertions based on the expected behavior of the command
  }
}

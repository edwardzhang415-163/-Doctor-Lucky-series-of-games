package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import src.controller.command.SpaceInfoCommand;
import src.model.World;



/**
 * Tests the SpaceInfoCommand.
 */
public class SpaceInfoCommandTest {

  private SpaceInfoCommand spaceInfoCommand;
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
    spaceInfoCommand = new SpaceInfoCommand();
    out = new StringWriter();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testSpaceInfoCommand() throws IOException {
    // Add some spaces to the mock world for testing

    // Set up the scanner input for the test
    int inputSpaceIndex = 0;
    String input = String.valueOf(inputSpaceIndex);
    scanner = new Scanner(input);

    spaceInfoCommand.execute(mockWorld, scanner, out);

    // Assuming that the execute method of SpaceInfoCommand does not return any output,
    // you can assert the Appendable output to check the message appended.
    assertEquals(String.format("please provide space index\n%s\n",
        mockWorld.getSpace(inputSpaceIndex).toString()), out.toString());
  }
}

package test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import src.controller.command.AddPlayerCommand;
import src.model.World;



/**
 * Tests the AddPlayerCommand.
 */
public class AddPlayerCommandTest {

  private AddPlayerCommand addPlayerCommand;
  private World mockWorld;
  private StringWriter out;
  private Scanner scanner;

  /**
   * Sets up the test.
   *
   */
  @Before
  public void setUp() throws IOException {
    // Create a mock world for testing using the "res/mansion.txt" file
    File inputFile = new File("res/mansion.txt");
    mockWorld = new MockWorld(inputFile.getPath());
    addPlayerCommand = new AddPlayerCommand();
    out = new StringWriter();
  }

  private void provideInput(String input) {
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    scanner = new Scanner(in);
  }

  @Test
  public void testExecuteWithValidInput() throws IOException {
    provideInput("Player1\n0\ntrue\n");
    addPlayerCommand.execute(mockWorld, scanner, out);
    assertEquals(String.format("please provide player name\n",
        "please provide initial space index\n",
        "bot player chose true, human player chose false\n",
        "successfully added player Player1 to the game\n"), out.toString());
  }

  @Test
  public void testExecuteWithInvalidInput() throws IOException {
    provideInput("Player1\n5\ntrue\n");
    addPlayerCommand.execute(mockWorld, scanner, out);
    assertEquals(String.format("please provide player name\n",
        "please provide initial space index\n",
        "bot player chose true, human player chose false\n",
        "invalid input, please try again\n"), out.toString());
  }
}

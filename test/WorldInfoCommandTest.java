package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import src.controller.command.WorldInfoCommand;
import src.model.Space;
import src.model.World;


/**
 * Tests the WorldInfoCommand.
 */
public class WorldInfoCommandTest {

  private WorldInfoCommand worldInfoCommand;
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
    worldInfoCommand = new WorldInfoCommand();
    out = new StringWriter();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testWorldInfoCommand() throws IOException {
    // Set up a mock world with mock data for testing
    List<Space> spaces = new ArrayList<>();

    worldInfoCommand.execute(mockWorld, scanner, out);

    // Assuming that the execute method of WorldInfoCommand does not return any output,
    // you can assert the Appendable output to check the message appended.
    assertEquals(String.format("please provide world name\n%s\n",
        mockWorld.toString()), out.toString());
  }
}

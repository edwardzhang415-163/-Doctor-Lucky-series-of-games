package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import src.controller.command.MoveCharacterCommand;
import src.model.World;

/**
 * Tests the MoveCharacterCommand.
 */
public class MoveCharacterCommandTest {

  private MoveCharacterCommand moveCharacterCommand;
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
    moveCharacterCommand = new MoveCharacterCommand();
    out = new StringWriter();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testMoveCharacterCommand() throws IOException {
    moveCharacterCommand.execute(mockWorld, scanner, out);
    // Assuming that the moveCharacter method in the world returns a string indicating new space,
    // you can assert the output based on the expected result.
    assertEquals("Character is now in SomeSpace\n", out.toString());
  }
}

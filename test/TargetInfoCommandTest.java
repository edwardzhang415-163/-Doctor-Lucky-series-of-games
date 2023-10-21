package test;

import org.junit.Before;
import org.junit.Test;
import src.controller.command.TargetInfoCommand;
import src.model.Character;
import src.model.World;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class TargetInfoCommandTest {

  private TargetInfoCommand targetInfoCommand;
  private World mockWorld;
  private StringWriter out;
  private Scanner scanner;

  @Before
  public void setUp() {
    // Create a mock world for testing
    mockWorld = new MockWorld("res/mansion.txt");
    targetInfoCommand = new TargetInfoCommand();
    out = new StringWriter();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testTargetInfoCommand() throws IOException {
    // Set up a mock character for the world

    targetInfoCommand.execute(mockWorld, scanner, out);

    // Assuming that the execute method of TargetInfoCommand does not return any output,
    // you can assert the Appendable output to check the message appended.
    assertEquals(String.format("please provide target name\n%s\n",
        mockWorld.getCharacter().toString()), out.toString());
  }
}

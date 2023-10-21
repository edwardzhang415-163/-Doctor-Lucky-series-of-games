package test;

import org.junit.Before;
import org.junit.Test;
import src.controller.command.NextTurnCommand;
import src.model.Character;
import src.model.World;
import src.model.Space;
import src.model.Item;
import src.model.Player;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class NextTurnCommandTest {

  private NextTurnCommand nextTurnCommand;
  private World mockWorld;
  private StringWriter out;
  private Scanner scanner;

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

    // Assert the output based on the expected behavior of NextTurnCommand
    // and the current state of the mock world and players.
    // For example, you can check if the turn number is correctly incremented,
    // if the players' actions are correctly executed, and if the appropriate messages are generated.
    // You might need to update the assertions based on your specific requirements.

    // Example assertions (update as needed):
    assertEquals("turn 1\n", out.toString()); // Check if the turn number is correctly incremented
    // Add more assertions based on the expected behavior of the command
  }
}

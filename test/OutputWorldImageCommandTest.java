package test;



import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;
import src.controller.command.OutputWorldImageCommand;
import src.model.World;


/**
 * Tests the OutputWorldImageCommand.
 */
public class OutputWorldImageCommandTest {

  private OutputWorldImageCommand outputWorldImageCommand;
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
    outputWorldImageCommand = new OutputWorldImageCommand();
    out = new StringWriter();
    scanner = new Scanner(System.in);
  }

  @Test
  public void testOutputWorldImageCommand() throws IOException {
    outputWorldImageCommand.execute(mockWorld, scanner, out);

    // Assuming that the execute method of OutputWorldImageCommand does not return any output,
    // you can assert the Appendable output to check the message appended.
    assertEquals("successfully output the world to an image file\n", out.toString());
  }
}

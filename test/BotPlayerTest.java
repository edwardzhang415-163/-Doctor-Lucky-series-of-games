package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import src.model.BotPlayer;
import src.model.MyWorld;
import src.model.MyWorldSpace;
import src.model.Space;

/**
 * Tests the BotPlayer class.
 */
public class BotPlayerTest {

  private BotPlayer botPlayer;
  private Space mockSpace;
  private MyWorld myWorld;


  /**
   * Sets up the test fixture.
   * Called before every test case method.
   */
  @Before
  public void setUp() {
    myWorld = new MyWorld("src/test/resources/world.txt");
    mockSpace = new MyWorldSpace(0, 0, 1, 1, "Mock Space");
    botPlayer = new BotPlayer("Bot", mockSpace, myWorld);
  }

  @Test
  public void testDoActionPickupItems() {
    String result = botPlayer.doAction();
    assertTrue(result.startsWith("Player Bot picked up"));
  }

  @Test
  public void testDoActionMoveTo() {
    String result = botPlayer.doAction();
    assertTrue(result.startsWith("Player Bot moved to"));
  }

  @Test
  public void testDoActionLookAround() {
    String result = botPlayer.doAction();
    assertTrue(result.startsWith("Player Bot is in"));
  }

  @Test
  public void testDoActionMovePet() {
    String result = botPlayer.doAction();
    assertTrue(result.startsWith("Player Bot moved pet to"));
  }

  @Test
  public void testDoActionAttackTarget() {
    String result = botPlayer.doAction();
    assertTrue(result.startsWith("Player Bot attacked"));
  }

  @Test
  public void testToString() {
    String expectedString = "botplayer{\nname='Bot'\nitems=\ncurrentSpace=Mock Space\n}";
    assertEquals(expectedString, botPlayer.toString());
  }
}
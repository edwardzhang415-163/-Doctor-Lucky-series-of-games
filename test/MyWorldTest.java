package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import src.model.Character;
import src.model.Item;
import src.model.MyWorld;
import src.model.MyWorldItem;
import src.model.Space;



/**
 * test for MyWorld.
 *
 */
public class MyWorldTest {
  private MyWorld myWorld;

  @Before
  public void setUp() {
    myWorld = new MyWorld("res/mansion.txt");
  }

  @Test
  public void testConstructorAndGetters() {
    assertNotNull(myWorld);
    assertEquals("Sample World", myWorld.getName());
    assertEquals(4, myWorld.getNumRows());
    assertEquals(4, myWorld.getNumCols());
    assertNotNull(myWorld.getSpaces());
    assertNotNull(myWorld.getCharacter());
    assertNotNull(myWorld.getItems());
    assertNotNull(myWorld.getPlayers());
  }

  @Test
  public void testGetSpace() {
    Space space = myWorld.getSpace(0);
    assertNotNull(space);
    assertEquals("Space1", space.getName());
  }

  @Test
  public void testRenderWorldImage() {
    assertNotNull(myWorld.renderWorldImage());
  }

  @Test
  public void testMoveCharacter() {
    String result = myWorld.moveCharacter();
    assertNotNull(result);
    assertTrue(result.contains("Character is now in "));
  }

  @Test
  public void testAddPlayer() {
    String playerName = "Player1";
    int initialSpaceIndex = 0;
    boolean isBot = false;
    String result = myWorld.addPlayer(playerName, initialSpaceIndex, isBot);
    assertNotNull(result);
    assertTrue(result.contains("Player Player1 is added to Space1"));
  }

  @Test
  public void testAddDuplicatePlayer() {
    String playerName = "Player1";
    int initialSpaceIndex = 0;
    boolean isBot = false;
    myWorld.addPlayer(playerName, initialSpaceIndex, isBot);
    String result = myWorld.addPlayer(playerName, initialSpaceIndex, isBot);
    assertNotNull(result);
    assertTrue(result.contains("Player name already exists"));
  }

  @Test
  public void testPlayerInfo() {
    String playerName = "Player1";
    int initialSpaceIndex = 0;
    boolean isBot = false;
    myWorld.addPlayer(playerName, initialSpaceIndex, isBot);
    String result = myWorld.playerInfo(playerName);
    assertNotNull(result);
    assertTrue(result.contains("Player{name='Player1', currentSpace=Space1}"));
  }

  @Test
  public void testPlayerInfoNonExistingPlayer() {
    String playerName = "NonExistingPlayer";
    String result = myWorld.playerInfo(playerName);
    assertNotNull(result);
    assertTrue(result.contains("there is no such player in this world"));
  }



  @Test
  public void testEqualsAndHashCode() {
    MyWorld myWorld1 = new MyWorld("src/test/resources/world.txt");
    MyWorld myWorld2 = new MyWorld("src/test/resources/world.txt");
    MyWorld myWorld3 = new MyWorld("src/test/resources/world2.txt");

    assertEquals(myWorld, myWorld1);
    assertEquals(myWorld, myWorld2);
    assertNotEquals(myWorld, myWorld3);
    assertEquals(myWorld.hashCode(), myWorld1.hashCode());
    assertEquals(myWorld.hashCode(), myWorld2.hashCode());
    assertNotEquals(myWorld.hashCode(), myWorld3.hashCode());
  }
}
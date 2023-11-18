package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import src.model.Item;
import src.model.MyWorld;
import src.model.MyWorldItem;
import src.model.MyWorldPlayer;
import src.model.MyWorldSpace;
import src.model.Player;
import src.model.Space;


/**
 * test for MyWorldSpace.
 */

public class MyWorldSpaceTest {

  private MyWorldSpace space;
  private MyWorldSpace neighbor;
  private MyWorld myWorld;

  /**
   * Sets up the test fixture.
   * Called before every test case method.
   */
  @Before
  public void setUp() {
    space = new MyWorldSpace(0, 0, 1, 1, "Space1");
    neighbor = new MyWorldSpace(0, 2, 1, 3, "Space2");
    myWorld = new MyWorld("src/test/resources/world.txt");
  }

  @Test
  public void testConstructorAndGetters() {
    assertNotNull(space);
    assertEquals("Space1", space.getName());
    assertEquals(0, space.getUpperLeftRow());
    assertEquals(0, space.getUpperLeftCol());
    assertEquals(1, space.getLowerRightRow());
    assertEquals(1, space.getLowerRightCol());
    assertNotNull(space.getNeighbors());
    assertNotNull(space.getItems());
    assertNotNull(space.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidCoordinates() {
    new MyWorldSpace(1, 1, 0, 0, "InvalidSpace");
  }

  @Test
  public void testIsNeighbor() {
    assertTrue(space.isNeighbor(neighbor));
  }

  @Test
  public void testAddNeighbor() {
    space.addNeighbor(neighbor);
    List<Space> neighbors = space.getNeighbors();
    assertEquals(1, neighbors.size());
    assertEquals(neighbor, neighbors.get(0));
  }

  @Test
  public void testAddItem() {
    Item item = new MyWorldItem(0, 10, "Sword");
    space.addItem(item);
    List<Item> items = space.getItems();
    assertEquals(1, items.size());
    assertEquals(item, items.get(0));
  }

  @Test
  public void testRemoveItem() {
    Item item = new MyWorldItem(0, 10, "Sword");
    space.addItem(item);
    String removedItemName = space.revomeItem(item);
    assertEquals("Sword", removedItemName);
    assertEquals(0, space.getItems().size());
  }

  @Test
  public void testRemoveNonExistingItem() {
    Item item = new MyWorldItem(0, 10, "Sword");
    String result = space.revomeItem(item);
    assertEquals("there is no such item in this space", result);
  }

  @Test
  public void testAddPlayer() {
    Player player = new MyWorldPlayer("Player1", space, myWorld);
    String result = space.addPlayer(player);
    assertEquals("player Player1 is added to Space1", result);
  }

  @Test
  public void testRemovePlayer() {
    Player player = new MyWorldPlayer("Player1", space, myWorld);
    space.addPlayer(player);
    String result = space.removePlayer(player);
    assertEquals("player Player1 is removed from Space1", result);
  }

  @Test
  public void testRemoveNonExistingPlayer() {
    Player player = new MyWorldPlayer("Player1", space, myWorld);
    String result = space.removePlayer(player);
    assertEquals("player Player1 is removed from Space1", result);
  }

  @Test
  public void testEqualsAndHashCode() {
    MyWorldSpace space1 = new MyWorldSpace(0, 0, 1, 1, "Space1");
    MyWorldSpace space2 = new MyWorldSpace(0, 0, 1, 1, "Space1");
    MyWorldSpace space3 = new MyWorldSpace(1, 1, 2, 2, "Space2");

    assertEquals(space1, space2);
    assertNotEquals(space1, space3);
    assertEquals(space1.hashCode(), space2.hashCode());
    assertNotEquals(space1.hashCode(), space3.hashCode());
  }
}
package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import src.model.MyWorldItem;
import src.model.MyWorldPlayer;
import src.model.Item;
import src.model.MyWorldSpace;
import src.model.Space;

public class MyWorldPlayerTest {

  private MyWorldPlayer player;
  private Space space;

  @Before
  public void setUp() {
    space = new MyWorldSpace(0, 0, 1, 1, "Space1");
    player = new MyWorldPlayer("Player1", space);
  }

  @Test
  public void testConstructorAndGetters() {
    assertNotNull(player);
    assertEquals("Player1", player.getName());
    assertEquals(space, player.getCurrentSpace());
    assertNotNull(player.getItems());
    assertEquals(0, player.getItems().size());
  }

  @Test
  public void testPickupItems() {
    Item item = new MyWorldItem(0, 0, "Item1");
    space.addItem(item);

    String result = player.pickupItems("Item1");
    assertNotNull(result);
    assertEquals("Player Player1 picked up Item1", result);
    assertEquals(1, player.getItems().size());
    assertEquals("Item1", player.getItems().get(0).getName());
  }

  @Test
  public void testPickupNonExistingItem() {
    String result = player.pickupItems("NonExistingItem");
    assertNotNull(result);
    assertEquals("There is no such item in this space", result);
  }

  @Test
  public void testPickupItemsExceedingLimit() {
    space.addItem(new MyWorldItem(0, 0, "Item1"));
    space.addItem(new MyWorldItem(0, 0, "Item2"));
    space.addItem(new MyWorldItem(0, 0, "Item3"));
    space.addItem(new MyWorldItem(0, 0, "Item4"));
    space.addItem(new MyWorldItem(0, 0, "Item5"));

    String result = player.pickupItems("NewItem");
    assertNotNull(result);
    assertEquals("Player Player1 can't pick up more items", result);
  }

  @Test
  public void testMoveToExistingNeighbor() {
    Space neighbor = new MyWorldSpace(1, 1, 2, 2, "NeighborSpace");
    space.addNeighbor(neighbor);

    String result = player.moveTo("NeighborSpace");
    assertNotNull(result);
    assertEquals("Player Player1 moved to NeighborSpace", result);
    assertEquals(neighbor, player.getCurrentSpace());
  }

  @Test
  public void testMoveToNonExistingNeighbor() {
    String result = player.moveTo("NonExistingNeighbor");
    assertNotNull(result);
    assertEquals("There is no such neighbor", result);
  }

  @Test
  public void testLookAround() {
    Space neighbor1 = new MyWorldSpace(1, 1, 2, 2, "NeighborSpace1");
    Space neighbor2 = new MyWorldSpace(1, 1, 2, 2, "NeighborSpace2");
    space.addNeighbor(neighbor1);
    space.addNeighbor(neighbor2);

    String result = player.lookAround();
    assertNotNull(result);
    assertEquals("Player Player1 is in Space1. You can go to: NeighborSpace1, NeighborSpace2", result);
  }

  @Test
  public void testDoAction() {
    String result = player.doAction();
    assertNotNull(result);
    assertEquals("", result);
  }


  @Test
  public void testToString() {
    String result = player.toString();
    assertNotNull(result);
    assertEquals("player{\nname='Player1'\nitems=\ncurrentSpace=Space1\n}", result);
  }
}
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;
import src.world.Item;
import src.world.MyWorldItem;
import src.world.MyWorldSpace;
import src.world.Space;


/**
 * test for MyWorldSpace.
 */

public class MyWorldSpaceTest {

  @Test
  public void testGetName() {
    MyWorldSpace space = new MyWorldSpace(0, 0, 1, 1, "Test Space");
    assertEquals("Test Space", space.getName());
  }

  @Test
  public void testGetUpperLeftRow() {
    MyWorldSpace space = new MyWorldSpace(1, 2, 3, 4, "Test Space");
    assertEquals(1, space.getUpperLeftRow());
  }

  @Test
  public void testGetUpperLeftCol() {
    MyWorldSpace space = new MyWorldSpace(1, 2, 3, 4, "Test Space");
    assertEquals(2, space.getUpperLeftCol());
  }

  @Test
  public void testGetLowerRightRow() {
    MyWorldSpace space = new MyWorldSpace(1, 2, 3, 4, "Test Space");
    assertEquals(3, space.getLowerRightRow());
  }

  @Test
  public void testGetLowerRightCol() {
    MyWorldSpace space = new MyWorldSpace(1, 2, 3, 4, "Test Space");
    assertEquals(4, space.getLowerRightCol());
  }

  @Test
  public void testIsNeighbor() {
    MyWorldSpace space1 = new MyWorldSpace(1, 1, 2, 2, "Space 1");
    MyWorldSpace space2 = new MyWorldSpace(2, 1, 3, 2, "Space 2");
    assertTrue(space1.isNeighbor(space2));
  }

  @Test
  public void testAddNeighbor() {
    MyWorldSpace space1 = new MyWorldSpace(1, 1, 2, 2, "Space 1");
    MyWorldSpace space2 = new MyWorldSpace(2, 1, 3, 2, "Space 2");
    space1.addNeighbor(space2);
    List<Space> neighbors = space1.getNeighbors();
    assertNotNull(neighbors);
    assertTrue(neighbors.contains(space2));
  }

  @Test
  public void testAddItem() {
    MyWorldSpace space = new MyWorldSpace(1, 1, 2, 2, "Test Space");
    Item item = new MyWorldItem(1, 10, "Test Item");
    space.addItem(item);
    List<Item> items = space.getItems();
    assertNotNull(items);
    assertTrue(items.contains(item));
  }

  @Test
  public void testEquals() {
    MyWorldSpace space1 = new MyWorldSpace(1, 1, 2, 2, "Test Space");
    MyWorldSpace space2 = new MyWorldSpace(1, 1, 2, 2, "Test Space");
    assertEquals(space1, space2);
  }

  @Test
  public void testNotEquals() {
    MyWorldSpace space1 = new MyWorldSpace(1, 1, 2, 2, "Test Space 1");
    MyWorldSpace space2 = new MyWorldSpace(1, 1, 2, 2, "Test Space 2");
    assertNotEquals(space1, space2);
  }

  @Test
  public void testHashCode() {
    MyWorldSpace space = new MyWorldSpace(1, 1, 2, 2, "Test Space");
    assertEquals(space.hashCode(), space.hashCode());
  }

  @Test
  public void testToString() {
    MyWorldSpace space1 = new MyWorldSpace(1, 1, 2, 2, "Test Space 1");
    MyWorldSpace space2 = new MyWorldSpace(2, 2, 3, 3, "Test Space 2");
    space1.addNeighbor(space2);
    space2.addNeighbor(space1);
    Item item = new MyWorldItem(1, 10, "Test Item");
    space1.addItem(item);
    space2.addItem(item);
    assertEquals("MyWorldSpace{\nname='Test Space 1\nneighbors="
            + "Test Space 2, items=Test Item}", space1.toString());
    assertEquals("MyWorldSpace{\nname='Test Space 2\nneighbors="
            + "Test Space 1, items=Test Item}", space2.toString());
  }
}
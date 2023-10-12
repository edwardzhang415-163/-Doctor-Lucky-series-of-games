package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import src.world.MyWorldItem;



/**
  * test for MyWorldItem.
 */
public class MyWorldItemTest {

  @Test
  public void testGetRoomIndex() {
    MyWorldItem item = new MyWorldItem(1, 10, "Test Item");
    assertEquals(1, item.getRoomIndex());
  }

  @Test
  public void testGetDamage() {
    MyWorldItem item = new MyWorldItem(1, 10, "Test Item");
    assertEquals(10, item.getDamage());
  }

  @Test
  public void testGetName() {
    MyWorldItem item = new MyWorldItem(1, 10, "Test Item");
    assertEquals("Test Item", item.getName());
  }

  @Test
  public void testEquals() {
    MyWorldItem item1 = new MyWorldItem(1, 10, "Test Item");
    MyWorldItem item2 = new MyWorldItem(1, 10, "Test Item");
    assertEquals(item1, item2);
  }

  @Test
  public void testNotEquals() {
    MyWorldItem item1 = new MyWorldItem(1, 10, "Test Item");
    MyWorldItem item2 = new MyWorldItem(2, 20, "Another Item");
    assertNotEquals(item1, item2);
  }

  @Test
  public void testHashCode() {
    MyWorldItem item = new MyWorldItem(1, 10, "Test Item");
    assertEquals(item.hashCode(), item.hashCode());
  }

  @Test
  public void testToString() {
    MyWorldItem item = new MyWorldItem(1, 10, "Test Item");
    assertEquals("MyWorldItem{roomIndex=1, damage=10, name='Test Item'}", item.toString());
  }
}
package test;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import src.world.MyWorldItem;



/**
  * test for MyWorldItem.
 */
public class MyWorldItemTest {
  /*
   * test for getRoomIndex()
   */
  @Test
  public void testGetRoomIndex() {
    MyWorldItem item = new MyWorldItem(0, 0, "item");
    assertEquals(0, item.getRoomIndex());
  }

  /*
   * test for getDamage()
   */
  @Test
  public void testGetDamage() {
    MyWorldItem item = new MyWorldItem(0, 0, "item");
    assertEquals(0, item.getDamage());
  }

  /*
   * test for getName()
   */
  @Test
  public void testGetName() {
    MyWorldItem item = new MyWorldItem(0, 0, "item");
    assertEquals("item", item.getName());
  }
}
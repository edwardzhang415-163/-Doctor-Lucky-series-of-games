package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import src.model.MyWorldItem;



/**
  * test for MyWorldItem.
 */
public class MyWorldItemTest {

  @Test
  public void testConstructor() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertNotNull(item);
  }

  @Test
  public void testGetRoomIndex() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertEquals(1, item.getRoomIndex());
  }

  @Test
  public void testGetDamage() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertEquals(10, item.getDamage());
  }

  @Test
  public void testGetName() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertEquals("Sword", item.getName());
  }

  @Test
  public void testEqualsSameObject() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertEquals(item, item);
  }

  @Test
  public void testEqualsDifferentClass() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertNotEquals(item, "Sword");
  }

  @Test
  public void testEqualsNullObject() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertNotEquals(item, null);
  }

  @Test
  public void testEqualsDifferentProperties() {
    MyWorldItem item1 = new MyWorldItem(1, 10, "Sword");
    MyWorldItem item2 = new MyWorldItem(2, 20, "Axe");
    assertNotEquals(item1, item2);
  }

  @Test
  public void testEqualsSameProperties() {
    MyWorldItem item1 = new MyWorldItem(1, 10, "Sword");
    MyWorldItem item2 = new MyWorldItem(1, 10, "Sword");
    assertEquals(item1, item2);
  }

  @Test
  public void testHashCodeConsistency() {
    MyWorldItem item1 = new MyWorldItem(1, 10, "Sword");
    MyWorldItem item2 = new MyWorldItem(1, 10, "Sword");
    assertEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testHashCodeDifferentProperties() {
    MyWorldItem item1 = new MyWorldItem(1, 10, "Sword");
    MyWorldItem item2 = new MyWorldItem(2, 20, "Axe");
    assertNotEquals(item1.hashCode(), item2.hashCode());
  }

  @Test
  public void testToString() {
    MyWorldItem item = new MyWorldItem(1, 10, "Sword");
    assertEquals("MyWorldItem{roomIndex=1, damage=10, name='Sword'}", item.toString());
  }
}
package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import src.world.MyWorldSpace;

/**
 * test for MyWorldSpace.
 */

public class MyWorldSpaceTest {
  /*
   * test for getName().
   */
  @Test
  public void testGetName() {
    MyWorldSpace space = new MyWorldSpace(0, 0, 0, 0, "space");
    assertEquals("space", space.getName());
  }

  /*
   * test for getUpperLeftRow()
   */
  @Test
  public void testGetUpperLeftRow() {
    MyWorldSpace space = new MyWorldSpace(1, 0, 2, 2, "space");
    assertEquals(1, space.getUpperLeftRow());
  }

  /*
   * test for getUpperLeftCol()
   */
  @Test
  public void testGetUpperLeftCol() {
    MyWorldSpace space = new MyWorldSpace(0, 1, 1, 2, "space");
    assertEquals(1, space.getUpperLeftCol());
  }

  /*
   * test for getLowerRightRow()
   */
  @Test
  public void testGetLowerRightRow() {
    MyWorldSpace space = new MyWorldSpace(0, 0, 1, 1, "space");
    assertEquals(1, space.getLowerRightRow());
  }

  /*
   * test for getLowerRightCol()
   */
  @Test
  public void testGetLowerRightCol() {
    MyWorldSpace space = new MyWorldSpace(0, 0, 0, 1, "space");
    assertEquals(1, space.getLowerRightCol());
  }

  /*
   * test for addNeighbor()
   */
  @Test
  public void testAddNeighbor() {
    MyWorldSpace space = new MyWorldSpace(0, 0, 0, 0, "space");
    MyWorldSpace neighbor = new MyWorldSpace(0, 0, 0, 0, "neighbor");
    space.addNeighbor(neighbor);
    assertEquals(1, space.getNeighbors().size());
  }

  /*
   * test for getNeighbors()
   */
  @Test
  public void testGetNeighbors() {
    MyWorldSpace space = new MyWorldSpace(0, 0, 0, 0, "space");
    MyWorldSpace neighbor = new MyWorldSpace(0, 0, 0, 0, "neighbor");
    space.addNeighbor(neighbor);
    assertEquals("neighbor", space.getNeighbors().get(0).getName());
  }
}
package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import src.world.MyWorld;



/**
 * test for MyWorld.
 *
 */
public class MyWorldTest {
  /*
   * test for getNumRows()
   */
  @Test
  public void testGetNumRows() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotEquals(0, world.getNumRows());
  }

  /*
   * test for getNumCols()
   */
  @Test
  public void testGetNumCols() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotEquals(0, world.getNumCols());
  }

  /*
   * test for getSpaces()
   */
  @Test
  public void testGetSpaces() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotEquals(0, world.getSpaces().size());
  }

  /*
   * test for getCharacter()
   */
  @Test
  public void testGetCharacter() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotEquals(null, world.getCharacter());
  }

  /*
   * test for getItems()
   */
  @Test
  public void testGetItems() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotEquals(0, world.getItems().size());
  }

  /*
   * test for renderWorldImage()
   */
  @Test
  public void testRenderWorldImage() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotEquals(null, world.renderWorldImage());
  }

  /*
   * test for getName()
   */
  @Test
  public void testGetName() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotNull(world.getName());
  }

  /*
   * test for moveCharacter()
   */
  @Test
  public void testMoveCharacter() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotNull(world.moveCharacter());
  }

  /*
   * test for getSpace()
   */
  @Test
  public void testGetSpace() {
    MyWorld world = new MyWorld("res\\mansion.txt");
    assertNotNull(world.getSpace(0));
  }

}
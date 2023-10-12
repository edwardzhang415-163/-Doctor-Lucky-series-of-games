package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import src.world.MyWorldCharacter;




/**
 * test for MyWorldCharacter.
 */

public class MyWorldCharacterTest {

  @Test
  public void testGetMaxIndex() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    assertEquals(10, character.getMaxIndex());
  }

  @Test
  public void testSetMaxIndex() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    character.setMaxIndex(15);
    assertEquals(15, character.getMaxIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetMaxIndexWithInvalidValue() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    character.setMaxIndex(-5); // Should throw an exception
  }

  @Test
  public void testGetHealth() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    assertEquals(100, character.getHealth());
  }

  @Test
  public void testGetName() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    assertEquals("Test Character", character.getName());
  }

  @Test
  public void testGetCurrentRoomIndex() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 5, 10);
    assertEquals(5, character.getCurrentRoomIndex());
  }

  @Test
  public void testSetCurrentRoomIndex() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    character.setCurrentRoomIndex(7);
    assertEquals(7, character.getCurrentRoomIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentRoomIndexWithInvalidValue() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    character.setCurrentRoomIndex(15); // Should throw an exception
  }

  @Test
  public void testMoveSpace() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 0, 10);
    character.moveSpace();
    assertEquals(1, character.getCurrentRoomIndex());
  }

  @Test
  public void testMoveSpaceWithWrapAround() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 10, 10);
    character.moveSpace();
    assertEquals(0, character.getCurrentRoomIndex());
  }

  @Test
  public void testEquals() {
    MyWorldCharacter character1 = new MyWorldCharacter(100, "Test Character", 5, 10);
    MyWorldCharacter character2 = new MyWorldCharacter(100, "Test Character", 5, 10);
    assertEquals(character1, character2);
  }

  @Test
  public void testHashCode() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Test Character", 5, 10);
    assertEquals(character.hashCode(), character.hashCode());
  }
}
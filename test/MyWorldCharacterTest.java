package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import src.model.MyWorldCharacter;




/**
 * test for MyWorldCharacter.
 */

public class MyWorldCharacterTest {


  @Test
  public void testConstructorAndGetters() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Player", 0, 10);
    assertNotNull(character);
    assertEquals(100, character.getHealth());
    assertEquals("Player", character.getName());
    assertEquals(0, character.getCurrentRoomIndex());
    assertEquals(10, character.getMaxIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidMaxIndex() {
    new MyWorldCharacter(100, "Player", 0, -1);
  }

  @Test
  public void testSetMaxIndex() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Player", 0, 10);
    character.setMaxIndex(15);
    assertEquals(15, character.getMaxIndex());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetMaxIndexWithInvalidValue() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Player", 0, 10);
    character.setMaxIndex(-1);
  }

  @Test
  public void testMoveSpaceWithinMaxIndex() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Player", 0, 10);
    int newRoomIndex = character.moveSpace();
    assertEquals(1, newRoomIndex);
    assertEquals(1, character.getCurrentRoomIndex());
  }

  @Test
  public void testMoveSpaceWithMaxIndexBoundary() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Player", 10, 10);
    int newRoomIndex = character.moveSpace();
    assertEquals(0, newRoomIndex);
    assertEquals(0, character.getCurrentRoomIndex());
  }

  @Test
  public void testMoveSpaceWithInvalidMaxIndex() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Player", 10, 5);
    int newRoomIndex = character.moveSpace();
    assertEquals(0, newRoomIndex);
    assertEquals(0, character.getCurrentRoomIndex());
  }

  @Test
  public void testEqualsAndHashCode() {
    MyWorldCharacter character1 = new MyWorldCharacter(100, "Player", 0, 10);
    MyWorldCharacter character2 = new MyWorldCharacter(100, "Player", 0, 10);
    MyWorldCharacter character3 = new MyWorldCharacter(50, "Enemy", 0, 10);

    assertEquals(character1, character2);
    assertNotEquals(character1, character3);
    assertEquals(character1.hashCode(), character2.hashCode());
    assertNotEquals(character1.hashCode(), character3.hashCode());
  }

  @Test
  public void testToString() {
    MyWorldCharacter character = new MyWorldCharacter(100, "Player", 0, 10);
    assertEquals("Target{health=100, name='Player', currentRoomIndex=0}", character.toString());
  }
}
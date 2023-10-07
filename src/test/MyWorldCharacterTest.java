package test;

import org.junit.Test;
import src.world.MyWorldCharacter;

import static org.junit.Assert.*;
/**
 * test for MyWorldCharacter
 */
public class MyWorldCharacterTest {
  /*
   * test for getMaxIndex()
   */
  @Test
  public void testGetMaxIndex() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    assertEquals(0, character.getMaxIndex());
  }

  /*
   * test for setMaxIndex()
   */
  @Test
  public void testSetMaxIndex() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    character.setMaxIndex(0);
    assertEquals(0, character.getMaxIndex());
  }

  /*
   * test for getHealth()
   */
  @Test
  public void testGetHealth() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    assertEquals(0, character.getHealth());
  }

  /*
   * test for getName()
   */
  @Test
  public void testGetName() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    assertEquals("character", character.getName());
  }

  /*
   * test for getCurrentRoomIndex()
   */
  @Test
  public void testGetCurrentRoomIndex() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    assertEquals(0, character.getCurrentRoomIndex());
  }

  /*
   * test for setCurrentRoomIndex()
   */
  @Test
  public void testSetCurrentRoomIndex() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    character.setCurrentRoomIndex(0);
    assertEquals(0, character.getCurrentRoomIndex());
  }

  /*
   * test for equals()
   */
  @Test
  public void testEquals() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    assertEquals(true, character.equals(character));
  }

  /*
   * test for hashCode()
   */
  @Test
  public void testHashCode() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    assertEquals(-45634454, character.hashCode());
  }

  /*
   * test for toString()
   */
  @Test
  public void testToString() {
    MyWorldCharacter character = new MyWorldCharacter(0, "character", 0, 0);
    assertEquals("MyWorldCharacter{"
            + "health=" + 0
            + ", name='" + "character" + '\''
            + ", currentRoomIndex=" + 0
            + '}', character.toString());
  }
}

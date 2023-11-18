package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import src.model.Character;
import src.model.Item;
import src.model.MyWorld;
import src.model.MyWorldItem;
import src.model.MyWorldPet;
import src.model.MyWorldSpace;
import src.model.Space;

/**
 * Tests the MyWorld class.
 */
public class MyWorldPetTest {

  private MyWorldSpace space;
  private MyWorldSpace neighbor;

  /**
   * Sets up the test fixture.
   * Called before every test case method.
   */
  @Before
  public void setUp() {
    space = new MyWorldSpace(0, 0, 1, 1, "Space1");
    neighbor = new MyWorldSpace(0, 2, 1, 3, "Space2");
  }

  @Test
  public void getName_ReturnsName() {
    MyWorldPet pet = new MyWorldPet("Fluffy", space, 5);
    assertEquals("Fluffy", pet.getName());
  }

  @Test
  public void getCurrentSpace_ReturnsCurrentSpace() {
    MyWorldPet pet = new MyWorldPet("Fluffy", space, 5);
    assertEquals(space, pet.getCurrentSpace());
  }

  @Test
  public void setCurrentSpace_ChangesCurrentSpace() {
    MyWorldPet pet = new MyWorldPet("Fluffy", neighbor, 5);
    pet.setCurrentSpace(space);
    assertEquals(space, pet.getCurrentSpace());
  }



  @Test
  public void wandering_WithNoPath_ReturnsCurrentSpace() {
    MyWorldPet pet = new MyWorldPet("Fluffy", space, 2);
    assertEquals("Pet Fluffy moved to Space A", pet.wandering());
  }

  @Test
  public void wandering_WithNoUnvisitedNeighbor_ReturnsPreviousSpace() {
    space.addNeighbor(neighbor);
    MyWorldPet pet = new MyWorldPet("Fluffy", space, 1);
    pet.wandering(); // Move to spaceB
    assertEquals("Pet Fluffy moved to Space1", pet.wandering()); // Move back to spaceA
  }

  @Test
  public void wandering_WithUnvisitedNeighbor_ReturnsNextSpace() {
    space.addNeighbor(neighbor);
    MyWorldPet pet = new MyWorldPet("Fluffy", neighbor, 1);
    assertEquals("Pet Fluffy moved to Space B", pet.wandering()); // Move to spaceB
  }

  @Test
  public void wandering_WhenAllSpacesVisited_ReturnsStartSpace() {
    Space spaceA = new MyWorldSpace(0, 0, 1, 1, "Space A");
    Space spaceB = new MyWorldSpace(0, 0, 1, 1, "Space B");
    spaceA.addNeighbor(spaceB);
    spaceB.addNeighbor(spaceA);

    MyWorldPet pet = new MyWorldPet("Fluffy", spaceA, 3);
    assertEquals("Pet Fluffy moved to SpaceB", pet.wandering());
    assertEquals("Pet Fluffy moved to SpaceA", pet.wandering());
    assertEquals("Pet Fluffy stucked, will traverse again", pet.wandering());
    assertEquals("Pet Fluffy moved to Space B", pet.wandering());
    assertEquals("Pet Fluffy moved to Space A", pet.wandering());
    assertEquals("Pet Fluffy stucked, will traverse again", pet.wandering());
    // all spaces visited
  }
}

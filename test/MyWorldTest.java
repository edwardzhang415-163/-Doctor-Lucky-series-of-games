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
import src.world.Character;
import src.world.Item;
import src.world.MyWorld;
import src.world.MyWorldItem;
import src.world.Space;



/**
 * test for MyWorld.
 *
 */
public class MyWorldTest {
  private MyWorld myWorld;

  @Before
  public void setUp() {
    // Initialize the MyWorld object before each test
    myWorld = new MyWorld("test-world.txt");
  }

  @Test
  public void testGetNumRows() {
    assertEquals(3, myWorld.getNumRows());
  }

  @Test
  public void testGetNumCols() {
    assertEquals(3, myWorld.getNumCols());
  }

  @Test
  public void testGetName() {
    assertEquals("Test World", myWorld.getName());
  }

  @Test
  public void testGetSpaces() {
    List<Space> spaces = myWorld.getSpaces();
    assertNotNull(spaces);
    assertEquals(9, spaces.size()); // Assuming there are 9 spaces in the test-world.txt file
  }

  @Test
  public void testGetCharacter() {
    Character character = myWorld.getCharacter();
    assertNotNull(character);
    assertEquals("Test Character", character.getName());
  }

  @Test
  public void testGetItems() {
    List<Item> items = myWorld.getItems();
    assertNotNull(items);
    assertEquals(3, items.size()); // Assuming there are 3 items in the test-world.txt file
  }

  @Test
  public void testMoveCharacter() {
    String initialSpaceName = myWorld.getSpaces().get(0).getName();
    myWorld.moveCharacter();
    String newSpaceName = myWorld.getSpaces()
            .get(myWorld.getCharacter().getCurrentRoomIndex()).getName();
    assertNotEquals(initialSpaceName, newSpaceName);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomIndexForItems() {
    // Test if an IllegalArgumentException is thrown when an item has an invalid room index
    MyWorld invalidWorld = new MyWorld("invalid-world.txt");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingSpaces() {
    // Test if an IllegalArgumentException is thrown when spaces overlap
    MyWorld invalidWorld = new MyWorld("invalid-overlapping-world.txt");
  }

  @Test
  public void testRenderWorldImage() {
    BufferedImage mapImage = myWorld.renderWorldImage();
    assertNotNull(mapImage);
    // You can add additional assertions related to the generated image if needed
  }

  @Test
  public void testSpaceNeighbors() {
    List<Space> spaces = myWorld.getSpaces();
    for (Space space : spaces) {
      List<Space> neighbors = space.getNeighbors();
      assertNotNull(neighbors);
      for (Space neighbor : neighbors) {
        assertTrue(space.isNeighbor(neighbor));
      }
    }
  }

  @Test
  public void testAddItemToSpace() {
    Space space = myWorld.getSpaces().get(0);
    Item newItem = new MyWorldItem(1, 1, "New Item");
    space.addItem(newItem);
    assertTrue(space.getItems().contains(newItem));
  }

  @Test
  public void testMoveCharacterToInvalidSpace() {
    // Move character to an invalid space index and check if it throws an exception
    myWorld.getCharacter().setCurrentRoomIndex(-1);
    try {
      myWorld.moveCharacter();
      fail("Expected IllegalArgumentException not thrown");
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid room index for character: -1", e.getMessage());
    }
  }
}
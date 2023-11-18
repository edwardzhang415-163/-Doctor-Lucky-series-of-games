package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import src.model.Item;
import src.model.MyWorld;
import src.model.MyWorldItem;
import src.model.MyWorldPet;
import src.model.MyWorldPlayer;
import src.model.MyWorldSpace;
import src.model.Player;
import src.model.Space;

/**
 * Tests the MyWorldPlayer.
 */
public class MyWorldPlayerTest {

  private MyWorldPlayer player;
  private Space space;
  private MyWorld myWorld;

  /**
   * Sets up the test fixture.
   * Called before every test case method.
   */
  @Before
  public void setUp() {
    myWorld = new MyWorld("res\\mansion.txt");
    space = new MyWorldSpace(0, 0, 1, 1, "Space1");
    player = new MyWorldPlayer("Player1", myWorld.getSpaces().get(0), myWorld);
  }

  @Test
  public void testConstructorAndGetters() {
    assertNotNull(player);
    assertEquals("Player1", player.getName());
    assertEquals(space, player.getCurrentSpace());
    assertNotNull(player.getItems());
    assertEquals(0, player.getItems().size());
  }

  @Test
  public void testPickupItems() {
    Item item = new MyWorldItem(0, 0, "Item1");
    space.addItem(item);

    String result = player.pickupItems("Item1");
    assertNotNull(result);
    assertEquals("Player Player1 picked up Item1", result);
    assertEquals(1, player.getItems().size());
    assertEquals("Item1", player.getItems().get(0).getName());
  }

  @Test
  public void testPickupNonExistingItem() {
    String result = player.pickupItems("NonExistingItem");
    assertNotNull(result);
    assertEquals("There is no such item in this space", result);
  }

  @Test
  public void testPickupItemsExceedingLimit() {
    space.addItem(new MyWorldItem(0, 0, "Item1"));
    space.addItem(new MyWorldItem(0, 0, "Item2"));
    space.addItem(new MyWorldItem(0, 0, "Item3"));
    space.addItem(new MyWorldItem(0, 0, "Item4"));
    space.addItem(new MyWorldItem(0, 0, "Item5"));

    String result = player.pickupItems("NewItem");
    assertNotNull(result);
    assertEquals("Player Player1 can't pick up more items", result);
  }

  @Test
  public void testMoveToExistingNeighbor() {
    Space neighbor = new MyWorldSpace(1, 1, 2, 2, "NeighborSpace");
    space.addNeighbor(neighbor);

    String result = player.moveTo("NeighborSpace");
    assertNotNull(result);
    assertEquals("Player Player1 moved to NeighborSpace", result);
    assertEquals(neighbor, player.getCurrentSpace());
  }

  @Test
  public void testMoveToNonExistingNeighbor() {
    String result = player.moveTo("NonExistingNeighbor");
    assertNotNull(result);
    assertEquals("There is no such neighbor", result);
  }

  @Test
  public void testLookAround() {
    Space neighbor1 = new MyWorldSpace(1, 1, 2, 2, "NeighborSpace1");
    Space neighbor2 = new MyWorldSpace(1, 1, 2, 2, "NeighborSpace2");
    space.addNeighbor(neighbor1);
    space.addNeighbor(neighbor2);

    String result = player.lookAround();
    assertNotNull(result);
    assertEquals("Player Player1 is in Space1. You can go to: NeighborSpace2", result);
  }

  @Test
  public void testDoAction() {
    String result = player.doAction();
    assertNotNull(result);
    assertEquals("", result);
  }


  @Test
  public void testToString() {
    String result = player.toString();
    assertNotNull(result);
    assertEquals("player{\nname='Player1'\nitems=\ncurrentSpace=Space1\n}", result);
  }

  @Test
  public void attackTarget_WhenNoWeapon_ReturnsFailedMessage() {
    Player player2 = new MyWorldPlayer("Player2", player.getCurrentSpace().getNeighbors().get(0),
        myWorld);
    myWorld.addPlayer("player2", 1, true);
    // Attacking without having a weapon
    assertEquals("Player Player1 attacked Enemy but failed.", player.attackTarget());
  }

  @Test
  public void attackTarget_WithWeapon_Succeeds() {
    // Adding a weapon to the player
    Item weapon = new MyWorldItem(1, 2, "Sword");
    player.getItems().add(weapon);

    // Attacking with the equipped weapon
    assertEquals("Player Player1 attacked Enemy with Sword and damaged 2", player.attackTarget());
  }

  @Test
  public void attackTarget_WithMultipleWeapons_SucceedsWithStrongest() {


    // Adding multiple weapons to the player
    Item weakWeapon = new MyWorldItem(1, 1, "sword");
    Item strongWeapon = new MyWorldItem(1, 3, "Dagger");
    myWorld.getCharacter().setCurrentRoomIndex(0);
    player.getItems().add(strongWeapon);

    // Attacking with multiple weapons, should use the stronger one
    assertEquals("Player Player1 attacked Enemy with Sword and damaged 3", player.attackTarget());
  }

  @Test
  public void movePet_MoveToValidSpace_SuccessfullyMovesPet() {
    MyWorldPet pet = new MyWorldPet("Pet", space, 3);
    assertEquals("Pet is now in Library", player.movePet("Library"));
  }

  @Test
  public void movePet_MoveToInvalidSpace_ReturnsErrorMessage() {
    MyWorldPet pet = new MyWorldPet("Pet", space, 3);
    assertEquals("There is no such space in this world", player.movePet("Space C"));
  }
}
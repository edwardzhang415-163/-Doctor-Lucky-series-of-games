package src.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * This class represents a pet in the game.
 */
public class MyWorldPet implements Pet {

  private String name;

  private Set<Space> visitedSpaces;

  private Stack<Space> path;

  private Space currentSpace;

  private int maxIndex;

  /**
   * Constructs a pet object.
   *
   * @param name The name of the pet.
   * @param currentSpace The current space of the pet.
   */
  public MyWorldPet(String name, Space currentSpace, int index) {
    this.name = name;
    this.currentSpace = currentSpace;
    this.visitedSpaces = new HashSet<>();
    this.path = new Stack<>();
    visitedSpaces.add(currentSpace);
    path.add(currentSpace);
    this.maxIndex = index;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Space getCurrentSpace() {
    return currentSpace;
  }

  @Override
  public void setCurrentSpace(Space currentSpaceN) {
    this.currentSpace. removePet();
    this.currentSpace = currentSpaceN;
    this.currentSpace.addPet(this);
  }

  @Override
  public void refresh() {
    this.visitedSpaces.clear();
    this.path.clear();
  }

  @Override
  public String wandering() {
    if (path.isEmpty() || visitedSpaces.size() == maxIndex) {
      this.visitedSpaces.clear();
      path.add(currentSpace);
      visitedSpaces.add(currentSpace);
    }
    Space nextSpace = getNextUnvisitedNeighbor();
    if (nextSpace != null) {
      setCurrentSpace(nextSpace);
      return String.format("Pet %s moved to %s", name, nextSpace.getName());
    } else {
      path.pop();
      if (!path.isEmpty()) {
        setCurrentSpace(path.peek());
        return String.format("Pet %s moved to %s", name, path.peek().getName());
      } else {
        return String.format("Pet %s stucked, will traverse again", name);
      }
    }
  }

  private Space getNextUnvisitedNeighbor() {
    Space current = path.peek();
    for (Space neighbor : current.getNeighbors()) {
      if (!visitedSpaces.contains(neighbor)) {
        visitedSpaces.add(neighbor);
        path.push(neighbor);
        return neighbor;
      }
    }
    return null;
  }

  @Override
  public String toString() {
    return String.format("Target's pet %s is in %s", name, currentSpace.getName());
  }
}

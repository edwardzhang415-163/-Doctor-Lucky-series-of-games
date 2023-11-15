package src.model;

public class MyWorldPet implements Pet {

  private String name;

  private int currentRoomIndex;

  public MyWorldPet(String name) {
    this.name = name;
    this.currentRoomIndex = 0;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getCurrentRoomIndex() {
    return currentRoomIndex;
  }

  @Override
  public void setCurrentRoomIndex(int roomIndex) {
    this.currentRoomIndex = roomIndex;
  }

}

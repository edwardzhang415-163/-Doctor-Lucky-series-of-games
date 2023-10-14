package src.world;

public interface Player {
  public String getName();

  public String pickupItems(String itemName);

  public Space getCurrentSpace();

  public String MoveTo(String nighbourName);

  public String lookAround();

  public String doAction();




}

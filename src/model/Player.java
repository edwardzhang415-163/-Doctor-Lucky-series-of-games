package src.model;

import java.util.List;

public interface Player {
  public String getName();

  public String pickupItems(String itemName);

  public Space getCurrentSpace();

  public String moveTo(String nighbourName);

  public String lookAround();

  public List<Item> getItems();

  public String doAction();




}

package Models;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Interfaces.PropertyType;

public class Filter {
  private PropertyType propertyType;
  private String propertyQuad;
  private int minBedroom;
  private int maxBedroom;
  private boolean isFurnished;

  public Filter(PropertyType propertyType, String propertyQuad, int minBedroom, int maxBedroom, boolean isFurnished) {
    this.propertyType = propertyType;
    this.propertyQuad = propertyQuad;
    this.minBedroom = minBedroom;
    this.maxBedroom = maxBedroom;
    this.isFurnished = isFurnished;
  }

  // Not sure if we'll use
  public ArrayList<Property> filterAll(ArrayList<Property> properties)
  {
    return new ArrayList<Property>(
      properties.stream()
      .filter((prop) -> check(prop))
      .collect(Collectors.toList()));
  }

  // ===============================================
  // TODO
  // For Noel to finish (return false if property does not match search criteria)
  // Null or -1 means do not check!
  // ===============================================
  public boolean check(Property property)
  {
    return true;
  }
}

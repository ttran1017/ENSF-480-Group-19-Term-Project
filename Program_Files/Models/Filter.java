package Models;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Interfaces.PropertyQuadrant;
import Interfaces.PropertyStatus;
import Interfaces.PropertyType;

public class Filter {
  private PropertyType propertyType;
  private PropertyQuadrant propertyQuad;
  private Integer minBedroom;
  private Integer maxBedroom;
  private Integer minBathroom;
  private Integer maxBathroom;
  private Boolean isFurnished;

  public Filter(
    PropertyType propertyType, 
    PropertyQuadrant propertyQuad, 
    Integer minBedroom, 
    Integer maxBedroom, 
    Integer minBathroom, 
    Integer maxBathroom, 
    Boolean isFurnished ) 
  {
    this.propertyType = propertyType;
    this.propertyQuad = propertyQuad;
    this.minBedroom = minBedroom;
    this.maxBedroom = maxBedroom;
    this.minBathroom = minBathroom;
    this.maxBathroom = maxBathroom;
    this.isFurnished = isFurnished;
  }

  public ArrayList<Property> filterAll(ArrayList<Property> properties)
  {
    return new ArrayList<Property>(
      properties.stream()
      .filter((prop) -> checkPass(prop))
      .collect(Collectors.toList()));
  }

  public boolean checkPass(Property property)
  {
    if(property.getPropertyStatus() != PropertyStatus.Active)
      return false;
    else if(propertyType != null && property.getPropertyType() != propertyType)
      return false;
    else if(propertyQuad != null && property.getPropertyQuadrant() != propertyQuad)
      return false;
    else if(minBedroom != null && property.getNumBedrooms() < minBedroom)
      return false;
    else if(maxBedroom != null && property.getNumBedrooms() > maxBedroom)
      return false;
    else if(minBathroom != null && property.getNumBathrooms() < minBathroom)
      return false;
    else if(maxBathroom != null && property.getNumBathrooms() > maxBathroom)
      return false;
    else if(isFurnished != null && property.getIsFurnished() != isFurnished)
      return false;
    return true;
  }
}

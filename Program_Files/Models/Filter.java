/**
 * FileName: Filter.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Interfaces.PropertyQuadrant;
import Interfaces.PropertyStatus;
import Interfaces.PropertyType;

/**
 * Handles filtering Properties by
 * checking if the property matches
 * the filter criteria
 */
public class Filter {
  /**
   * Filter Fields
   */
  private PropertyType propertyType;
  private PropertyQuadrant propertyQuad;
  private Integer minBedroom;
  private Integer maxBedroom;
  private Integer minBathroom;
  private Integer maxBathroom;
  private Boolean isFurnished;
  private LocalDate startDate;
  private LocalDate endDate;

  /**
   * Filter Constructor
   * @param propertyType Property Type
   * @param propertyQuad Property Quadrant
   * @param minBedroom Minimum number of bedrooms
   * @param maxBedroom Maximum number of bedrooms
   * @param minBathroom Minimum number of bathrooms
   * @param maxBathroom Maximum number of bathrooms
   * @param isFurnished Whether property is furnished or not
   */
  public Filter(
    PropertyType propertyType,
    PropertyQuadrant propertyQuad,
    Integer minBedroom,
    Integer maxBedroom,
    Integer minBathroom,
    Integer maxBathroom,
    Boolean isFurnished,
    LocalDate startDate,
    LocalDate endDate )
  {
    this.propertyType = propertyType;
    this.propertyQuad = propertyQuad;
    this.minBedroom = minBedroom;
    this.maxBedroom = maxBedroom;
    this.minBathroom = minBathroom;
    this.maxBathroom = maxBathroom;
    this.isFurnished = isFurnished;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  /**
   * Filter ArrayList based on filter criteria
   * @param properties Original array
   * @return Subset of original array
   */
  public ArrayList<Property> filterAll(ArrayList<Property> properties)
  {
    return new ArrayList<Property>(
      properties.stream()
      .filter((prop) -> checkPass(prop))
      .collect(Collectors.toList()));
  }

  // GETTER METHODS - To be commented
  public PropertyType getPropertyType() { return propertyType; }
  public PropertyQuadrant getPropertyQuad() {return propertyQuad; }
  public Integer getMinBedroom() { return minBedroom; }
  public Integer getMaxBedroom() { return maxBedroom; }
  public Integer getMinBathroom() { return minBathroom; }
  public Integer getMaxBathroom() { return maxBathroom; }
  public Boolean getFurnished() { return isFurnished; }
  // Manager
  public LocalDate getStartDate() { return startDate;  }
  public LocalDate getEndDate() { return endDate;  }


  /**
   * Checks if individual property matches filter criteria
   * @param property Property to be checked
   * @return true if matches criteria, false otherwise
   */

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

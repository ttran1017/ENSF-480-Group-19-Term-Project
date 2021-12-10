/**
 * FileName: FilterBuilder.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

package SystemControllers;

import Enums.PropertyQuadrant;
import Enums.PropertyType;
import InteractionControllers.IO;
import Models.*;

public class FilterBuilder {
    /* Properties that can the search can be filtered by */
    private PropertyType propertyType = null;
    private PropertyQuadrant propertyQuad = null;
    private Integer minBedroom = null;
    private Integer maxBedroom = null;
    private Integer minBathroom = null;
    private Integer maxBathroom = null;
    private Boolean isFurnished = null;

    /* Setters */
    public FilterBuilder setPropertyType(PropertyType propType)
    {
      this.propertyType = propType;
      return this;
    }

    public FilterBuilder setPropertyQuad(PropertyQuadrant propQuad)
    {
      this.propertyQuad = propQuad;
      return this;
    }

    public FilterBuilder setMinBedroom(Integer m)
    {
      this.minBedroom = m;
      return this;
    }

    public FilterBuilder setMaxBedroom(Integer m)
    {
      this.maxBedroom = m;
      return this;
    }

    public FilterBuilder setMinBathroom(Integer m)
    {
        this.minBathroom = m;
        return this;
    }

    public FilterBuilder setMaxBathroom(Integer m)
    {
        this.maxBathroom = m;
        return this;
    }

    public FilterBuilder setIsFurnished(Boolean m)
    {
      this.isFurnished = m;
      return this;
    }

    /* Take make filter from FilterBuilder class
    * @returns Filter initialized by FilterBuilder object
    */
    public Filter build(){
      return new Filter(propertyType, propertyQuad, minBedroom, maxBedroom, minBathroom, maxBathroom, isFurnished);
    }

    /* Creates Filter by taking in inputs from the user
    * @returns Filter initialized by user input
    */
    public static Filter buildFilter()
    {
        FilterBuilder newFilter = new FilterBuilder();
        PropertyType pType = (PropertyType)IO.getDropdownInputNoPref("Filter", "Preferred Property Type:", PropertyType.values());
        if(pType != null)
            newFilter.setPropertyType(pType);

        PropertyQuadrant pQuad = (PropertyQuadrant)IO.getDropdownInputNoPref("Filter", "Preferred Property Quadrant:", PropertyQuadrant.values());
        if(pQuad != null)
            newFilter.setPropertyQuad(pQuad);

        Integer minBed = (Integer)IO.getDropdownInputNoPref(
            "Filter",
            "Minimum Number of Bedrooms:",
            new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        if(minBed != null)
            newFilter.setMinBedroom(minBed);

        Integer maxBed = (Integer)IO.getDropdownInputNoPref(
            "Filter",
            "Maximum Number of Bedrooms:",
            new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        if(maxBed != null)
            newFilter.setMaxBedroom(maxBed);

        Integer minBath = (Integer)IO.getDropdownInputNoPref(
                "Filter",
                "Minimum Number of Bathrooms:",
                new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        if(minBath != null)
            newFilter.setMinBathroom(minBath);

        Integer maxBath = (Integer)IO.getDropdownInputNoPref(
                "Filter",
                "Maximum Number of Bedrooms:",
                new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        if(maxBath != null)
            newFilter.setMaxBathroom(maxBath);

        String furnished = (String)IO.getDropdownInputNoPref(
            "Filter",
            "Preference of Furnishment:", new String[]{
            "Furnished",
            "Unfurnished",
            });
        if(furnished != null)
            newFilter.setIsFurnished(furnished.equals("Furnished"));
        IO.outputMessage("Filter Successfully Updated");
        return newFilter.build();
    }

    public static void main(String[] args)
    {
        buildFilter();
    }
}

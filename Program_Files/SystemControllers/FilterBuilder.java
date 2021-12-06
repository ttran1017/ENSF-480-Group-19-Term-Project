package SystemControllers;

import InteractionControllers.Input;
import Interfaces.PropertyQuadrant;
import Interfaces.PropertyType;
import Models.*;

public class FilterBuilder {
    private PropertyType propertyType = null;
    private PropertyQuadrant propertyQuad = null;
    private Integer minBedroom = null;
    private Integer maxBedroom = null;
    private Boolean isFurnished = null;
  
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
  
    public FilterBuilder setIsFurnished(Boolean m) {
      this.isFurnished = m;
      return this;
    }
  
    public Filter build(){
      return new Filter(propertyType, propertyQuad, minBedroom, maxBedroom, isFurnished);
    }

    public static Filter buildFilter()
    {
        FilterBuilder newFilter = new FilterBuilder();
        PropertyType pType = (PropertyType)Input.getDropdownInputNoPref("Filter", "Preferred Property Type:", PropertyType.values());
        if(pType != null)
            newFilter.setPropertyType(pType);

        PropertyQuadrant pQuad = (PropertyQuadrant)Input.getDropdownInputNoPref("Filter", "Preferred Property Quadrant:", PropertyQuadrant.values());
        if(pQuad != null)
            newFilter.setPropertyQuad(pQuad);

        Integer min = (Integer)Input.getDropdownInputNoPref(
            "Filter",
            "Minimum Number of Bedrooms:", 
            new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        if(min != null)
            newFilter.setMinBedroom(min);

        Integer max = (Integer)Input.getDropdownInputNoPref(
            "Filter",
            "Maximum Number of Bedrooms:", 
            new Integer[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        if(max != null)
            newFilter.setMinBedroom(min);

        String furnished = (String)Input.getDropdownInputNoPref(
            "Filter",
            "Preference of Furnishment:", new String[]{
            "Furnished",
            "Unfurnished",
            });
        if(furnished != null)
            newFilter.setIsFurnished(furnished.equals("Furnished"));
        return newFilter.build();
    }

    public static void main(String[] args)
    {
        buildFilter();
    }
}

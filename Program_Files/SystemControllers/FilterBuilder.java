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
  
    public FilterBuilder setPropertyType(PropertyType propType) {
      this.propertyType = propType;
      return this;
    }
  
    public FilterBuilder setPropertyQuad(PropertyQuadrant propQuad) {
      this.propertyQuad = propQuad;
      return this;
    }
  
    public FilterBuilder setMinBedroom(Integer m) {
      this.minBedroom = m;
      return this;
    }
  
    public FilterBuilder setMaxBedroom(Integer m) {
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
        String pType = (String)Input.getDropdownInput(
            "Filter",
            "Preferred Property Type:", new String[]{
            "No Preference",
            "Apartment",
            "AttachedHouse",
            "DetachedHouse",
            "Condo",
            "Townhouse",
            });
        switch(pType){
            case "Apartment":
                newFilter.setPropertyType(PropertyType.Apartment);
            case "AttachedHouse":
                newFilter.setPropertyType(PropertyType.AttachedHouse);
            case "DetachedHouse":
                newFilter.setPropertyType(PropertyType.DetachedHouse);
            case "Condo":
                newFilter.setPropertyType(PropertyType.Condo);
            case "Townhouse":
                newFilter.setPropertyType(PropertyType.Townhouse);
            default:
                break;
        }

        String pQuad = (String)Input.getDropdownInput(
            "Filter",
            "Preferred Property Quadrant:", new String[]{
            "No Preference",
            "NE",
            "NW",
            "SE",
            "SW",
            });
        switch(pQuad){
            case "NE":
                newFilter.setPropertyQuad(PropertyQuadrant.NE);
            case "NW":
                newFilter.setPropertyQuad(PropertyQuadrant.NW);
            case "SE":
                newFilter.setPropertyQuad(PropertyQuadrant.SE);
            case "SW":
                newFilter.setPropertyQuad(PropertyQuadrant.SW);
            default:
                break;
        }
        String min = (String)Input.getDropdownInput(
            "Filter",
            "Minimum Number of Bedrooms:", new String[]{
            "No Preference",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            });
        switch(min){
            case "1":
                newFilter.setMinBedroom(1);
            case "2":
                newFilter.setMinBedroom(2);
            case "3":
                newFilter.setMinBedroom(3);
            case "4":
                newFilter.setMinBedroom(4);
            case "5":
                newFilter.setMinBedroom(5);
            case "6":
                newFilter.setMinBedroom(6);
            case "7":
                newFilter.setMinBedroom(7);
            case "8":
                newFilter.setMinBedroom(8);
            case "9":
                newFilter.setMinBedroom(9);
            case "10":
                newFilter.setMinBedroom(10);
            default:
                break;
        }
        String max = (String)Input.getDropdownInput(
            "Filter",
            "Maximum Number of Bedrooms:", new String[]{
            "No Preference",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            });
        switch(max){
            case "1":
                newFilter.setMaxBedroom(1);
            case "2":
                newFilter.setMaxBedroom(2);
            case "3":
                newFilter.setMaxBedroom(3);
            case "4":
                newFilter.setMaxBedroom(4);
            case "5":
                newFilter.setMaxBedroom(5);
            case "6":
                newFilter.setMaxBedroom(6);
            case "7":
                newFilter.setMaxBedroom(7);
            case "8":
                newFilter.setMaxBedroom(8);
            case "9":
                newFilter.setMaxBedroom(9);
            case "10":
                newFilter.setMaxBedroom(10);
            default:
                break;
        }

        String furnished = (String)Input.getDropdownInput(
            "Filter",
            "Preference of Furnishment:", new String[]{
            "No Preference",
            "Furnished",
            "Unfurnished",
            });
        switch(furnished){
            case("Furnished"):
                newFilter.setIsFurnished(true);
            case("Unfurnished"):
                newFilter.setIsFurnished(false);
            default:
                break;
        }
        return newFilter.build();
    }
}

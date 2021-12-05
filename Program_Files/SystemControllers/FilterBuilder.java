package SystemControllers;
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
}

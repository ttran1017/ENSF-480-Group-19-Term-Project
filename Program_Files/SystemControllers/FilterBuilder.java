package SystemControllers;
import Models.*;

public class FilterBuilder {
    private String propertyType = null;
    private String propertyQuad = null;
    private int minBedroom = -1;
    private int maxBedroom = -1;
    private boolean isFurnished = false;
  
    public FilterBuilder setPropertyType(String propType) {
      this.propertyType = propType;
      return this;
    }
  
    public FilterBuilder setPropertyQuad(String propQuad) {
      this.propertyQuad = propQuad;
      return this;
    }
  
    public FilterBuilder setMinBedroom(int m) {
      this.minBedroom = m;
      return this;
    }
  
    public FilterBuilder setMaxBedroom(int m) {
      this.maxBedroom = m;
      return this;
    }
  
    public FilterBuilder setIsFurnished(boolean m) {
      this.isFurnished = m;
      return this;
    }
  
    public Filter build(){
      return new Filter(propertyType, propertyQuad, minBedroom, maxBedroom, isFurnished);
    }
}

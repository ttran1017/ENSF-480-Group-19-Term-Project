package Models;

import java.util.*;

public class Filter {

    private String propertyType;
    private String propertyQuad;
    private int minBedroom;
    private int maxBedroom;
    private boolean isFurnished;

    public Filter(String propertyType, String propertyQuad, int minBedroom, int maxBedroom, boolean isFurnished) {
      this.propertyType = propertyType;
      this.propertyQuad = propertyQuad;
      this.minBedroom = minBedroom;
      this.maxBedroom = maxBedroom;
      this.isFurnished = isFurnished;
    }
}

class FilterBuilder{
  private String propertyType = "";
  private String propertyQuad = "";
  private int minBedroom = 0;
  private int maxBedroom = 0;
  private boolean isFurnished = true;

  FilterBuilder setPropertyType(String propType) {
    this.propType = propType;
    return this;
  }

  FilterBuilder setPropertyQuad(String propQuad) {
    this.propQuad = propQuad;
    return this;
  }

  FilterBuilder setMinBedroom(int m) {
    this.minBedroom = m;
    return this;
  }

  FilterBuilder setMaxBedroom(int m) {
    this.maxBedroom = m;
    return this;
  }

  FilterBuilder setIsFurnished(boolean m) {
    this.isFurnished = m;
    return this;
  }

  Filter build(){
    return new Filter(propType, propQuad, minBedroom, maxBedroom, isFurnished);
  }
}

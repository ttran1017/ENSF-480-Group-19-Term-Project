package Models;

import java.util.*;

public class Filter {

    private String propertyType;
    private String propertyQuad;
    private int minBedroom;
    private int maxBedroom;
    private boolean isFurnished;
    private int minYear;
    private int maxYear;


    public Filter(String propertyType, String propertyQuad, int minBedroom, int maxBedroom, boolean isFurnished, int minYear, int maxYear) {
      this.propertyType = propertyType;
      this.propertyQuad = propertyQuad;
      this.minBedroom = minBedroom;
      this.maxBedroom = maxBedroom;
      this.isFurnished = isFurnished;
      this.minYear = minYear;
      this.maxYear = maxYear;
    }


    public boolean checkProperty(Property prop) {
        return false;
    }

    public void Filter() {
            }


    public String getPropertyType() {
                return "";
    }


    public String getPropertyQuad() {
                return "";
    }


    public int getMinBedroom() {
                return 0;
    }


    public int getMaxBedroom() {
                return 0;
    }


    public boolean getIsFurnished() {
                return false;
    }


    public void setPropertyType(String propType) {
    }


    public void setPropertyQuad(String quad) {
    }


    public void setMinBedroom(int min) {
    }


    public void setMaxBedroom(int max) {
    }


    public void setIsFurnished(boolean furnished) {
    }

}

class FilterBuilder{
  private String propertyType = "";
  private String propertyQuad = "";
  private int minBedroom = 0;
  private int maxBedroom = 0;
  private boolean isFurnished = true;
  private int minYear = 0;
  private int maxYear = 0;

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

  FilterBuilder setMinYear(int min) {
    this.minYear = min;
    return this;
  }

  FilterBuilder setMaxYear(String m) {
    this.maxYear = m;
    return this;
  }

  Filter build(){
    return new Filter(propType, propQuad, minBedroom, maxBedroom, isFurnished, minYear, maxYear);
  }
}

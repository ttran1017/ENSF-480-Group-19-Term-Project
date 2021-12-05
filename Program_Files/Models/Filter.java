package Models;

import java.util.*;

public class Filter {

    private String propertyType;
    private String propertyQuad;
    private int minBedroom;
    private int maxBedroom;
    private int minBathroom;
    private int maxBathroom;
    private boolean isFurnished;

    public Filter(String propertyType, String propertyQuad, int minBedroom, int maxBedroom, int minBathroom, int maxBathroom, boolean isFurnished, boolean isNotFurnished) {
      this.propertyType = propertyType;
      this.propertyQuad = propertyQuad;
      this.minBedroom = minBedroom;
      this.maxBedroom = maxBedroom;
      this.minBathroom = minBathroom;
      this.maxBathroom = maxBathroom;
      this.isFurnished = isFurnished;
      this.isNotFurnished = isNotFurnished;
    }

    // ARG: Database return object;
    public boolean check(Property prop){
      if(prop.propertyType != this.propertyType && this.propertyType != NULL){
        return 0;
      }

      if(prop.propertyQuadrant != this.propertyQuadrant && this.propertyQuadrant != NULL){
        return 0;
      }

      if(prop.numBedrooms >= this.minBedroom && prop.numBedrooms <= this.maxBedroom){
        return 0;
      }

      if(prop.numBathrooms >= this.minBathroom && prop.numBathrooms <= this.maxBathroom){
        return 0;
      }

      if(this.isFurnished && prop.isNotFurnished){
        return 0;
      }

      if(!this.isFurnished && prop.isFurnished){
        return 0;
      }

      return 1;
    }

    // public ArrayList<int> checkArray(ArrayList<Property> propArray){
    //   Iterator it = propArray.iterator();
    //
    //   while(it.hasNext()){
    //
    //   }
    // }
}

class FilterBuilder{
  private String propertyType = NULL;
  private String propertyQuad = NULL;
  private int minBedroom = 0;
  private int maxBedroom = 1000;
  private int minBathroom = 0;
  private int maxBathroom = 1000;
  private boolean isFurnished = true;
  private boolean isNotFurnished = true;

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

  FilterBuilder setMinBathroom(int m) {
    this.minBathroom = m;
    return this;
  }

  FilterBuilder setMaxBathroom(int m) {
    this.maxBathroom = m;
    return this;
  }

  FilterBuilder setIsFurnished(boolean m) {
    this.isFurnished = m;
    return this;
  }

  FilterBuilder setIsNotFurnished(boolean m) {
    this.isNotFurnished = m;
    return this;
  }

  Filter build(){
    return new Filter(propType, propQuad, minBedroom, maxBedroom, minBathroom, maxBathroom, isFurnished, isNotFurnished);
  }
}

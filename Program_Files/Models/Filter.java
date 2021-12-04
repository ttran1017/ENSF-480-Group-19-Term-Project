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

package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the ChildLocation type in your schema. */
public final class ChildLocation {
  private final Double latitude;
  private final Double longitude;
  public Double getLatitude() {
      return latitude;
  }
  
  public Double getLongitude() {
      return longitude;
  }
  
  private ChildLocation(Double latitude, Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ChildLocation childLocation = (ChildLocation) obj;
      return ObjectsCompat.equals(getLatitude(), childLocation.getLatitude()) &&
              ObjectsCompat.equals(getLongitude(), childLocation.getLongitude());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getLatitude())
      .append(getLongitude())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(latitude,
      longitude);
  }
  public interface BuildStep {
    ChildLocation build();
    BuildStep latitude(Double latitude);
    BuildStep longitude(Double longitude);
  }
  

  public static class Builder implements BuildStep {
    private Double latitude;
    private Double longitude;
    public Builder() {
      
    }
    
    private Builder(Double latitude, Double longitude) {
      this.latitude = latitude;
      this.longitude = longitude;
    }
    
    @Override
     public ChildLocation build() {
        
        return new ChildLocation(
          latitude,
          longitude);
    }
    
    @Override
     public BuildStep latitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
    
    @Override
     public BuildStep longitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(Double latitude, Double longitude) {
      super(latitude, longitude);
      
    }
    
    @Override
     public CopyOfBuilder latitude(Double latitude) {
      return (CopyOfBuilder) super.latitude(latitude);
    }
    
    @Override
     public CopyOfBuilder longitude(Double longitude) {
      return (CopyOfBuilder) super.longitude(longitude);
    }
  }
  
}

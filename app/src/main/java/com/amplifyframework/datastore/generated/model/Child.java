package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Child type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Children", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, provider = "apiKey", operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
}, hasLazySupport = true)
@Index(name = "undefined", fields = {"email"})
public final class Child implements Model {
  public static final ChildPath rootPath = new ChildPath("root", false, null);
  public static final QueryField EMAIL = field("Child", "email");
  public static final QueryField FIRST_NAME = field("Child", "firstName");
  public static final QueryField LAST_NAME = field("Child", "lastName");
  public static final QueryField IN_TRIP = field("Child", "inTrip");
  public static final QueryField LOCATION = field("Child", "location");
  private final @ModelField(targetType="ID", isRequired = true) String email;
  private final @ModelField(targetType="String") String firstName;
  private final @ModelField(targetType="String") String lastName;
  private final @ModelField(targetType="Boolean") Boolean inTrip;
  private final @ModelField(targetType="ChildLocation") ChildLocation location;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return email;
  }
  
  public String getEmail() {
      return email;
  }
  
  public String getFirstName() {
      return firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public Boolean getInTrip() {
      return inTrip;
  }
  
  public ChildLocation getLocation() {
      return location;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Child(String email, String firstName, String lastName, Boolean inTrip, ChildLocation location) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.inTrip = inTrip;
    this.location = location;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Child child = (Child) obj;
      return ObjectsCompat.equals(getEmail(), child.getEmail()) &&
              ObjectsCompat.equals(getFirstName(), child.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), child.getLastName()) &&
              ObjectsCompat.equals(getInTrip(), child.getInTrip()) &&
              ObjectsCompat.equals(getLocation(), child.getLocation()) &&
              ObjectsCompat.equals(getCreatedAt(), child.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), child.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getEmail())
      .append(getFirstName())
      .append(getLastName())
      .append(getInTrip())
      .append(getLocation())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Child {")
      .append("email=" + String.valueOf(getEmail()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("inTrip=" + String.valueOf(getInTrip()) + ", ")
      .append("location=" + String.valueOf(getLocation()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static EmailStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(email,
      firstName,
      lastName,
      inTrip,
      location);
  }
  public interface EmailStep {
    BuildStep email(String email);
  }
  

  public interface BuildStep {
    Child build();
    BuildStep firstName(String firstName);
    BuildStep lastName(String lastName);
    BuildStep inTrip(Boolean inTrip);
    BuildStep location(ChildLocation location);
  }
  

  public static class Builder implements EmailStep, BuildStep {
    private String email;
    private String firstName;
    private String lastName;
    private Boolean inTrip;
    private ChildLocation location;
    public Builder() {
      
    }
    
    private Builder(String email, String firstName, String lastName, Boolean inTrip, ChildLocation location) {
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.inTrip = inTrip;
      this.location = location;
    }
    
    @Override
     public Child build() {
        
        return new Child(
          email,
          firstName,
          lastName,
          inTrip,
          location);
    }
    
    @Override
     public BuildStep email(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }
    
    @Override
     public BuildStep firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    
    @Override
     public BuildStep lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    
    @Override
     public BuildStep inTrip(Boolean inTrip) {
        this.inTrip = inTrip;
        return this;
    }
    
    @Override
     public BuildStep location(ChildLocation location) {
        this.location = location;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String email, String firstName, String lastName, Boolean inTrip, ChildLocation location) {
      super(email, firstName, lastName, inTrip, location);
      Objects.requireNonNull(email);
    }
    
    @Override
     public CopyOfBuilder email(String email) {
      return (CopyOfBuilder) super.email(email);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
    
    @Override
     public CopyOfBuilder inTrip(Boolean inTrip) {
      return (CopyOfBuilder) super.inTrip(inTrip);
    }
    
    @Override
     public CopyOfBuilder location(ChildLocation location) {
      return (CopyOfBuilder) super.location(location);
    }
  }
  

  public static class ChildIdentifier extends ModelIdentifier<Child> {
    private static final long serialVersionUID = 1L;
    public ChildIdentifier(String email) {
      super(email);
    }
  }
  
}

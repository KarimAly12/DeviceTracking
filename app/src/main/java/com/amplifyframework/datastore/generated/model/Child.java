package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.core.model.ModelIdentifier;

import java.util.UUID;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Child type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Children", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, provider = "apiKey", operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
}, hasLazySupport = true)
public final class Child implements Model {
  public static final ChildPath rootPath = new ChildPath("root", false, null);
  public static final QueryField ID = field("Child", "id");
  public static final QueryField CHILD_ID = field("Child", "childID");
  public static final QueryField FIRST_NAME = field("Child", "firstName");
  public static final QueryField LAST_NAME = field("Child", "lastName");
  public static final QueryField IN_TRIP = field("Child", "inTrip");
  public static final QueryField CHILD_LOCATION = field("Child", "childLocation");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String childID;
  private final @ModelField(targetType="String") String firstName;
  private final @ModelField(targetType="String") String lastName;
  private final @ModelField(targetType="Boolean") Boolean inTrip;
  private final @ModelField(targetType="ChildChildLocation") ChildLocation childLocation;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return id;
  }
  
  public String getId() {
      return id;
  }
  
  public String getChildId() {
      return childID;
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
  
  public ChildLocation getChildLocation() {
      return childLocation;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Child(String id, String childID, String firstName, String lastName, Boolean inTrip, ChildLocation childLocation) {
    this.id = id;
    this.childID = childID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.inTrip = inTrip;
    this.childLocation = childLocation;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Child child = (Child) obj;
      return ObjectsCompat.equals(getId(), child.getId()) &&
              ObjectsCompat.equals(getChildId(), child.getChildId()) &&
              ObjectsCompat.equals(getFirstName(), child.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), child.getLastName()) &&
              ObjectsCompat.equals(getInTrip(), child.getInTrip()) &&
              ObjectsCompat.equals(getChildLocation(), child.getChildLocation()) &&
              ObjectsCompat.equals(getCreatedAt(), child.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), child.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getChildId())
      .append(getFirstName())
      .append(getLastName())
      .append(getInTrip())
      .append(getChildLocation())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Child {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("childID=" + String.valueOf(getChildId()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("inTrip=" + String.valueOf(getInTrip()) + ", ")
      .append("childLocation=" + String.valueOf(getChildLocation()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /**
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   */
  public static Child justId(String id) {
    return new Child(
      id,
      null,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      childID,
      firstName,
      lastName,
      inTrip,
      childLocation);
  }
  public interface BuildStep {
    Child build();
    BuildStep id(String id);
    BuildStep childId(String childId);
    BuildStep firstName(String firstName);
    BuildStep lastName(String lastName);
    BuildStep inTrip(Boolean inTrip);
    BuildStep childLocation(ChildLocation childLocation);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String childID;
    private String firstName;
    private String lastName;
    private Boolean inTrip;
    private ChildLocation childLocation;
    public Builder() {
      
    }
    
    private Builder(String id, String childID, String firstName, String lastName, Boolean inTrip, ChildLocation childLocation) {
      this.id = id;
      this.childID = childID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.inTrip = inTrip;
      this.childLocation = childLocation;
    }
    
    @Override
     public Child build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Child(
          id,
          childID,
          firstName,
          lastName,
          inTrip,
          childLocation);
    }
    
    @Override
     public BuildStep childId(String childId) {
        this.childID = childId;
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
     public BuildStep childLocation(ChildLocation childLocation) {
        this.childLocation = childLocation;
        return this;
    }
    
    /**
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String childId, String firstName, String lastName, Boolean inTrip, ChildLocation childLocation) {
      super(id, childID, firstName, lastName, inTrip, childLocation);
      
    }
    
    @Override
     public CopyOfBuilder childId(String childId) {
      return (CopyOfBuilder) super.childId(childId);
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
     public CopyOfBuilder childLocation(ChildLocation childLocation) {
      return (CopyOfBuilder) super.childLocation(childLocation);
    }
  }
  

  public static class ChildIdentifier extends ModelIdentifier<Child> {
    private static final long serialVersionUID = 1L;
    public ChildIdentifier(String id) {
      super(id);
    }
  }
  
}

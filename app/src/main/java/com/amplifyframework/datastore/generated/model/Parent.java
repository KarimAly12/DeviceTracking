package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;
import com.amplifyframework.core.model.ModelList;
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

/** This is an auto generated class representing the Parent type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Parents", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, provider = "apiKey", operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
}, hasLazySupport = true)
@Index(name = "undefined", fields = {"parentEmail"})
public final class Parent implements Model {
  public static final ParentPath rootPath = new ParentPath("root", false, null);
  public static final QueryField PARENT_EMAIL = field("Parent", "parentEmail");
  public static final QueryField FIRST_NAME = field("Parent", "firstName");
  public static final QueryField LAST_NAME = field("Parent", "lastName");
  private final @ModelField(targetType="ID", isRequired = true) String parentEmail;
  private final @ModelField(targetType="String") String firstName;
  private final @ModelField(targetType="String") String lastName;
  private final @ModelField(targetType="ParentChild") @HasMany(associatedWith = "parent", type = ParentChild.class) ModelList<ParentChild> children = null;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  /** @deprecated This API is internal to Amplify and should not be used. */
  @Deprecated
   public String resolveIdentifier() {
    return parentEmail;
  }
  
  public String getParentEmail() {
      return parentEmail;
  }
  
  public String getFirstName() {
      return firstName;
  }
  
  public String getLastName() {
      return lastName;
  }
  
  public ModelList<ParentChild> getChildren() {
      return children;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Parent(String parentEmail, String firstName, String lastName) {
    this.parentEmail = parentEmail;
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Parent parent = (Parent) obj;
      return ObjectsCompat.equals(getParentEmail(), parent.getParentEmail()) &&
              ObjectsCompat.equals(getFirstName(), parent.getFirstName()) &&
              ObjectsCompat.equals(getLastName(), parent.getLastName()) &&
              ObjectsCompat.equals(getCreatedAt(), parent.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), parent.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getParentEmail())
      .append(getFirstName())
      .append(getLastName())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Parent {")
      .append("parentEmail=" + String.valueOf(getParentEmail()) + ", ")
      .append("firstName=" + String.valueOf(getFirstName()) + ", ")
      .append("lastName=" + String.valueOf(getLastName()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static ParentEmailStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(parentEmail,
      firstName,
      lastName);
  }
  public interface ParentEmailStep {
    BuildStep parentEmail(String parentEmail);
  }
  

  public interface BuildStep {
    Parent build();
    BuildStep firstName(String firstName);
    BuildStep lastName(String lastName);
  }
  

  public static class Builder implements ParentEmailStep, BuildStep {
    private String parentEmail;
    private String firstName;
    private String lastName;
    public Builder() {
      
    }
    
    private Builder(String parentEmail, String firstName, String lastName) {
      this.parentEmail = parentEmail;
      this.firstName = firstName;
      this.lastName = lastName;
    }
    
    @Override
     public Parent build() {
        
        return new Parent(
          parentEmail,
          firstName,
          lastName);
    }
    
    @Override
     public BuildStep parentEmail(String parentEmail) {
        Objects.requireNonNull(parentEmail);
        this.parentEmail = parentEmail;
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
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String parentEmail, String firstName, String lastName) {
      super(parentEmail, firstName, lastName);
      Objects.requireNonNull(parentEmail);
    }
    
    @Override
     public CopyOfBuilder parentEmail(String parentEmail) {
      return (CopyOfBuilder) super.parentEmail(parentEmail);
    }
    
    @Override
     public CopyOfBuilder firstName(String firstName) {
      return (CopyOfBuilder) super.firstName(firstName);
    }
    
    @Override
     public CopyOfBuilder lastName(String lastName) {
      return (CopyOfBuilder) super.lastName(lastName);
    }
  }
  

  public static class ParentIdentifier extends ModelIdentifier<Parent> {
    private static final long serialVersionUID = 1L;
    public ParentIdentifier(String parentEmail) {
      super(parentEmail);
    }
  }
  
}

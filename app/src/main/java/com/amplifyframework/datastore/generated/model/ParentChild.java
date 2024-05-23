package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.ModelReference;
import com.amplifyframework.core.model.LoadedModelReferenceImpl;
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

/** This is an auto generated class representing the ParentChild type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "ParentChildren", type = Model.Type.USER, version = 1, authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, provider = "apiKey", operations = { ModelOperation.CREATE, ModelOperation.UPDATE, ModelOperation.DELETE, ModelOperation.READ })
}, hasLazySupport = true)
public final class ParentChild implements Model {
  public static final ParentChildPath rootPath = new ParentChildPath("root", false, null);
  public static final QueryField ID = field("ParentChild", "id");
  public static final QueryField CHILD = field("ParentChild", "childEmail");
  public static final QueryField PARENT = field("ParentChild", "parentEmail");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="Child") @BelongsTo(targetName = "childEmail", targetNames = {"childEmail"}, type = Child.class) ModelReference<Child> child;
  private final @ModelField(targetType="Parent") @BelongsTo(targetName = "parentEmail", targetNames = {"parentEmail"}, type = Parent.class) ModelReference<Parent> parent;
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
  
  public ModelReference<Child> getChild() {
      return child;
  }
  
  public ModelReference<Parent> getParent() {
      return parent;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private ParentChild(String id, ModelReference<Child> child, ModelReference<Parent> parent) {
    this.id = id;
    this.child = child;
    this.parent = parent;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ParentChild parentChild = (ParentChild) obj;
      return ObjectsCompat.equals(getId(), parentChild.getId()) &&
              ObjectsCompat.equals(getChild(), parentChild.getChild()) &&
              ObjectsCompat.equals(getParent(), parentChild.getParent()) &&
              ObjectsCompat.equals(getCreatedAt(), parentChild.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), parentChild.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getChild())
      .append(getParent())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("ParentChild {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("child=" + String.valueOf(getChild()) + ", ")
      .append("parent=" + String.valueOf(getParent()) + ", ")
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
  public static ParentChild justId(String id) {
    return new ParentChild(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      child,
      parent);
  }
  public interface BuildStep {
    ParentChild build();
    BuildStep id(String id);
    BuildStep child(Child child);
    BuildStep parent(Parent parent);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private ModelReference<Child> child;
    private ModelReference<Parent> parent;
    public Builder() {
      
    }
    
    private Builder(String id, ModelReference<Child> child, ModelReference<Parent> parent) {
      this.id = id;
      this.child = child;
      this.parent = parent;
    }
    
    @Override
     public ParentChild build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new ParentChild(
          id,
          child,
          parent);
    }
    
    @Override
     public BuildStep child(Child child) {
        this.child = new LoadedModelReferenceImpl<>(child);
        return this;
    }
    
    @Override
     public BuildStep parent(Parent parent) {
        this.parent = new LoadedModelReferenceImpl<>(parent);
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
    private CopyOfBuilder(String id, ModelReference<Child> child, ModelReference<Parent> parent) {
      super(id, child, parent);
      
    }
    
    @Override
     public CopyOfBuilder child(Child child) {
      return (CopyOfBuilder) super.child(child);
    }
    
    @Override
     public CopyOfBuilder parent(Parent parent) {
      return (CopyOfBuilder) super.parent(parent);
    }
  }
  

  public static class ParentChildIdentifier extends ModelIdentifier<ParentChild> {
    private static final long serialVersionUID = 1L;
    public ParentChildIdentifier(String id) {
      super(id);
    }
  }
  
}

package com.amplifyframework.datastore.generated.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.core.model.ModelPath;
import com.amplifyframework.core.model.PropertyPath;

/** This is an auto generated class representing the ModelPath for the ParentChild type in your schema. */
public final class ParentChildPath extends ModelPath<ParentChild> {
  private ChildPath child;
  private ParentPath parent;
  ParentChildPath(@NonNull String name, @NonNull Boolean isCollection, @Nullable PropertyPath parent) {
    super(name, isCollection, parent, ParentChild.class);
  }
  
  public synchronized ChildPath getChild() {
    if (child == null) {
      child = new ChildPath("child", false, this);
    }
    return child;
  }
  
  public synchronized ParentPath getParent() {
    if (parent == null) {
      parent = new ParentPath("parent", false, this);
    }
    return parent;
  }
}

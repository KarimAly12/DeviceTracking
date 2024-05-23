package com.amplifyframework.datastore.generated.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amplifyframework.core.model.ModelPath;
import com.amplifyframework.core.model.PropertyPath;

/** This is an auto generated class representing the ModelPath for the Child type in your schema. */
public final class ChildPath extends ModelPath<Child> {
  private ParentChildPath parents;
  ChildPath(@NonNull String name, @NonNull Boolean isCollection, @Nullable PropertyPath parent) {
    super(name, isCollection, parent, Child.class);
  }
  
  public synchronized ParentChildPath getParents() {
    if (parents == null) {
      parents = new ParentChildPath("parents", true, this);
    }
    return parents;
  }
}

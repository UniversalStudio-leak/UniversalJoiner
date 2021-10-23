package ru.universalstudio.universaljoiner.gui;

import java.util.function.Consumer;

public class InventoryListener<T>
{
  private Class<T> type;
  private Consumer<T> consumer;
  
  public InventoryListener(Class<T> paramClass, Consumer<T> paramConsumer) {
    this.type = paramClass;
    this.consumer = paramConsumer;
  }
  public void accept(T paramT) {
    this.consumer.accept(paramT);
  } public Class<T> getType() {
    return this.type;
  }
}

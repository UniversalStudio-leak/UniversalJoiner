package ru.universalstudio.universaljoiner.gui;

import java.util.UUID;
import java.util.function.Consumer;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;





public class ClickableItem
{
  private ItemStack item;
  private Consumer<InventoryClickEvent> action;
  private boolean visible = true;
  private Sound sound;
  private UUID uuid = UUID.randomUUID();
  
  public static ClickableItem empty(Material paramMaterial) {
    return empty(new ItemStack(paramMaterial));
  }
  
  public static ClickableItem empty(ItemStack paramItemStack) {
    return of(paramItemStack, (Consumer<InventoryClickEvent>)null);
  }
  
  public static ClickableItem of(Material paramMaterial, Consumer<InventoryClickEvent> paramConsumer) {
    return new ClickableItem(new ItemStack(paramMaterial), paramConsumer);
  }
  
  public static ClickableItem of(ItemStack paramItemStack, Consumer<InventoryClickEvent> paramConsumer) {
    return new ClickableItem(paramItemStack, paramConsumer);
  }
  
  private ClickableItem(ItemStack paramItemStack, Consumer<InventoryClickEvent> paramConsumer) {
    this.action = (paramConsumer != null) ? paramConsumer : ClickableItem::lambda$new$0;
    
    ItemNBTWrapper itemNBTWrapper = new ItemNBTWrapper(paramItemStack);
    itemNBTWrapper.setNBT("clickableitem", this.uuid.toString());
    
    this.item = itemNBTWrapper.getItem();
  }
  
  private static void lambda$new$0(InventoryClickEvent paramInventoryClickEvent) {}
  
  public ItemStack getItem() {
    return this.item;
  }
  
  public void setAction(Consumer<InventoryClickEvent> paramConsumer) {
    this.action = paramConsumer;
  }
  
  public Consumer<InventoryClickEvent> getAction() {
    return this.action;
  }

  
  public ClickableItem sound(Sound paramSound) {
    this.sound = paramSound;
    
    return this;
  }
  
  public void playSound(Player paramPlayer) {
    if (this.sound == null)
      return; 
    paramPlayer.playSound(paramPlayer.getLocation(), this.sound, 10.0F, 1.0F);
  }

  
  public boolean isVisible() {
    return this.visible;
  }
  
  public void setVisible(boolean paramBoolean) {
    this.visible = paramBoolean;
  }


  
  public boolean equalsItemStack(ItemStack paramItemStack) {
    ItemNBTWrapper itemNBTWrapper = new ItemNBTWrapper(paramItemStack);
    
    if (!itemNBTWrapper.hasKey("clickableitem")) return false;
    
    UUID uUID = UUID.fromString(itemNBTWrapper.getValue("clickableitem"));
    
    return uUID.equals(this.uuid);
  }
}

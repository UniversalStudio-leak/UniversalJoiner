package ru.universalstudio.universaljoiner.gui;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public class ItemNBTWrapper
{
  private String version;
  private Object nbtTagCompound;
  private Object itemNMS;
  private Class<?> craftItemStack;
  private ItemStack item;
  
  public ItemNBTWrapper(ItemStack paramItemStack) {
    this.item = paramItemStack;
    
    try {
      this.version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
      
      this.craftItemStack = getCraftItemStack();
      this.itemNMS = this.craftItemStack.getMethod("asNMSCopy", new Class[] { ItemStack.class }).invoke(null, new Object[] { paramItemStack });
      
      boolean bool = ((Boolean)this.itemNMS.getClass().getMethod("hasTag", new Class[0]).invoke(this.itemNMS, new Object[0])).booleanValue();
      Object object = this.itemNMS.getClass().getMethod("getTag", new Class[0]).invoke(this.itemNMS, new Object[0]);
      
      this.nbtTagCompound = bool ? object : getNBTTagCompound().newInstance();
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public void setNBT(String paramString1, String paramString2) {
    try {
      this.nbtTagCompound.getClass().getMethod("setString", new Class[] { String.class, String.class }).invoke(this.nbtTagCompound, new Object[] { paramString1, paramString2 });
      this.itemNMS.getClass().getMethod("setTag", new Class[] { getNBTTagCompound() }).invoke(this.itemNMS, new Object[] { this.nbtTagCompound });
      
      Object object = this.craftItemStack.getMethod("asBukkitCopy", new Class[] { Class.forName("net.minecraft.server." + this.version + ".ItemStack") }).invoke(this.craftItemStack, new Object[] { this.itemNMS });
      ItemMeta itemMeta = (ItemMeta)object.getClass().getMethod("getItemMeta", new Class[0]).invoke(object, new Object[0]);
      
      this.item.setItemMeta(itemMeta);
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  public boolean hasKey(String paramString) {
    try {
      String str = (String)this.nbtTagCompound.getClass().getMethod("getString", new Class[] { String.class }).invoke(this.nbtTagCompound, new Object[] { paramString });
      
      return (str != null && !str.isEmpty());
    }
    catch (Exception exception) {
      exception.printStackTrace();
      
      return false;
    } 
  }
  public String getValue(String paramString) {
    try {
      return (String)this.nbtTagCompound.getClass().getMethod("getString", new Class[] { String.class }).invoke(this.nbtTagCompound, new Object[] { paramString });

    
    }
    catch (Exception exception) {
      exception.printStackTrace();
      
      return null;
    } 
  }
  public Class<?> getCraftItemStack() {
    try {
      return Class.forName("org.bukkit.craftbukkit." + this.version + ".inventory.CraftItemStack");
    }
    catch (Exception exception) {
      exception.printStackTrace();
      
      return null;
    } 
  }
  public Class<?> getNBTTagCompound() {
    try {
      return Class.forName("net.minecraft.server." + this.version + ".NBTTagCompound");
    }
    catch (Exception exception) {
      exception.printStackTrace();
      
      return null;
    } 
  }
  public ItemStack getItem() {
    return this.item;
  }
}

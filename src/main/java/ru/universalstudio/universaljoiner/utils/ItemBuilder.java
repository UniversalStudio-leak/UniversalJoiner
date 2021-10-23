package ru.universalstudio.universaljoiner.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;



public class ItemBuilder
{
  private ItemStack item;
  
  public static ItemBuilder loadItemBuilder(FileConfiguration paramFileConfiguration, String paramString) {
    if (paramFileConfiguration.getString(paramString) == null) return of(Material.AIR);
    
    Material material = Material.valueOf(paramFileConfiguration.getString(paramString + ".type").toUpperCase());
    ItemBuilder itemBuilder = new ItemBuilder(material);
    
    String str = paramFileConfiguration.getString(paramString + ".title");
    List<String> list = paramFileConfiguration.getStringList(paramString + ".lore");
    
    itemBuilder.setDurability((short)paramFileConfiguration.getInt(paramString + ".data"));
    
    if (str != null) {
      itemBuilder.setDisplayName(str);
    }
    if (list != null) {
      itemBuilder.setLore(list);
    }
    itemBuilder.setAmount((paramFileConfiguration.getInt(paramString + ".amount") > 0) ? paramFileConfiguration.getInt(paramString + ".amount") : 1);
    
    if (paramFileConfiguration.getString(paramString + ".enchants") != null) {
      for (String str1 : paramFileConfiguration.getStringList(paramString + ".enchants")) {
        String[] arrayOfString = str1.split(":");
        itemBuilder.enchant(Enchantment.getByName(arrayOfString[0].toUpperCase()), Integer.valueOf(arrayOfString[1]).intValue());
      } 
    }

    
    for (String str1 : paramFileConfiguration.getStringList(paramString + ".flags")) {
      itemBuilder.flag(ItemFlag.valueOf(str1.toUpperCase()));
    }
    
    if (paramFileConfiguration.getString(paramString + ".potion-color") != null) {
      try {
        Color color = (Color)Color.class.getField(paramFileConfiguration.getString(paramString + ".potion-color").toUpperCase()).get(null);
        
        itemBuilder.setPotionColor(color);
      }
      catch (Exception exception) {
        exception.printStackTrace();
      } 
    }
    
    if (paramFileConfiguration.getStringList(paramString + ".potion-effects") != null)
    {
      for (String str1 : paramFileConfiguration.getStringList(paramString + ".potion-effects")) {
        
        PotionEffectType potionEffectType = PotionEffectType.getByName(str1.split(":")[0].toUpperCase());
        int i = Integer.parseInt(str1.split(":")[1]) - 1;
        int j = Integer.parseInt(str1.split(":")[2]) * 20;
        
        itemBuilder.addPotionEffect(new PotionEffect(potionEffectType, j, i));
      } 
    }
    
    if (paramFileConfiguration.getString(paramString + ".texture") != null) {
      itemBuilder.setSkullTexture(paramFileConfiguration.getString(paramString + ".texture"));
    }
    return itemBuilder;
  }


  
  public static ItemBuilder of(ItemStack paramItemStack) {
    return new ItemBuilder(paramItemStack);
  }
  
  public static ItemBuilder of(Material paramMaterial) {
    return new ItemBuilder(paramMaterial);
  }
  
  public ItemBuilder(Material paramMaterial) {
    this.item = new ItemStack(paramMaterial);
  }
  
  public ItemBuilder(ItemStack paramItemStack) {
    this.item = paramItemStack;
  }
  
  public ItemBuilder setAmount(int paramInt) {
    this.item.setAmount(paramInt);
    return this;
  }
  
  public ItemBuilder setDurability(short paramShort) {
    this.item.setDurability(paramShort);
    return this;
  }
  
  public ItemBuilder enchant(Enchantment paramEnchantment, int paramInt) {
    ItemMeta itemMeta = this.item.getItemMeta();
    itemMeta.addEnchant(paramEnchantment, paramInt, true);
    this.item.setItemMeta(itemMeta);
    return this;
  }
  
  public ItemBuilder enchantall(int paramInt) {
    for (Enchantment enchantment : Enchantment.values()) {
      enchant(enchantment, paramInt);
    }
    return this;
  }
  
  public ItemBuilder flags(List<String> paramList) {
    if (paramList == null) return this;
    
    for (String str : paramList) {
      flag(ItemFlag.valueOf(str.toUpperCase()));
    }
    return this;
  }
  
  public ItemBuilder flag(ItemFlag paramItemFlag) {
    ItemMeta itemMeta = this.item.getItemMeta();
    itemMeta.addItemFlags(new ItemFlag[] { paramItemFlag });
    this.item.setItemMeta(itemMeta);
    return this;
  }
  
  public ItemBuilder flagall() {
    for (ItemFlag itemFlag : ItemFlag.values()) {
      flag(itemFlag);
    }
    return this;
  }
  
  public ItemBuilder setDisplayName(String paramString) {
    if (paramString == null) return this;
    
    ItemMeta itemMeta = this.item.getItemMeta();
    itemMeta.setDisplayName(color(paramString));
    this.item.setItemMeta(itemMeta);
    return this;
  }

  
  public ItemBuilder replaceDisplayName(String paramString1, String paramString2) {
    ItemMeta itemMeta = this.item.getItemMeta();
    if (!itemMeta.hasDisplayName()) return this;
    
    itemMeta.setDisplayName(color(itemMeta.getDisplayName().replace(paramString1, paramString2)));
    this.item.setItemMeta(itemMeta);
    
    return this;
  }
  
  public ItemBuilder setLore(List<String> paramList) {
    if (paramList == null) return this;
    
    ItemMeta itemMeta = this.item.getItemMeta();
    itemMeta.setLore(color(paramList));
    this.item.setItemMeta(itemMeta);
    return this;
  }

  
  public ItemBuilder replaceLore(String paramString1, String paramString2) {
    // Byte code:
    //   0: goto -> 5
    //   3: nop
    //   4: athrow
    //   5: aload_0
    //   6: getfield item : Lorg/bukkit/inventory/ItemStack;
    //   9: invokevirtual getItemMeta : ()Lorg/bukkit/inventory/meta/ItemMeta;
    //   12: astore_3
    //   13: aload_3
    //   14: invokevirtual hasLore : ()Z
    //   17: ifne -> 27
    //   20: aload_0
    //   21: areturn
    //   22: nop
    //   23: nop
    //   24: nop
    //   25: nop
    //   26: athrow
    //   27: aload_3
    //   28: invokevirtual getLore : ()Ljava/util/List;
    //   31: invokevirtual stream : ()Ljava/util/stream/Stream;
    //   34: aload_1
    //   35: aload_2
    //   36: <illegal opcode> apply : (Ljava/lang/String;Ljava/lang/String;)Ljava/util/function/Function;
    //   41: invokevirtual map : (Ljava/util/function/Function;)Ljava/util/stream/Stream;
    //   44: invokestatic toList : ()Ljava/util/stream/Collector;
    //   47: invokevirtual collect : (Ljava/util/stream/Collector;)Ljava/lang/Object;
    //   50: checkcast java/util/List
    //   53: astore #4
    //   55: aload_3
    //   56: aload #4
    //   58: invokevirtual setLore : (Ljava/util/List;)V
    //   61: aload_0
    //   62: getfield item : Lorg/bukkit/inventory/ItemStack;
    //   65: aload_3
    //   66: invokevirtual setItemMeta : (Lorg/bukkit/inventory/meta/ItemMeta;)Z
    //   69: pop
    //   70: aload_0
    //   71: areturn
    //   72: nop
    //   73: nop
    //   74: nop
    //   75: nop
    //   76: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #185	-> 5
    //   #186	-> 13
    //   #188	-> 27
    //   #189	-> 31
    //   #190	-> 41
    //   #191	-> 44
    //   #193	-> 55
    //   #194	-> 61
    //   #196	-> 70
  }

  
  private static String lambda$replaceLore$0(String paramString1, String paramString2, String paramString3) {
    return paramString3.replace(paramString1, paramString2);
  }






  
  public ItemBuilder replace(String paramString1, String paramString2) {
    replaceDisplayName(paramString1, paramString2);
    replaceLore(paramString1, paramString2);
    
    return this;
  }
  public ItemBuilder addLore(String paramString) {
    List<String> list;
    ItemMeta itemMeta = this.item.getItemMeta();


    
    if (itemMeta.hasLore()) {
      list = itemMeta.getLore();
    } else {
      list = new ArrayList();
    } 
    list.add(color(paramString));
    itemMeta.setLore(list);
    this.item.setItemMeta(itemMeta);
    
    return this;
  }
  
  public ItemBuilder removeLore(int paramInt) {
    ItemMeta itemMeta = this.item.getItemMeta();


    
    if (!itemMeta.hasLore())
      return this; 
    List<String> list = itemMeta.getLore();
    
    if (paramInt > list.size()) {
      return this;
    }
    list.remove(paramInt);
    itemMeta.setLore(color(list));
    this.item.setItemMeta(itemMeta);
    
    return this;
  }
  
  public ItemBuilder setPotionColor(Color paramColor) {
    if (!(this.item.getItemMeta() instanceof PotionMeta)) return this;
    
    PotionMeta potionMeta = (PotionMeta)this.item.getItemMeta();
    potionMeta.setColor(paramColor);
    this.item.setItemMeta((ItemMeta)potionMeta);
    
    return this;
  }
  
  public ItemBuilder addPotionEffect(PotionEffect paramPotionEffect) {
    if (!(this.item.getItemMeta() instanceof PotionMeta)) return this;
    
    PotionMeta potionMeta = (PotionMeta)this.item.getItemMeta();
    potionMeta.addCustomEffect(paramPotionEffect, true);
    this.item.setItemMeta((ItemMeta)potionMeta);
    
    return this;
  }
  
  public ItemBuilder setSkullTexture(String paramString) {
    if (!(this.item.getItemMeta() instanceof SkullMeta)) return this;
    
    SkullMeta skullMeta = (SkullMeta)this.item.getItemMeta();
    GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
    
    gameProfile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"http://textures.minecraft.net/texture/" + paramString + "\"}}}")));

    
    ReflectionUtils.setField(skullMeta, "profile", gameProfile);
    
    this.item.setItemMeta((ItemMeta)skullMeta);
    
    return this;
  }
  
  public ItemStack build() {
    return this.item;
  }
  
  private String color(String paramString) {
    return ChatColor.translateAlternateColorCodes('&', paramString);
  }
  
  private List<String> color(List<String> paramList) {
    return (List<String>)paramList.stream().map(this::lambda$color$1).collect(Collectors.toList()); } private String lambda$color$1(String paramString) { return color(paramString); }

}

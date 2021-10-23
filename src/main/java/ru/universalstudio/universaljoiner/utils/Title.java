package ru.universalstudio.universaljoiner.utils;

import java.lang.reflect.Constructor;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Title
{
  public static void sendTitle(Player paramPlayer, String paramString) {
    sendTitle(paramPlayer, paramString, 15, 60, 15);
  }
  
  public static void sendTitle(Player paramPlayer, String paramString, int paramInt1, int paramInt2, int paramInt3) {
    paramString = ChatColor.translateAlternateColorCodes('&', paramString);
    String[] arrayOfString = paramString.split("%nl%");
    
    try {
      String str = arrayOfString[0];
      
      Object object1 = ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("TIMES").get(null);
      Object object2 = ((Class)Objects.requireNonNull((T)getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + str + "\"}" });
      Constructor<Object> constructor = ((Class)Objects.<Class<?>>requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(new Class[] { ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent"), int.class, int.class, int.class });
      Object object = constructor.newInstance(new Object[] { object1, object2, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
      sendPacket(paramPlayer, object);
      
      object1 = ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("TITLE").get(null);
      object2 = ((Class)Objects.requireNonNull((T)getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + str + "\"}" });
      constructor = ((Class<Object>)Objects.<Class<?>>requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(new Class[] { ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent") });
      object = constructor.newInstance(new Object[] { object1, object2 });
      sendPacket(paramPlayer, object);
      
      if (arrayOfString.length == 2) {
        String str1 = arrayOfString[1];
        
        Object object3 = ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("TIMES").get(null);
        Object object4 = ((Class)Objects.requireNonNull((T)getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + str1 + "\"}" });
        Constructor<Object> constructor1 = ((Class)Objects.<Class<?>>requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(new Class[] { ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent"), int.class, int.class, int.class });
        Object object5 = constructor1.newInstance(new Object[] { object3, object4, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
        sendPacket(paramPlayer, object5);
        
        object3 = ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0].getField("SUBTITLE").get(null);
        object4 = ((Class)Objects.requireNonNull((T)getNMS("IChatBaseComponent"))).getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + str1 + "\"}" });
        constructor1 = ((Class<Object>)Objects.<Class<?>>requireNonNull(getNMS("PacketPlayOutTitle"))).getConstructor(new Class[] { ((Class)Objects.requireNonNull((T)getNMS("PacketPlayOutTitle"))).getDeclaredClasses()[0], getNMS("IChatBaseComponent"), int.class, int.class, int.class });
        object5 = constructor1.newInstance(new Object[] { object3, object4, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) });
        sendPacket(paramPlayer, object5);
      }
    
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  private static void sendPacket(Player paramPlayer, Object paramObject) {
    try {
      Object object1 = paramPlayer.getClass().getMethod("getHandle", new Class[0]).invoke(paramPlayer, new Object[0]);
      Object object2 = object1.getClass().getField("playerConnection").get(object1);
      object2.getClass().getMethod("sendPacket", new Class[] { getNMS("Packet") }).invoke(object2, new Object[] { paramObject });
    }
    catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
  
  private static Class<?> getNMS(String paramString) {
    String str = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    
    try {
      return Class.forName("net.minecraft.server." + str + "." + paramString);
    }
    catch (Exception exception) {
      exception.printStackTrace();

      
      return null;
    } 
  }
}

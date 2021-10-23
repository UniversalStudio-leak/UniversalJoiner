package ru.universalstudio.universaljoiner.utils.actionbar;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Actionbar
{
  private static String version;
  
  static {
    String str = Bukkit.getServer().getClass().getPackage().getName();
    version = str.substring(str.lastIndexOf('.') + 1);
  }
  
  public static void sendActionbar(Player paramPlayer, String paramString) {
    paramString = ChatColor.translateAlternateColorCodes('&', paramString);
    
    try {
      if (version.equals("v1_16_R1") || version.equals("v1_16_R2") || version.equals("v1_16_R3")) {
        new PreAction(paramPlayer, paramString);
      }
      else if (version.equals("v1_12_R1") || version.startsWith("v1_13") || version.startsWith("v1_14_") || version.startsWith("v1_15_")) {
        new LegacyPreAction(paramPlayer, paramString);
      }
      else {
        
        Class<?> clazz1 = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
        Object object = clazz1.cast(paramPlayer);
        Class<?> clazz2 = Class.forName("net.minecraft.server." + version + ".PacketPlayOutChat");
        Class<?> clazz3 = Class.forName("net.minecraft.server." + version + ".Packet");
        Class<?> clazz4 = Class.forName("net.minecraft.server." + version + ".ChatComponentText");
        Class<?> clazz5 = Class.forName("net.minecraft.server." + version + ".IChatBaseComponent");
        
        if (!version.equalsIgnoreCase("v1_8_R1") && !version.contains("v1_7_")) {
          
          Object object1 = clazz4.getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramString });
          Object object2 = clazz2.getConstructor(new Class[] { clazz5, byte.class }).newInstance(new Object[] { object1, Byte.valueOf((byte)2) });
          Method method1 = clazz1.getDeclaredMethod("getHandle", new Class[0]);
          Object object3 = method1.invoke(object, new Object[0]);
          Field field = object3.getClass().getDeclaredField("playerConnection");
          Object object4 = field.get(object3);
          Method method2 = object4.getClass().getDeclaredMethod("sendPacket", new Class[] { clazz3 });
          method2.invoke(object4, new Object[] { object2 });
        }
        else {
          
          Method method1 = clazz4.getDeclaredMethod("a", new Class[] { String.class });
          Object object1 = clazz5.cast(method1.invoke(clazz4, new Object[] { "{\"text\": \"" + paramString + "\"}" }));
          Object object2 = clazz2.getConstructor(new Class[] { clazz5, byte.class }).newInstance(new Object[] { object1, Byte.valueOf((byte)2) });
          Method method2 = clazz1.getDeclaredMethod("getHandle", new Class[0]);
          Object object3 = method2.invoke(object, new Object[0]);
          Field field = object3.getClass().getDeclaredField("playerConnection");
          Object object4 = field.get(object3);
          Method method3 = object4.getClass().getDeclaredMethod("sendPacket", new Class[] { clazz3 });
          method3.invoke(object4, new Object[] { object2 });
        } 
      } 
    } catch (Exception exception) {
      exception.printStackTrace();
    } 
  }
}

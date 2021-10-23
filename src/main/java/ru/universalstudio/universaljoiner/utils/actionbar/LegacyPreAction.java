package ru.universalstudio.universaljoiner.utils.actionbar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LegacyPreAction
{
  private final String packageVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",")
    .split(",")[3];

  
  public LegacyPreAction(Player paramPlayer, String paramString) throws ClassNotFoundException {
    try {
      Object object1 = getNMSClass("ChatComponentText").getConstructor(new Class[] { String.class }).newInstance(new Object[] { paramString });
      Object object2 = getNMSClass("ChatMessageType").getField("GAME_INFO").get(null);

      
      Object object3 = getNMSClass("PacketPlayOutChat").getConstructor(new Class[] { getNMSClass("IChatBaseComponent"), getNMSClass("ChatMessageType") }).newInstance(new Object[] { object1, object2 });
      Object object4 = paramPlayer.getClass().getMethod("getHandle", new Class[0]).invoke(paramPlayer, new Object[0]);
      Object object5 = object4.getClass().getField("playerConnection").get(object4);
      object5.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet")
          }).invoke(object5, new Object[] { object3 });
    } catch (InstantiationException|IllegalAccessException|java.lang.reflect.InvocationTargetException|NoSuchMethodException|NoSuchFieldException instantiationException) {
      
      instantiationException.printStackTrace();
    } 
  }
  
  private Class<?> getNMSClass(String paramString) {
    try {
      return Class.forName("net.minecraft.server." + this.packageVersion + "." + paramString);
    } catch (ClassNotFoundException classNotFoundException) {
      classNotFoundException.printStackTrace();
      return null;
    } 
  }
}

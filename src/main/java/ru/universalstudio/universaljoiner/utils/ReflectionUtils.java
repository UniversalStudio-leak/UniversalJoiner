package ru.universalstudio.universaljoiner.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;



public final class ReflectionUtils
{
  public static Constructor<?> getConstructor(Class<?> paramClass, Class<?>... paramVarArgs) throws NoSuchMethodException {
    Class[] arrayOfClass = DataType.getPrimitive(paramVarArgs); Constructor[] arrayOfConstructor; int i;
    byte b;
    for (i = (arrayOfConstructor = (Constructor[])paramClass.getConstructors()).length, b = 0; b < i; b++) {
      Constructor<?> constructor = arrayOfConstructor[b];
      if (DataType.compare(DataType.getPrimitive(constructor.getParameterTypes()), arrayOfClass)) {
        return constructor;
      }
    } 
    throw new NoSuchMethodException("There is no such constructor in this class with the specified parameter types");
  }
  
  public static Constructor<?> getConstructor(String paramString, PackageType paramPackageType, Class<?>... paramVarArgs) throws NoSuchMethodException, ClassNotFoundException {
    return getConstructor(paramPackageType.getClass(paramString), paramVarArgs);
  }
  
  public static Object instantiateObject(Class<?> paramClass, Object... paramVarArgs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
    return getConstructor(paramClass, DataType.getPrimitive(paramVarArgs)).newInstance(paramVarArgs);
  }
  
  public static Object instantiateObject(String paramString, PackageType paramPackageType, Object... paramVarArgs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    return instantiateObject(paramPackageType.getClass(paramString), paramVarArgs);
  }
  
  public static Method getMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs) throws NoSuchMethodException {
    Class[] arrayOfClass = DataType.getPrimitive(paramVarArgs); Method[] arrayOfMethod; int i;
    byte b;
    for (i = (arrayOfMethod = paramClass.getMethods()).length, b = 0; b < i; b++) {
      Method method = arrayOfMethod[b];
      if (method.getName().equals(paramString) && DataType.compare(DataType.getPrimitive(method.getParameterTypes()), arrayOfClass)) {
        return method;
      }
    } 
    throw new NoSuchMethodException("There is no such method in this class with the specified name and parameter types");
  }
  
  public static Method getMethod(String paramString1, PackageType paramPackageType, String paramString2, Class<?>... paramVarArgs) throws NoSuchMethodException, ClassNotFoundException {
    return getMethod(paramPackageType.getClass(paramString1), paramString2, paramVarArgs);
  }
  
  public static Object invokeMethod(Object paramObject, String paramString, Object... paramVarArgs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
    return getMethod(paramObject.getClass(), paramString, DataType.getPrimitive(paramVarArgs)).invoke(paramObject, paramVarArgs);
  }
  
  public static Object invokeMethod(Object paramObject, Class<?> paramClass, String paramString, Object... paramVarArgs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
    return getMethod(paramClass, paramString, DataType.getPrimitive(paramVarArgs)).invoke(paramObject, paramVarArgs);
  }
  
  public static Object invokeMethod(Object paramObject, String paramString1, PackageType paramPackageType, String paramString2, Object... paramVarArgs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
    return invokeMethod(paramObject, paramPackageType.getClass(paramString1), paramString2, paramVarArgs);
  }
  
  public static Field getField(Class<?> paramClass, boolean paramBoolean, String paramString) throws NoSuchFieldException, SecurityException {
    Field field = paramBoolean ? paramClass.getDeclaredField(paramString) : paramClass.getField(paramString);
    field.setAccessible(true);
    return field;
  }
  
  public static Field getField(String paramString1, PackageType paramPackageType, boolean paramBoolean, String paramString2) throws NoSuchFieldException, SecurityException, ClassNotFoundException {
    return getField(paramPackageType.getClass(paramString1), paramBoolean, paramString2);
  }
  
  public static Object getValue(Object paramObject, Class<?> paramClass, boolean paramBoolean, String paramString) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    return getField(paramClass, paramBoolean, paramString).get(paramObject);
  }
  
  public static Object getValue(Object paramObject, String paramString1, PackageType paramPackageType, boolean paramBoolean, String paramString2) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
    return getValue(paramObject, paramPackageType.getClass(paramString1), paramBoolean, paramString2);
  }
  
  public static Object getValue(Object paramObject, boolean paramBoolean, String paramString) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    return getValue(paramObject, paramObject.getClass(), paramBoolean, paramString);
  }
  
  public static void setValue(Object paramObject1, Class<?> paramClass, boolean paramBoolean, String paramString, Object paramObject2) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    getField(paramClass, paramBoolean, paramString).set(paramObject1, paramObject2);
  }
  
  public static void setField(Object paramObject1, String paramString, Object paramObject2) {
    Field field = null;
    Class<?> clazz = paramObject1.getClass();
    
    try { field = clazz.getDeclaredField(paramString);
      field.setAccessible(true);
      field.set(paramObject1, paramObject2); }
    
    catch (NoSuchFieldException noSuchFieldException) {  }
    catch (IllegalAccessException illegalAccessException)
    { illegalAccessException.printStackTrace(); }
    finally
    
    { field.setAccessible(false); }
  
  }
  
  public static void setValue(Object paramObject1, String paramString1, PackageType paramPackageType, boolean paramBoolean, String paramString2, Object paramObject2) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException {
    setValue(paramObject1, paramPackageType.getClass(paramString1), paramBoolean, paramString2, paramObject2);
  }
  
  public static void setValue(Object paramObject1, boolean paramBoolean, String paramString, Object paramObject2) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
    setValue(paramObject1, paramObject1.getClass(), paramBoolean, paramString, paramObject2);
  }
  
  public enum DataType
  {
    BYTE((String)byte.class, Byte.class),
    SHORT((String)short.class, Short.class),
    INTEGER((String)int.class, Integer.class),
    LONG((String)long.class, Long.class),
    CHARACTER((String)char.class, Character.class),
    FLOAT((String)float.class, Float.class),
    DOUBLE((String)double.class, Double.class),
    BOOLEAN((String)boolean.class, Boolean.class);


    
    private static final DataType[] $VALUES = new DataType[] { BYTE, SHORT, INTEGER, LONG, CHARACTER, FLOAT, DOUBLE, BOOLEAN };

    
    private static final Map<Class<?>, DataType> CLASS_MAP = new HashMap<>(); private final Class<?> primitive; static { DataType[] arrayOfDataType; int i;
      byte b;
      for (i = (arrayOfDataType = values()).length, b = 0; b < i; b++) {
        DataType dataType = arrayOfDataType[b];
        CLASS_MAP.put(dataType.primitive, dataType);
        CLASS_MAP.put(dataType.reference, dataType);
      }  }
    
    private final Class<?> reference;
    DataType(Class<?> param1Class1, Class<?> param1Class2) {
      this.primitive = param1Class1;
      this.reference = param1Class2;
    }
    
    public Class<?> getPrimitive() {
      return this.primitive;
    }
    
    public Class<?> getReference() {
      return this.reference;
    }
    
    public static DataType fromClass(Class<?> param1Class) {
      return CLASS_MAP.get(param1Class);
    }
    
    public static Class<?> getPrimitive(Class<?> param1Class) {
      DataType dataType = fromClass(param1Class);
      return (dataType == null) ? param1Class : dataType.getPrimitive();
    }
    
    public static Class<?> getReference(Class<?> param1Class) {
      DataType dataType = fromClass(param1Class);
      return (dataType == null) ? param1Class : dataType.getReference();
    }
    
    public static Class<?>[] getPrimitive(Class<?>[] param1ArrayOfClass) {
      byte b1 = (param1ArrayOfClass == null) ? 0 : param1ArrayOfClass.length;
      Class[] arrayOfClass = new Class[b1];
      for (byte b2 = 0; b2 < b1; b2++) {
        arrayOfClass[b2] = getPrimitive(param1ArrayOfClass[b2]);
      }
      return arrayOfClass;
    }
    
    public static Class<?>[] getReference(Class<?>[] param1ArrayOfClass) {
      byte b1 = (param1ArrayOfClass == null) ? 0 : param1ArrayOfClass.length;
      Class[] arrayOfClass = new Class[b1];
      for (byte b2 = 0; b2 < b1; b2++) {
        arrayOfClass[b2] = getReference(param1ArrayOfClass[b2]);
      }
      return arrayOfClass;
    }
    
    public static Class<?>[] getPrimitive(Object[] param1ArrayOfObject) {
      byte b1 = (param1ArrayOfObject == null) ? 0 : param1ArrayOfObject.length;
      Class[] arrayOfClass = new Class[b1];
      for (byte b2 = 0; b2 < b1; b2++) {
        arrayOfClass[b2] = getPrimitive(param1ArrayOfObject[b2].getClass());
      }
      return arrayOfClass;
    }
    
    public static Class<?>[] getReference(Object[] param1ArrayOfObject) {
      byte b1 = (param1ArrayOfObject == null) ? 0 : param1ArrayOfObject.length;
      Class[] arrayOfClass = new Class[b1];
      for (byte b2 = 0; b2 < b1; b2++) {
        arrayOfClass[b2] = getReference(param1ArrayOfObject[b2].getClass());
      }
      return arrayOfClass;
    }
    
    public static boolean compare(Class<?>[] param1ArrayOfClass1, Class<?>[] param1ArrayOfClass2) {
      if (param1ArrayOfClass1 == null || param1ArrayOfClass2 == null || param1ArrayOfClass1.length != param1ArrayOfClass2.length) {
        return false;
      }
      for (byte b = 0; b < param1ArrayOfClass1.length; b++) {
        Class<?> clazz1 = param1ArrayOfClass1[b];
        Class<?> clazz2 = param1ArrayOfClass2[b];
        if (!clazz1.equals(clazz2) && !clazz1.isAssignableFrom(clazz2)) {
          return false;
        }
      } 
      return true;
    }
  }
  
  public enum PackageType
  {
    MINECRAFT_SERVER("MINECRAFT_SERVER", 0, "net.minecraft.server." + getServerVersion()),
    CRAFTBUKKIT("CRAFTBUKKIT", 1, "org.bukkit.craftbukkit." + getServerVersion()),
    CRAFTBUKKIT_BLOCK("CRAFTBUKKIT_BLOCK", 2, (String)CRAFTBUKKIT, "block"),
    CRAFTBUKKIT_CHUNKIO("CRAFTBUKKIT_CHUNKIO", 3, (String)CRAFTBUKKIT, "chunkio"),
    CRAFTBUKKIT_COMMAND("CRAFTBUKKIT_COMMAND", 4, (String)CRAFTBUKKIT, "command"),
    CRAFTBUKKIT_CONVERSATIONS("CRAFTBUKKIT_CONVERSATIONS", 5, (String)CRAFTBUKKIT, "conversations"),
    CRAFTBUKKIT_ENCHANTMENS("CRAFTBUKKIT_ENCHANTMENS", 6, (String)CRAFTBUKKIT, "enchantments"),
    CRAFTBUKKIT_ENTITY("CRAFTBUKKIT_ENTITY", 7, (String)CRAFTBUKKIT, "entity"),
    CRAFTBUKKIT_EVENT("CRAFTBUKKIT_EVENT", 8, (String)CRAFTBUKKIT, "event"),
    CRAFTBUKKIT_GENERATOR("CRAFTBUKKIT_GENERATOR", 9, (String)CRAFTBUKKIT, "generator"),
    CRAFTBUKKIT_HELP("CRAFTBUKKIT_HELP", 10, (String)CRAFTBUKKIT, "help"),
    CRAFTBUKKIT_INVENTORY("CRAFTBUKKIT_INVENTORY", 11, (String)CRAFTBUKKIT, "inventory"),
    CRAFTBUKKIT_MAP("CRAFTBUKKIT_MAP", 12, (String)CRAFTBUKKIT, "map"),
    CRAFTBUKKIT_METADATA("CRAFTBUKKIT_METADATA", 13, (String)CRAFTBUKKIT, "metadata"),
    CRAFTBUKKIT_POTION("CRAFTBUKKIT_POTION", 14, (String)CRAFTBUKKIT, "potion"),
    CRAFTBUKKIT_PROJECTILES("CRAFTBUKKIT_PROJECTILES", 15, (String)CRAFTBUKKIT, "projectiles"),
    CRAFTBUKKIT_SCHEDULER("CRAFTBUKKIT_SCHEDULER", 16, (String)CRAFTBUKKIT, "scheduler"),
    CRAFTBUKKIT_SCOREBOARD("CRAFTBUKKIT_SCOREBOARD", 17, (String)CRAFTBUKKIT, "scoreboard"),
    CRAFTBUKKIT_UPDATER("CRAFTBUKKIT_UPDATER", 18, (String)CRAFTBUKKIT, "updater"),
    CRAFTBUKKIT_UTIL("CRAFTBUKKIT_UTIL", 19, (String)CRAFTBUKKIT, "util"); private static final PackageType[] $VALUES = new PackageType[] { 
        MINECRAFT_SERVER, CRAFTBUKKIT, CRAFTBUKKIT_BLOCK, CRAFTBUKKIT_CHUNKIO, CRAFTBUKKIT_COMMAND, CRAFTBUKKIT_CONVERSATIONS, CRAFTBUKKIT_ENCHANTMENS, CRAFTBUKKIT_ENTITY, CRAFTBUKKIT_EVENT, CRAFTBUKKIT_GENERATOR, 
        CRAFTBUKKIT_HELP, CRAFTBUKKIT_INVENTORY, CRAFTBUKKIT_MAP, CRAFTBUKKIT_METADATA, CRAFTBUKKIT_POTION, CRAFTBUKKIT_PROJECTILES, CRAFTBUKKIT_SCHEDULER, CRAFTBUKKIT_SCOREBOARD, CRAFTBUKKIT_UPDATER, CRAFTBUKKIT_UTIL }; private final String path;
    
    PackageType(String param1String1, int param1Int1, String param1String2) {
      this.path = param1String2;
    }




    
    public String getPath() {
      return this.path;
    }
    
    public Class<?> getClass(String param1String) throws ClassNotFoundException {
      return Class.forName(this + "." + param1String);
    }

    
    public String toString() {
      return this.path;
    }
    
    public static String getServerVersion() {
      return Bukkit.getServer().getClass().getPackage().getName().substring(23);
    }
  }
}

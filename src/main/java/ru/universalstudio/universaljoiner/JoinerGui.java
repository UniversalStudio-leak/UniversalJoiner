package ru.universalstudio.universaljoiner;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import ru.universalstudio.universaljoiner.gui.GuiLoader;
import ru.universalstudio.universaljoiner.gui.SmartInventory;
import ru.universalstudio.universaljoiner.gui.content.InventoryContents;
import ru.universalstudio.universaljoiner.gui.content.InventoryProvider;
import ru.universalstudio.universaljoiner.gui.content.Pagination;
import ru.universalstudio.universaljoiner.utils.Utils;









public class JoinerGui
  implements InventoryProvider
{
  private GuiLoader gui = GuiLoader.guiLoad(Utils.getConfig(), "gui");
  
  private String lambda$new$0(Player paramPlayer) {
    return Utils.color(this.gui.getTitle().replace("%player%", paramPlayer.getName())); } private SmartInventory inventory = SmartInventory.builder().title(this::lambda$new$0)
    .size(this.gui.getRows(), this.gui.getColumns())
    .provider(this).build();
  
  public static void open(Player paramPlayer) {
    (new JoinerGui()).inventory.open(paramPlayer);
  }

















  
  public void init(Player paramPlayer, InventoryContents paramInventoryContents) {
    // Byte code:
    //   0: goto -> 4
    //   3: athrow
    //   4: aload_0
    //   5: getfield gui : Lru/universalstudio/universaljoiner/gui/GuiLoader;
    //   8: invokevirtual getWindow : ()Lorg/bukkit/inventory/ItemStack;
    //   11: ifnull -> 37
    //   14: aload_2
    //   15: aload_0
    //   16: getfield gui : Lru/universalstudio/universaljoiner/gui/GuiLoader;
    //   19: invokevirtual getWindow : ()Lorg/bukkit/inventory/ItemStack;
    //   22: invokestatic empty : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   25: invokevirtual fillBorders : (Lru/universalstudio/universaljoiner/gui/ClickableItem;)Lru/universalstudio/universaljoiner/gui/content/InventoryContents;
    //   28: pop
    //   29: goto -> 48
    //   32: nop
    //   33: nop
    //   34: nop
    //   35: nop
    //   36: athrow
    //   37: aload_2
    //   38: getstatic org/bukkit/Material.AIR : Lorg/bukkit/Material;
    //   41: invokestatic empty : (Lorg/bukkit/Material;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   44: invokevirtual fillBorders : (Lru/universalstudio/universaljoiner/gui/ClickableItem;)Lru/universalstudio/universaljoiner/gui/content/InventoryContents;
    //   47: pop
    //   48: aload_2
    //   49: invokevirtual pagination : ()Lru/universalstudio/universaljoiner/gui/content/Pagination;
    //   52: aload_0
    //   53: getfield gui : Lru/universalstudio/universaljoiner/gui/GuiLoader;
    //   56: invokevirtual pagination : ()Lru/universalstudio/universaljoiner/gui/GuiLoader$Pagination;
    //   59: invokevirtual getPerPage : ()I
    //   62: invokevirtual setItemsPerPage : (I)Lru/universalstudio/universaljoiner/gui/content/Pagination;
    //   65: astore_3
    //   66: new java/util/ArrayList
    //   69: dup
    //   70: invokespecial <init> : ()V
    //   73: astore #4
    //   75: invokestatic getInstance : ()Lru/universalstudio/universaljoiner/JoinerPlugin;
    //   78: invokevirtual getJoinerManager : ()Lru/universalstudio/universaljoiner/JoinerManager;
    //   81: invokevirtual getJoiners : ()Ljava/util/List;
    //   84: invokevirtual iterator : ()Ljava/util/Iterator;
    //   87: astore #5
    //   89: aload #5
    //   91: invokevirtual hasNext : ()Z
    //   94: ifeq -> 127
    //   97: aload #5
    //   99: invokevirtual next : ()Ljava/lang/Object;
    //   102: checkcast ru/universalstudio/universaljoiner/Joiner
    //   105: astore #6
    //   107: aload #4
    //   109: aload #6
    //   111: aload_1
    //   112: invokevirtual collectClickableItem : (Lorg/bukkit/entity/Player;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   115: invokevirtual add : (Ljava/lang/Object;)Z
    //   118: pop
    //   119: goto -> 89
    //   122: nop
    //   123: nop
    //   124: nop
    //   125: nop
    //   126: athrow
    //   127: aload_3
    //   128: aload #4
    //   130: aload #4
    //   132: invokevirtual size : ()I
    //   135: anewarray ru/universalstudio/universaljoiner/gui/ClickableItem
    //   138: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   141: checkcast [Ljava/lang/Object;
    //   144: checkcast [Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   147: invokevirtual setItems : ([Lru/universalstudio/universaljoiner/gui/ClickableItem;)Lru/universalstudio/universaljoiner/gui/content/Pagination;
    //   150: pop
    //   151: aload_3
    //   152: aload_2
    //   153: getstatic ru/universalstudio/universaljoiner/gui/content/SlotIterator$Type.HORIZONTAL : Lru/universalstudio/universaljoiner/gui/content/SlotIterator$Type;
    //   156: aload_0
    //   157: getfield gui : Lru/universalstudio/universaljoiner/gui/GuiLoader;
    //   160: invokevirtual pagination : ()Lru/universalstudio/universaljoiner/gui/GuiLoader$Pagination;
    //   163: invokevirtual getStartRow : ()I
    //   166: aload_0
    //   167: getfield gui : Lru/universalstudio/universaljoiner/gui/GuiLoader;
    //   170: invokevirtual pagination : ()Lru/universalstudio/universaljoiner/gui/GuiLoader$Pagination;
    //   173: invokevirtual getStartColumn : ()I
    //   176: invokevirtual newIterator : (Lru/universalstudio/universaljoiner/gui/content/SlotIterator$Type;II)Lru/universalstudio/universaljoiner/gui/content/SlotIterator;
    //   179: iconst_0
    //   180: invokevirtual allowOverride : (Z)Lru/universalstudio/universaljoiner/gui/content/SlotIterator;
    //   183: invokevirtual addToIterator : (Lru/universalstudio/universaljoiner/gui/content/SlotIterator;)Lru/universalstudio/universaljoiner/gui/content/Pagination;
    //   186: pop
    //   187: aload_0
    //   188: getfield gui : Lru/universalstudio/universaljoiner/gui/GuiLoader;
    //   191: invokevirtual getItems : ()Ljava/util/List;
    //   194: invokevirtual iterator : ()Ljava/util/Iterator;
    //   197: astore #5
    //   199: aload #5
    //   201: invokevirtual hasNext : ()Z
    //   204: ifeq -> 503
    //   207: aload #5
    //   209: invokevirtual next : ()Ljava/lang/Object;
    //   212: checkcast ru/universalstudio/universaljoiner/gui/GuiLoader$GuiItem
    //   215: astore #6
    //   217: aload #6
    //   219: invokevirtual getItem : ()Lorg/bukkit/inventory/ItemStack;
    //   222: invokestatic empty : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   225: astore #7
    //   227: aload #6
    //   229: ldc 'next'
    //   231: invokevirtual isType : (Ljava/lang/String;)Z
    //   234: ifeq -> 342
    //   237: aload_3
    //   238: invokevirtual isLast : ()Z
    //   241: ifeq -> 284
    //   244: aload #6
    //   246: invokevirtual getAirReplace : ()Lorg/bukkit/inventory/ItemStack;
    //   249: ifnull -> 270
    //   252: aload #6
    //   254: invokevirtual getAirReplace : ()Lorg/bukkit/inventory/ItemStack;
    //   257: invokestatic empty : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   260: astore #7
    //   262: goto -> 480
    //   265: nop
    //   266: nop
    //   267: nop
    //   268: nop
    //   269: athrow
    //   270: aload #7
    //   272: iconst_0
    //   273: invokevirtual setVisible : (Z)V
    //   276: goto -> 480
    //   279: nop
    //   280: nop
    //   281: nop
    //   282: nop
    //   283: athrow
    //   284: aload #6
    //   286: invokevirtual getItem : ()Lorg/bukkit/inventory/ItemStack;
    //   289: invokestatic of : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   292: ldc_w '%page%'
    //   295: aload_3
    //   296: invokevirtual getPage : ()I
    //   299: invokestatic toString : (I)Ljava/lang/String;
    //   302: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   305: ldc_w '%maxpage%'
    //   308: aload_3
    //   309: invokevirtual getPages : ()I
    //   312: invokestatic toString : (I)Ljava/lang/String;
    //   315: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   318: invokevirtual build : ()Lorg/bukkit/inventory/ItemStack;
    //   321: aload_0
    //   322: aload_1
    //   323: aload_3
    //   324: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/JoinerGui;Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/gui/content/Pagination;)Ljava/util/function/Consumer;
    //   329: invokestatic of : (Lorg/bukkit/inventory/ItemStack;Ljava/util/function/Consumer;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   332: astore #7
    //   334: goto -> 480
    //   337: nop
    //   338: nop
    //   339: nop
    //   340: nop
    //   341: athrow
    //   342: aload #6
    //   344: ldc_w 'previous'
    //   347: invokevirtual isType : (Ljava/lang/String;)Z
    //   350: ifeq -> 458
    //   353: aload_3
    //   354: invokevirtual isFirst : ()Z
    //   357: ifeq -> 400
    //   360: aload #6
    //   362: invokevirtual getAirReplace : ()Lorg/bukkit/inventory/ItemStack;
    //   365: ifnull -> 386
    //   368: aload #6
    //   370: invokevirtual getAirReplace : ()Lorg/bukkit/inventory/ItemStack;
    //   373: invokestatic empty : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   376: astore #7
    //   378: goto -> 480
    //   381: nop
    //   382: nop
    //   383: nop
    //   384: nop
    //   385: athrow
    //   386: aload #7
    //   388: iconst_0
    //   389: invokevirtual setVisible : (Z)V
    //   392: goto -> 480
    //   395: nop
    //   396: nop
    //   397: nop
    //   398: nop
    //   399: athrow
    //   400: aload #6
    //   402: invokevirtual getItem : ()Lorg/bukkit/inventory/ItemStack;
    //   405: invokestatic of : (Lorg/bukkit/inventory/ItemStack;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   408: ldc_w '%page%'
    //   411: aload_3
    //   412: invokevirtual getPage : ()I
    //   415: invokestatic toString : (I)Ljava/lang/String;
    //   418: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   421: ldc_w '%maxpage%'
    //   424: aload_3
    //   425: invokevirtual getPages : ()I
    //   428: invokestatic toString : (I)Ljava/lang/String;
    //   431: invokevirtual replace : (Ljava/lang/String;Ljava/lang/String;)Lru/universalstudio/universaljoiner/utils/ItemBuilder;
    //   434: invokevirtual build : ()Lorg/bukkit/inventory/ItemStack;
    //   437: aload_0
    //   438: aload_1
    //   439: aload_3
    //   440: <illegal opcode> accept : (Lru/universalstudio/universaljoiner/JoinerGui;Lorg/bukkit/entity/Player;Lru/universalstudio/universaljoiner/gui/content/Pagination;)Ljava/util/function/Consumer;
    //   445: invokestatic of : (Lorg/bukkit/inventory/ItemStack;Ljava/util/function/Consumer;)Lru/universalstudio/universaljoiner/gui/ClickableItem;
    //   448: astore #7
    //   450: goto -> 480
    //   453: nop
    //   454: nop
    //   455: nop
    //   456: nop
    //   457: athrow
    //   458: aload #6
    //   460: ldc_w 'back'
    //   463: invokevirtual isType : (Ljava/lang/String;)Z
    //   466: ifeq -> 480
    //   469: aload #7
    //   471: aload_1
    //   472: <illegal opcode> accept : (Lorg/bukkit/entity/Player;)Ljava/util/function/Consumer;
    //   477: invokevirtual setAction : (Ljava/util/function/Consumer;)V
    //   480: aload_2
    //   481: aload #6
    //   483: invokevirtual getSlot : ()I
    //   486: invokestatic of : (I)Lru/universalstudio/universaljoiner/gui/content/SlotPos;
    //   489: aload #7
    //   491: invokevirtual set : (Lru/universalstudio/universaljoiner/gui/content/SlotPos;Lru/universalstudio/universaljoiner/gui/ClickableItem;)Lru/universalstudio/universaljoiner/gui/content/InventoryContents;
    //   494: pop
    //   495: goto -> 199
    //   498: nop
    //   499: nop
    //   500: nop
    //   501: nop
    //   502: athrow
    //   503: return
    //   504: nop
    //   505: nop
    //   506: nop
    //   507: nop
    //   508: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #37	-> 4
    //   #38	-> 14
    //   #40	-> 37
    //   #42	-> 48
    //   #44	-> 66
    //   #46	-> 75
    //   #47	-> 107
    //   #48	-> 119
    //   #50	-> 127
    //   #52	-> 151
    //   #53	-> 180
    //   #52	-> 183
    //   #56	-> 187
    //   #58	-> 217
    //   #60	-> 227
    //   #62	-> 237
    //   #63	-> 244
    //   #64	-> 252
    //   #66	-> 270
    //   #69	-> 284
    //   #70	-> 296
    //   #71	-> 309
    //   #69	-> 329
    //   #74	-> 342
    //   #76	-> 353
    //   #77	-> 360
    //   #78	-> 368
    //   #80	-> 386
    //   #83	-> 400
    //   #84	-> 402
    //   #85	-> 412
    //   #86	-> 425
    //   #83	-> 445
    //   #89	-> 458
    //   #91	-> 469
    //   #94	-> 480
    //   #95	-> 495
    //   #96	-> 503
  }
















  
  private void lambda$init$1(Player paramPlayer, Pagination paramPagination, InventoryClickEvent paramInventoryClickEvent) {
    this.inventory.open(paramPlayer, paramPagination.next().getPage());
  }











  
  private void lambda$init$2(Player paramPlayer, Pagination paramPagination, InventoryClickEvent paramInventoryClickEvent) {
    this.inventory.open(paramPlayer, paramPagination.previous().getPage());
  }
  
  private static void lambda$init$3(Player paramPlayer, InventoryClickEvent paramInventoryClickEvent) {
    paramPlayer.closeInventory();
  }
}

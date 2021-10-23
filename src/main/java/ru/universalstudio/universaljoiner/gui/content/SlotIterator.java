package ru.universalstudio.universaljoiner.gui.content;public interface SlotIterator { public static final boolean      ​‌; Optional<ClickableItem> get(); SlotIterator set(ClickableItem paramClickableItem); SlotIterator previous();
  SlotIterator next();
  SlotIterator blacklist(int paramInt1, int paramInt2);
  SlotIterator blacklist(SlotPos paramSlotPos);
  int row();
  SlotIterator row(int paramInt);
  int column();
  SlotIterator column(int paramInt);
  boolean started();
  boolean ended();
  boolean doesAllowOverride();
  SlotIterator allowOverride(boolean paramBoolean);
  public enum Type { HORIZONTAL,
    VERTICAL;


    
    private static final Type[] $VALUES = new Type[] { HORIZONTAL, VERTICAL }; }




  
  public static class Impl
    implements SlotIterator
  {
    private InventoryContents contents;


    
    private SmartInventory inv;

    
    private SlotIterator.Type type;

    
    private boolean started = false;

    
    private boolean allowOverride = true;

    
    private int row;

    
    private int column;

    
    private Set<SlotPos> blacklisted = new HashSet<>();


    
    public Impl(InventoryContents param1InventoryContents, SmartInventory param1SmartInventory, SlotIterator.Type param1Type, int param1Int1, int param1Int2) {
      this.contents = param1InventoryContents;
      this.inv = param1SmartInventory;
      
      this.type = param1Type;
      
      this.row = param1Int1;
      this.column = param1Int2;
    }


    
    public Impl(InventoryContents param1InventoryContents, SmartInventory param1SmartInventory, SlotIterator.Type param1Type) {
      this(param1InventoryContents, param1SmartInventory, param1Type, 0, 0);
    }

    
    public Optional<ClickableItem> get() {
      return this.contents.get(this.row, this.column);
    }

    
    public SlotIterator set(ClickableItem param1ClickableItem) {
      if (canPlace()) {
        this.contents.set(this.row, this.column, param1ClickableItem);
      }
      return this;
    }





















    
    public SlotIterator previous() {
      // Byte code:
      //   0: goto -> 5
      //   3: nop
      //   4: athrow
      //   5: aload_0
      //   6: getfield row : I
      //   9: ifne -> 30
      //   12: aload_0
      //   13: getfield column : I
      //   16: ifne -> 30
      //   19: aload_0
      //   20: iconst_1
      //   21: putfield started : Z
      //   24: aload_0
      //   25: areturn
      //   26: nop
      //   27: nop
      //   28: nop
      //   29: athrow
      //   30: aload_0
      //   31: getfield started : Z
      //   34: ifne -> 49
      //   37: aload_0
      //   38: iconst_1
      //   39: putfield started : Z
      //   42: goto -> 179
      //   45: nop
      //   46: nop
      //   47: nop
      //   48: athrow
      //   49: getstatic ru/universalstudio/universaljoiner/gui/content/SlotIterator$1.$SwitchMap$ru$universalstudio$universaljoiner$gui$content$SlotIterator$Type : [I
      //   52: aload_0
      //   53: getfield type : Lru/universalstudio/universaljoiner/gui/content/SlotIterator$Type;
      //   56: invokevirtual ordinal : ()I
      //   59: iaload
      //   60: lookupswitch default -> 179, 1 -> 92, 2 -> 139
      //   88: nop
      //   89: nop
      //   90: nop
      //   91: athrow
      //   92: aload_0
      //   93: dup
      //   94: getfield column : I
      //   97: iconst_1
      //   98: isub
      //   99: putfield column : I
      //   102: aload_0
      //   103: getfield column : I
      //   106: ifne -> 179
      //   109: aload_0
      //   110: aload_0
      //   111: getfield inv : Lru/universalstudio/universaljoiner/gui/SmartInventory;
      //   114: invokevirtual getColumns : ()I
      //   117: iconst_1
      //   118: isub
      //   119: putfield column : I
      //   122: aload_0
      //   123: dup
      //   124: getfield row : I
      //   127: iconst_1
      //   128: isub
      //   129: putfield row : I
      //   132: goto -> 179
      //   135: nop
      //   136: nop
      //   137: nop
      //   138: athrow
      //   139: aload_0
      //   140: dup
      //   141: getfield row : I
      //   144: iconst_1
      //   145: isub
      //   146: putfield row : I
      //   149: aload_0
      //   150: getfield row : I
      //   153: ifne -> 179
      //   156: aload_0
      //   157: aload_0
      //   158: getfield inv : Lru/universalstudio/universaljoiner/gui/SmartInventory;
      //   161: invokevirtual getRows : ()I
      //   164: iconst_1
      //   165: isub
      //   166: putfield row : I
      //   169: aload_0
      //   170: dup
      //   171: getfield column : I
      //   174: iconst_1
      //   175: isub
      //   176: putfield column : I
      //   179: aload_0
      //   180: invokespecial canPlace : ()Z
      //   183: ifne -> 200
      //   186: aload_0
      //   187: getfield row : I
      //   190: ifne -> 30
      //   193: aload_0
      //   194: getfield column : I
      //   197: ifne -> 30
      //   200: aload_0
      //   201: areturn
      //   202: nop
      //   203: nop
      //   204: nop
      //   205: athrow
      // Line number table:
      //   Java source line number -> byte code offset
      //   #84	-> 5
      //   #85	-> 19
      //   #86	-> 24
      //   #90	-> 30
      //   #91	-> 37
      //   #94	-> 49
      //   #96	-> 92
      //   #98	-> 102
      //   #99	-> 109
      //   #100	-> 122
      //   #104	-> 139
      //   #106	-> 149
      //   #107	-> 156
      //   #108	-> 169
      //   #114	-> 179
      //   #116	-> 200
    }





















    
    public SlotIterator next() {
      // Byte code:
      //   0: goto -> 5
      //   3: nop
      //   4: athrow
      //   5: aload_0
      //   6: invokevirtual ended : ()Z
      //   9: ifeq -> 23
      //   12: aload_0
      //   13: iconst_1
      //   14: putfield started : Z
      //   17: aload_0
      //   18: areturn
      //   19: nop
      //   20: nop
      //   21: nop
      //   22: athrow
      //   23: aload_0
      //   24: getfield started : Z
      //   27: ifne -> 42
      //   30: aload_0
      //   31: iconst_1
      //   32: putfield started : Z
      //   35: goto -> 171
      //   38: nop
      //   39: nop
      //   40: nop
      //   41: athrow
      //   42: getstatic ru/universalstudio/universaljoiner/gui/content/SlotIterator$1.$SwitchMap$ru$universalstudio$universaljoiner$gui$content$SlotIterator$Type : [I
      //   45: aload_0
      //   46: getfield type : Lru/universalstudio/universaljoiner/gui/content/SlotIterator$Type;
      //   49: invokevirtual ordinal : ()I
      //   52: iaload
      //   53: lookupswitch default -> 171, 1 -> 84, 2 -> 131
      //   80: nop
      //   81: nop
      //   82: nop
      //   83: athrow
      //   84: aload_0
      //   85: aload_0
      //   86: dup
      //   87: getfield column : I
      //   90: iconst_1
      //   91: iadd
      //   92: dup_x1
      //   93: putfield column : I
      //   96: aload_0
      //   97: getfield inv : Lru/universalstudio/universaljoiner/gui/SmartInventory;
      //   100: invokevirtual getColumns : ()I
      //   103: irem
      //   104: putfield column : I
      //   107: aload_0
      //   108: getfield column : I
      //   111: ifne -> 171
      //   114: aload_0
      //   115: dup
      //   116: getfield row : I
      //   119: iconst_1
      //   120: iadd
      //   121: putfield row : I
      //   124: goto -> 171
      //   127: nop
      //   128: nop
      //   129: nop
      //   130: athrow
      //   131: aload_0
      //   132: aload_0
      //   133: dup
      //   134: getfield row : I
      //   137: iconst_1
      //   138: iadd
      //   139: dup_x1
      //   140: putfield row : I
      //   143: aload_0
      //   144: getfield inv : Lru/universalstudio/universaljoiner/gui/SmartInventory;
      //   147: invokevirtual getRows : ()I
      //   150: irem
      //   151: putfield row : I
      //   154: aload_0
      //   155: getfield row : I
      //   158: ifne -> 171
      //   161: aload_0
      //   162: dup
      //   163: getfield column : I
      //   166: iconst_1
      //   167: iadd
      //   168: putfield column : I
      //   171: aload_0
      //   172: invokespecial canPlace : ()Z
      //   175: ifne -> 185
      //   178: aload_0
      //   179: invokevirtual ended : ()Z
      //   182: ifeq -> 23
      //   185: aload_0
      //   186: areturn
      //   187: nop
      //   188: nop
      //   189: nop
      //   190: athrow
      // Line number table:
      //   Java source line number -> byte code offset
      //   #121	-> 5
      //   #122	-> 12
      //   #123	-> 17
      //   #127	-> 23
      //   #128	-> 30
      //   #131	-> 42
      //   #133	-> 84
      //   #135	-> 107
      //   #136	-> 114
      //   #139	-> 131
      //   #141	-> 154
      //   #142	-> 161
      //   #147	-> 171
      //   #149	-> 185
    }





















    
    public SlotIterator blacklist(int param1Int1, int param1Int2) {
      this.blacklisted.add(SlotPos.of(param1Int1, param1Int2));
      return this;
    }

    
    public SlotIterator blacklist(SlotPos param1SlotPos) {
      return blacklist(param1SlotPos.getRow(), param1SlotPos.getColumn());
    }
    
    public int row() {
      return this.row;
    }
    
    public SlotIterator row(int param1Int) {
      this.row = param1Int;
      return this;
    }
    
    public int column() {
      return this.column;
    }
    
    public SlotIterator column(int param1Int) {
      this.column = param1Int;
      return this;
    }

    
    public boolean started() {
      return this.started;
    }

    
    public boolean ended() {
      return (this.row == this.inv.getRows() - 1 && this.column == this.inv
        .getColumns() - 1);
    }
    
    public boolean doesAllowOverride() {
      return this.allowOverride;
    }
    
    public SlotIterator allowOverride(boolean param1Boolean) {
      this.allowOverride = param1Boolean;
      return this;
    }
    
    private boolean canPlace() {
      return (!this.blacklisted.contains(SlotPos.of(this.row, this.column)) && (this.allowOverride || !get().isPresent()));
    }
  } }

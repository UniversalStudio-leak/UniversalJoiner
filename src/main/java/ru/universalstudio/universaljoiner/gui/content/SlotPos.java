package ru.universalstudio.universaljoiner.gui.content;

public class SlotPos
{
  private final int row;
  private final int column;
  
  public SlotPos(int paramInt1, int paramInt2) {
    this.row = paramInt1;
    this.column = paramInt2;
  }

  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null || getClass() != paramObject.getClass()) {
      return false;
    }
    SlotPos slotPos = (SlotPos)paramObject;
    
    return (this.row == slotPos.row && this.column == slotPos.column);
  }

  
  public int hashCode() {
    int i = this.row;
    i = 31 * i + this.column;
    
    return i;
  }
  
  public int getRow() { return this.row; } public int getColumn() {
    return this.column;
  }
  public static SlotPos of(int paramInt1, int paramInt2) {
    return new SlotPos(paramInt1, paramInt2);
  }
  
  public static SlotPos of(int paramInt) {
    return new SlotPos(paramInt / 9, paramInt % 9);
  }
}

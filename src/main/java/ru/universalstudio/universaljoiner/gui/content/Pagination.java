package ru.universalstudio.universaljoiner.gui.content;
import ru.universalstudio.universaljoiner.gui.ClickableItem;

public interface Pagination {
  public static final boolean        ;
  
  ClickableItem[] getPageItems();
  
  int getPage();
  
  int getPages();
  
  Pagination page(int paramInt);
  
  boolean isFirst();
  
  boolean isLast();
  
  Pagination first();
  
  Pagination previous();
  
  Pagination next();
  
  Pagination last();
  
  Pagination addToIterator(SlotIterator paramSlotIterator);
  
  Pagination setItems(ClickableItem... paramVarArgs);
  
  Pagination setItemsPerPage(int paramInt);
  
  public static class Impl implements Pagination {
    private ClickableItem[] items = new ClickableItem[0]; private int currentPage;
    private int itemsPerPage = 5;

    
    public ClickableItem[] getPageItems() {
      return Arrays.<ClickableItem>copyOfRange(this.items, this.currentPage * this.itemsPerPage, (this.currentPage + 1) * this.itemsPerPage);
    }



    
    public int getPage() {
      return this.currentPage;
    }

    
    public int getPages() {
      return (int)Math.ceil(this.items.length / this.itemsPerPage) - 1;
    }

    
    public Pagination page(int param1Int) {
      this.currentPage = param1Int;
      return this;
    }

    
    public boolean isFirst() {
      return (this.currentPage == 0);
    }

    
    public boolean isLast() {
      return (this.currentPage >= getPages());
    }

    
    public Pagination first() {
      this.currentPage = 0;
      return this;
    }

    
    public Pagination previous() {
      if (!isFirst()) {
        this.currentPage--;
      }
      return this;
    }

    
    public Pagination next() {
      if (!isLast()) {
        this.currentPage++;
      }
      return this;
    }

    
    public Pagination last() {
      this.currentPage = this.items.length / this.itemsPerPage;
      return this;
    }

    
    public Pagination addToIterator(SlotIterator param1SlotIterator) {
      for (ClickableItem clickableItem : getPageItems()) {
        param1SlotIterator.next().set(clickableItem);
        
        if (param1SlotIterator.ended()) {
          break;
        }
      } 
      return this;
    }

    
    public Pagination setItems(ClickableItem... param1VarArgs) {
      this.items = param1VarArgs;
      return this;
    }

    
    public Pagination setItemsPerPage(int param1Int) {
      this.itemsPerPage = param1Int;
      return this;
    }
  }
}

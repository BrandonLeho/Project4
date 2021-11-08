
public interface MaxHeapInterface<T extends Comparable<? super T>>
{
	/** Gets the value at the index number 1
       @return  The first index or nothing if the heap is empty */
   public T getMax();
   
   /** Is the heap empty? Does it have any values in any index?
       @return  True if the heap is empty or false if the heap has any values */
   public boolean isEmpty();
   
   /** How many indexes have values?
       @return  The amount indexes that have values */
   public int getSize();
   
   /** Clears entire heap */
   public void clear();
   
   /** Adds new value to next available index.
       @param newEntry  The new value to be assigned */
   public void add(T newEntry);
   
   /** Builds the heap using O(nlogn). Compares a new entry with the median index
    * of the heap. If the entry id greater than the median, it will then compare 
    * the entry with the latter half median of the heap. Same with if the entry is
    * less than the median. It will then compare the entry with the former half median
    * of the heap. It will loop through these steps while swapping each value it is
    * compared with until it finds a spot where the index below is less in value than it
    * and the index above is greater in value than it.
    * 	@param newEntry	The new value to be assigned
    */
   public void addSequential(T newEntry);
   
   /** Builds the heap using O(n). After a complete tree is made, it will begin to compare
    * leaf nodes of the tree with the parent nodes by calling reheap multiple times. If the 
    * leaf node is larger than the parent node, they will swap. It will gradually move up the 
    * tree comparing each child with their parent.
    */
   public void optimal();

   /** Removes the first index of the heap, then call reheap to fill in the empty index.
       @return  The largest value or nothing if the heap is empty. */
   public T removeMax();
   
   /** prints the amount of swaps */
   public void showSwaps();

   /** prints the first 10 indexes in the heap. */
   public void displayHeap();
   
} // end MaxHeapInterface

import java.util.Arrays;

public class SequentialMethod<T extends Comparable<? super T>> implements MaxHeapInterface<T>
{

	private T[] heap;		//Array of heap entries
	private int lastIndex;	//Index of last entry
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
	private int swapCount = 0;
	
	public SequentialMethod()
	{
		this(DEFAULT_CAPACITY); //call next constructor
	} //end default constructor
	
	public SequentialMethod(int initialCapacity)
	{
		//Is initialCapicity too small?
		if(initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else //Is initialCapacity too big?
			checkCapacity(initialCapacity);
		
		//The cast is safe because the new array contains all null entries
		@SuppressWarnings("unchecked")
		T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
		heap = tempHeap;
		lastIndex = 0;
		integrityOK = true;
	} //end constructor
	
	public T getMax()
	{
		checkIntegrity();
		T root = null;
		if (!isEmpty())
			root = heap[1];
		return root;
	} //end getMax
	
	public boolean isEmpty()
	{
		return lastIndex < 1;
	} //end isEmpty
	
	public int getSize()
	{
		return lastIndex;
	} //end getSize
	
	public void clear()
	{
		checkIntegrity();
		while(lastIndex > -1)
		{
			heap[lastIndex] = null;
			lastIndex--;
		} //end while
		lastIndex = 0;
		heap = Arrays.copyOf(heap, lastIndex);
	} //end clear
	
	public void addSequential(T newEntry)
	{
		checkIntegrity();
		int newIndex = lastIndex + 1;
		int parentIndex = newIndex / 2;
		while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
		{
			heap[newIndex] = heap[parentIndex];
			swapCount++;
			newIndex = parentIndex;
			parentIndex = newIndex / 2;		
		} //end while
		heap[newIndex] = newEntry;
		swapCount++;
		lastIndex++;
		ensureCapacity();
	} //end addSequential
	
	public T removeMax()
	{
		checkIntegrity();
		T root = null;
		
		if(!isEmpty())
		{
			root = heap[1];				//Return value
			heap[1] = heap[lastIndex];	//Form a semiheap
			swapCount++;
			lastIndex--;				//Decrease size
			heap = Arrays.copyOf(heap, lastIndex + 1);
			reheap(1);					//Transform to a heap			
		} //end if
		return root;
	} //end removeMax	
	
	private void reheap(int rootIndex)
	{
		boolean done = false;
		T orphan = heap[rootIndex];
		int leftChildIndex = 2 * rootIndex;
		
		while(!done && (leftChildIndex <= lastIndex))
		{
			int largerChildIndex = leftChildIndex; //assume larger
			int rightChildIndex = leftChildIndex + 1;
			if((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
			{
				largerChildIndex = rightChildIndex;
			} //end if
			if(orphan.compareTo(heap[largerChildIndex]) < 0)
			{
				heap[rootIndex] = heap[largerChildIndex];
				swapCount++;
				rootIndex = largerChildIndex;
				leftChildIndex = 2 * rootIndex;			
			}
			else
				done = true;
		} //end while
		heap[rootIndex] = orphan;
		swapCount++;
	} //end reheap
	
	public void showSwaps()
	{
		System.out.println(swapCount);
	}
	
	public void displayHeap()
	{
		for(int i = 0; i < 10; i++)
		{
			System.out.print(heap[i + 1] + ",");
		}
		System.out.println("...");
	}
	
	public void displayEntireHeap()
	{
		for(int i = 1; i < heap.length - 1; i++)
		{
			System.out.println(heap[i]);
		}
	}
	
	//Throws an exception if object is not initialized.
	private void checkIntegrity()
	{
		if(integrityOK == false)
		{
			throw new SecurityException("Bro, your input is invalid >:(");
		}
	} //end checkIntegrity
	
	private void ensureCapacity()
	{
		if (lastIndex >= heap.length - 1) //if array is full, add an extra index
		{
			int newLength = 1 + heap.length;
			checkCapacity(newLength);
			heap = Arrays.copyOf(heap, newLength);
		} //end if
	} //end ensureCapacity
	
	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity)
	{
		if(capacity > MAX_CAPACITY)
		{
			throw new IllegalStateException("Ayo! U tryna make a bag capacity bigger than the maximum of " + MAX_CAPACITY);
		}
	} //end checkCapacity
}


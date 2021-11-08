import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class DriverSequential {
	
	public static void main(String[] args) throws IOException
	{
		
		Scanner scan = new Scanner(System.in);
		
		//TEST 1 WITH DATA_SORTED
		File sorted = new File("C:/Users/Brandon/OneDrive/Desktop/data_sorted.txt"); //file input for data_sorted	
		FileReader sortfr = new FileReader(sorted);
		BufferedReader sortbr = new BufferedReader(sortfr);
		
		File fileOutput = new File("C:/Users/Brandon/OneDrive/Desktop/sequential_output.txt"); //file output for data_sorted
		PrintStream stream = new PrintStream(fileOutput);
		PrintStream console = System.out; //command to print output to console
		System.out.println("Check " + fileOutput.getAbsolutePath() + " for results.");
		System.setOut(stream); //command to print output to txt file
		
		//creates empty heap
		SequentialMethod<String> heap1 = new SequentialMethod<>();	
		
		String line1;
		while((line1 = sortbr.readLine()) != null)
		{	
			heap1.addSequential(line1);	
		}
		
		System.out.print("Sorted heap built using sequential insertions: ");
		heap1.displayHeap();
		System.out.print("Number of swaps in the heap creation: ");
		heap1.showSwaps();
		
		for(int i = 0; i < 10; i++)
		{
			heap1.removeMax();
		}
		
		System.out.print("Sorted heap after 10 removals: ");
		heap1.displayHeap();
		
		System.out.println();
		
		//TEST 2 WITH DATA_RANDOM
		File random = new File("C:/Users/Brandon/OneDrive/Desktop/data_random.txt"); //file input for data_random
		FileReader randfr = new FileReader(random);
		BufferedReader randbr = new BufferedReader(randfr);
		
		//creates empty heap
		SequentialMethod<String> heap2 = new SequentialMethod<>();	
		
		String line2;
		while((line2 = randbr.readLine()) != null)
		{	
			heap2.addSequential(line2);
		}
		
		System.out.print("Random heap built using sequential insertions: ");
		heap2.displayHeap();
		System.out.print("Number of swaps in the heap creation: ");
		heap2.showSwaps();
		
		for(int i = 0; i < 10; i++)
		{
			heap2.removeMax();
		}
		
		System.out.print("Random heap after 10 removals: ");
		heap2.displayHeap();
		
	} //end main
} //end DriverSequential

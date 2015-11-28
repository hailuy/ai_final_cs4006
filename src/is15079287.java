import java.io.*;
import java.util.*;
import java.lang.Math;
import java.awt.Graphics;
import javax.swing.JFrame;

public class is15079287{

	static int range = 26;
	static int cnt;
	static int count = 100;
	static int i = 0,j = 0;
	static double [][]adjacency = new double[count][range + 1];
	static double [][]mate = new double[count][range + 1];
	static double [][]arr = new double[range][range];
	static double []ordering_last_result = new double[range];
	static int n=25;
	static double cp = 0.05;
	static double mp = 0.10;
	static double rp = 0.85;
	static int fit = -1;
	
	public static void main(String [] args) {

		// open the file .
		Scanner scanner;
		try {
			scanner = new Scanner(new File("AI2015.txt"));
			for(int i = 0;i < count;i++) {
				for(int j = 0;scanner.hasNextInt() && j < range;j++){
					arr[i][j] = scanner.nextInt();//read in
				}
			}   
			scanner.close();
			order();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		PrintWriter out;
		try {
			System.out.println("enter a number indicating how many round do you want:");//read from the user
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String rad = br.readLine(); //We read from user's input
			cnt = Integer.parseInt(rad); 
			out = new PrintWriter("output.txt", "UTF-8");
			for(int f = 0;f< cnt;f++){	
				util.mating(adjacency,mate,arr);	//mating pool
				fit = util.fitness(adjacency);//get the smallest fitness 
								out.println("round " + (f+ 1) + " is ");
								out.println();
								for(int i1 = 0;i1 < count;i1++) {
									//					out.println();
									//					out.println("line " + (i1 + 1) + " is ");
									//					out.println();
									for(int j = 0;j < range + 1;j++){
										out.printf(" " + adjacency[i1][j]); 
									}
									out.println();
								} 
								out.println();
				out.println("line " + (f + 1) + " fitness is  " + adjacency[fit][range]);
				out.println();
				out.println();
			}
			for(int j = 0;j < range;j++){
				ordering_last_result[j] = adjacency[fit][j];
			}
			out.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				
		//		for(int j = 0;j < range;j++){
		//			System.out.printf(ordering_last_result[j]+" ");
		//		}

	}

	static boolean unique(){
		for(int p = 0;p < j;p++){
			if(adjacency[i][j] == adjacency[i][p]){
				return false;
			}
		}
		return true;
	}

	static void order(){
		Random randomno = new Random();
		// setting seed
		randomno.setSeed(20);

		for(i = 0;i < count;i++){
			for(j = 0;j < range;j++){
				do{
					adjacency[i][j] = (int) (Math.random() * (range));
				}while(!unique());   //if equal,do it again
//			System.out.printf(adjacency[i][j]+" ");	
			}
			adjacency[i][j] = util.fitness_function(adjacency,i,arr);

//				System.out.println();	//first fitness_function
		}
	}

	//	static void pop(int cnt){
	//		Random randomno = new Random();
	//
	//		// setting seed
	//		randomno.setSeed(20);
	//
	//		int ran = 0;
	//
	//		int [][]arr = new int[range][range];
	//		int j = 0;
	//		int k = range - j;
	//
	//		for(int i = 0;i < cnt;i++){
	//			System.out.println("\n");
	//			for(;j < range;j++,k = range - j){
	//				for(;k > 0;k--){
	//					ran = (int) (Math.random() * range);
	//					arr[j][k] = ran;
	//					arr[k][j] = ran;
	//				}	         
	//			}
	//		}
	//
	//		for(int m = 0;m < range;m++){
	//			for(int n = 0;n < range;n++){
	//				out.print(arr[m][n] + "\t\r"); 
	//			}
	//			out.println("\n");
	//		}
	//	}  
}

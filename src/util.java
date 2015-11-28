import java.util.Random;

public class util {

	static int range = 25;
	static int count = 100;
	static double cp = 0.05;
	static double mp = 0.10;
	static double rp = 0.85;

	static int fitness(double [][]adjacency){
		int j = 0;
		for(int i = 1;i < count;i++) {
			if(adjacency[i][range + 1] < adjacency[j][range + 1]){
				j = i;
			}
		}
		return j;
	}

	static double fitness_function(double [][]ordering,int l,double [][]arr){
		double chunk = Math.PI * 2/(range + 1);
		double []Polar_coordinate = new double[range + 1];
		double []x_coordinate = new double[range + 1];
		double []y_coordinate = new double[range + 1];

		for(int j = 0;j < range + 1;j++){
			Polar_coordinate[(int) ordering[l][j]] = chunk * j;
			x_coordinate[j] = Math.cos(Polar_coordinate[j]);
			y_coordinate[j] = Math.sin(Polar_coordinate[j]);
		}
		double fitness = 0;
		for(int i = 0;i < range + 1;i++){
			for(int j = 0;j < range + 1;j++){
				if(arr[i][j] == 1){
					fitness += Math.sqrt((x_coordinate[i] - x_coordinate[j]) * (x_coordinate[i] - x_coordinate[j])
							+ (y_coordinate[i] - y_coordinate[j]) * (y_coordinate[i] - y_coordinate[j])); 
				}
			}
		}
		for(int i = 0;i < range + 1;i++){
			for(int j = 0;j < range + 1;j++){
				if(arr[i][j] == 1){
					for(int m = 0;m < range + 1;m++){
						for(int n = 0;n < range + 1;n++){
							if(arr[m][n] == 1 && !(m == i && j == n)){
								if((((Math.abs(x_coordinate[i] - x_coordinate[j]) * Math.abs(y_coordinate[i] - y_coordinate[j])
										- Math.abs(y_coordinate[i] - y_coordinate[n]) * Math.abs(x_coordinate[j] - x_coordinate[n])
										) * 
										(Math.abs(x_coordinate[i] - x_coordinate[j]) * Math.abs(y_coordinate[i] - y_coordinate[j])
												- Math.abs(y_coordinate[i] - y_coordinate[m]) * Math.abs(x_coordinate[j] - x_coordinate[m])) < 0))){
									fitness += 1;	
								}								
							}
						}
					}
				}
			}
		}
		return fitness;
	}

	static void mating(double [][]adjacency,double [][]mate,double [][]arr){
		int k = 0,l = 0,temp = 0,cnt = 0,tour = 4;
		Random randomno = new Random();
		// setting seed
		randomno.setSeed(20);
		for(int i = 0;i < count;i++){
			l = (int) (Math.random() * count);
			for(int j = 0;j < tour;j++){
				k = (int) (Math.random() * count);	
				if(adjacency[l][range + 1] <= adjacency[k][range + 1]){
					temp = l;
				}
				else{
					temp = k;
				}
				l = temp;
			}
			for(int j = 0;j < range + 2;j++){
				mate[i][j] = adjacency[temp][j];//System.out.printf(mate[i][j]+" ");	
			}					//System.out.println();		
		}

		int g = 0;
		double t = 0;
		while(cnt < count){
			if(cnt < 99){
				t = (Math.random() * 1);
			}
			else{
				t = (Math.random() * (rp + mp));
			}
			do{
				g = (int) (Math.random() * count);	
			}while(mate[g][0] < 0);
			if(t < rp){ 							
			}
			else if(t < rp + mp){
				//				do{ 
				mutation(g,mate);
				//				}while(!ifUnique(g,adjacency,mate));
			}
			else{
				int y;
				//				//System.out.println();//System.out.println();
				//				for(int j = 0;j < range;j++){
				//					//System.out.print(" "+mate[g][j]);
				//				}//System.out.println();
				//				do{
				do{
					y = (int) (Math.random() * count);
				}while(mate[y][0] < 0 || y == g /*|| equal(g,y,mate)*/);
				crossover(g,y,mate);////System.out.println("\n\nt = "+g+y);
				//					for(int j = 0;j < range;j++){
				//						//System.out.print(" "+mate[g][j]);
				//					}//System.out.println();
				//					for(int j = 0;j < range;j++){
				//						//System.out.print(" "+mate[y][j]);
				//					}
				//				}while(!ifUnique(g,adjacency,mate) || !ifUnique(y,adjacency,mate));

				for(int j = 0;j < range + 1;j++){
					adjacency[cnt][j] = mate[y][j];
				}
				adjacency[cnt][range + 1] = util.fitness_function(adjacency,cnt,arr);
				mate[y][0] = -1.0;
				cnt += 1;
			}
			int j = 0;
			for(;j < range + 1;j++){
				adjacency[cnt][j] = mate[g][j];
			}
			adjacency[g][j] = util.fitness_function(adjacency,g,arr);
			mate[g][0] = -1.0;
			cnt += 1;
		}
	}
//	private static boolean equal(int g, int y,double[][]mate) {
//		int cou = 0;
//		for(int p = 0;p < range;p++){
//			if(mate[g][p] == mate[y][p]){
//				cou++;
//			}
//			if(cou == range){
//				return true;
//			}
//		}		
//		return false;
//	}
	static boolean ifUnique(int cnt,double[][]adjacency,double[][]mate){
		for(int i = 0;i < count;i++){
			int cou = 0;
			for(int p = 0;p < range;p++){
				if(mate[cnt][p] == adjacency[i][p]){
					cou++;
				}
				if(cou == range){for(int i1 = 0;i1 < count;i1++) {
					//					out.println();
					//					out.println("line " + (i1 + 1) + " is ");
					//					out.println();
					for(int j = 0;j < range + 1;j++){
						//System.out.printf(" " + adjacency[i1][j]); 
					}
					//System.out.println();
				} 
				//System.out.println();
				//System.out.println();
				for(int p1 = 0;p1 < range;p1++){
					//System.out.print(" "+adjacency[i][p1]);
				}
				//System.out.print("\ni= "+i+" cou= "+cou);
				//System.out.println();
				return false;
				}
			}			
		}
		return true;
	}

	private static void crossover(int g,int y,double [][]mate) {
		int x = 0,z = 0;
		//		Random randomno = new Random();
		//		// setting seed
		//		randomno.setSeed(20);
		//		do{
		//			y = (int) (Math.random() * count);	
		//		}while(mate[g][0] < 0 && y != g);
		z = (int) (Math.random() * (range + 1));
		double []tem = new double[range + 1];
		for(x = 0;x < (range + 1);x++){
			tem[x] = mate[g][x];
		}
		for(x = 0;x < z;x++){
			mate[g][x] = mate[y][x];
			mate[y][x] = tem[x];
		}
		swa(g,y,mate);

	}

	private static void swa(int g,int y,double [][]mate) {

		int []tem1 = new int[range + 1];
		int []temp = new int[range + 1];
		int cou = 0,co = 0;
//		System.out.println("before");
//		System.out.println();
//		for(int j = 0;j < range;j++){
//			System.out.printf(mate[g][j]+" ");	
//		}
//		System.out.println();
//		System.out.println();	//first fitness_function
//		for(int j = 0;j < range;j++){
//			System.out.printf(mate[y][j]+" ");	
//		}
//		System.out.println();	//first fitness_function
		for(int u = 0;u < range;u++){
			for(int v = u + 1;v < (range + 1);v++){
				if(mate[g][u] == mate[g][v]){
					tem1[cou] = u;
					if(cou < range){				
//						System.out.printf("cou= " + cou+" mate[g][u]= "+mate[g][u]); 		
						cou++;
					}
					else{
					}
				}
				if(mate[y][u] == mate[y][v]){
					temp[co] = u;
					if(co < range){						
//						System.out.printf("co= " + co+" mate[y][u]= "+mate[y][u]); 
						co++;
					}
					else{
					}				
				}
			}
		}
		double t;
		if(cou == co && cou != 0){
			for(int u = 0,v = 0;u <= cou && v <=co;u++,v++){
//				System.out.println("ffffffffffffffffff mate[g][tem[cou]]="+ mate[g][tem1[u]]+" mate[y][temp[co]]="+mate[y][temp[v]]); 
				t = mate[g][tem1[u]];
				mate[g][tem1[u]] = mate[y][temp[v]];
				mate[y][temp[v]] = t;
			}

		}
//		System.out.println("after");
//		System.out.println();
//		for(int j = 0;j < range;j++){
//			System.out.printf(mate[g][j]+" ");	
//		}
//		System.out.println();
//		System.out.println();	//first fitness_function
//		for(int j = 0;j < range;j++){
//			System.out.printf(mate[y][j]+" ");	
//		}
//		System.out.println();	//first fitness_function
	}


	private static void mutation(int g,double [][]mate) {
		int m = 0,n = 0;
		Random randomno = new Random();
		// setting seed
		randomno.setSeed(2);
		m = (int) (Math.random() * (range + 1));	
		do{
			n = (int) (Math.random() * (range + 1));	
		}while(n == m);
		double t = mate[g][m];
		mate[g][m] = mate[g][n];
		mate[g][n] = t;		
	}
}

package knn_ratings;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.util.Collections;
import java.util.Objects;

public class knn_ratings_float {
	//public static int[][] ratings = new int[10][65];
	public static int[][] ratings = new int[20][6];
	
	public static void main(String[] args){
		int[][] ratings = {//(11 user ratings 1-5 for some locations                
                {5,3,1,5,1,1,1,3,5,2,3,4,3,3,4,2,2,4,4,1,2,2,5,1,5,4,1,2,3,4,4,5,5,4,5,4,2,1,2,4,4,2,3,5,4,1,2,3,3,5,3,2,4,3,3,3,5,5,2,1,3,2,5,2,5}, 
                {5,2,2,3,5,5,1,3,2,5,1,5,4,1,5,5,4,4,2,5,5,5,3,3,1,4,2,4,3,1,4,2,4,5,2,4,2,1,4,1,4,2,2,1,3,5,4,5,3,2,2,4,3,3,3,5,1,2,5,3,2,1,2,1,4}, 
                {2,2,2,2,2,5,3,4,4,1,4,2,5,2,1,4,2,2,3,1,1,4,5,2,5,4,4,2,2,1,1,1,4,4,2,3,2,4,2,2,4,3,4,3,5,5,5,5,5,2,1,1,5,3,2,1,2,5,5,3,1,5,3,5,4}, 
                {3,5,2,4,3,4,5,5,1,1,5,4,5,1,5,5,5,4,2,4,2,4,1,5,1,1,5,4,5,5,1,1,4,4,4,4,1,5,2,1,4,5,5,1,1,4,3,3,2,5,4,5,3,2,5,4,5,2,4,4,5,1,4,3,3}, 
                {2,4,4,3,4,3,3,3,3,5,5,1,4,2,3,4,2,5,4,5,1,5,1,2,2,3,4,4,3,4,1,5,3,2,5,5,2,1,1,4,3,2,2,2,5,1,2,4,4,5,1,3,2,3,2,5,2,5,4,2,4,4,4,4,1},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0},
                {3,1,5,3,3,4,5,3,1,2,2,1,1,1,2,2,2,3,1,2,2,4,2,1,5,2,5,4,3,1,3,1,3,1,3,5,1,1,2,5,2,2,4,2,1,1,5,3,4,5,5,3,1,3,2,3,4,1,2,3,4,1,4,3,5}, 
                {1,5,2,2,5,3,5,1,2,2,1,2,2,5,5,4,3,3,4,4,4,3,1,5,2,1,2,2,5,1,5,1,1,2,1,1,5,4,1,5,2,5,5,3,2,4,5,1,4,3,2,4,5,1,1,5,4,4,1,1,1,3,2,3,1},
                {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
                {1,2,2,1,5,3,5,5,5,5,3,2,5,1,5,1,5,2,2,1,5,3,2,4,2,5,2,2,1,4,2,5,3,4,2,1,5,1,2,5,1,4,4,2,4,2,2,2,3,2,5,4,3,2,4,1,1,3,4,3,2,4,1,3,3},
                {2,4,1,2,4,2,5,1,5,4,3,1,2,3,3,1,2,1,1,1,5,2,2,3,3,3,5,1,4,4,3,4,4,1,3,5,4,5,3,1,3,1,5,1,5,4,2,1,2,5,3,5,1,2,1,5,2,3,5,1,1,4,5,5,1},
                {5,2,3,4,3,4,2,1,1,1,2,3,5,3,4,4,4,3,5,4,2,1,3,1,2,5,1,3,3,1,2,5,2,5,4,2,3,1,4,4,1,3,4,1,3,4,3,5,2,4,5,3,1,1,2,3,3,2,1,4,3,3,1,2,2}
                
              };
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter 0-11"); //pick user 5 (zero ratings)
		
		
		int num = scan.nextInt();// the user index that we will check rest users for similarity
		
		System.out.println("Choose k: ");
		int k = scan.nextInt();
		//System.out.println(num);
		scan.close();
		
		ArrayList<Integer> user = new ArrayList<Integer>();
		ArrayList<Integer> suser = new ArrayList<Integer>(); //sorted
		ArrayList<Double> sim = new ArrayList<Double>();
		ArrayList<Double> ssim = new ArrayList<Double>(); //sorted 
		
		for(int i=0; i<ratings.length; i++) {	//for all users
			//HEURISTICS for similarity
			//euclideanDist(ratings, num, i, user, sim, ssim);
			cosineSimilarity(ratings,i,num,user,sim,ssim);
			
		}

	    Collections.sort(ssim, Collections.reverseOrder()); //from smaller to bigger
	    //Collections.reverse(ssim); //descending
	    int remove = ssim.size() - k;
	    for (int i =0; i<remove;i++) {
	    	ssim.remove(ssim.size()-1); //we want the k nearest neighbours to remain
	    }
	    boolean once = true;
	    for (int i=0; i<ssim.size(); i++) {
	    	for (int j=0; j<sim.size(); j++) {
	    		if(Math.floor(ssim.get(i)*10000) == Math.floor(sim.get(j)*10000) && once && !suser.contains(user.get(j))) { // x10000 gia na emfanistoun arketa dekadika psifia wste na ginei match
	    			once = false; //if the user found above is satisfied then he has been found already
	    			suser.add(user.get(j)); //add the similar user with max cosine similarity
	    		}
	    	}
	    	once = true; 
	    }
	    
	    
	    System.out.println(user);
	    System.out.println(sim);
	    System.out.println(suser);
	    System.out.println(ssim);
	    
	    //double[] recommended = new double[ratings[0].length];

    	ArrayList <Double> recommended = new ArrayList<Double>();
    	ArrayList <String> recommended_name = new ArrayList<String>();
    	double weightedSum = 0; 
	   double similaritySum = 0; //osoi users einai pio konta tote ypologizetai 
	    
	    for(int i=0; i<ratings[0].length; i++) { //for n locations
	    	for(int j=0; j<ratings.length-1; j++) { //for m users
	    		//if(ssim.contains(sim.get(j))) {
	    		if(suser.contains(user.get(j))) { // if user is one of the knn
	    			if(ratings[num][i] == 0) { //if the rating we are considering has not been found already
	    				weightedSum += ratings[j][i]*sim.get(j); //then the prediction results from the neighbour rating at the specific location times similarity between 2 users
	    				//System.out.println(user.get(j)+","+sim.get(j));
	    				similaritySum += sim.get(j); // sum of similarity of all neighbours
	    			}
	    		}
	    		
	    	}
	    	if(weightedSum == 0) { // if there is no neigbours or all the ratings of the selected user for recommendation already exist
			double d = ratings[num][i]; //give the rating that already exist
	    		recommended.add(d);
	    		//recommended[i] = d;
	    	}
	    	else { //if he has zero rating
		    	weightedSum /= similaritySum; // divide the product of the prediction by the sum of the similars user distances to obtain the RATING
		    	recommended.add(weightedSum);
		    	//recommended[i] = weightedSum;
	    	}
	    	weightedSum=0;
	    	similaritySum=0;
	    }     
	    
    }
	

	public static void euclideanDist(int [][] ratings,int num, int i, ArrayList<Integer> user,ArrayList<Double> sim,ArrayList<Double> ssim) {
		double sumSquares = 0;
		boolean flag = true;
		for(int j=0; j<ratings[0].length; j++) { //for each location
			if(i != num && ratings[num][j]!=0 && ratings[i][j]!=0) { //an o pros eksetasi user den einai o user pou kanoume recommend (i!=num) & gia ta diathesima 
					//ratings pou ehoume apo ton user pou provlepoume alla kai tous ypoloipous users pou eksetazoume
					//euclidean
				double diff = 0;
				diff = ratings[num][j] - ratings[i][j]; 
				sumSquares += diff*diff; //to be eucledian
				flag = true;
			}
			else if (i == num) //an o xristis pros eksetasi einai o user pou theloume provlepsi
				flag = false;
		}
		if(flag) { //an kaname SUGKRISI me olous ektos apo ton xristi pou epizitoume PROVLEPSI
			double d = Math.sqrt(sumSquares); //eucledian dist
			double similarity = 1/d;
			if (Double.isInfinite(similarity)) {
				similarity = 1; //einai EXACT SAME
			}
		
			System.out.println("user: "+num+" has similarity to user: "+ i+" of value "+similarity); //similarity me tous ypoloipous users
			user.add(i);
			sim.add(similarity);
			ssim.add(similarity);
				
		//return similarity;
		}
	}
	
	public static void cosineSimilarity(int [][] ratings, int i, int num, ArrayList<Integer> user,ArrayList<Double> sim,ArrayList<Double> ssim)
	{

		double sumProduct = 0;
		double sumASq = 0;
		double sumBSq = 0;
		double similarity;
		
		for(int j=0; j<ratings[0].length; j++) { //gia oles tis topothesies
			if(i != num && ratings[num][j]!=0 && ratings[i][j]!=0) { //den endiaferomaste gia ta 0 values, diladi gi'auta pou den exoune vathmologies
				sumProduct += ratings[num][j]*ratings[i][j];
				sumASq += ratings[num][j]*ratings[num][j];
				sumBSq += ratings[i][j] * ratings[i][j];
				}
		}
		if (sumASq == 0 && sumBSq == 0) {
			similarity = 0.0;
		}
		else {
			similarity = sumProduct / (Math.sqrt(sumASq) * Math.sqrt(sumBSq));
			System.out.println("user: "+num+" has similarity to user: "+ i+" of value "+similarity); //similarity me tous ypoloipous users
		}
		
		
		user.add(i);
		sim.add(similarity);
		ssim.add(similarity);
		
	}
	
	public static void manhattanDistance(int [][] ratings, int i, int num,ArrayList<Integer> user,ArrayList<Double> sim,ArrayList<Double> ssim )
	{
		int res =0, sum =0, res1=0, sum1=0;
		boolean flag = true;
		double total;
		for (int j=0; j<ratings[0].length;j++)
		{
			if (i!=num) {
				flag = true;
				res += Math.abs(ratings[num][j]*j - sum);
				//sum += Math.abs(ratings[num][j]);
				sum += ratings[num][j];
				res1 += Math.abs(ratings[i][j]*j - sum);
				//sum1 += Math.abs(ratings[i][j]);
				sum1 += ratings[i][j];
				
			}
			else
				flag = false;
			
		}
		if (flag)
		{
			total = res + res1;
			System.out.println("user: "+num+" has similarity to user: "+ i+" of value "+ total); //similarity me tous ypoloipous users
			user.add(i);
			sim.add(total);
			ssim.add(total);
		}
	}
}

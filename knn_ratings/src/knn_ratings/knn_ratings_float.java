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
		int[][] ratings = {
				//{2,5,1,2,4,2,5,1,5,4,3,1,2,3,3,1,2,1,1,1,5,2,2,3,3,3,5,1,4,4,3,4,4,1,3,5,4,5,3,1,3,1,5,1,5,4,2,1,2,5,3,5,1,2,1,5,2,3,5,1,1,4,5,5,1},
                 
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
		System.out.println("Enter 0-9");
		
		
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
			/*for(int j=0; j<ratings[0].length; j++) { //for eacch topothesia
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
			}*/
			
			//GIA RATING DATASET EUCL, COSINE
			//euclideanDist(ratings, num, i, user, sim, ssim);
			cosineSimilarity(ratings,i,num,user,sim,ssim);
			
		}

		Collections.sort(ssim, Collections.reverseOrder()); //apo to mikrotero sto megalutero
	    //Collections.reverse(ssim); //descending
	    //int k=3; //5-nn
	    int remove = ssim.size() - k;
	    for (int i =0; i<remove;i++) {
	    	ssim.remove(ssim.size()-1); //afairw tosous wste na meinoun k
	    }
	    boolean once = true;
	    for (int i=0; i<ssim.size(); i++) {
	    	for (int j=0; j<sim.size(); j++) {
	    		if(Math.floor(ssim.get(i)*10000) == Math.floor(sim.get(j)*10000) && once && !suser.contains(user.get(j))) { // x10000 gia na emfanistoun arketa dekadika psifia wste na ginei match
	    			once = false; //an o vrethei xristis me to parapanw if na ikanopoieitai tote exei vrethei IDI MIA FORA
	    			suser.add(user.get(j)); //prosthese ton similar user me max cosine similarity
	    		}
	    	}
	    	once = true; 
	    }
	    
	    
	    System.out.println(user);
	    System.out.println(sim);
	    System.out.println(suser);
	    System.out.println(ssim);
	    
	    //double[] recommended = new double[ratings[0].length]; //vathmologies oles gia ton user

    	ArrayList <Double> recommended = new ArrayList<Double>();
    	ArrayList <String> recommended_name = new ArrayList<String>();
    	double weightedSum = 0; 
	    double similaritySum = 0; //osoi users einai pio konta tote ypologizetai 
	    
	    for(int i=0; i<ratings[0].length; i++) { //gia n topothesies
	    	for(int j=0; j<ratings.length-1; j++) { //gia m xristes
	    		//if(ssim.contains(sim.get(j))) {
	    		if(suser.contains(user.get(j))) { // an o user einai apo tous neighbors
	    			if(ratings[num][i] == 0) { //an to rating tou pros eksetasi user DEN YPARXEI AKOMI
	    				weightedSum += ratings[j][i]*sim.get(j); //tote i provlepsi prokuptei apo to rating tou neighbor sti sugkekrimeni topothesia X similarity metaksu twn 2 xrhstwn 
	    				//System.out.println(user.get(j)+","+sim.get(j));
	    				similaritySum += sim.get(j); // athroisma similarity of all neighbors
	    			}
	    		}
	    		
	    	}
	    	if(weightedSum == 0) { // an den yparxei kanenas neighbor h ola ta ratings tou user pros provlepsi yparxoun
	    		double d = ratings[num][i]; //dwse apla to rating p ehei idi dwsei
	    		recommended.add(d);
	    		//recommended[i] = d;
	    	}
	    	else { //an den ehei dwsei ratings se kapoies topothesies
		    	weightedSum /= similaritySum; // diairese to ginomeno tis problepsis me to athroisma twn distances apo similar users gia na pROKUPSEI TO RATING
		    	recommended.add(weightedSum);
		    	//recommended[i] = weightedSum;
	    	}
	    	weightedSum=0;
	    	similaritySum=0;
	    }
	    
	    
	    /*int[] maxTopo = new int[65]; //top 10 
	    double[] maxRat = new double[65];
	    maxRat[0] = 0;
	    int j=0;
	    for (int i=0; i< recommended.size();i++)
	    {
	    	if (recommended.get(i) > maxRat[j]) { //sorting kanw edw
	    		maxRat[j] = recommended.get(i);
	    		maxTopo[j] = i;
	    		j++;
	    		
	    	}
	    }
	    
	    for (k=0;k<maxRat.length;k++) {
	    	System.out.println("TOPOTHESIA: " + maxTopo[k]);
		    System.out.println("RATING: " + maxRat[k]);
	    }
	    */
	    //for(double d: recommended)
	    	//System.out.println(d);
	    
	    
    }
	

	public static void euclideanDist(int [][] ratings,int num, int i, ArrayList<Integer> user,ArrayList<Double> sim,ArrayList<Double> ssim) {
		double sumSquares = 0;
		boolean flag = true;
		for(int j=0; j<ratings[0].length; j++) { //for eacch topothesia
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
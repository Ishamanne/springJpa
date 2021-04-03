package decorative.design.pattren;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecoratorPatternCustomer {
	private static int choice;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// TODO Auto-generated method stub
		do {
			
			System.out.print("========= Food Menu ============ \n");  
	        System.out.print("            1. Vegetarian Food.   \n");  
	        System.out.print("            2. Non-Vegetarian Food.\n");  
	
	        System.out.print("            3. Exit                        \n");  
	        System.out.print("Enter your choice: ");  
	        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));   
	        choice=Integer.parseInt(br.readLine());  
	        
	        switch(choice) {
	        case 1:{
	        	VegFood v = new VegFood();
	        	System.out.println(v.prepareFood());
	        	System.out.println(v.foodPrice());
	        
	        }
	        break;
	        case 2:
	        	
	        {
	        	Food f1 = new NonVegFood((Food) new VegFood());
	        	System.out.println(f1.prepareFood());
	        	System.out.println(f1.foodPrice());
	        	
	        }
	        break;
	        default:{    
	            System.out.println("Other than these no food available");  
	        }         
	    return;  
		}
			
		}while(choice!=3);

	}

}

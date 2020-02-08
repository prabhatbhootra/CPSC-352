import java.util.*;

/*A class to accept user input and give answers to user queries*/
public class SamplingTester{

	/*A method to check whether an input string can be converted to an integer*/
	public static boolean isNumeric(String str){
		try { 
        	Integer.parseInt(str); 
    	} catch(NumberFormatException e) { 
        	return false; 
    	} catch(NullPointerException e) {
        	return false;
    	}
   		return true;
	}

	/*A main to display a menu and accept user input for queries to be run*/
	public static void main (String args[]){
		Scanner scan = new Scanner(System.in);
		boolean flag = true;
		Sampling s1 = new Sampling();
		while (flag == true){
			System.out.println("");
			System.out.println("Please choose the number selection of the query/option that you wish to run");
			System.out.println("1. How likely is the hare to win?");
			System.out.println("2. Given that it is coldwet, how likely is the hare to win?");
			System.out.println("3. Given that the Tortoise won on the short course, what is the probability distribution for the Weather?");
			System.out.println("4. Exit");
			System.out.println("Enter valid number selection: ");
			String input = scan.nextLine().trim();
			if ((input.length() <= 0) && (input.length() > 2)){
				continue;
			}
			if (!isNumeric(input)){
				continue;
			}
			if ((Integer.parseInt(input) < 1) && (Integer.parseInt(input) > 4)){
				continue;
			}
			if (Integer.parseInt(input) == 1){
				System.out.println("The probability of the hare winning using prior sampling is " + s1.queryOne());
			}
			if (Integer.parseInt(input) == 2){
				System.out.println("Given that it is coldWet, the probability of the hare winning using rejection sampling is " + s1.queryTwo());
			}
			if (Integer.parseInt(input) == 3){
				float[] f1 = s1.queryThree();
				System.out.println("Given that the Tortoise won on the short course, the probability distribution for the Weather is " + "<" + f1[0] + ", " + f1[1] + ", " + f1[2] + ">");
			}
			if (Integer.parseInt(input) == 4){
				flag = false;
			}
		}
	}
}
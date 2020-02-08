import java.util.*;
import java.lang.String;

/*
 * A class to input puzzles to be solved
 * using different search strategies
 * @author Prabhat Bhootra
 * @version 1.00 02/27/2019
 */

public class Tester{                    //Tester class
	public static void main (String args[]){
		boolean flag = false;
		boolean var;
		boolean flag1 = false;
		String num = "";
		String line = "";
		int parity = 0;
		String goalState = "";

		while (flag == false){
			flag = false;
			var = false;
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter list of 9 digits separated by spaces in row-major order: ");
			line = reader.nextLine();
			line = line.replaceAll("\\s","");
			if (line.length() == 9){
				for (int a = 0; a < 9; a++){
					for (int b = a + 1; b < 9; b++){
						if (line.charAt(a) == line.charAt(b)){
							var = true;
						}
					}
				}
				for (int k = 0; k < 9; k++){
					if ((!Character.isDigit(line.charAt(k))) || (line.charAt(k) == '9')){
						flag = false;
						break;
					}
					else{
						flag = true;
					}
				}
			}
			else{
				flag = false;
			}
			if (var == true){
				flag = false;
			}
		}

		System.out.println("");

		for (int i = 0; i < 9; i++){
			for (int j = i+1; j < 9; j++){
				if ((line.charAt(j) < line.charAt(i)) && (line.charAt(j) != '0')){
					parity++;
				}
			}
		}

		if ((parity%2) == 0){							//Determining goal state based on parity
			goalState = "123456780";
		}
		else{
			goalState = "123804765";
		}

		while (flag1 == false){
			System.out.println("Enter 1 to run BFS");
			System.out.println("Enter 2 to run A* search with heuristic h1");
			System.out.println("Enter 3 to run A* search with heuristic h2 ");
			System.out.println("Enter anything else to quit program ");
			Scanner reader1 = new Scanner(System.in);
			System.out.println("");
			num = reader1.nextLine();
			if ((num.equals("1")) || (num.equals("2")) || (num.equals("3"))){
				eightSquare e1 = new eightSquare(line, goalState);
				System.out.println("");
				System.out.println("Initial State: " + line);
				if (num.equals("1")){
					e1.BFS();
				}
				else if (num.equals("2")){
					e1.aStarH1();
				}
				else{
					e1.aStarH2();
				}
			}
			else{
				flag1 = true;
			}	
		}


	}
}
import java.util.*;
import java.lang.String;

/*
 * A class to input moves for a tic-tac-toe board
 * and to display board arrangements
 * @author Prabhat Bhootra
 * @version 1.00 03/26/2019
 */

public class Tester{                    //Tester class
	public static void main (String args[]){
		String line = "";
		boolean flag = false;
		minimax m1 = new minimax();
		System.out.println("Welcome to TTT! Make your move (row-major order):");

		while (flag == false){
			Scanner reader = new Scanner(System.in);
			line = reader.nextLine();
			line = line.replaceAll("\\s","");
			if (m1.setMove(Integer.valueOf(line))){
				m1.minimaxDecision(m1.initialState);
				if (m1.TerminalTest(m1.initialState)){
					flag = true;
					for (int i = 0; i < 3; i++){
      					if ((m1.initialState[i][0] == m1.initialState[i][1]) && (m1.initialState[i][1] == m1.initialState[i][2]) && (m1.initialState[i][0] != '-')){
        					if (m1.initialState[i][0] == 'X'){
        						System.out.println("X is the winner!");
        						return;
        					}
        					else{
        						System.out.println("O is the winner!");
        						return;
        					}
  						}
      					if ((m1.initialState[0][i] == m1.initialState[1][i]) && (m1.initialState[1][i] == m1.initialState[2][i]) && (m1.initialState[0][i] != '-')){
        					if (m1.initialState[0][i] == 'X'){
        						System.out.println("X is the winner!");
        						return;
        					}
        					else{
        						System.out.println("O is the winner!");
        						return;
        					}
      					}
					}
    				if ((m1.initialState[0][0] == m1.initialState[1][1]) && (m1.initialState[1][1] == m1.initialState[2][2]) && (m1.initialState[0][0] != '-')){
 					 	if (m1.initialState[0][0] == 'X'){
        					System.out.println("X is the winner!");
        					return;
    				 	}
    				 	else{
        					System.out.println("O is the winner!");
        					return;
    					}
    				}
    				else if ((m1.initialState[0][2] == m1.initialState[1][1]) && (m1.initialState[1][1] == m1.initialState[2][0]) && (m1.initialState[0][2] != '-')){
     				 	if (m1.initialState[0][2] == 'X'){
        					System.out.println("X is the winner!");
        					return;
    				 	}
    				 	else{
        					System.out.println("O is the winner!");
        					return;
    				 	}
   					}
   					else{
   						System.out.println("The game is a draw!");
   						return;
   					}
				}
				else{
					System.out.println("Make your move: ");
				}
			}
			else{
				System.out.println("Please make a move in a square that is empty!");
				System.out.println("Make your move: ");
			}	
		}
	}
}


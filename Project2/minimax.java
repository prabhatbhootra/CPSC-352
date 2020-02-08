import java.util.*;
import java.lang.Math;

/*
 * A class to implement the minimax
 * algorithm for tic-tac-toe
 * @author Prabhat Bhootra
 * @version 1.00 03/26/2019
 */

public class minimax{
  char[][] initialState;
  public minimax(){
    initialState = new char[3][3];
    for (int i = 0; i < 3; i++){
    	for (int j = 0; j < 3; j++){
    		initialState[i][j] = '-';
    	}
    }
  }
  
  /*A function to place an X where the user wants
   * @param n the position where X should be placed
   * @return true if it is a valid move, false otherwise
   */
  public boolean setMove(int n){
    if (n < 4){
      if (initialState[0][n-1] == '-'){
      	initialState[0][n-1] = 'X';
      	return true;
      }
      else{
      	return false;
      }
    }
    else if (n < 7){
      if (initialState[1][n-4] == '-'){
      	initialState[1][n-4] = 'X';
      	return true;
      }
      else{
      	return false;
      }
    }
    else{
      if (initialState[2][n-7] == '-'){
      	initialState[2][n-7] = 'X';
      	return true;
      }
      else{
      	return false;
      } 
    }
  }
  
  /*A function to decide which players turn it is
   * @param s the state of the board for whom the next player is to be decided
   * @return MIN if it is MIN's turn, MAX otherwise
   */
  public String Player(char[][] s){
    int xCount = 0;
    int oCount = 0;
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
        if (s[i][j] == 'X'){
          xCount++;
        }
        else if (s[i][j] == 'O'){
          oCount++;
        }
      }
    }
    if (xCount > oCount){
      return "MIN";         
    }
    else{
      return "MAX";     
    }
  }

  /*A function to return an iterable collection of future game states
   * @param s the state whose possible moves are to be examined
   * @return results a deque of possible future game states
   */
  public ArrayDeque<char[][]> Results(char[][] s){
    ArrayDeque<char[][]> results = new ArrayDeque<char[][]>();
    for (int i = 0; i < 3; i++){
      for (int j = 0; j < 3; j++){
      	char[][] newState = new char[3][3];
  		for (int a = 0; a < 3; a++){
      		for (int b = 0; b < 3; b++){
      			newState[a][b] = s[a][b];
			}
		}

        if (s[i][j] == '-'){
          if (Player(s) == "MAX"){
            newState[i][j] = 'X';
            results.add(newState);
          }
          else{
            newState[i][j] = 'O';
            results.add(newState);
          }
        }
      }
    }
    return results;
  }

  /*A function to check if a state represents the end of the game
   * @param s the state for which the test is to be carried out
   * @return true if the state is the end of the game, false otherwise
   */
  public boolean TerminalTest(char[][] s){
    for (int i = 0; i < 3; i++){
      if ((s[i][0] == s[i][1]) && (s[i][1] == s[i][2]) && (s[i][0] != '-')){
        return true;
      }
      if ((s[0][i] == s[1][i]) && (s[1][i] == s[2][i]) && (s[0][i] != '-')){
        return true;
      }
    }
    if ((s[0][0] == s[1][1]) && (s[1][1] == s[2][2]) && (s[0][0] != '-')){
      return true;
    }
    else if ((s[0][2] == s[1][1]) && (s[1][1] == s[2][0]) && (s[0][2] != '-')){
      return true;
    }
    else{
      boolean y = false;
      for (int j = 0; j < 3; j++){
      	for (int k = 0; k < 3; k++){
      		if (s[j][k] == '-'){
      			y = true;
      		}
      	}
      }
      if (y == false){
      	return true;
      }
    }
    return false;
  }

  /*A function to return the utility value of a terminal state
   * @param s the state whose utility is to be found
   * @return 1 if the user wins, -1 if the computer wins, 0 otherwise
   */
  public int Utility(char[][] s){
    for (int i = 0; i < 3; i++){
      if ((s[i][0] == s[i][1]) && (s[i][1] == s[i][2])){
        if (s[i][0] == 'X'){
          return 1;
        }
        else if (s[i][0] == 'O'){
          return -1;
        }
      }
      if ((s[0][i] == s[1][i]) && (s[1][i] == s[2][i])){
        if (s[0][i] == 'X'){
          return 1;
        }
        else if (s[0][i] == 'O'){
          return -1;
        }
      }
    }
    if ((s[0][0] == s[1][1]) && (s[1][1] == s[2][2])){
      if (s[0][0] == 'X'){
        return 1;
      }
      else if (s[0][0] == 'O'){
        return -1;
      }
    }
    else if ((s[0][2] == s[1][1]) && (s[1][1] == s[2][0])){
      if (s[0][2] == 'X'){
        return 1;
      }
      else if (s[0][2] == 'O'){
        return -1;
      }
    }
    return 0;
  }

  /*A function to return an iterable collection of a node's children based on 8 puzzle actions
   * @param s the state for which the next move is to be made
   */
  public void minimaxDecision(char[][] s){
  	boolean x = true;
  	for (int k = 0; k < 3; k++){
  		for (int h = 0; h < 3; h++){
  			if (s[k][h] == '-'){
  				x = false;
  			}
  		}
  	}
  	if (x == true){
  		return;
  	}
    Iterator itr = Results(s).iterator();
    char[][] decision = (char[][]) itr.next();
    while (itr.hasNext()){
      char[][] temp = (char[][]) itr.next();
      if (maxValue(temp) < maxValue(decision)){
        decision = temp;
      }
    }
    System.out.println(decision[0][0] + "  " + decision[0][1] + "  " + decision[0][2]);
    System.out.println(decision[1][0] + "  " + decision[1][1] + "  " + decision[1][2]);
    System.out.println(decision[2][0] + "  " + decision[2][1] + "  " + decision[2][2]);
    
    initialState = decision;
  }

  /*A function to return the value of the state closest to the computer winning
   * @param s the state whose value is to be calculated
   * @return v the minimum value of all possible moves
   */
  public int minValue(char[][] s){
    if (TerminalTest(s)){
      return Utility(s);
    }
    Integer v = Integer.MAX_VALUE;
    Iterator itr = Results(s).iterator();
    while (itr.hasNext()){
      v = Math.min(v, maxValue((char[][]) itr.next()));
    }
    return v;
  }

  /*A function to return the value of the state closest to the user winning
   * @param s the state whose value is to be calculated
   * @return v the maximum value of all possible moves
   */
  public int maxValue(char[][] s){
    if (TerminalTest(s)){
      return Utility(s);
    }
    Integer v = Integer.MIN_VALUE;
    Iterator itr = Results(s).iterator();
    while (itr.hasNext()){
      v = Math.max(v, minValue((char[][]) itr.next()));
    }
    return v;
  }
}

import java.util.*;
import java.lang.String;
import java.lang.Math;

/*
 * A class to implement searching
 * algorithms to solve the 8 puzzle
 * @author Prabhat Bhootra
 * @version 1.00 02/27/2019
 */

public class eightSquare{
	String initialState;
	String goalState;
	ArrayDeque<Node> frontier;
	ArrayDeque<Node> explored;
	public eightSquare(String iS, String gS){
		initialState = iS;
		goalState = gS;
		frontier = new ArrayDeque<Node>();
		explored = new ArrayDeque<Node>();
	}

	/*A function to return an iterable collection of a node's children based on 8 puzzle actions
	 * @param p the node whose children are to be found
	 * @return children a queue of p's child nodes
	*/
	public ArrayDeque<Node> getChildren(Node p){
		ArrayDeque<Node> children = new ArrayDeque<Node>();
		int pos = p.state.indexOf('0');
		if ((pos == 1) || (pos == 2) || (pos == 4) || (pos == 5) || (pos == 7) || (pos == 8)){
			String newState = p.state.substring(0, pos-1) + '0' + p.state.charAt(pos-1) + p.state.substring(pos+1);
			Node node1 = new Node(newState, p, "Move tile on left of blank into blank", p.pathCost + 1);
			children.add(node1);
		}
		if ((pos == 0) || (pos == 1) || (pos == 3) || (pos == 4) || (pos == 6) || (pos == 7)){
			String newState = p.state.substring(0, pos) + p.state.charAt(pos+1) + '0' + p.state.substring(pos+2);
			Node node2 = new Node(newState, p, "Move tile on right of blank into blank", p.pathCost + 1);
			children.add(node2);
		}
		if ((pos == 3) || (pos == 4) || (pos == 5) || (pos == 6) || (pos == 7) || (pos == 8)){
			String newState = p.state.substring(0, pos - 3) + '0' + p.state.substring(pos - 2, pos) + p.state.charAt(pos - 3) + p.state.substring(pos + 1);
			Node node3 = new Node(newState, p, "Move tile above blank into blank", p.pathCost + 1);
			children.add(node3);
		}
		if ((pos == 0) || (pos == 1) || (pos == 2) || (pos == 3) || (pos == 4) || (pos == 5)){
			String newState = p.state.substring(0, pos) + p.state.charAt(pos + 3) + p.state.substring(pos + 1, pos + 3) + '0' + p.state.substring(pos + 4);
			Node node4 = new Node(newState, p, "Move tile below blank into blank", p.pathCost + 1);
			children.add(node4);
		}
		return children;
	}

	/*Breadth-First Search for finding solution of 8 puzzle*/
	public void BFS(){
		long startTime = System.nanoTime();
		int count = 1;
		Stack<String> moves = new Stack<String>();
		Node initState = new Node(initialState, null, "", 0);
		if (initState.state.equals(goalState)){   //check if intital state is goal state
			System.out.println("BFS Search found solution");
			System.out.println("First three moves after initial state: ");
			System.out.println("No moves needed.");
			System.out.println("Total number of moves required: 0");
			System.out.println("Total number of search tree nodes explored: " + explored.size());
			long endTime = System.nanoTime();
			long runTime = endTime - startTime;
			System.out.println("Execution time for BFS in nanoseconds: " + runTime);
			System.out.println("");
			return;                          
		}
		frontier.add(initState);       //add initial state to frontier
		while (!frontier.isEmpty()){
			Node current = frontier.poll();
			explored.add(current);
			Iterator itr = getChildren(current).iterator();      //get children of head node of frontier
			while (itr.hasNext()){
				boolean firstTime = true;
				Node currentChild = (Node) itr.next();
				Iterator frontitr = frontier.iterator();
				Iterator exploitr = explored.iterator();
				while (frontitr.hasNext()){
					if (((Node) frontitr.next()).state.equals(currentChild.state)){
						firstTime = false;
					}
				}
				while (exploitr.hasNext()){
					if (((Node) exploitr.next()).state.equals(currentChild.state)){
						firstTime = false;
					}
				}
				if (firstTime == true){                         //check if child is goal state if it is not in frontier or explored
					if (currentChild.state.equals(goalState)){
						Node n = currentChild;
						while (!n.state.equals(initialState)){
							moves.push(n.state);
							n = n.Parent;
						}
						System.out.println("BFS Search found solution");
			            System.out.println("First three moves after initial state: ");
						while ((count < 4) && (!moves.empty())){
							System.out.println("	" + count + ".  " + moves.pop());
							count++;
						}
						System.out.println("Total number of moves required: " + currentChild.pathCost);
						System.out.println("Total number of search tree nodes explored (including root node): " + (explored.size() + 1));
						long endTime = System.nanoTime();
						long runTime = endTime - startTime;
						System.out.println("Execution time for BFS in nanoseconds: " + runTime);
						System.out.println("");
						return;
					}
					else{
						frontier.add(currentChild);
					}
				}
			}
		}
		System.out.println("BFS Search failed");                //solution not found
	}

	/*A* Search with Heuristic one for finding solution of 8 puzzle*/
	public void aStarH1(){
		int count = 1;
		Stack<String> moves = new Stack<String>();
		long startTime = System.nanoTime();
		Node initState = new Node(initialState, null, "", 0);
		HashMap<Node, Integer> f = new HashMap<Node, Integer>();
		f.put(initState, (initState.pathCost + heuristicOne(initState)));     //add initial state to frontier
		while (!f.isEmpty()){
			Iterator fitr = f.entrySet().iterator();
			Node temp = f.keySet().iterator().next();
			while (fitr.hasNext()){
				Map.Entry pair = (Map.Entry) fitr.next();
				if ((Integer) pair.getValue() < (temp.pathCost + heuristicOne(temp))){
					temp = (Node) pair.getKey();
				}
			}
			Iterator<Node> it = f.keySet().iterator();
			while(it.hasNext()){
      			Node current = it.next();
      			if(current.state.equals(temp.state)){
        			it.remove();
      			}
			}
			if (temp.state.equals(goalState)){        //check state with lowest heuristic plus path cost to see if it is goal
				Node n = temp;
				while (!n.state.equals(initialState)){
					moves.push(n.state);
					n = n.Parent;
				} 
				System.out.println("A* Search with Heuristic one found solution");
				System.out.println("First three moves after initial state: ");
				while ((count < 4) && (!moves.empty())){
					System.out.println("	" + count + ".  " + moves.pop());
					count++;
				}	
				System.out.println("Total number of moves required: " + temp.pathCost);
				System.out.println("Total number of search tree nodes explored (including root node): " + (explored.size() + 1));
				long endTime = System.nanoTime();
				long runTime = endTime - startTime;
				System.out.println("Execution time for A* Search with Heuristic one in nanoseconds: " + runTime);
				System.out.println("");
				return;                          
			}
			explored.add(temp);                      //If checked node is not goal, add to explored queue
			Iterator itr = getChildren(temp).iterator();
			while (itr.hasNext()){
				boolean firstTime = true;
				Node currentChild = (Node) itr.next();
				Iterator exploitr = explored.iterator();
				fitr = f.entrySet().iterator();
				while (fitr.hasNext()){              //If child of checked node is saved in frontier with higher value, replace it
					Map.Entry pair = (Map.Entry) fitr.next();
					if (((Node) pair.getKey()).state.equals(currentChild.state)){
						if ((currentChild.pathCost + heuristicOne(currentChild)) < (Integer) pair.getValue()){
							((Node) pair.getKey()).setParent(currentChild.Parent);
							((Node) pair.getKey()).setAction(currentChild.action);
							((Node) pair.getKey()).setPathCost(currentChild.pathCost);
							pair.setValue((currentChild.pathCost + heuristicOne(currentChild)));
						}
						firstTime = false;
					}
				}
				while (exploitr.hasNext()){
					if (((Node) exploitr.next()).state.equals(currentChild.state)){
						firstTime = false;
					}
				}
				if (firstTime == true){   //If child is neither in frontier or explored, add to frontier
					f.put(currentChild, (currentChild.pathCost + heuristicOne(currentChild)));     
				}
			}
		}
		System.out.println("A* Search with Heuristic one failed");        //Solution not found
	}

	/*A* Search with Heuristic two for finding solution of 8 puzzle*/
	public void aStarH2(){
		int count = 1;
		Stack<String> moves = new Stack<String>();
		long startTime = System.nanoTime();
		Node initState = new Node(initialState, null, "", 0);
		HashMap<Node, Integer> f = new HashMap<Node, Integer>();
		f.put(initState, (initState.pathCost + heuristicTwo(initState)));    //add initial state to frontier
		while (!f.isEmpty()){
			Iterator fitr = f.entrySet().iterator();
			Node temp = f.keySet().iterator().next();
			while (fitr.hasNext()){
				Map.Entry pair = (Map.Entry) fitr.next();
				if ((Integer) pair.getValue() < (temp.pathCost + heuristicTwo(temp))){
					temp = (Node) pair.getKey();
				}
			}
			Iterator<Node> it = f.keySet().iterator();
			while(it.hasNext()){
      			Node current = it.next();
      			if(current.state.equals(temp.state)){
        			it.remove();
      			}
			}
			if (temp.state.equals(goalState)){        //check state with lowest heuristic plus path cost to see if it is goal
				Node n = temp;
				while (!n.state.equals(initialState)){
					moves.push(n.state);
					n = n.Parent;
				} 
				System.out.println("A* Search with Heuristic two found solution");
				System.out.println("First three moves after initial state: ");
				while ((count < 4) && (!moves.empty())){
					System.out.println("	" + count + ".  " + moves.pop());
					count++;
				}	
				System.out.println("Total number of moves required: " + temp.pathCost);
				System.out.println("Total number of search tree nodes explored (including root node): " + (explored.size() + 1));
				long endTime = System.nanoTime();
				long runTime = endTime - startTime;
				System.out.println("Execution time for A* Search with Heuristic two in nanoseconds: " + runTime);
				System.out.println("");
				return;                          
			}
			explored.add(temp);                      //If checked node is not goal, add to explored queue
			Iterator itr = getChildren(temp).iterator();
			while (itr.hasNext()){
				boolean firstTime = true;
				Node currentChild = (Node) itr.next();
				Iterator exploitr = explored.iterator();
				fitr = f.entrySet().iterator();
				while (fitr.hasNext()){              //If child of checked node is saved in frontier with higher value, replace it
					Map.Entry pair = (Map.Entry) fitr.next();
					if (((Node) pair.getKey()).state.equals(currentChild.state)){
						if ((currentChild.pathCost + heuristicTwo(currentChild)) < (Integer) pair.getValue()){
							((Node) pair.getKey()).setParent(currentChild.Parent);
							((Node) pair.getKey()).setAction(currentChild.action);
							((Node) pair.getKey()).setPathCost(currentChild.pathCost);
							pair.setValue((currentChild.pathCost + heuristicTwo(currentChild)));
						}
						firstTime = false;
					}
				}
				while (exploitr.hasNext()){
					if (((Node) exploitr.next()).state.equals(currentChild.state)){
						firstTime = false;
					}
				}
				if (firstTime == true){   //If child is neither in frontier or explored, add to frontier
					f.put(currentChild, (currentChild.pathCost + heuristicTwo(currentChild)));
				}
			}
		}
		System.out.println("A* Search with Heuristic two failed");        //Solution not found
	}

	/*function to calculate heuristic one value for a given node
	 * @param n the node whose heuristic needs to be calculated
	 * @return h1 the heuristic value for node n
	 */
	public int heuristicOne(Node n){
		int h1 = 0;
		for (int i = 0; i < 9; i++){
			if ((n.state.charAt(i) != goalState.charAt(i)) && (n.state.charAt(i) != '0')){    ////FIX
				h1++;
			}
		}
		return h1;
	}

	/*function to calculate heuristic two value for a given node
	 * @param w the node whose heuristic needs to be calculated
	 * @return h2 the heuristic value for node n
	 */
	public int heuristicTwo(Node w){
		int h2 = 0;
		char[][] I = new char[3][3];
		char[][] G = new char[3][3];
		for (int i = 0; i < 9; i++){
			if (i < 3){
				I[0][i] = w.state.charAt(i);
				G[0][i] = goalState.charAt(i);
			}
			else if (i < 6){
				I[1][i-3] = w.state.charAt(i);
				G[1][i-3] = goalState.charAt(i);
			}
			else{
				I[2][i-6] = w.state.charAt(i);
				G[2][i-6] = goalState.charAt(i);
			}
		}
		for (int row = 0; row < 3; row++){
			for (int col = 0; col < 3; col++){
				for (int row1 = 0; row1 < 3; row1++){
					for (int col1 = 0; col1 < 3; col1++){
						if ((I[row][col] == G[row1][col1]) && (I[row][col] != '0')){
							h2 += Math.abs((row - row1)) + Math.abs((col - col1));
						}
					}
				}
			}
		}
		return h2;
	}
}
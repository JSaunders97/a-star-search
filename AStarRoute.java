import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AStarRoute {
	
    public ArrayList<Node> find_route(ArrayList<Node> obstacles, int goal_x, int goal_y){
    	
    	//Initialise open and closed list
    	ArrayList<Node> open = new ArrayList<Node>();
    	ArrayList<Node> closed = new ArrayList<Node>();
    	
    	//Initialise start node
      double h_start = 1.414 * Math.min(Math.abs(goal_x), Math.abs(goal_y)) + Math.max(Math.abs(goal_x), Math.abs(goal_y)) - Math.min(Math.abs(goal_x), Math.abs(goal_y));
    	Node start = new Node(null, 0, 0, 0, h_start);
    	open.add(start);
    	
    	//Store x and y location of goal 
    	//int goal_x = (int) x_goal;
    	//int goal_y = (int) y_goal;
    	Node goal = new Node(null, goal_x, goal_y, 0, 0);
    	
    	//ArrayList<Node> path to store path found
    	ArrayList<Node> path = new ArrayList<Node>();
    	
    	//Check that goal isn't an obstacle
    	if (goal.isObstacle(obstacles)) {
    		//ListTerm moves = new ListTermImpl();
        // Return empty path
    		return path;
    	}
    	
    	//Find path
    	while (!open.isEmpty()){
    		//Get Node in open list with smallest f value 
    		Node current = Collections.min(open, Comparator.comparing(node -> (node.g + node.h)));
    		
    		if (current.x == goal.x && current.y == goal.y) {
    			//If current node is goal get path
    			path = current.path_to_goal();
    			//Break if path is found
    			break;
    		}
    		  
    		//Remove current node from open list
    		open.remove(current);
    		//Add current node to closed list
    		closed.add(current);
    		
    		//Get neighbours 
    		ArrayList<Node> neighbours = current.legal_neighbours(obstacles, goal);
    		for(int i = 0; i<neighbours.size(); i++) {
    			Node neighbour = neighbours.get(i);
    			if (!neighbour.isInList(closed)) {
    				if (!neighbour.isInList(open)) {
    					open.add(neighbour);
    				}else {
    					Node open_neighbour = neighbour.getInList(open);
    					if (neighbour.g < open_neighbour.g) {
    						open_neighbour.g = neighbour.g;
    						open_neighbour.parent = neighbour.parent;
    					}
    				}
    			}
    		}
    		
    	}
    		
      //If successful return path
      return path;
    }
    

}
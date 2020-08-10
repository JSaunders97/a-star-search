import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Node {
  public Node parent;
  public int x, y;
  public double g, h;
    
  Node(Node parent, int xpos, int ypos, double g, double h) {
    this.parent = parent;
    this.x = xpos;
    this.y = ypos;
    this.g = g;
    this.h = h;
  }
   
  public boolean isInList(ArrayList<Node> check_list) {
    //Check if this node is in check_list
    ListIterator<Node> iterator = check_list.listIterator();
    while (iterator.hasNext()) {
      Node next = iterator.next();
      if(next.x == this.x && next.y == this.y) {
        return true;
      }
    }
    return false;
  }
   
  public Node getInList(ArrayList<Node> check_list) {
    //Get this node from check_list
    ListIterator<Node> iterator = check_list.listIterator();
    while (iterator.hasNext()) {
      Node next = iterator.next();
      if(next.x == this.x && next.y == this.y) {
        return next;
      }
    }
    return null;
  }  

  public int heuristic(Node that) {
    //Use diagonal heuristic when movement in all 8 directions is allowed
    return Math.max(Math.abs(this.x-that.x),Math.abs(this.y-that.y));
  }
   
  public boolean isParentNull() {
    //Check if parent of Node is empty 
    if (this.parent == null) {
      return true;
    }else {
      return false;
    }
  }

  public ArrayList<Node> path_to_goal() {
  //Gets the path from start to goal 
    ArrayList<Node> path = new ArrayList<Node>();
    Node that = this;
    while (!that.isParentNull()) {
      path.add(that);
      that = that.parent;
    }
  Collections.reverse(path);
  return path;
  }
   
	public ArrayList<Node> legal_neighbours(ArrayList<Node> obstacles, Node goal){
		//Get legal neighbours of Node
		
		//Get all possible neighbours
		ArrayList<Node> neighbours = get_neighbours(goal);

    Node obstacle;
    int x_ob, y_ob;
		
		//Remove neighbours which are obstacles
		for (int ob = 0; ob<obstacles.size(); ob++) {
			obstacle = obstacles.get(ob);
			x_ob = (int) obstacle.x;
			y_ob = (int) obstacle.y;
			
			for (int neigh = 0; neigh<neighbours.size(); neigh++) {
				Node cur_neighbour = neighbours.get(neigh);
				if(cur_neighbour.x == x_ob && cur_neighbour.y == y_ob) {
					neighbours.remove(neigh);
				}
			}
		}
		return neighbours;
	}
	
	public ArrayList<Node> get_neighbours(Node goal){
		//Get list of neighbours, may include obstacles
		ArrayList<Node> neighbours = new ArrayList<Node>();
		for (int i=-1; i<2; i++) {
			for (int j=-1; j<2; j++) {
				if (i != 0 || j != 0) {
					//Change cost to be 1.4 for diagonal
					if (i == j) {
						Node neighbour = new Node(this, this.x+i, this.y+j, this.g + 1.414, 0);
						neighbour.h = neighbour.heuristic(goal);
					    neighbours.add(neighbour);
					}else {
						Node neighbour = new Node(this, this.x+i, this.y+j, this.g + 1, 0);
						neighbour.h = neighbour.heuristic(goal);
					    neighbours.add(neighbour);
					}
					}
					
			}
		}
		return neighbours;
	}
	
	public boolean isObstacle(ArrayList<Node> obstacles){
		//Checks if this node is an obstacle

    Node obstacle;
    int x_ob, y_ob;

		for (int ob = 0; ob<obstacles.size(); ob++) {
			obstacle = obstacles.get(ob);
			x_ob = (int) obstacle.x;
			y_ob = (int) obstacle.y;
			
			if(this.x == x_ob && this.y == y_ob) {
				return true;
			}
		}
		return false;
	}
}
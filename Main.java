import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Main {
  public static void main(String[] args) {

    // Initialise A Star Route Solver
    AStarRoute solver = new AStarRoute();

    // Define Goal x and y positions
    int goal_x = 4;
    int goal_y = 4;

    //Create ArrayList of Obstacles
    ArrayList<Node> obstacles = new ArrayList<Node>();

    Node obstacle1 = new Node(null, 1, 1, 0, 0);
    obstacles.add(obstacle1);
    Node obstacle2 = new Node(null, 1, 2, 0, 0);
    obstacles.add(obstacle2);
    Node obstacle3 = new Node(null, 1, 3, 0, 0);
    obstacles.add(obstacle3);
    Node obstacle4 = new Node(null, 1, 4, 0, 0);
    obstacles.add(obstacle4);
    Node obstacle5 = new Node(null, 2, 1, 0, 0);
    obstacles.add(obstacle5);
    Node obstacle6 = new Node(null, 4, 1, 0, 0);
    obstacles.add(obstacle6);
    Node obstacle7 = new Node(null, 3, 3, 0, 0);
    obstacles.add(obstacle7);
    Node obstacle8 = new Node(null, 4, 3, 0, 0);
    obstacles.add(obstacle8);
    Node obstacle9 = new Node(null, 4, 2, 0, 0);
    obstacles.add(obstacle9);
 
    // Find Path using A Star Search
    ArrayList<Node> solution = solver.find_route(obstacles, goal_x, goal_y);

    // Print Solution
    for (int i = 0; i<solution.size(); i++) {
			Node square = solution.get(i);
			System.out.println("[ " + square.x + ", " + square.y + " ]" );
    }


  }
}
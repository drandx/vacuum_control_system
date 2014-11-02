package controlsystem.navigation;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Comparator;
import controlsystem.model.Cell;

public class PathCalculator {
	
    private static final int INFINITY = Integer.MAX_VALUE;
    private int[][] dist;
    private coordPair[][] prev;
    private PriorityQueue<Cell> pq;
    private boolean[][] marked;
    
    public PathCalculator() {
    	
    }
    
  //Comparator anonymous class implementation
    public static Comparator<Cell> distComparator = new Comparator<Cell>(){
         
        @Override
        public int compare(Cell c1, Cell c2) {
            return (c1.getSurfaceType().getCode() - c2.getSurfaceType().getCode());
        }
    };
    
    public coordPair[][] getPath() {
    	return prev;
    }
    
    // calculates the shortest path to the charging station, using Dijkstra
    // TODO - this is called when the battery is 'critical'
    public void getPathHome( ArrayDeque<coordPair> knownCells ){
    	
    	ArrayDeque<coordPair> pathHome = new ArrayDeque<coordPair>();
    	
    	coordPair root = knownCells.getFirst();
        dist = new int[knownCells.size()][knownCells.size()];
        prev = new coordPair[knownCells.size()][knownCells.size()];
        
        marked = new boolean[knownCells.size()][knownCells.size()];
        pq = new PriorityQueue<Cell>(knownCells.size(), distComparator);
    	
    	Dijkstra(knownCells, root);
    	
    	for (coordPair point : pathHome)
    		System.out.println("next cell to get back is (" + Integer.toString(point.getX()) + "," + Integer.toString(point.getY()) + ")");
    }
    
    //Dijkstra
    private coordPair[][] Dijkstra( ArrayDeque<coordPair> graph, coordPair source ) {
    	
    	dist[source.getX()][source.getY()] = 0;
    	
    	for (int v = 0; v < graph.size(); v++) {
    		for (int z = 0; z < graph.size(); z++) {
    			if (v != source.getX() && z != source.getY()) {
    				dist[v][z] = INFINITY;
    				prev[v][z] = null;
    			}
    		Cell c = new Cell();
    		coordPair pair = new coordPair();
    		pair.setX(v);
    		pair.setY(z);
    		c.setPosition(pair);
    		pq.add(c);
    		}		
    	}

        while (!pq.isEmpty()) {
        	int newDist;
            Cell cell = pq.remove();
            marked[cell.getPosition().getX()][cell.getPosition().getY()] = true;
            coordPair pair = new coordPair();
            pair.setX(cell.getPosition().getX());
            pair.setY(cell.getPosition().getY());
            for (Cell c : getNeighboringCells(graph, pair)) { //Fix this calculation to return a queue of cells not coordPairs
                if (!marked[c.getPosition().getX()][c.getPosition().getY()]) {
                	newDist = (dist[pair.getX()][pair.getY()]) + c.getSurfaceType().getCode();
                	if (newDist < dist[c.getPosition().getX()][c.getPosition().getY()]) {
                		dist[c.getPosition().getX()][c.getPosition().getY()] = newDist;
                		prev[c.getPosition().getX()][c.getPosition().getY()] = cell.getPosition(); //should change priority based on comparator
                	}
                }
            }
        }
    	return prev;
    }
    
    // This is essentially the 'getChildrenOfNode' method in Dijkstra
    // IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE
    // THIS DOES NOT CHECK TO SEE IF A CELL HAS ALREADY BEEN ADDED.  SEPERATE CALLS CAN YEILD THE SAME CELL BEING NEIGHBOR'D, WHICH
    // SLIGHTLY BREAKS DIJKSTRA.
    // This has been fixed by the commented line 'FIXED' above.
    // FIXME if there is time, clean this up and enable that functionality.
    private ArrayDeque<coordPair> getNeighboringCells( ArrayDeque<coordPair> knownCells, coordPair cell ){
    	ArrayDeque<coordPair> neighbors = new ArrayDeque<coordPair>();
    	
    	// add X + 1
    	coordPair temp = new coordPair( cell.getX() + 1, cell.getY() );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	// add X - 1
    	temp = new coordPair( cell.getX() - 1, cell.getY() );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	//add Y + 1
    	temp = new coordPair( cell.getX(), cell.getY() + 1 );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	//add Y - 1
    	temp = new coordPair( cell.getX(), cell.getY() - 1 );
    	if( isCellKnown( knownCells, temp ) )
    		neighbors.add( temp );
    	
    	return neighbors; //this means you'll probably have to do an empty check in the BFS
    }
    
    // Checks if a cell is 'known' - has the robot traversed through this cell?
    private boolean isCellKnown( ArrayDeque<coordPair> knownCells, coordPair cell ){
        boolean cellIsKnown = false;
        
        // because we don't care about efficiency, yet.
        for( coordPair coord : knownCells ){
            if( cellsAreEqual( cell, coord ) ){
                cellIsKnown = true;
                //System.out.println( "Compare: " );
                //System.out.println( "Cell: " + cell.getX() + "," + cell.getY() );
                //System.out.println( "OtherCell: " + coord.getX() + "," + coord.getY() );
                break;
            }
        }
        return cellIsKnown;
    }
    
    // are two cells equal?
    private boolean cellsAreEqual( coordPair cell1, coordPair cell2 ){
    	return ( cell1.getX() == cell2.getX() ) && ( cell1.getY() == cell2.getY() );
    }
}
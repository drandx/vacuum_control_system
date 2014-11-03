package controlsystem.navigation;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Comparator;

import controlsystem.model.Cell;

public class PathCalculator {
	
    private static final int INFINITY = Integer.MAX_VALUE;
    private int[][] dist;
    private ArrayDeque<Cell> prev;
    private PriorityQueue<Cell> pq;
    private boolean[][] marked;
    Comparator<Cell> distComparator;
    
    public PathCalculator() {
    	
    }
    
  //Comparator anonymous class implementation
    /*public static Comparator<Cell> distComparator = new Comparator<Cell>(){
         
        @Override
        public int compare(Cell c1, Cell c2) {
        	System.out.println("code = " + c1.getSurfaceType().getCode());
        	System.out.println("code = " + c2.getSurfaceType().getCode());
            return (c1.getSurfaceType().getCode() - c2.getSurfaceType().getCode());
        }
    };*/
    
    public ArrayDeque<Cell> getPath() {
    	return prev;
    }
    
    // calculates the shortest path to the charging station, using Dijkstra
    // TODO - this is called when the battery is 'critical'
    public void getPathHome( ArrayDeque<Cell> knownCells ){
    	
    	Cell root = knownCells.getFirst();
        dist = new int[knownCells.size()][knownCells.size()];
        prev = new ArrayDeque<Cell>();
        distComparator = new surfaceComparator();
        
        marked = new boolean[knownCells.size()][knownCells.size()];
        pq = new PriorityQueue<Cell>(knownCells.size(), distComparator);
    	
    	Dijkstra(knownCells, root);
    }
    
    //Dijkstra
    private ArrayDeque<Cell> Dijkstra( ArrayDeque<Cell> graph, Cell source ) {
    	
    	dist[source.getPosition().getX()][source.getPosition().getY()] = 0;
    	Cell cell = new Cell();
		coordPair p = new coordPair();
		p.setX(source.getPosition().getX());
		p.setY(source.getPosition().getY());
		cell.setPosition(p);
    	prev.add(cell);
    	
    	
    	for (int v = 0; v < graph.size(); v++) {
    		for (int z = 0; z < graph.size(); z++) {
    			if (v != source.getPosition().getX() && z != source.getPosition().getY()) {
    				dist[v][z] = INFINITY;
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
            Cell pqCell = pq.remove();
            marked[pqCell.getPosition().getX()][pqCell.getPosition().getY()] = true;
            coordPair pair = new coordPair();
            pair.setX(pqCell.getPosition().getX());
            pair.setY(pqCell.getPosition().getY());
            for (Cell c : getNeighboringCells( graph, pqCell )) { //right here
            	coordPair adjPair = new coordPair();
            	adjPair.setX(c.getPosition().getX());
            	adjPair.setY(c.getPosition().getY());
                if (!marked[c.getPosition().getX()][c.getPosition().getY()]) {
                	newDist = (dist[pair.getX()][pair.getY()]) + c.getSurfaceType().getCode();
                	if (newDist < dist[c.getPosition().getX()][c.getPosition().getY()]) {
                		dist[c.getPosition().getX()][c.getPosition().getY()] = newDist;
                		prev.add(cell);
                		pq.add(c);
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
    private ArrayDeque<Cell> getNeighboringCells( ArrayDeque<Cell> knownCells, Cell cell ){
    	ArrayDeque<Cell> neighbors = new ArrayDeque<Cell>();
    	
    	for( Cell adjacent : cell.getAdjacentCells() ) {
    		
    		if( isCellKnown( knownCells, adjacent ) )
    			neighbors.add( adjacent );
    	}
    	
    	return neighbors; //this means you'll probably have to do an empty check in the Dijkstra
    }
    
    // Checks if a cell is 'known' - has the robot traversed through this cell?
    private boolean isCellKnown( ArrayDeque<Cell> knownCells, Cell cell ){
        boolean cellIsKnown = false;
        
        // because we don't care about efficiency, yet.
        for( Cell coord : knownCells ){
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
    private boolean cellsAreEqual( Cell cell1, Cell cell2 ){
    	return ( cell1.getPosition().getX() == cell2.getPosition().getX() ) && ( cell1.getPosition().getY() == cell2.getPosition().getY() );
    }
}
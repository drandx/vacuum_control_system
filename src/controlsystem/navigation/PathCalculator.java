package controlsystem.navigation;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

import controlsystem.model.Cell;

public class PathCalculator {
	
    private static final int INFINITY = Integer.MAX_VALUE;
    private int[][] dist;
    //private HashMap<Cell, Integer> dist;
    private HashMap<Cell, Cell> prev;
    private PriorityQueue<Cell> pq;
    //private HashMap<Cell, Boolean> marked;
    Comparator<Cell> distComparator;
    
    public PathCalculator() {
    	
    }
    
    public HashMap<Cell, Cell> getPath() {
    	return prev;
    }
    
    //public HashMap<Cell, Integer> getDist() {
    	//return dist;
    //}
    
    public int[][] getDist() {
    	return dist;
    }
    
    // calculates the shortest path to the charging station, using Dijkstra
    // TODO - this is called when the battery is 'critical'
    public void getPathHome( ArrayDeque<Cell> knownCells ){
    	
    	Cell root = knownCells.getFirst();
        dist = new int[knownCells.size()][knownCells.size()];
    	//dist = new HashMap<>();
        prev = new HashMap<>();
        distComparator = new surfaceComparator();
        //marked = new HashMap<>();
        //marked = new ArrayList<>();
        
        pq = new PriorityQueue<Cell>(knownCells.size(), distComparator);
    	
    	Dijkstra(knownCells, root);
    }
    
    //Dijkstra
    private void Dijkstra( ArrayDeque<Cell> graph, Cell source ) {
    	
    	dist[source.getPosition().getX()][source.getPosition().getY()] = 0;
    	//dist.put(source, 0);
    	
    	for(Cell c : graph) {
    		if (c != source) {
    			dist[c.getPosition().getX()][c.getPosition().getY()] = INFINITY;
    			//dist.put(c, 100);
    			prev.put(c, null);
    			//marked.put(c, false);
    		}
    		pq.add(c);
    	}

        while (!pq.isEmpty()) {
            Cell cell = pq.remove();
            cell.setMarked(true);
            for (Cell c : getNeighboringCells( graph, cell )) {
                if (!c.getMarked()) { //KEEP GETTING NULL FOR SOME REASON!!!
                	int newDist = (dist[cell.getPosition().getX()][cell.getPosition().getY()]) + c.getSurfaceType().getCode();
                	System.out.println("new Dist = " + newDist);
                	if (newDist < dist[c.getPosition().getX()][c.getPosition().getY()]) {
                		dist[c.getPosition().getX()][c.getPosition().getY()] = newDist;
                		prev.put(c, cell);
                		pq.add(c);
                	}
                }
            }
        }
    	
    	/*while (!pq.isEmpty()) {
        Cell cell = pq.remove();
        marked.put(cell, true);
        for (Cell c : getNeighboringCells( graph, cell )) {
            if (marked.equals(null)) {
            	int newDist = (dist.get(cell)) + c.getSurfaceType().getCode();
            	if (newDist < dist.get(c)) {
            		dist.put(c, newDist);
            		prev.put(c, cell);
            		pq.add(c);
            	}
            }
        }
    }*/
    }
    
    // This is essentially the 'getChildrenOfNode' method in Dijkstra
    // IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE
    // THIS DOES NOT CHECK TO SEE IF A CELL HAS ALREADY BEEN ADDED.  SEPERATE CALLS CAN YEILD THE SAME CELL BEING NEIGHBOR'D, WHICH
    // SLIGHTLY BREAKS DIJKSTRA.
    // This has been fixed by the commented line 'FIXED' above.
    // FIXME if there is time, clean this up and enable that functionality.
    private ArrayDeque<Cell> getNeighboringCells( ArrayDeque<Cell> knownCells, Cell cell ) {
    	ArrayDeque<Cell> neighbors = new ArrayDeque<Cell>();
    	
    	for( Cell adjacent : cell.getAdjacentCells() ) {
    		
    		//if( isCellKnown( knownCells, adjacent ) ) //if getKnownCell != null, add the known cell
    		Cell temp = getKnownCell( knownCells, adjacent );
    		
    		if( temp != null )
    			neighbors.add( temp );
    	}
    	
    	return neighbors; //this means you'll probably have to do an empty check in the Dijkstra
    }
    
    // Checks if a cell is 'known' - has the robot traversed through this cell?
    // TODO: May not be needed, anymore
    private boolean isCellKnown( ArrayDeque<Cell> knownCells, Cell cell ) {
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
    private boolean cellsAreEqual( Cell cell1, Cell cell2 ) {
    	return ( cell1.getPosition().getX() == cell2.getPosition().getX() ) && ( cell1.getPosition().getY() == cell2.getPosition().getY() );
    }
    
    /**
     * This gets the known cell data based on an input 'dummy' cell
     * 
     * @param knownCells
     * @param cell
     * @return
     */
    private Cell getKnownCell( ArrayDeque<Cell> knownCells, Cell cell ) {
    	
    	Cell foundCell = null;
    	
    	for( Cell temp : knownCells ) {
    		if( cellsAreEqual( cell, temp) ) {
    			foundCell = temp;
    			break;
    		}
    	}
    	
    	return foundCell;
    }
}
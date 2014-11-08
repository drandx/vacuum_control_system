package controlsystem.navigation;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;

import controlsystem.model.Cell;

public class PathCalculator {
	
    private static final int INFINITY = Integer.MAX_VALUE;
    private HashMap<Cell, Integer> dist;
    private HashMap<Cell, Cell> prev;
    private PriorityQueue<Cell> pq;
    Comparator<Cell> distComparator;
    
    public PathCalculator() {
    	
    }
    
    //This gets the HashMap of all the cells and their corresponding next cell back
    public HashMap<Cell, Cell> getPathMap() {
    	return prev;
    }
    
    //This gets the HashMap of all the cells and their corresponding distance to charging station
    public HashMap<Cell, Integer> getDistMap() {
    	return dist;
    }
    
    //This will return the cell that the robot would go to next to get back to station based on current cell
    public Cell getNextCellBack(Cell current) {
    	return prev.get(current);
    }
    
    //This will return the distance (in battery power) that it will take to get robot back to sation based on current cell
    public int getMinDist(Cell current) {
    	return dist.get(current);
    }
    
    // calculates the shortest path to the charging station, using Dijkstra
    // TODO - this is called when the battery is 'critical'
    public void getPathHome( ArrayDeque<Cell> knownCells ){
    	
    	Cell root = knownCells.getFirst();
    	dist = new HashMap<>();
        prev = new HashMap<>();
        distComparator = new distComp();
        
        pq = new PriorityQueue<Cell>(knownCells.size(), distComparator);
    	
    	Dijkstra(knownCells, root);
    }
    
    //Dijkstra
    private void Dijkstra( ArrayDeque<Cell> graph, Cell source ) {
    	
    	dist.put(source, 0);
    	ArrayDeque<Cell> searchedCells = new ArrayDeque<Cell>();
    	
    	for(Cell c : graph) {
    		if (c != source) {
    			dist.put(c, INFINITY);
    			prev.put(c, null);
    		}
    		pq.add(c);
    	}
    	
    	while (!pq.isEmpty()) {
    		Cell cell = pq.poll();
    		searchedCells.add(cell);
	        for (Cell c : getNeighboringCells( graph, cell )) {
	            if(!searchedCells.contains(c)) {
	            	int newDist = dist.get(cell) + c.getSurfaceType().getCode();
	            	if (newDist < dist.get(c)) {
	            		dist.put(c, newDist);
	            		prev.put(c, cell);
	            		pq.remove(c);
	            		pq.add(c);
	            	}
	            }
	        }
    	}
    }
    
    public class distComp implements Comparator<Cell> {    	
    	@Override
    	public int compare(Cell c1, Cell c2) {
    		return(dist.get(c1) - dist.get(c2));
    	}
    }
    
    // This is essentially the 'getChildrenOfNode' method in Dijkstra
    // IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE IMPORTANT NOTE
    // THIS DOES NOT CHECK TO SEE IF A CELL HAS ALREADY BEEN ADDED.  SEPERATE CALLS CAN YEILD THE SAME CELL BEING NEIGHBOR'D, WHICH
    // SLIGHTLY BREAKS DIJKSTRA.
    // This has been fixed by the commented line 'FIXED' above.
    // FIXME if there is time, clean this up and enable that functionality.
    private ArrayDeque<Cell> getNeighboringCells( ArrayDeque<Cell> knownCells, Cell cell ) {
    	ArrayDeque<Cell> neighbors = new ArrayDeque<Cell>();
    	
    	for( Cell adjacent : cell.getDummyAdjacentCells() ) {//cell.getAdjacentCells() ) {
    		
    		//if( isCellKnown( knownCells, adjacent ) ) //if getKnownCell != null, add the known cell
    		Cell temp = getKnownCell( knownCells, adjacent );
    		
    		if( temp != null )
    			neighbors.add( temp );
    	}
    	
    	return neighbors; //this means you'll probably have to do an empty check in the Dijkstra
    }
    
    // Checks if a cell is 'known' - has the robot traversed through this cell?
    //TODO remove this i dont think we will need it
    /*private boolean isCellKnown( ArrayDeque<Cell> knownCells, Cell cell ) {
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
    }*/
    
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
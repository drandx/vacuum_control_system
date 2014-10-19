package controlsystem.navigation;

import java.util.ArrayDeque;

public class PathCalculator {
	
    //private ArrayDeque<coordPair> m_pathHome; probably unneeded.
    
    private coordPair m_chargerLoc;
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[][] marked;  // marked[v] = is there an s-v path
    private coordPair[][] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[][] distTo;      // distTo[v] = number of edges shortest s-v path
    
    public PathCalculator() {
    //public PathCalculator( coordPair homeIn ){
         //m_knownPath = pathHistory;
    	 //Do we need this? I think the charger location is always at 0,0
         //m_chargerLoc = homeIn;
    }
    
    // calculates the shortest path to the charging station, using BreathFirstSearch
    // TODO - this is called when the battery is 'critical'
    public ArrayDeque<coordPair> getPathHome( ArrayDeque<coordPair> knownCells ){
    	coordPair root = knownCells.getFirst();
    	marked = new boolean[knownCells.size()][knownCells.size()];
        distTo = new int[knownCells.size()][knownCells.size()];
        edgeTo = new coordPair[knownCells.size()][knownCells.size()];
    	
    	return BFS( knownCells, root );
    }
    
    //Breadth-first-search
    private ArrayDeque<coordPair> BFS( ArrayDeque<coordPair> graph, coordPair node ) {
    	
    	ArrayDeque<coordPair> pathHome = new ArrayDeque<coordPair>();
    	
    	for (int v = 0; v < graph.size(); v++) {
    		for (int z = 0; z < graph.size(); z++)
    			distTo[v][z] = INFINITY;
    	}
        distTo[node.getX()][node.getY()] = 0;
        marked[node.getX()][node.getY()] = true;
        pathHome.add(node);

        while (!pathHome.isEmpty()) {
            coordPair pair = pathHome.remove();
            for (coordPair w : getNeighboringCells(graph, pair)) {
                if (!marked[w.getX()][w.getY()]) {
                    edgeTo[w.getX()][w.getY()] = pair;
                    distTo[w.getX()][w.getY()] = distTo[pair.getX()][pair.getY()] + 1;
                    marked[w.getX()][w.getY()] = true;
                    pathHome.add(w);
                }
            }
        }
    	
    	//TODO - implement BFS...
    	
    	return pathHome;
    }
    
    // This is essentially the 'getChildrenOfNode' method in BFS
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
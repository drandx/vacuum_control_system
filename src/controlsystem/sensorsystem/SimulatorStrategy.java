package controlsystem.sensorsystem;
import controlsystem.model.*;
import controlsystem.model.Cell;
import controlsystem.navigation.coordPair;
import simulator.model.*;

public class SimulatorStrategy implements SensorStrategy{

	/**
	 * Returns the Cell with its adjacent cells with the according state.
	 * 
	 */
	public Cell getCell(coordPair position) {
		HomeModel.Load();
		controlsystem.model.Cell newCell = new Cell();
		
		for(FloorLevel floor : HomeModel.loadedHome.getFloors())
		{
			for(simulator.model.Cell cell : floor.getCells() )
			{
				if((cell.getXs() == position.getX()) && (cell.getYs() == position.getY()))
				{
					
					if(position.getX() == 0 && position.getY() == 0)
						newCell.setState(CellState.OPEN);
					newCell.setDirtUnits(cell.getDs());
					newCell.setIsStation(cell.getCs() == 1 ? true : false);
					newCell.setPosition(position);
					newCell.setSurfaceType(SurfaceType.getStatus(cell.getSs()));
					
					String path = cell.getPs();
					
					//West cell
					for (int i = 0; i < path.length(); i++) {
						Cell adjCell = new Cell();
						
						int x=position.getX();
						int y=position.getY();
						
						switch (i) {
						case 1:
							x = x - 1;
							break;
						case 0:
							x = x + 1;
							break;
						case 3:
							y = y - 1;
							break;
						case 2:
							y = y + 1;
							break;

						default:
							break;
						}
						
						adjCell.setPosition(new coordPair(x,y));
						CellState state = CellState.getStatus(Character.getNumericValue(path.charAt(i)));						
						adjCell.setState(state);
						newCell.getAdjacentCells().add(i, adjCell);
					}
					
					break;					
				}
			
			}
			
		}
		
		return newCell;
	}	
	
	
	

}

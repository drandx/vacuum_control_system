package simulator.GUI;

import java.util.ArrayList;
import java.util.Random;

import simulator.model.Cell;
import simulator.model.HomeModel;
import simulator.GUI.GUIWindow;

public class ProcessMovement 
{
	ArrayList<Cell> floorplanCells = new ArrayList<Cell>();
	
    private int oneX = 0;
    private int oneY = 925;
    
    private int xCoord = 0;
    private int yCoord = 0;
    
    private int xDirection = 1;
    private int yDirection = 0;

    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;
        
    public ProcessMovement()
    {
    	HomeModel.Load();
    	getCells();
    }
    
	public void moveVacuum()
	{			
		//while(true)
		{
			if( !(xCoord == 0 && yCoord == 0))
			{
				calculateNewPosition();
			}
			else
			{
				xCoord += 1;
			    yDirection = 0;
			    xDirection = 1;
			}
			
			oneX += (xDirection * 100);
			oneY +=  (yDirection *100);
			try{ Thread.sleep(1000);} catch(Exception e){}
		}
	}   
	
	private void calculateNewPosition()
	{
		//Cleaner starts at coords 0,0, which is 1000,1000 in this window
		
		
		Cell currentCell = null;
		
		// 1 is open, 2 is obstacle, 4 is stairs
		for(int find = 0; find < floorplanCells.size(); find++)
		{
		    currentCell = floorplanCells.get(find);
		    
		    if(currentCell.getXs() == xCoord && currentCell.getYs() == yCoord)
		    {
		    	break;
		    }
		}
		
		String cellSensors = currentCell.getPs();
		
		char xPos = cellSensors.charAt(0);
		char xNeg = cellSensors.charAt(1);
		char yPos = cellSensors.charAt(2);
		char yNeg = cellSensors.charAt(3);
		
		
		//***************************************************************** ACCOUNT FOR CORNERS FIRST ****************************************************************
		
		// SE CORNER - 1000,1000 - 0,10
		if(xPos != '1' && yNeg != '1')
		{
			if(xDirection == 0) // Approaching corner from the north - head west
			{
				yDirection = 0;
				xDirection = -1;
				xCoord -= 1;
			}
			else // Approaching along wall from the west - head north
			{
				yDirection = -1;
				xDirection = 0;
				yCoord += 1;
			}
		}
		
		// NE CORNER - 1000,0 - 10,10
		else if(xPos != '1' && yPos != '1')
		{
			if(xDirection == 0) // Approaching corner from the south - head west
			{
				yDirection = 0;
				xDirection = -1;
				xCoord -= 1;
			}
			else // Approaching along wall from the west - head south
			{
				yDirection = 1;
				xDirection = 0;
				yCoord -= 1;
			}
		}
		
		// NW CORNER - 0,0 - 0,10
		else if(xNeg != '1' && yPos != '1')
		{
			if(xDirection == 0) // Approaching corner from the south - head east
			{
				yDirection = 0;
				xDirection = 1;
				xCoord += 1;
			}
			else // Approaching along wall from the east - head south
			{
				yDirection = 1;
				xDirection = 0;
				yCoord -= 1;
			}
		}
		
		// SW CORNER - 0,1000, 0,0
		else if(xNeg != '1' && yNeg != '1')
		{
			if(xDirection == 0) // Approaching corner from the north - head east
			{
				yDirection = 0;
				xDirection = 1;
				xCoord += 1;
			}
			else // Approaching along wall from the east, head north
			{
				yDirection = -1;
				xDirection = 0;
				yCoord += 1;
			}
		}
		
		//***************************************************************** ACCOUNT FOR WALLS ****************************************************************
		
		//Heading towards south wall from north
		else if(yNeg != '1' && yDirection == -1)
		{
			yDirection = 0;
			
			int pickDirection = getRandomDirection(2);
			
			//If 0, then head west. if 1, then head east
			if(pickDirection == 0)
			{
				xDirection = -1;
				xCoord -= 1;
			}
			else
			{
				xDirection = 1;
				xCoord += 1;
			}
		}
		
		//Heading towards east wall from west
		else if(xDirection == 1 && xPos != '1')
		{
			xDirection = 0;
			
			int pickDirection = getRandomDirection(2);
			
			//If 0, then head south. if 1, then head north
			if(pickDirection == 0)
			{
				yDirection = 1;
				yCoord -= 1;
			}
			else
			{
				yDirection = -1;
				yCoord += 1;
			}
		}
		
		//Heading towards north wall from south
		else if (yPos != '1' && yDirection == 1)
		{
			yDirection = 0;
			
			int pickDirection = getRandomDirection(2);
			
			//If 0, then head west. if 1, then head east
			if(pickDirection == 0)
			{
				xDirection = -1;
				xCoord -= 1;
			}
			else
			{
				xDirection = 1;
				xCoord += 1;
			}
		}
		
		//Heading towards west wall from east
		else if (xDirection == -1 && xNeg != '1')
		{
			xDirection = 0;
			
			int pickDirection = getRandomDirection(2);
			
			//If 0, then head south. if 1, then head north
			if(pickDirection == 0)
			{
				yDirection = 1;
				yCoord -= 1;
			}
			else
			{
				yDirection = -1;
				yCoord += 1;
			}
		}
		
		//***************************************************************** NO OBSTACLES ****************************************************************
		// If there are no obstacles at any sensor, then pick a random direction
		else if(xPos == '1' && xNeg == '1' && yPos == '1' && yNeg == '1')
		{
			int pickDirection = getRandomDirection(4);
			
			//If 0, then head south. if 1, then head north. if 2 then head west. if 3, then head east
			if(pickDirection == 0)
			{
				xDirection = 0;
				yDirection = 1;
				yCoord -= 1;
			}
			else if(pickDirection == 1)
			{
				xDirection = 0;
				yDirection = -1;
				yCoord += 1;
			}
			else if(pickDirection == 2)
			{
				yDirection= 0;
				xDirection = -1;
				xCoord -= 1;
			}
			else
			{
				yDirection = 0;
				xDirection = 1;
				xCoord += 1;
			}
		}
		
		//***************************************************************** STUCK IN CLOSET ****************************************************************
		// If N, W, and S are obstructed, head east
		else if(xPos == '1' && xNeg != '1' && yPos != '1' && yNeg != '1')
		{
			xDirection = 1;
			xCoord += 1;
		}
			
		// If N, E, and S are obstructed, head west
		else if(xPos == '1' && xNeg != '1' && yPos != '1' && yNeg != '1')
		{
			xDirection = -1;
			xCoord -= 1;
		}
			
		// Continue in current direction
		else
		{
			if(xDirection != 0)
			{
				xCoord += xDirection;
			}
			else
			{
				if(yDirection == -1)
				{	
					yCoord += 1;
				}
				else
				{
					yCoord -= 1;
				}
			}
		}
		
	}
	
	private void getCells()
	{
		int numFloors = 0;
		
		for(int j = 0; j < HomeModel.loadedHome.getFloors().get(numFloors).getCells().size(); j++)
		{
		    Cell currentCell = HomeModel.loadedHome.getFloors().get(numFloors).getCells().get(j);
		    floorplanCells.add(currentCell);
		}
	}
	
	private int getRandomDirection(int numDirections)
	{
		
		Random random = new Random(System.currentTimeMillis());
		
		return random.nextInt(numDirections);
		
	}
	public int getXValue()
	{
		return oneX;
	}
	
	public int getYValue()
	{
		return oneY;
	}
	

}

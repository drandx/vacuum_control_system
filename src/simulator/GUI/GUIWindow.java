package simulator.GUI;

import simulator.model.Cell;
import simulator.model.HomeModel;

import java.util.*;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.*;

public class GUIWindow extends Frame{


	JFrame frame;
	DrawElements drawElements;
	
	ArrayList<Cell> floorplanCells = new ArrayList<Cell>();
	
    private int oneX = 0;
    private int oneY = 900;
    
    private int xCoord = 0;
    private int yCoord = 0;
    
    private int xDirection = 1;
    private int yDirection = 0;

    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;
	
   public GUIWindow()
   {
       HomeModel.Load();
	   createWindow();
	   getCells();
   }
	
	public void createWindow()
	{
	    frame = new JFrame("Test");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    drawElements = new DrawElements();
	    
	    frame.getContentPane().add(BorderLayout.CENTER, drawElements);
	    
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.setSize(1000,1000);
	    frame.setLocation(0,0);
	    
	}
	
	public void moveVacuum()
	{	
		while(true)
		{
			calculateNewPosition();
			for(int move = 0; move < 100; move ++)
			{
				oneX += xDirection;
				oneY += yDirection;
				frame.repaint();
				try{ Thread.sleep(20);} catch(Exception e){}
			}
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
		if(xPos != 1 && yNeg != 1)
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
		if(xPos != 1 && yPos != 1)
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
		if(xNeg != 1 && yPos != 1)
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
		if(xNeg != 1 && yNeg != 1)
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
				yCoord = 1;
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
	class DrawElements extends JPanel {
	    public void paintComponent(Graphics g) {

	        g.setColor(Color.BLACK);
	        g.fillRect(oneX, oneY-25, 100, 100);
	    }
	}
}




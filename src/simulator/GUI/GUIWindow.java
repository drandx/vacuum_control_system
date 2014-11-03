package simulator.GUI;



import java.util.*;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

import java.awt.*;

public class GUIWindow extends Frame{


	JFrame frame;
	DrawElements drawElements;
	
	ProcessMovement movement = new ProcessMovement();
	
	int xPos = 0;
	int yPos = 925;
	
   public GUIWindow()
   {
	   createWindow();
	   
	   while(true)
	   {
		   movement.moveVacuum();
		   xPos = movement.getXValue();
		   yPos = movement.getYValue();
		   
		   frame.repaint();
	   }
   }
	
	public void createWindow()
	{
	    frame = new JFrame("Test");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    drawElements = new DrawElements();
	    
	    frame.getContentPane().add(BorderLayout.CENTER, drawElements);
	    
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.setSize(1000,1100);
	    frame.setLocation(0,0);
	    frame.setBackground(Color.BLACK);
	}
	
	class DrawElements extends JPanel {
	    public void paintComponent(Graphics g) {
	    	
	    	xPos = movement.getXValue();
	    	yPos = movement.getYValue();
	    	
	    	//Grid Lines - For Debugging
	    	
	    	/*
	    	g.setColor(Color.YELLOW);
	    	g.drawLine(0, 0, 0,1000);
	    	g.drawLine(100, 0,100,1000);
	    	g.drawLine(200, 0,200,1000);
	    	g.drawLine(300, 0,300,1000);
	    	g.drawLine(400, 0,400,1000);
	    	g.drawLine(500, 0,500,1000);
	    	g.drawLine(600, 0,600,1000);
	    	g.drawLine(700, 0,700,1000);
	    	g.drawLine(800, 0,800,1000);
	    	g.drawLine(900, 0,900,1000);
	    	g.drawLine(1000, 0,1000,1000);
	    	
	    	g.drawLine(0,100,1000,100);
	    	g.drawLine(0,200,1000,200);
	    	g.drawLine(0,300,1000,300);
	    	g.drawLine(0,400,1000,400);
	    	g.drawLine(0,500,1000,500);
	    	g.drawLine(0,600,1000,600);
	    	g.drawLine(0,700,1000,700);
	    	g.drawLine(0,800,1000,800);
	    	g.drawLine(0,900,1000,900);
	    	g.drawLine(0,1000,1000,1000);
	    	*/
	    	
	    	// Test floorplan - Walls
	    	g.setColor(Color.WHITE);
	    	g.drawLine(0,0,0,1000);
	    	g.drawLine(1000, 1000, 0, 1000);
	    	g.drawLine(0, 0, 1000, 0);
	    	g.drawLine(1000,0, 1000, 1000);
	    	g.drawLine(400,1000,400,700);
	    	g.drawLine(400,600,400,300);
	    	g.drawLine(400,200,400,0);
	    	g.drawLine(0,300,400,300);
	    	g.drawLine(0,500,400,500);
	    	g.drawLine(600, 1000, 600, 300);
	    	g.drawLine(800,0,800,300);
	    	g.drawLine(800, 300, 1000, 300);
	    	g.drawLine(400, 100, 800, 100);
	    	g.drawLine(400, 200, 600, 200);
	    	g.drawLine(600,200,600,100);
	    	g.drawLine(0, 400, 400, 400);
	    	g.drawLine(200, 300, 200, 400);
	    	
	    	// Test floorplan - Doors
	    	g.setColor(Color.GREEN);
	    	g.drawLine(100,500,300,500);
	    	g.drawLine(400, 600, 400, 700);
	    	g.drawLine(0, 300, 200, 300);
	    	g.drawLine(400, 300, 400, 400);
	    	g.drawLine(400, 200, 400, 300);
	    	g.drawLine(400, 200, 600, 200);
	    	g.drawLine(600,200,600,300);
	    	g.drawLine(600, 100, 800, 100);
	    	g.drawLine(800,100,800,200);
	    	
	        g.setColor(Color.BLUE);
	        g.fillRect(xPos, yPos - 25, 100, 100);
	    }
	}
}




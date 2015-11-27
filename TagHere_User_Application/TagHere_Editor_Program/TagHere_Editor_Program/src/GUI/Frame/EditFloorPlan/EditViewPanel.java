package GUI.Frame.EditFloorPlan;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import FloorPlan.FloorPlan;
import FloorPlanObject.FPObject;
import FloorPlanObject.Line;
import FloorPlanObject.ObjectType;
import FloorPlanObject.Oval;
import FloorPlanObject.Rectangle;
import Resource.ColorR;
import Resource.DataProvider;
import Resource.DimenR;

public class EditViewPanel extends JPanel implements MouseListener, MouseMotionListener {
	
	/** For approach parent frame */
	EditFloorPlanFrame context;
	
	/** For approach current floor plan */
	private FloorPlan floorPlan;
	
	/** New object to insert floor plan */
	private FPObject tempObject;
	
	/** Decide now drawing or not */
	private boolean drawing = false;
	
	/** Current cursor position */
	private int cursorX;
	private int cursorY;
	
	private ArrayList<FPObject> obejects;
	
	
	public EditViewPanel(EditFloorPlanFrame context, int width, int height, FloorPlan floorPlan) {
		super();
		this.floorPlan = floorPlan;
		this.context = context;
		this.setLayout(null);
		
		obejects = new ArrayList<FPObject>();
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(ColorR.GRAY, 1));
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	/**
	 * When press mouse, set start position of tempObject and
	 * make new tempObject of appropriate type
	 * @param e MouseEvent
	 * @author DongKyu
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(DataProvider.getInstance().getCurrentObjectType() != null)
			switch(DataProvider.getInstance().getCurrentObjectType()) {
			case ICON:
				setDrawing(true);
				break;
				
			case LINE:
				tempObject = new Line(e.getX(), e.getY());
				setDrawing(true);
				break;
				
			case OVAL:
				tempObject = new Oval(e.getX(), e.getY());
				setDrawing(true);
				break;
				
			case RECTANGLE:
				tempObject = new Rectangle(e.getX(), e.getY());
				setDrawing(true);
				break;
				
			case TAG:
				setDrawing(true);
				break;
			}
	}

	/**
	 * When release mouse, set end position of tempObject and
	 * add tempObject to object list
	 * @param e MouseEvent
	 * @author DongKyu
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		setDrawing(false);
		if(DataProvider.getInstance().getCurrentObjectType() != null)
			switch(DataProvider.getInstance().getCurrentObjectType()) {
			case ICON:
				break;
				
			case LINE:
				((Line)tempObject).setEndPosition(new Point(e.getX(), e.getY()));
				obejects.add((Line)tempObject);
				break;
				
			case OVAL:
				((Oval)tempObject).setEndPosition(new Point(e.getX(), e.getY()));
				obejects.add((Oval)tempObject);
				break;
				
			case RECTANGLE:
				((Rectangle)tempObject).setEndPosition(new Point(e.getX(), e.getY()));
				obejects.add((Rectangle)tempObject);
				break;
				
			case TAG:
				break;
			}
	}

	/**
	 * During dragging, continuously get cursor position and repaint panel
	 * */
	@Override
	public void mouseDragged(MouseEvent e) {
		cursorX = e.getX();
		cursorY = e.getY();
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
        ((Graphics2D)g).setRenderingHint(
        		RenderingHints.KEY_ANTIALIASING,
        		RenderingHints.VALUE_ANTIALIAS_ON
        		);
        
		/** Draw all other objects */
		for(FPObject object : obejects)
			drawObject(
					g,
					object.getType(),
					object.getStartPosition(),
					object.getEndPosition()
					);
		
		/** Draw current object */
		if(isDrawing() && DataProvider.getInstance().getCurrentObjectType() != null) {
			drawObject(
					g,
					DataProvider.getInstance().getCurrentObjectType(),
					new Point(tempObject.getStartPosition().x, tempObject.getStartPosition().y),
					new Point(cursorX, cursorY)
					);
		}
	}
	
	/**
	 * Draw object
	 * Line - Draw line at start position to end position.
	 * Oval - Draw oval, start position is center position and end position is radius of oval.
	 * Rectangle - Draw rectangle, start position is center position and end position is size of rectangle.
	 * @param g Graphics
	 * @param type Object Type
	 * @param start Start position (Point)
	 * @param end End position (Point)
	 * @author DongKyu
	 * */
	public void drawObject(Graphics g, ObjectType type, Point start, Point end) {
		switch(type) {
		case ICON:
			break;
			
		case LINE:
			g.drawLine(
					start.x,
					start.y,
					end.x,
					end.y
					);
			
			g.drawOval(
					start.x - Math.abs(DimenR.VERTEX_RADIUS),
					start.y - Math.abs(DimenR.VERTEX_RADIUS),
					Math.abs(DimenR.VERTEX_RADIUS) * 2,
					Math.abs(DimenR.VERTEX_RADIUS) * 2
					);
			
			g.drawOval(
					end.x - Math.abs(DimenR.VERTEX_RADIUS),
					end.y - Math.abs(DimenR.VERTEX_RADIUS),
					Math.abs(DimenR.VERTEX_RADIUS) * 2,
					Math.abs(DimenR.VERTEX_RADIUS) * 2
					);
			break;
			
		case OVAL:
			g.drawOval(
					start.x - Math.abs(DimenR.VERTEX_RADIUS),
					start.y - Math.abs(DimenR.VERTEX_RADIUS),
					Math.abs(DimenR.VERTEX_RADIUS) * 2,
					Math.abs(DimenR.VERTEX_RADIUS) * 2
					);
			
			g.drawOval(
					start.x - Math.abs(end.x - start.x),
					start.y - Math.abs(end.y - start.y),
					Math.abs(start.x - end.x) * 2,
					Math.abs(start.y - end.y) * 2
					);
			break;
			
		case RECTANGLE:
			g.drawRect(
					start.x - Math.abs(end.x - start.x),
					start.y - Math.abs(end.y - start.y),
					Math.abs(start.x - end.x) * 2,
					Math.abs(start.y - end.y) * 2
					);
			
			g.drawOval(
					end.x - Math.abs(DimenR.VERTEX_RADIUS),
					end.y - Math.abs(DimenR.VERTEX_RADIUS),
					Math.abs(DimenR.VERTEX_RADIUS) * 2,
					Math.abs(DimenR.VERTEX_RADIUS) * 2
					);
			break;
			
		case TAG:
			break;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	public boolean isDrawing() {
		return drawing;
	}

	public void setDrawing(boolean drawing) {
		this.drawing = drawing;
	}
}

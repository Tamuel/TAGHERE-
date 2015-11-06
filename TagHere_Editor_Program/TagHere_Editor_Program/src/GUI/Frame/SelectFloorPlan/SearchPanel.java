package GUI.Frame.SelectFloorPlan;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

import GUI.Component.SimpleButton;

/**
 * JPanel for search floor plan
 * @author DongKyu
 *
 */
public class SearchPanel extends JPanel {
	
	/** Buttons for floor plan list */
	private ArrayList<SimpleButton> floorPlanButtons;
	
	public SearchPanel(int width, int height) {
		super();
		
		floorPlanButtons = new ArrayList<SimpleButton>();
		
		this.setLayout(null);
		this.setBorder(null);
		this.setSize(new Dimension(width, height));
	}
	
}

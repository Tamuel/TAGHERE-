package GUI.Frame.EditFloorPlan;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import FloorPlanObject.ObjectType;
import GUI.Component.SimpleButton;
import GUI.Component.SimpleSelectButton;
import Resource.ColorR;
import Resource.DataProvider;
import Resource.DimenR;

public class ToolsPanel extends JPanel {

	/** Get object types */
	private ObjectType objectType;
	/** Buttons for object list */
	private ArrayList<SimpleSelectButton> buttons;
	
	private int xBorder = 10;
	private int yBorder = 10;
	
	public ToolsPanel(int width, int height) {
		super();
		this.setLayout(null);
		
		buttons = new ArrayList<SimpleSelectButton>();
		
		int buttonWidth = (width - xBorder * 3) / 2;
		
		for(int i = 0; i < objectType.values().length; i++) {
			buttons.add(new SimpleSelectButton(
					buttons,
					null,
					objectType.values()[i].name(),
					buttonWidth,
					DimenR.BUTTON_HEIGHT
					));
			final int temp = i;
			buttons.get(i).addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				    	DataProvider.getInstance().setCurrentObjectType(
				    			ObjectType.values()[temp]);
				    }
			    });
			
			if(i % 2 == 0) {
				buttons.get(i).setLocation(
						xBorder,
						yBorder * (i / 2 + 1) + (i / 2) * DimenR.BUTTON_HEIGHT
						);
			} else {
				buttons.get(i).setLocation(
						xBorder * 2 + buttonWidth,
						buttons.get(i - 1).getLocation().y
						);
			}
			
			this.add(buttons.get(i));
		}
		

		this.setBackground(ColorR.WHITE);
		this.setBorder(null);
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
	}
}

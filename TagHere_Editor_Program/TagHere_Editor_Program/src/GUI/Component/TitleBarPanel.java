package GUI.Component;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Resource.ColorR;
import Resource.DataProvider;
import Resource.DimenR;
import Resource.StringR;

/**
 * JPanel for title bar
 * @author DongKyu
 *
 */
public class TitleBarPanel extends JPanel {
	
	/** Label for show title text */
	private SimpleLabel title;
	/** Label for show user id */
	private SimpleLabel id;
	/** JFrame for approach parent frame */
	private SimpleJFrame context;
	/** Button for save floor plan */
	private SimpleButton saveButton;
	
	/** String for title bar text */
	String titleText;
	
	private int xBorder = 20;
	
	public TitleBarPanel(SimpleJFrame context, String text, int width) {
		titleText = text;
		this.context = context;
		this.setLayout(null);
		
		title = new SimpleLabel(text);
		title.setSize(width, DimenR.TITLE_BAR_HEIGHT);
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setForeground(ColorR.TITLE_COLOR);
		title.setLocation(xBorder, 0);
		
		id = new SimpleLabel(DataProvider.getInstance().getId());
		id.setSize(width, DimenR.TITLE_BAR_HEIGHT);
		id.setHorizontalAlignment(SwingConstants.LEFT);
		id.setForeground(ColorR.TITLE_COLOR);
		id.setLocation(width * 5 / 6, 0);
		
		addDecoration();
		
		this.add(title);
		this.add(id);
		
		this.setBackground(ColorR.TITLE_BAR_COLOR);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, ColorR.GRAY));
		this.setPreferredSize(new Dimension(width, DimenR.TITLE_BAR_HEIGHT));
		this.setSize(width, DimenR.TITLE_BAR_HEIGHT);
	}
	
	/**
	 * Function for add decoration in this panel
	 * for various parent frames
	 * */
	public void addDecoration() {
		switch(context.toString()) {
		case StringR.SELECT_BUILDING:
			break;
			
		case StringR.EDIT_FLOOR_PLAN:
			saveButton = new SimpleButton(StringR.SAVE);
			saveButton.setSize(
					DimenR.BUTTON_WIDTH,
					DimenR.BUTTON_HEIGHT
					);
			saveButton.setLocation(
					id.getLocation().x - saveButton.getWidth() - xBorder,
					0
					);
			
			this.add(saveButton);
			
			break;
		}
	}

}

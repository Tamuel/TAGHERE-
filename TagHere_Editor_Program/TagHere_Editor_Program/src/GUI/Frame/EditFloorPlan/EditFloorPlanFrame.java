package GUI.Frame.EditFloorPlan;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;

import FloorPlan.FloorPlan;
import GUI.Component.SimpleJFrame;
import GUI.Component.SimpleLabel;
import GUI.Component.SimpleScrollPane;
import GUI.Component.TitleBarPanel;
import Resource.ColorR;
import Resource.DimenR;
import Resource.StringR;

/**
 * JFrame for edit floor plan
 * @author DongKyu
 * @since 11.07.15
 */
public class EditFloorPlanFrame extends SimpleJFrame {
	
	/** For show title bar */
	private TitleBarPanel titleBar;
	/** Label for show tools panel title */
	private SimpleLabel toolsLabel;
	/** Label for show properties panel title */
	private SimpleLabel propertiesLabel;
	/** For tools list scroll */
	private SimpleScrollPane toolsScrollPane;
	/** For properties list scroll */
	private SimpleScrollPane propertiesScrollPane;

	/** For show current editor view */
	private EditViewPanel editViewPanel;
	/** For show tools list */
	private ToolsPanel toolsPanel;
	/** For show properties list */
	private PropertiesPanel propertiesPanel;
	
	private int xBorder = 20;
	private int yBorder = 20;
	private int scrollBarBorder = 10;

	public EditFloorPlanFrame(String frameName, int width, int height) {
		super(frameName, width, height);
		this.setLayout(null);

		editViewPanel = new EditViewPanel(
				this,
				width * DimenR.EDIT_FLOOR_PLAN_VIEW /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS)
				- xBorder * 2 - scrollBarBorder,
				height - DimenR.TITLE_BAR_HEIGHT - yBorder * 2,
				new FloorPlan()
				);
		editViewPanel.setLocation(
				xBorder,
				DimenR.TITLE_BAR_HEIGHT + yBorder
				);
		
		
		toolsPanel = new ToolsPanel(
				width * DimenR.EDIT_FLOOR_PLAN_TOOLS /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS),
				(height - DimenR.TITLE_BAR_HEIGHT * 3) / 2
				);
		propertiesPanel = new PropertiesPanel(
				width * DimenR.EDIT_FLOOR_PLAN_TOOLS /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS),
				(height - DimenR.TITLE_BAR_HEIGHT * 3) / 2
				);
		
		titleBar = new TitleBarPanel(this, frameName, width);
		titleBar.setLocation(0, 0);
		
		toolsLabel = new SimpleLabel(
				StringR.TOOLS,
				width * DimenR.EDIT_FLOOR_PLAN_TOOLS /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) +
				scrollBarBorder,
				DimenR.TITLE_BAR_HEIGHT
				);
		toolsLabel.setLocation(
				width * DimenR.EDIT_FLOOR_PLAN_VIEW /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) -
				scrollBarBorder,
				DimenR.TITLE_BAR_HEIGHT
				);
		toolsLabel.setOpaque(true);
		toolsLabel.setBackground(ColorR.LIGHT_GRAY);
		
		
		
		toolsScrollPane = new SimpleScrollPane(toolsPanel);
		toolsScrollPane.setSize(
				width * DimenR.EDIT_FLOOR_PLAN_TOOLS /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) +
				scrollBarBorder,
				(height - DimenR.TITLE_BAR_HEIGHT * 3) / 2
				);
		toolsScrollPane.setLocation(
				width * DimenR.EDIT_FLOOR_PLAN_VIEW /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) -
				scrollBarBorder,
				toolsLabel.getLocation().y + DimenR.TITLE_BAR_HEIGHT
				);
		toolsScrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, ColorR.LIGHT_GRAY));
		toolsScrollPane.setBackground(ColorR.WHITE);
		toolsScrollPane.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
				);
		
		
		propertiesLabel = new SimpleLabel(
				StringR.PROPERTIES,
				width * DimenR.EDIT_FLOOR_PLAN_TOOLS /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) +
				scrollBarBorder,
				DimenR.TITLE_BAR_HEIGHT
				);
		propertiesLabel.setLocation(
				width * DimenR.EDIT_FLOOR_PLAN_VIEW /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) -
				scrollBarBorder,
				toolsScrollPane.getLocation().y + toolsScrollPane.getHeight()
				);
		propertiesLabel.setOpaque(true);
		propertiesLabel.setBackground(ColorR.LIGHT_GRAY);
		
		
		propertiesScrollPane = new SimpleScrollPane(propertiesPanel);
		propertiesScrollPane.setSize(
				width * DimenR.EDIT_FLOOR_PLAN_TOOLS /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) +
				scrollBarBorder,
				(height - DimenR.TITLE_BAR_HEIGHT * 3) / 2
				);
		propertiesScrollPane.setLocation(
				width * DimenR.EDIT_FLOOR_PLAN_VIEW /
				(DimenR.EDIT_FLOOR_PLAN_VIEW + DimenR.EDIT_FLOOR_PLAN_TOOLS) -
				scrollBarBorder,
				propertiesLabel.getLocation().y + DimenR.TITLE_BAR_HEIGHT
				);
		propertiesScrollPane.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, ColorR.LIGHT_GRAY));
		propertiesScrollPane.setBackground(ColorR.WHITE);
		propertiesScrollPane.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
				);
		
		
		this.add(toolsLabel);
		this.add(propertiesLabel);
		
		this.add(toolsScrollPane);
		this.add(propertiesScrollPane);
		
		this.add(editViewPanel);
		this.add(titleBar);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(ColorR.GRAY, 2));
	}

}

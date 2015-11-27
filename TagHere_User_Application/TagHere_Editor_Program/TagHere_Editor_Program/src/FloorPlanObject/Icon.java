package FloorPlanObject;

import java.awt.Image;
import java.awt.Point;

/**
 * Icon Object
 * Have image, position and text
 * @author DongKyu
 *
 */

public class Icon extends FPObject {
	
	/** Image for icon image */
	private Image iconImage;
	/** Point for position */
	private Point position;
	/** String for extra data */
	private String text;
	
	
	public Image getIconImage() {
		return iconImage;
	}
	public void setIconImage(Image iconImage) {
		this.iconImage = iconImage;
	}
	public Point getPosition() {
		return position;
	}
	public void setPosition(Point position) {
		this.position = position;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public Point getStartPosition() {
		return getPosition();
	}
	
	@Override
	public Point getEndPosition() {
		return null;
	}
	
	@Override
	public ObjectType getType() {
		return ObjectType.ICON;
	}
}

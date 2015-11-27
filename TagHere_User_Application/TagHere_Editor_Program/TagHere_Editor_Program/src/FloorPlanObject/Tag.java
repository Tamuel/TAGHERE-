package FloorPlanObject;

import java.awt.Color;
import java.awt.Point;

/**
 * Tag Object
 * Have position, key and data
 * @author DongKyu
 *
 */

public class Tag extends FPObject {
	
	/** Point for position */
	private Point position;
	/** Long integer for key */
	private long key;
	/** String for extra data */
	private String data;
	
	
	public Point getPosition() {
		return position;
	}
	
	public void setPosition(Point position) {
		this.position = position;
	}
	
	public long getKey() {
		return key;
	}
	
	public void setKey(long key) {
		this.key = key;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
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
		return ObjectType.TAG;
	}
}

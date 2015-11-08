package FloorPlanObject;

import java.awt.Point;

/**
 * Super Class of floor plan objects
 * @author DongKyu
 *
 */
public abstract class FPObject {

	abstract public Point getStartPosition();
	abstract public Point getEndPosition();
	abstract public ObjectType getType();
}

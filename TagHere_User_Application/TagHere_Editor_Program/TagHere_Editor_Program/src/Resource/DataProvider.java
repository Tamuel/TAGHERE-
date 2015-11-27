package Resource;

import java.util.ArrayList;

import FloorPlan.Building;
import FloorPlanObject.ObjectType;

/**
 * Handle Data from server
 * @author DongKyu
 *
 */
public class DataProvider {
	
	/** For Singleton design pattern */
	private static DataProvider singleton = null;
	/** String for user id */
	private String id;
	/** For get list of building */
	private ArrayList<String> buildingsData;
	/** For get current building */
	private Building building;
	
	/** For editor */
	/** Type of current selected object */
	private ObjectType objectType;

	
	private DataProvider() {
		buildingsData = new  ArrayList<String>();
		buildingsData.add("aaa 5類");
		buildingsData.add("bbb 3類");
		buildingsData.add("ccc 7類");
		buildingsData.add("aaa 5類");
		buildingsData.add("bbb 3類");
		buildingsData.add("ccc 7類");
		buildingsData.add("aaa 5類");
		buildingsData.add("bbb 3類");
		buildingsData.add("ccc 7類");
		buildingsData.add("aaa 5類");
		buildingsData.add("bbb 3類");
		buildingsData.add("ccc 7類");
		buildingsData.add("aaa 5類");
		buildingsData.add("bbb 3類");
		buildingsData.add("ccc 7類");
	}

	/** For Singleton design.
	 * This function provide get instance of singleton */
	public static DataProvider getInstance()
	{ 
		if(singleton == null)
		{
			singleton  = new DataProvider();
		}
		return singleton;
	}

	public ArrayList<String> getBuildingsData() {
		return buildingsData;
	}

	public void setBuildingsData(ArrayList<String> buildingsData) {
		this.buildingsData = buildingsData;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObjectType getCurrentObjectType() {
		return objectType;
	}

	public void setCurrentObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}
}

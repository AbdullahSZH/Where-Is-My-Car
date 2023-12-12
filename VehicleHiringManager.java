package StudentCode;
public class VehicleHiringManager {
	LocatorMap<String> tlm;

	public VehicleHiringManager() {
		tlm = new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return tlm;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		tlm = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		
		return tlm.add(id, loc).first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		
		return tlm.move(id, loc).first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		return tlm.remove(id).first;
		}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		
		if(tlm.getLoc(id)!=null)
			return tlm.getLoc(id).first;
		
		else return null;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		
		return tlm.getInRange(new Location(loc.x-r,loc.y-r), new Location(loc.x+r,loc.y+r)).first;
	}
}

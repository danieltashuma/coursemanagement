package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


import entity.Location;
import manager.ManagerHelper;

@Path("/location")
public class LocationService {
	@GET
	@Path("get")
	public List<Location> getAll() {
		return (List) ManagerHelper.getLocationManager().getAll();
	}

}

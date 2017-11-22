package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Massages;
import manager.ManagerHelper;
import webmy.Reply1;

@Path("/Massages")
public class MassagesService {
	
	@GET
	@Path("getMessagesByCourseId")
	public List<Massages> getMessagesByCourseId(@QueryParam("course")int courseid){
		System.out.println("inter message func, the corseid is ="+courseid);
		return ManagerHelper.getMassagesManager().getMessagesByCourseId(courseid);
	}
	@GET
	@Path("createMassages")
	public Reply1 createMassage(@QueryParam("course")int course,
			@QueryParam("user")int user,@QueryParam("date")String date,@QueryParam("comment")String comment){	

		return ManagerHelper.getMassagesManager().createNewMassage(course,user,date,comment);
	}

}

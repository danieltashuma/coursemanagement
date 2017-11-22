package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Courseschedule;
import manager.ManagerHelper;
import webmy.Reply1;

@Path("/csescheduleService")
public class CoursescheduleService {

	@GET
	@Path("createchedule")
	public Reply1 createchedule
	        (@QueryParam("course")  int course, 
			@QueryParam("date") String date,
			@QueryParam("starttime")String starttime,
			@QueryParam("endtime")String endtime,
			@QueryParam("agenda")String agenda){
		System.out.println(course+ " "+ date+" "+starttime+" "+endtime+" "+agenda);
		return ManagerHelper.getCoursescheduleManager().createchedule(course, date, starttime, endtime, agenda);
		
		
		
	} 
	
	@GET
	@Path("getCourseScheduel")
	public List<Courseschedule> getCourseScheduel (@QueryParam("courseId") int courseId){
		System.out.println(courseId);
		 return ManagerHelper.getCoursescheduleManager().getCourseScheduel(courseId);
	}
}

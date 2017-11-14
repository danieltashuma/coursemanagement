package services;



import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import webmy.PropsHelper;
import webmy.Reply1;
import webmy.Targil;

@Path("/Properties")
public class Propertiesservice {

	HttpServletRequest request;
	
    @GET
	@Path("createsyllabus")
	public Reply1 setsyllabusProperties(@QueryParam("coursename") String coursename,@QueryParam("syllabus") String syllabus) {
    


		return PropsHelper.set(coursename+"syllabus", syllabus,coursename);		
	}
    @GET
	@Path("createschedule")
	public Reply1 setscheduleProperties(@QueryParam("coursename") String coursename,
			@QueryParam("schedule") String schedule) {
    
		return  PropsHelper.set(coursename+"schedule", schedule,coursename);		
	}

    
    @GET
   	@Path("getschedule")
   	public String getscheduleProperties(@QueryParam("coursename") String coursename,
   			@QueryParam("schedule") String schedule) {
    	PropsHelper.setCourse(coursename);

   		
   		return  PropsHelper.get(coursename+"schedule");		
   	}
    
    @GET
   	@Path("getsyllabus")
   	public String getgetsyllabusProperties(@QueryParam("coursename") String coursename,
   			@QueryParam("syllabus") String schedule) {
    	PropsHelper.setCourse(coursename);

   		
   		return  PropsHelper.get(coursename+"syllabus");		
   	}
}

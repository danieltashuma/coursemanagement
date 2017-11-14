package services;



import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Course;

import manager.CourseFileManager;
import manager.ManagerHelper;
import webmy.Reply1;



@Path("/course")
public class CourseService {
	
	HttpServletRequest request;
	


	@GET
	@Path("getCourseByInstractorId")
	public List<Course>  getCourseByInstractorId(@QueryParam("userId") int userId){
		System.out.println("getCourseByInstractorId"+userId);
		return (List)ManagerHelper.getCourseManager().getCourseByInstractorId(userId);
	}
	
	@GET
	@Path("GetCoursesByInstructorUserId")
	public List<Course>  GetCourseByInstructorUserId(@QueryParam("userId") int userId){
		System.out.println("service"+userId);
		return (List) ManagerHelper.getCourseManager().GetCourseByInstructorUserId(userId);
	}
	
	@GET
	@Path("getall")
	public List<Course> getall(){
		
		return ManagerHelper.getCourseManager().getAll();
	}
	
	@GET
	@Path("getallcoursefile")
	public List<CourseFileManager> getallcoursefile(){
		
		return ManagerHelper.getCourseFileManager().getAll();
	}
	@GET
	@Path("updateCourse")
	public Reply1 updateCourse(@QueryParam("id") int id,@QueryParam("name") String name,@QueryParam("instructorid)")int instructorid,@QueryParam("coursetagid)")int coursetagid,
			@QueryParam("starttime") String starttime,@QueryParam("endtime") String endtime,@QueryParam("agenda") String agenda,
			@QueryParam("locationid") int locationid,@QueryParam("description") String description,boolean archive){
		
		return ManagerHelper.getCourseManager().updateCourse(id,name, instructorid, coursetagid, starttime, agenda, locationid, description,archive);
	}

	@GET
	@Path("deletecourse")
	public Reply1 deletecourse(@QueryParam("courseid)")int courseid){		
		return ManagerHelper.getCourseManager().deletecourse(courseid);
	}
	
	@GET
	@Path("createschedule")
	public Reply1 createchedule(@QueryParam("courseid")int courseid,@QueryParam("date")String date,
			@QueryParam("starttime")String starttime,@QueryParam("endtime")String endtime){	

		return ManagerHelper.getCoursescheduleManager().createchedule(courseid, date,starttime,endtime);
	}
	@GET
	@Path("deleteschedule")
	public Reply1 deletechedule(@QueryParam("courseid")int courseid){	
		return ManagerHelper.getCoursescheduleManager().deleteschedule(courseid);
	}
	@GET
	@Path("getAvileableCourses")
	public List<Course> getAvileableCourses(){
		
		return ManagerHelper.getCourseManager().getAvileableCourses();
	}
	@GET
	@Path("uploadfile")
	public Reply1 uploadfile(@QueryParam("fileaderrs") String fileaderrs,@QueryParam("courseid") int courseid) throws IOException, ServletException{
		System.out.println("11filepath="+fileaderrs);
		return ManagerHelper.getCourseManager().uploadfile(fileaderrs,courseid);
	}
	@GET
	@Path("GetCourseById")
	public Course GetCourseById(@QueryParam("courseId")int courseId){		
		return (Course )ManagerHelper.getCourseManager().GetCourseById(courseId);
	}
	@GET
	@Path("createNewCourse")
	public Course createNewCourse(@QueryParam("name")String name,@QueryParam("agenda")String agenda){		
		return (Course )ManagerHelper.getCourseManager().createNewCourse(name,agenda);
	}
	@GET
	@Path("getCoursesByTagCourse")
	public List<Course> getCoursesByTagCourse(@QueryParam("coursetagId")int coursetagId ){		
		return (List )ManagerHelper.getCourseManager().getCoursesByTagCourse(coursetagId);
	}
}

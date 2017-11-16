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
	@Path(" getCourseByInstractorId")
	public List<Course>  getCourseByInstractorId(@QueryParam("InstractorId") int InstractorId){
		return ManagerHelper.getCourseManager().getCourseByInstractorId(InstractorId);
	}
	
	@GET
	@Path("GetCourseByInstructorUserId")
	public List<Course>  GetCourseByInstructorUserId(@QueryParam("userId") int userId){
		System.out.println("דקרהןבק"+userId);
		return  ManagerHelper.getCourseManager().GetCourseByInstructorUserId(userId);
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
	@Path("caretCourseByInstructor")
	public Reply1 caretCourseByInstructor
	        (@QueryParam("id") int id,
			@QueryParam("instructor")int instructor,
			@QueryParam("coursetag")int coursetag,
			@QueryParam("starttime") String starttime,
			@QueryParam("agenda") String agenda,
			@QueryParam("location") int location,
			@QueryParam("description") String description){
		System.out.println(+instructor+"instrucot"+coursetag+"coursetag" +starttime+"starttime"+ agenda+"agenda"+location+"location"+description+"descroption"+"archive");
		return ManagerHelper.getCourseManager().caretCourseByInstructor(id, instructor, coursetag, starttime, location, description);
	}

	@GET
	@Path("deletecourse")
	public Reply1 deletecourse(@QueryParam("courseId)")int courseId){		
		return ManagerHelper.getCourseManager().deletecourse(courseId);
	}
	
	@GET
	@Path("createschedule")
	public Reply1 createchedule(@QueryParam("courseId")int courseId,@QueryParam("date")String date,
			@QueryParam("starttime")String starttime,@QueryParam("endtime")String endtime){	

		return ManagerHelper.getCoursescheduleManager().createchedule(courseId, date,starttime,endtime);
	}
	@GET
	@Path("deleteschedule")
	public Reply1 deletechedule(@QueryParam("courseId")int courseId){	
		return ManagerHelper.getCoursescheduleManager().deleteschedule(courseId);
	}
	@GET
	@Path("getAvileableCourses")
	public List<Course> getAvileableCourses(){
		
		return ManagerHelper.getCourseManager().getAvileableCourses();
	}
	@GET
	@Path("uploadfile")
	public Reply1 uploadfile(@QueryParam("fileaderrs") String fileaderrs,@QueryParam("courseId") int courseId) throws IOException, ServletException{
		System.out.println("11filepath="+fileaderrs);
		return ManagerHelper.getCourseManager().uploadfile(fileaderrs,courseId);
	}
	@GET
	@Path("GetCourseById")
	public Course GetCourseById(@QueryParam("courseId")int courseId){		
		return (Course )ManagerHelper.getCourseManager().GetCourseById(courseId);
	}
	@GET
	@Path("getCoursesByTagCourse")
	public List<Course> getCoursesByTagCourse(@QueryParam("coursetagId")int coursetagId){		
		return ManagerHelper.getCourseManager().getCoursesByTagCourse(coursetagId);
	}
	@GET
	@Path("createNewCoursePartOne")
	public Course createNewCoursePartOne(@QueryParam("name")String name,@QueryParam("agenda")String agenda) {
		System.out.println(name+" "+agenda);
		return  ManagerHelper.getCourseManager().createNewCoursePartOne(name,agenda);
	}
	@GET
	@Path("getStudentsCourses")
	public List<Course> getStudentsCourses(@QueryParam("userId")int userId) {
		System.out.println(userId);
		return (List)ManagerHelper.getCourseManager().getStudentsCourses(userId);
	}
	
}

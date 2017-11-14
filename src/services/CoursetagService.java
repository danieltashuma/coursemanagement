package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Coursetag;

import manager.ManagerHelper;
import webmy.Reply1;
 


@Path("/Coursetag")
public class CoursetagService {
	
	@GET
	@Path("getall")
	public List<Coursetag> getAll() {
		return  ManagerHelper.getCoursetagManager().getAll();
	}
	@GET
	@Path("deleteCoursetag")
	public Reply1 deleteCoursetag(@QueryParam("id")int id) {
		return  ManagerHelper.getCoursetagManager().deleteCoursetag(id);
	}
	
	@GET
	@Path("createCoursetag")
	public Coursetag createCoursetag(@QueryParam("name")String name) {
		return  ManagerHelper.getCoursetagManager().createCoursetag(name);
	}
	@GET
	@Path("updateCoursetag")
	public Reply1 updateCoursetag(@QueryParam("id")int id,@QueryParam("name")String name) {
		return  ManagerHelper.getCoursetagManager().updateCoursetag(id,name);
	}
	@GET
	@Path("getCoursetagByCourseId")
	public Coursetag getCoursetagByCourseId(@QueryParam("courseId")int courseId ) {
		System.out.println("service"+courseId);
		return  (Coursetag)ManagerHelper.getCoursetagManager().getCoursetagByCourseId(courseId);
	}

}
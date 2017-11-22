package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Student;
import entity.Usercourse;
import manager.ManagerHelper;
import webmy.Reply1;

@Path("/Usercourse")
public class UsercourseService {
	@GET
	@Path("getall")
	public List<Usercourse> getAll() {
		return  ManagerHelper.getUsercourseManager().getAll();
	}
	
	
	@GET
	@Path("getCoursesBytUserId")
	public List<Usercourse>  getCoursesBytUserId(@QueryParam ("userId") int userId) {		
		return  ManagerHelper.getUsercourseManager().getCoursesBytUserId(userId);
	}
	
	@GET
	@Path("deleteUsercourse")
	public Reply1 deleteUsercourse(@QueryParam ("id") int id) {		
		return ManagerHelper.getUsercourseManager().deleteUsercourse(id);
	}
	
	@GET
	@Path("createUsercourse")
	public Reply1 createUsercourse(@QueryParam ("user") int user,@QueryParam ("course") int course) {	
		System.out.println("user service "+user+" "+"course"+course);
		return ManagerHelper.getUsercourseManager().createUsercourse(user,course);
	}
	
}

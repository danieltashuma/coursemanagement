package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Instructor;
import manager.ManagerHelper;
import webmy.Reply1;

@Path("/instructor")
public class InstructorService {
	
	@GET
	@Path("get")
	public List<Instructor> getAll() {
		return  ManagerHelper.getInstructorManager().getAll();
	}

	
	@GET
	@Path("createinstructor")
	public Instructor createInstructor(@QueryParam ("firstname") String firstname,@QueryParam ("lastname") String lastname,
			@QueryParam ("email") String email,@QueryParam ("phone") String phone,@QueryParam ("user") int userid) {
		
		return  ManagerHelper.getInstructorManager().createInstructor(firstname, lastname, email, phone, userid);
	}

	@GET
	@Path("deleteInstructor")
	public Reply1 deleteInstructor(@QueryParam ("Instructorid") int Instructorid) {		
		return  ManagerHelper.getInstructorManager().deleteInstructor(Instructorid);
	}
	@GET
	@Path("getInstructorByCourseId")
	public Instructor getInstructorByCourseId(@QueryParam ("courseId") int courseId) {
		
		return  (Instructor)ManagerHelper.getInstructorManager().getInstructorByCourseId(courseId);
	}	
	
}

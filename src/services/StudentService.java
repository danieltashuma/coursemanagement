package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Student;
import manager.ManagerHelper;
import webmy.Reply1;

@Path("/student")
public class StudentService {
	@GET
	@Path("getall")
	public List<Student> getAll() {
		return  ManagerHelper.getStudentManager().getAll();
	}
	
	
	@GET
	@Path("createstudent")
	public Student createstudent(@QueryParam ("firstname") String firstname,@QueryParam ("lastname") String lastname,
			@QueryParam ("email") String email,@QueryParam ("phone") String phone,@QueryParam ("user") int userid) {		
		return  ManagerHelper.getStudentManager().createstudent(firstname, lastname, email, phone, userid);
	}
	
	@GET
	@Path("deletestudent")
	public Reply1 deletestudent(@QueryParam ("user") int userid) {		
		return  ManagerHelper.getStudentManager().deletestudent(userid);
	}
	
}

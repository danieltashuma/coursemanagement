package services;

import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


import entity.User;

import manager.ManagerHelper;
import webmy.Reply1;

@Path("/user")
public class UserService {
	
	
	 
	@GET
	@Path("getall")
	public List<User> getAll() {
		return  ManagerHelper.getUserManager().getAll();
	}
	
	
	@GET
	@Path("forgotPassword")
	public Reply1 forgotPassword(@QueryParam("username") String username) {
		return  ManagerHelper.getUserManager().forgotPassword(username);
	}
	@GET
	@Path("getUser")
	public User getUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		System.out.println(" getUser");
		return (User) ManagerHelper.getUserManager().getUser(username, password);
	}	 
}


package com.boot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import com.boot.dao.User;
import com.boot.dao.UserRepo;
import com.boot.dao.UserRepoCustomImpl;


@Controller
public class UserController {

	@Autowired
	  private UserRepo userDao;
	
	static int interval;
	static TimerTask tt;
	
	
	@Value("${spring.session.timeout}")
	int session_timeout;
	
	@Value("${fixed.delay}")
	int fixed_delay;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	public  String SERVER_URI = "http://localhost:9000/list";
	
	@RequestMapping("/list")
	  @ResponseBody
	public List<User> getList(){
		return userDao.getList();
	}
	
	@RequestMapping("/invalidate/session")
	  @ResponseBody
	public String destroySession(HttpServletRequest request){
request.getSession().invalidate();
		
		return "Session invalidated";
	}
	
	@RequestMapping("/create/{email}/{name}")
	  @ResponseBody
	  public String create(@PathVariable String email, @PathVariable String name,HttpServletRequest request) {
		
		  List<User> users = (List<User>)  request.getSession().getAttribute("USERS_SESSION");
		  if(users==null) {
			  users = new ArrayList<User>();
			 
		  }
		  User user = new User(email, name);
		  users.add(user);
		  request.getSession().setAttribute("USERS_SESSION", users);
		
		  scheduleTASK();
		  
	   
	    try {
	    
	      userDao.save(user);
	     
	    }
	    catch (Exception ex) {
	      return "Error creating the user: " + ex.toString();
	    }
	    return "Current Time " + formatter.format(LocalDateTime.now());
//		  return "redirect:/home";
	  }
	
	
	
	public void scheduleTASK() {

		
		  ScheduledExecutorService scheduler
          = Executors.newSingleThreadScheduledExecutor();

			Runnable task = new Runnable() {

			public void run() {
			System.out.println("calling the http service");
			getAllUsers();
			}

			};			
			
			scheduler.schedule(task, fixed_delay, TimeUnit.SECONDS);
			scheduler.shutdown();
			System.out.println("Scheduler shutdown");

	}
	
	public List<LinkedHashMap> getAllUsers() {
	
			RestTemplate restTemplate = new RestTemplate();
			//we can't get List<Employee> because JSON convertor doesn't know the type of
			//object in the list and hence convert it to default JSON object type LinkedHashMap
			List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI, List.class);
			System.out.println(emps.size());
			for(LinkedHashMap map : emps){
				System.out.println(map.get("username")+",Email="+map.get("email"));;
			}
			return emps;
		
	}
	
//	private  final int setInterval() {
//	    if (interval == 0) {
//	        tt.cancel();
//	        System.out.println("executing the tasks");
//	        
//	    }
//	    return --interval;
//	}
	 /**
	   * GET /delete  --> Delete the user having the passed id.
	   */
	  @RequestMapping("/delete")
	  @ResponseBody
	  public String delete(long id) {
	    try {
	      User user = new User(id);
	      userDao.delete(user);
	    }
	    catch (Exception ex) {
	      return "Error deleting the user:" + ex.toString();
	    }
	    return "User succesfully deleted!";
	  }
	  
	  
	
	  @RequestMapping("/get-by-email")
	  @ResponseBody
	  public String getByEmail(String email) {
	    String userId = "";
	    try {
	      User user = userDao.findByEmail(email);
	      userId = String.valueOf(user.getId());
	    }
	    catch (Exception ex) {
	      return "User not found";
	    }
	    return "The user id is: " + userId;
	  }
	  
//	  @RequestMapping("/update")
//	  @ResponseBody
//	  public String updateUser(long id, String email, String name) {
//	    try {
//	      User user = userDao.findOne(id);
//	      user.setEmail(email);
//	      user.setUsername(name);
//	      userDao.save(user);
//	    }
//	    catch (Exception ex) {
//	      return "Error updating the user: " + ex.toString();
//	    }
//	    return "User succesfully updated!";
//	  }
	
}

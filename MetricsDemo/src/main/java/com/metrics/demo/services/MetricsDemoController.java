package com.metrics.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.metrics.demo.model.User;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/metrics")
public class MetricsDemoController {
	
	private final MeterRegistry meterRegistry;
    private Counter getAdminUsersCounter;
    private Counter deleteUserErrorCounter;
    
    public MetricsDemoController(MeterRegistry registry) {
    	//Initialize the meterRegistry
        this.meterRegistry = registry;
        
        //Create a Counter of how many getUsersByRole were for Admin Role
        this.getAdminUsersCounter = Counter.builder("getUsersByRole.admin")     
        					.tag("role", "admin")
        					.description("The number of getUsersByRole for Admin")
        					.register(meterRegistry);
        
        //Create a Counter of how many errors
        this.deleteUserErrorCounter = Counter.builder("deleteUserById.error")     
        					.tag("result", "error")
        					.description("The number of deleteUserById errors")
        					.register(meterRegistry);
    }	
	
	@GetMapping("/getUsersByRole")
	@Timed(value = "list_users_by_role", percentiles = {0.5, 0.95}, description = "Service to get Users by Role")
	public ResponseEntity<List<User>> getUsersByRole(@RequestParam String role){
		try {			 
			List<User> responseObj = new ArrayList<User>();
			
			User u1 = new User("1","Admin","Peter","Smith");
			User u2 = new User("2","Manager","John","Doe");
			User u3 = new User("3","Admin","Wesly","Preston");
			responseObj.add(u1);
			responseObj.add(u2);
			responseObj.add(u3);
			
			//If the request was for Admin Role, update counter
			if(role!=null && role.trim().equalsIgnoreCase("ADMIN")) {
				getAdminUsersCounter.increment();
			}
			
			return new ResponseEntity<List<User>>(responseObj, HttpStatus.OK);			
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	 
	
	@GetMapping("/deleteUserById")
	@Timed(value = "delete_user_by_id", percentiles = {0.5, 0.95}, description = "Service to delete User by Id")
	public ResponseEntity<String> deleteUserById(@RequestParam String id){
		try {
			//If the ID is 1, throw error
			if(id!=null && id.equals("1")) {
				throw new Exception("Attempting to delete User ID 1.");
			}
			Thread.sleep(1000);
			
			return new ResponseEntity<String>("Successfully Deleted "+id, HttpStatus.OK);			
		}catch (Exception e) {
			deleteUserErrorCounter.increment();
			return new ResponseEntity<String>("ERROR " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

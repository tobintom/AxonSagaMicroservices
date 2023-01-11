package com.demo.elasticsearch.controller;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest; 
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.elasticsearch.entity.Employee;
import com.demo.elasticsearch.repository.ElasticSearchRepository;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticSearchController {
	
	private static final String DOCUMENT_INDEX = "employeeindex";
	
	@Autowired
	ElasticSearchRepository repository;
	
	private ElasticsearchOperations elasticsearchOperations;
	
	public ElasticSearchController(ElasticsearchOperations e) {
		super();
		this.elasticsearchOperations = e;
	}
	
	 @PostMapping("/employee")
	 public String addUpdateEmployee(@RequestBody Employee newEmployee) {
	     repository.save(newEmployee);
	     return "Employee added/modified successfully";
	  }
	 
	 @GetMapping("/employees")
	  List<Employee> findAllEmployees() {	    
	    Iterable<Employee> emps = repository.findAll();
	    List<Employee> employees = new ArrayList<>();
        emps.iterator().forEachRemaining(employees::add);
        return employees;
	  }
	 
	 @GetMapping("/employees/{id}")
	  Employee findEmployee(@PathVariable String id) {	    
	    return repository.findById(id).get();
	  }
	 
	 @GetMapping("/employee/delete/{id}")
	  String deleteEmployee(@PathVariable String id) {	    
	      repository.deleteById(id);
	      return "Employee Deleted Successfully";
	  }
	 
	 @GetMapping("/employeesByDept")
	  List<Employee> findEmployeesByDept(@RequestParam String department) {	    
	    Iterable<Employee> emps = repository.findEmployeesByDepartment(department,PageRequest.of(0, 10));
	    List<Employee> employees = new ArrayList<>();
       emps.iterator().forEachRemaining(employees::add);
       return employees;
	  }
	 
	 @GetMapping("/employeesByZip")
	  List<Employee> findEmployeesByZip(@RequestParam String zip) {	    
		 QueryBuilder queryBuilder =  QueryBuilders.matchQuery("zip", zip);
         Query searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();

		 SearchHits<Employee> emps = elasticsearchOperations.search(searchQuery, Employee.class,
			          IndexCoordinates.of(DOCUMENT_INDEX));	 
	     
	    List<Employee> employees = new ArrayList<>();
	    emps.forEach(hit ->{
	    	employees.add(hit.getContent());
	    });
      
	    return employees;
	  }
	 
	 @GetMapping("/employeesSalGreaterThan9000")
	  List<Employee> employeesSalGreaterThan9000() {	    
		 Criteria criteria = new Criteria("salary").greaterThan(9000);
		 Query searchQuery = new CriteriaQuery(criteria);

		 SearchHits<Employee> emps = elasticsearchOperations.search(searchQuery, Employee.class,
			          IndexCoordinates.of(DOCUMENT_INDEX));	 
	     
	    List<Employee> employees = new ArrayList<>();
	    emps.forEach(hit ->{
	    	employees.add(hit.getContent());
	    });
     
	    return employees;
	  }

}

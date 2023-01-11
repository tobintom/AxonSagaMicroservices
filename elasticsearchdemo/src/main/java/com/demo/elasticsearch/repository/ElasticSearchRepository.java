package com.demo.elasticsearch.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.demo.elasticsearch.entity.Employee;

@Repository
public interface ElasticSearchRepository extends ElasticsearchRepository<Employee, String>{
	
	public List<Employee> findByEmployeeId(String employeeId);
	
	@Query("{\"bool\": {\"must\": [{\"match\": {\"department\": \"?0\"}}]}}")
	public Page<Employee> findEmployeesByDepartment(String department, Pageable pageable);

}

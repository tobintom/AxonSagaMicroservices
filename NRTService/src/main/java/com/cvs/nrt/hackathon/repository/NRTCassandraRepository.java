package com.cvs.nrt.hackathon.repository;
 

import java.util.List;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.cvs.nrt.hackathon.model.NRTObject_Cassandra;  

@Repository
public interface NRTCassandraRepository extends CassandraRepository<NRTObject_Cassandra,String>{
	
	@AllowFiltering
	@Query("SELECT * FROM ice.trxclaim_claims WHERE tcd_sbm_date_of_service>=?0 ALLOW FILTERING ")
    List<NRTObject_Cassandra> findClaimsGreaterThanDate(String submittedDate);
    
    @Query("SELECT * FROM ice.trxclaim_claims WHERE tcd_sbm_date_of_service>=?0 AND tcd_sbm_date_of_service<=?1 ALLOW FILTERING ")
    List<NRTObject_Cassandra> findClaimsBetweenDates(String fromDate,String toDate);	
    
    @Query("SELECT * FROM ice.trxclaim_claims WHERE tcd_product_key =?0  ALLOW FILTERING ")
    List<NRTObject_Cassandra> findClaimsUsingDrug(String prdKey);	
    
    @Query("SELECT * FROM ice.trxclaim_claims")
    List<NRTObject_Cassandra> findClaimsAllClaims();

}

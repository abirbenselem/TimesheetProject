package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;


public interface MissionRepository extends CrudRepository<Mission, Integer> {
	@Query("select m from Mission m where m.name=:missionName")
	Mission findByName(@Param("missionName") String missionName);
	
	List<Mission> findAll() ;
	
	@Query("select name from Mission")
	List<String> missionsNames();
	
	@Query("SELECT count(*) FROM Mission")
    int nbreMission();
	
	
    @Query("Select "
			+ "DISTINCT m from Mission m "
			+ "join m.departement deps "
			+ "where deps=:dep")
     List<Mission> getAllMissionByDepartment(@Param("dep") Departement depar);
    
    
	
	
}

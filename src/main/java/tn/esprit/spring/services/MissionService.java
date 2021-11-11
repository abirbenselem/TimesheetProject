package tn.esprit.spring.services;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;

public interface MissionService {

	void addMission(Mission mission);
	void deleteById(@PathVariable("id") int id) ;
	void updateMission(Mission mission) ;
	Mission findByName(@Param("missionName") String missionName);
	List<Mission> findAll() ;
	List<String> missionsNames();
    Long nbreMission();
    List<Mission> getAllMissionsByDepartment(@Param("dep") Departement depar);
}

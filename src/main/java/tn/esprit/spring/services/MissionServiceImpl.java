package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.MissionRepository;

@Service
public class MissionServiceImpl implements MissionService {
	@Autowired
	MissionRepository mrepo;
	@Autowired
	DepartementRepository depRepo;

	@Override
	public void addMission(Mission mission) {
		mrepo.save(mission);
		
	}

	@Override
	public void deleteById(int id) {
		mrepo.deleteById(id);
	}
	
	public void deleteAll() {
		mrepo.deleteAll();		
	}
	

	@Override
	public void updateMission(Mission mission) {
		mrepo.save(mission);		
	}

	@Override
	public Mission findByName(String missionName) {

		return mrepo.findByName(missionName);
	}

	@Override
	public List<Mission> findAll() {
		return mrepo.findAll();}

	@Override
	public List<String> missionsNames() {
		return mrepo.missionsNames();
		
	}

	@Override
	public Long nbreMission() {
		
		return  mrepo.count();
	}

	@Override
	public List<Mission> getAllMissionsByDepartment(Departement depar) {
		
		
		return mrepo.getAllMissionByDepartment(depar);
	}

	}
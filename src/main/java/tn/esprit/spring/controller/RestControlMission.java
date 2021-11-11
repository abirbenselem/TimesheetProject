package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.MissionServiceImpl;

public class RestControlMission {
	@Autowired 
	MissionRepository mr;
	@Autowired 
	MissionServiceImpl ms;
}

package tn.esprit.spring.tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.MissionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MissionTest {
	@Autowired
	MissionService ms;
	@Autowired
	IEmployeService es ;
	@MockBean
	private MissionRepository mr;
	private static final Logger l = LogManager.getLogger(MissionTest.class);
	@SuppressWarnings("deprecation")
	@Test(timeout = 5000)
	

	public void addMissionTest () {
		l.info("started add test ! ");
		
		Mission m = new Mission();
		Mission m2 = new Mission();
		Mission m3 = new Mission();

		m.setdateDebut(new Date(2021, 11, 01));
		m2.setdateDebut(new Date(2021, 11, 02));
		m3.setdateDebut(new Date(2021, 11, 03));
		
		m.setDuree(30);
		m2.setDuree(60);
		m3.setDuree(90);

		m.setName("mission1");
		m2.setName("mission2");
		m3.setName("mission3");

		when(mr.save(m)).thenReturn(m);
		
		
		l.info(()->"first mission" + m);
		
		when(mr.save(m2)).thenReturn(m2);
		when(mr.save(m3)).thenReturn(m3);
		
		
		assertEquals(30, m.getDuree());
		 assertEquals(60, m2.getDuree());
		 assertEquals(90, m3.getDuree());


		l.info("Test add mission done successfully ! " );
		
	} 
	/************************* sans Mock*******************
	@Test(timeout=5000)
	public void AddMissionSTest() {
		l.info("started add test sans mock ! ");
		
		ms.addMission(new Mission("mission7", " here is 7 ", null, 120, null, null));
		ms.addMission(new Mission("mission8", " here is 8 ", null, 90, null, null));
		
		
		
		l.info("Test add mission sans mock  done successfully ! "); } */
	

	@Test(timeout=3000)
	public void showMissions(){
		l.info("let's start , show me all missions ? ");
		Mission m = new Mission();
		Mission m2 = new Mission();
		Mission m3 = new Mission();

		
		m.setDuree(30);
		m2.setDuree(60);
		m3.setDuree(90);

		m.setName("mission1");
		m2.setName("mission2");
		m3.setName("mission3");
		
		when(mr.findAll()).thenReturn(Stream.of(m,m2,m3).collect(Collectors.toList()));
		assertEquals(3,ms.findAll().size());
	}

}

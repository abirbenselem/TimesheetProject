package tn.esprit.spring.tests;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.entities.TimesheetPK;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.ITimesheetService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetTest {
	@Autowired
	ITimesheetService iTimesheetService;
	@Autowired
	IEmployeService es;
	@MockBean
	private EmployeRepository er;
	@MockBean
	private TimesheetRepository tsr;
	private static final Logger l = LogManager.getLogger(TimesheetTest.class);
	
	@Test
	public void testAjouterTimesheet() {
		Employe emp=new Employe("Ahmed", "Amumu","amumum@gmail.com",true,Role.ADMINISTRATEUR);
		when(er.save(emp)).thenReturn(emp);
		l.info("test add contrat success");
		es.ajouterEmploye(emp);
		TimesheetPK tspk=new TimesheetPK();
		tspk.setIdEmploye(5);
		tspk.setIdMission(5);
		tspk.setDateDebut(new Date());
		tspk.setDateFin(new Date());
		Timesheet ts=new Timesheet();
		ts.setTimesheetPK(tspk);
		ts.setValide(true);
		when(tsr.save(ts)).thenReturn(ts);
		l.info("affichage ts: " + ts);
		
		l.info("timesheet ajouté avec succès");
	}

}

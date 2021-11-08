package tn.esprit.spring.tests;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IEmployeService;
import tn.esprit.spring.services.IEntrepriseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContratServiceTest {
	
	@Autowired
	IContratService service;
	
	@Autowired
	IEmployeService serviceE;
	
	@Autowired
	ContratRepository rep;
	
	
	private static final Logger l = LogManager.getLogger(ContratServiceTest.class);	
	
	
	@Test
	public void testAjouterContrat()
	{
		try {
			  Date date = new Date(0);
			l.info("In testAjouterContrat():");
			Contrat C=new Contrat(date,"testAjout",0);
			ArrayList <Contrat> liste1 =(ArrayList<Contrat>) rep.findAll();
			int size1=liste1.size();
			l.info("nombre d'entreprises avant l'ajout: " + size1);
			l.info("Je vais ajouter une entreprise.");
			int id=service.ajouterContrat(C);
			ArrayList <Contrat> liste2 =(ArrayList<Contrat>) rep.findAll();
			int size2=liste2.size();
			l.info("nombre de contrats apres l'ajout: " + size2);
			l.info("comparaison size avant et apres.");
			assertTrue(size2==size1+1);
			service.deleteContratById(id);
			l.info("je supprime le Contrat.");
			l.info("Out testAjouterContrat() sans erreurs.");
		}catch (Exception e) { l.error("Erreur dans testAjouterContrat() : " + e); }
	}
	
	

/*
	@Test
	public void testAffecterContrat()
	{
		try {
			  Date date = new Date(0);
			l.info("In testAffecterDeparrtement():");
			l.info("Je vais affecter un contrat un employe.");
			Contrat C=new Contrat(date,"testAjout",0);
			l.info("Je vais creer un Employe.");
			Employe E=new Employe("employe");
			l.info("Je vais ajouter le contrat.");
			int id_contrat=service.ajouterContrat(C);
			l.info("Je vais ajouter l'employe.");
			int id_employe=serviceE.ajouterEmploye(E);
			l.info("Je vais affecter le departement a l'entreprise.");
			serviceE.affecterContratAEmploye(id_employe, id_contrat);
			l.info("Je vais reprendre le contrat depuis la base de donnée.");
			Contrat D1=rep.findById(id_contrat).get();
			l.info("Je vais tester si le contrat_id du departement est égale a l'id du contrat auquel je l'ai affecté.");
			assertTrue(D1.getId()==id_contrat);
			l.info("Je vais supprimer l'employe.");
			serviceE.deleteEmployeById(id_employe);
			l.info("Je vais supprimer le contrat.");
			service.deleteContratById(id_contrat);
			l.info("Out testAffecterContrat() sans erreurs.");
		}catch (Exception e) { l.error("Erreur dans testAffecterContrat() : " + e); }
	}
	
	**/
		@Test
	public void testSupprimerContrat()
	{ 
		 try{
			l.info("In testSupprimerContrat():");
			  Date date = new Date(0);
			l.info("Je vais supprimer un contrat.");
			Contrat C=new Contrat(date,"testAjout",0);
			l.info("Je vais ajouter le contrat.");
			int id_contrat=service.ajouterContrat(C);
			l.info("Je vais supprimer le contrat");
			service.deleteContratById(id_contrat);
			l.info("Je vais m'assurer que la methode getContratById() retourne null.");
			assertNull(service.getContratById(id_contrat));
			l.info("Out testSupprimerContrat() sans erreurs.");
		}catch (Exception e) { l.error("Erreur dans testSupprimerContrat() : " + e); }
	}
	
	
	

}

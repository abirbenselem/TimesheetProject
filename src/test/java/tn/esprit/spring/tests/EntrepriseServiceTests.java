package tn.esprit.spring.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;
import tn.esprit.spring.services.IEntrepriseService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EntrepriseServiceTests {
	
	@Autowired
	IEntrepriseService service;
	
	@Autowired
	EntrepriseRepository rep;
	
	@Autowired
	DepartementRepository drep;
	
	private static final Logger l = LogManager.getLogger(EntrepriseServiceTests.class);	
	
	
	@Test
	public void testAjouterEntreprise()
	{
		try {
			l.info("In testAjouterEntreprise():");
			Entreprise E=new Entreprise("testAjout", "testAjout");
			ArrayList <Entreprise> liste1 =(ArrayList<Entreprise>) rep.findAll();
			int size1=liste1.size();
			l.info("nombre d'entreprises avant l'ajout: " + size1);
			l.info("Je vais ajouter une entreprise.");
			int id=service.ajouterEntreprise(E);
			ArrayList <Entreprise> liste2 =(ArrayList<Entreprise>) rep.findAll();
			int size2=liste2.size();
			l.info("nombre d'entreprises apres l'ajout: " + size2);
			l.info("comparaison size avant et apres.");
			assertEquals(size2,(size1+1));
			
			service.deleteEntrepriseById(id);
			l.info("je supprime l'entreprise.");
			l.info("Out testAjouterEntreprise() sans erreurs.");
		}catch (Exception e) { l.error("Erreur dans testAjouterEntreprise() : " + e); }
	}
	
	
	
	@Test
	public void testAjouterDepartement()
	{
		try {
			l.info("In testAjouterDepartement():");
			Departement D=new Departement("Ressources Humaines.");
			D.setEntreprise(null);
			l.info("Je vais creer un departement.");
			int id=service.ajouterDepartement(D);
			l.info("Je vais ajouter un departement.");
			l.info("Id du departement que je viens d'ajouter: "+id);
			l.info("je teste si l'id du departement est bien different de 0.");
			assertNotEquals(id,0);
			l.info("je supprime le departement.");
			service.deleteDepartementById(id);
			l.info("Out testAjouterDepartement() sans erreurs.");
		}catch (Exception e) { l.error("Erreur dans testAjouterDepartement() : " + e); }
	}
	


	@Test
	public void testAffecterDepartement()
	{
		try {
			l.info("In testAjouterDepartement():");
			l.info("Je vais creer une entreprise.");
			Entreprise E=new Entreprise("testAjout", "testAjout");
			l.info("Je vais creer un departement.");
			Departement D=new Departement("Ressources Humaines");
			l.info("Je vais ajouter l'entreprise.");
			int id_entreprise=service.ajouterEntreprise(E);
			l.info("Je vais ajouter le departement.");
			int id_departement=service.ajouterDepartement(D);
			l.info("Je vais affecter le departement a l'entreprise.");
			service.affecterDepartementAEntreprise(id_departement, id_entreprise);
			l.info("Je vais reprendre le departement depuis la base de donnée.");
			Departement D1=drep.findById(id_departement).get();
			l.info("Je vais tester si l'entreprise_id du departement est égale a l'id de l'entreprise auquel je l'ai affecté.");
			
			assertEquals(D1.getEntreprise().getId(), id_entreprise);
			l.info("Je vais supprimer le departement.");
			service.deleteDepartementById(id_departement);
			l.info("Je vais supprimer l'entreprise.");
			service.deleteEntrepriseById(id_entreprise);
			l.info("Out testAffecterDepartement() sans erreurs.");
		}catch (Exception e) { l.error("Erreur dans testAffecterDepartement() : " + e); }
	}
	
	
	@Test
	public void testSupprimerEntreprise()
	{
		try{
			l.info("In testSupprimerEntreprise():");
			l.info("Je vais creer une entreprise.");
			Entreprise E=new Entreprise("testAjout", "testAjout");
			l.info("Je vais ajouter l'entreprise.");
			int id_entreprise=service.ajouterEntreprise(E);
			l.info("Je vais supprimer l'entreprise.");
			service.deleteEntrepriseById(id_entreprise);
			l.info("Je vais m'assurer que la methode getEntrepriseById() retourne null.");
			assertNull(service.getEntrepriseById(id_entreprise));
			l.info("Out testSupprimerEntreprise() sans erreurs.");
		}catch (Exception e) { l.error("Erreur dans testSupprimerEntreprise() : " + e); }
	}
	
	
	
	@Test
	public void testSupprimerDepartement()
	{
		try{
			l.info("In testSupprimerEntreprise():");
			l.info("Je vais creer un departement.");
			Departement D=new Departement("Ressources Humaines");
			D.setEntreprise(null);
			l.info("Je vais ajouter un departement.");
			int id_departement=service.ajouterDepartement(D);
			l.info("Je vais supprimer le departement.");
			service.deleteDepartementById(id_departement);
			l.info("Je vais m'assurer que la methode findDepartementById() retourne null.");
			assertNull(drep.findById(id_departement).orElse(null));
		}catch (Exception e) { l.error("Erreur dans testSupprimerDepartement() : " + e); }
	}
}

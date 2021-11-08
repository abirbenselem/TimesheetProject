package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;

@Service
public class ContratServiceImpl implements IContratService {


	@Autowired
	ContratRepository contratRepository;
	
	@Autowired
	EmployeRepository employeRepository;
	


	public List<Contrat> getAllContrats() {
		return (List<Contrat>) contratRepository.findAll();
	}
	
	public int ajouterContrat(Contrat con) {
		contratRepository.save(con);
		return con.getId();
	}
	
	public Contrat getContratById(int contratId) {
		return contratRepository.findById(contratId).get();	
	}
	
	@Transactional
	public void deleteContratById(int contratId) {
		contratRepository.delete(contratRepository.findById(contratId).get());	
	
	}
	
	public void affecterContratAEmployee(int contratId, int employeeId) {

				Contrat contratManagedEntity = contratRepository.findById(contratId).get();
				Employe employeManagedEntity = employeRepository.findById(employeeId).get();
				
				employeManagedEntity.setContrat(contratManagedEntity);
				employeRepository.save(employeManagedEntity);
		
	}


}

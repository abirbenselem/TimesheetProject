package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Contrat;

public interface IContratService {
	
	
	public List<Contrat> getAllContrats();
	public int ajouterContrat(Contrat con);
	public Contrat getContratById(int contratId);
	public void deleteContratById(int contratId);
	void affecterContratAEmployee(int contratId, int employeeId);
	
}
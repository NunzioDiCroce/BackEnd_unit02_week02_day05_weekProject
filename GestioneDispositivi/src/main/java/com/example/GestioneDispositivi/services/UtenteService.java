package com.example.GestioneDispositivi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestioneDispositivi.entities.Utente;
import com.example.GestioneDispositivi.exceptions.ItemNotFoundException;
import com.example.GestioneDispositivi.repositories.UtenteRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtenteService {

	private final UtenteRepository utenteRepository;

	@Autowired
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}

	// save utente
	public void save(Utente utente) {
		utenteRepository.save(utente);
		log.info("Utente con ID " + utente.getId() + " salvato con successo");

	}

//	// save by UtentePayload
//	public Utente save(UtentePayload body) {
//		Utente nuovoUtente = new Utente(body.getUserName(), body.getNome(), body.getMail());
//		return utenteRepository.save(nuovoUtente);
//	}

	public List<Utente> findAll() {
		return utenteRepository.findAll();
	}

	public Utente findById(long id) throws ItemNotFoundException {
		return utenteRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

//	public Utente findByIdAndUpdate(long id, UtentePayload body) throws ItemNotFoundException {
//		Utente found = this.findById(id);
//
//		found.setUserName(body.getUserName());
//		found.setNome(body.getNome());
//		found.setMail(body.getMail());
//
//		return utenteRepository.save(found);
//	}

	public void findByIdAndDelete(long id) throws ItemNotFoundException {
		Utente found = this.findById(id);
		utenteRepository.delete(found);
	}

}

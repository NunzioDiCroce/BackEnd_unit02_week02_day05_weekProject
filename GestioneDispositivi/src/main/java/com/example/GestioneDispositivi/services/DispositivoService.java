package com.example.GestioneDispositivi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.GestioneDispositivi.entities.Dispositivo;
import com.example.GestioneDispositivi.entities.DispositivoPayload;
import com.example.GestioneDispositivi.entities.DispositivoPayloadAssegna;
import com.example.GestioneDispositivi.entities.DispositivoPayloadRimuovi;
import com.example.GestioneDispositivi.entities.Utente;
import com.example.GestioneDispositivi.enums.StatoDispositivo;
import com.example.GestioneDispositivi.exceptions.ItemNotFoundException;
import com.example.GestioneDispositivi.repositories.DispositivoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DispositivoService {

	private final DispositivoRepository dispositivoRepository;

	private final UtenteService utenteService;

	@Autowired
	public DispositivoService(DispositivoRepository dispositivoRepository, UtenteService utenteService) {
		this.dispositivoRepository = dispositivoRepository;
		this.utenteService = utenteService;
	}

	// save dispositivo
	public void save(Dispositivo dispositivo) {
		dispositivoRepository.save(dispositivo);
		log.info("Dispositivo con ID " + dispositivo.getId() + " salvato con successo");

	}

	// save by DispositivoPayload
	public Dispositivo save(DispositivoPayload body) {
		Dispositivo nuovoDispositivo = new Dispositivo(body.getTipoDispositivo(), body.getStatoDispositivo());
		return dispositivoRepository.save(nuovoDispositivo);
	}

	public List<Dispositivo> findAll() {
		return dispositivoRepository.findAll();
	}

	public Dispositivo findById(long id) throws ItemNotFoundException {
		return dispositivoRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));

	}

	public Dispositivo findByIdAndUpdate(long id, DispositivoPayload body) throws ItemNotFoundException {
		Dispositivo found = this.findById(id);

		found.setTipoDispositivo(body.getTipoDispositivo());
		found.setStatoDispositivo(body.getStatoDispositivo());

		return dispositivoRepository.save(found);
	}

	public void findByIdAndDelete(long id) throws ItemNotFoundException {
		Dispositivo found = this.findById(id);
		dispositivoRepository.delete(found);
	}

	// * * * * * * * * * * METODO ASSEGNA DISPOSITIVO
	public Dispositivo assegnaDispositivo(long dispositivoId, DispositivoPayloadAssegna body)
			throws ItemNotFoundException, IllegalStateException {
		Dispositivo dispositivo = this.findById(dispositivoId);

		if (dispositivo.getUtente() != null) {
			throw new IllegalStateException("Il dispositivo è già assegnato ad un utente.");
		}

		if (dispositivo.getStatoDispositivo() != StatoDispositivo.DISPONIBILE) {
			throw new IllegalStateException("Il dispositivo non è disponibile per l'assegnazione.");
		}

		Utente utente = utenteService.findById(body.getUtenteId());
		dispositivo.setUtente(utente);
		dispositivo.setStatoDispositivo(StatoDispositivo.ASSEGNATO);

		return dispositivoRepository.save(dispositivo);

	}

	// * * * * * * * * * * METODO RITIRA DISPOSITIVO
	public Dispositivo ritiraDispositivo(long dispositivoId, DispositivoPayloadRimuovi body)
			throws ItemNotFoundException, IllegalStateException {
		Dispositivo dispositivo = this.findById(dispositivoId);

		if (dispositivo.getUtente() == null) {
			throw new IllegalStateException("Il dispositivo non è assegnato a nessun utente");
		}

		if (dispositivo.getStatoDispositivo() != StatoDispositivo.ASSEGNATO) {
			throw new IllegalStateException("Il dispositivo non è assegnato a nessun utente");
		}

		dispositivo.setUtente(null);
		dispositivo.setStatoDispositivo(StatoDispositivo.DISPONIBILE);

		return dispositivoRepository.save(dispositivo);

	}
}

package com.example.GestioneDispositivi.entities;

import java.util.Set;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Utente {

	@Id
	@GeneratedValue
	private long id;

	protected String userName;
	protected String nome;
	protected String cognome;
	protected String mail;

	@OneToMany
	protected Set<Dispositivo> dispositivi;

	public Utente(String userName, String nome, String cognome, String mail) {
		this.userName = userName;
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
	}

}

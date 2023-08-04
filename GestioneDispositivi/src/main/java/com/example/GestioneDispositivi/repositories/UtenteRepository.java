package com.example.GestioneDispositivi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GestioneDispositivi.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

}

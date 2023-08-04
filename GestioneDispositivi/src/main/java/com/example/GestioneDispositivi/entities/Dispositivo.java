package com.example.GestioneDispositivi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "dispositivi")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Slf4j
@Builder
public class Dispositivo {

	@Id
	@GeneratedValue
	private long id;

	@Enumerated(EnumType.STRING)
	protected TipoDispositivo tipoDispositivo;

	@Enumerated(EnumType.STRING)
	protected StatoDispositivo statoDispositivo;

	@ManyToOne
	protected Utente utente;

	public Dispositivo(TipoDispositivo tipoDispositivo, StatoDispositivo statoDispositivo) {
		this.tipoDispositivo = tipoDispositivo;
		this.statoDispositivo = statoDispositivo;
	}

}

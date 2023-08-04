package com.example.GestioneDispositivi.entities;

import com.example.GestioneDispositivi.enums.StatoDispositivo;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DispositivoPayloadAssegna {

	@Enumerated(EnumType.STRING)
	protected StatoDispositivo statoDispositivo;

	protected long utenteId;

}

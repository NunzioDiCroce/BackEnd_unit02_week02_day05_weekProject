package com.example.GestioneDispositivi.entities;

import com.example.GestioneDispositivi.enums.StatoDispositivo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DispositivoPayloadAssegna {

	protected StatoDispositivo statoDispositivo;
	protected long utenteId;

}

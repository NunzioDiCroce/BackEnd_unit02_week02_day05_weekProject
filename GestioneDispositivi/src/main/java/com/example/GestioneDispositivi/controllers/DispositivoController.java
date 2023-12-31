package com.example.GestioneDispositivi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.GestioneDispositivi.entities.Dispositivo;
import com.example.GestioneDispositivi.entities.DispositivoPayload;
import com.example.GestioneDispositivi.entities.DispositivoPayloadAssegna;
import com.example.GestioneDispositivi.entities.DispositivoPayloadRitira;
import com.example.GestioneDispositivi.services.DispositivoService;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {

	private final DispositivoService dispositivoService;

	@Autowired
	public DispositivoController(DispositivoService dispositivoService) {
		this.dispositivoService = dispositivoService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Dispositivo saveDevice(@RequestBody DispositivoPayload body) {
		Dispositivo created = dispositivoService.save(body);
		return created;
	}

	@GetMapping("")
	public List<Dispositivo> getDevices() {
		return dispositivoService.findAll();
	}

	@GetMapping("/{deviceId}")
	public Dispositivo getDeviceById(@PathVariable long deviceId) {
		return dispositivoService.findById(deviceId);

	}

	@PutMapping("/{deviceId}")
	public Dispositivo updateDevice(@PathVariable long deviceId, @RequestBody DispositivoPayload body) {
		return dispositivoService.findByIdAndUpdate(deviceId, body);
	}

	@DeleteMapping("/{deviceId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteDevice(@PathVariable long deviceId) {
		dispositivoService.findByIdAndDelete(deviceId);
	}

	// * * * * * * * * * * METODO ASSEGNA DISPOSITIVO
	@PutMapping("/{deviceId}/assegna")
	public Dispositivo assignDevice(@PathVariable long deviceId, @RequestBody DispositivoPayloadAssegna body) {
		return dispositivoService.assegnaDispositivo(deviceId, body);
	}

	// * * * * * * * * * * METODO RITIRA DISPOSITIVO
	@PutMapping("/{deviceId}/ritira")
	public Dispositivo retireDevice(@PathVariable long deviceId, @RequestBody DispositivoPayloadRitira body) {
		return dispositivoService.ritiraDispositivo(deviceId, body);
	}
}

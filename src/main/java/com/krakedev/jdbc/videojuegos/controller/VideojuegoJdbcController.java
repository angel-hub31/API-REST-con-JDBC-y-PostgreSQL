package com.krakedev.jdbc.videojuegos.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.jdbc.videojuegos.services.ServicioVideojuegoJdbc;
import com.krakedev.videojuegos.entidades.Videojuego;

@RestController
@RequestMapping("/jdbc/videojego")

public class VideojuegoJdbcController {
	
	private final ServicioVideojuegoJdbc servicio;
	//constructor VideojuegoJdbcController
	public VideojuegoJdbcController(ServicioVideojuegoJdbc servicio) {
		this.servicio=servicio;
	}
	//mapear crear Cliente
	public Videojuego crear(@RequestBody Videojuego videojuego) {
		return servicio.crear(videojuego);
	}
	
	
	

}

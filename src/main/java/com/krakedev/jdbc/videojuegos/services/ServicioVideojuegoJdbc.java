package com.krakedev.jdbc.videojuegos.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.jdbc.videojuego.VideojuegoJdbc;
import com.krakedev.videojuegos.entidades.Videojuego;

@Service
public class ServicioVideojuegoJdbc {
	
	public Videojuego crear(Videojuego videojuego) {
	return	VideojuegoJdbc.insertar(videojuego.getCodigo(), videojuego.getNombre(),videojuego.getPlataforma(),videojuego.getPrecio(), videojuego.isDisponible(), videojuego.getGenero());
		
	}
	
	public List<Videojuego>listar(){
		return VideojuegoJdbc.listar();
		
	}
	
	
	

}

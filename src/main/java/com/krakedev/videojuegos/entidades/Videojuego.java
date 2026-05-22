package com.krakedev.videojuegos.entidades;

public class Videojuego {
	private String codigo;
	private String nombre;
	private String plataforma;
	private double precio;
	private boolean disponible;
	private String genero;
	
	//constructor vacio
	public Videojuego() {
		
	}
	//constructor que recibe parametros
	public Videojuego(String codigo, String nombre, String plataforma, double precio, boolean disponible,
			String genero) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.plataforma = plataforma;
		this.precio = precio;
		this.disponible = disponible;
		this.genero = genero;
	}
	
	
	

}

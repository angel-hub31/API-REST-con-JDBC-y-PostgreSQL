package com.krakedev.jdbc.videojuego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

	private static final Logger log = LoggerFactory.getLogger(VideojuegoJdbc.class);

	public static Videojuego insertar(String codigo,String nombre,String plataforma,double precio,boolean disponible,String genero) {
		
		Connection con = null;
		PreparedStatement ps = null;
		Videojuego videojuego=null;
		
		try {
			con=Conexion.getConnection();
			String sql = "INSERT INTO videojuegos(codigo,nombre,plataforma,precio,disponible,genero) VALUES (?,?,?,?,?,?)";
			
			ps=con.prepareStatement(sql);
			
			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);
			
			
			videojuego=new Videojuego(codigo,nombre,plataforma,precio,disponible,genero);
			int filas =ps.executeUpdate();
			log.info("Filas insertadas: " + filas);
			
			if (con != null && !con.getAutoCommit()) {
				con.commit();
				log.info("Commit ejecutado con éxito en videojuegos.");
			}

			
		}catch(Exception e) {
			log.error("Error al insertar: ", e);
			throw new RuntimeException("error al insertar: "+ e.getMessage());


			
		}finally {
			try {
				if(con !=null) {
					con.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar la conexión: ", e);
			}
			try {
				if(ps !=null) {
					ps.close();

				}
	
			}catch(Exception e) {
				log.error("Error al cerrar la PreparedStatement: ", e);
				
			}
			
		}
		return videojuego;
	}

}

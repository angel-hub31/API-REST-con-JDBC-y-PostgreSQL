package com.krakedev.jdbc.videojuego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.jdbc.Conexion;
import com.krakedev.videojuegos.entidades.Videojuego;

public class VideojuegoJdbc {

	private static final Logger log = LoggerFactory.getLogger(VideojuegoJdbc.class);

	public static Videojuego insertar(String codigo, String nombre, String plataforma, double precio,
			boolean disponible, String genero) {

		Connection con = null;
		PreparedStatement ps = null;
		Videojuego videojuego = null;

		try {
			con = Conexion.getConnection();
			String sql = "INSERT INTO videojuegos(codigo,nombre,plataforma,precio,disponible,genero) VALUES (?,?,?,?,?,?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);
			ps.setString(2, nombre);
			ps.setString(3, plataforma);
			ps.setDouble(4, precio);
			ps.setBoolean(5, disponible);
			ps.setString(6, genero);

			videojuego = new Videojuego(codigo, nombre, plataforma, precio, disponible, genero);
			int filas = ps.executeUpdate();
			log.info("Filas insertadas: " + filas);

			if (con != null && !con.getAutoCommit()) {
				con.commit();
				log.info("Commit ejecutado con éxito en videojuegos.");
			}

		} catch (Exception e) {
			log.error("Error al insertar: ", e);
			throw new RuntimeException("error al insertar: " + e.getMessage());

		} finally {
			try {
				if (con != null) {
					con.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Error al cerrar la conexión: ", e);
			}
			try {
				if (ps != null) {
					ps.close();

				}

			} catch (Exception e) {
				log.error("Error al cerrar la PreparedStatement: ", e);

			}

		}
		return videojuego;
	}

	public static List<Videojuego> listar() {
		List<Videojuego> videojuego = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = Conexion.getConnection();
			String sql = "SELECT * FROM videojuegos";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Videojuego vj = new Videojuego(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("plataforma"), rs.getDouble("precio"), rs.getBoolean("disponible"),
						rs.getString("genero"));

				videojuego.add(vj);

			}

		} catch (Exception e) {
			log.error("Error al listar: ", e);
			throw new RuntimeException("error al listar: " + e.getMessage());

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				log.error("Error al cerrar el ResultSet: ", e);
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				log.error("Error al cerrar el PreparedStatement: ", e);
			}

			try {
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				log.error("Error al Listar: ", e);

			}

		}
		return videojuego;

	}
	
	public static Videojuego buscar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "SELECT * FROM videojuegos WHERE codigo=?";
		ResultSet rs = null;
		Videojuego videojuego = null;
		
		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);
			rs = ps.executeQuery();
			if (rs.next()) {
				videojuego  = new Videojuego(rs.getString("codigo"), rs.getString("nombre"),
						rs.getString("plataforma"), rs.getDouble("precio"), rs.getBoolean("disponible"),
						rs.getString("genero"));
			}

			
			
		}catch(Exception e) {
			log.error("Error al buscar por codigo: ", e);
			throw new RuntimeException("error al buscar: "+ e.getMessage());
			
		}finally {
			try {
				if(rs !=null) {
					rs.close();
				}
					
				
			}catch(Exception e) {
				log.error("Error al buscar: ", e);

				
			}
			try {
				if(ps !=null) {
					ps.close();
				}
					
				
			}catch(Exception e) {
				log.error("Error al buscar: ", e);

				
			}
			try {
				if(con !=null) {
					con.close();
				}
					
				
			}catch(Exception e) {
				log.error("Error al buscar: ", e);

				
			}
			
			
			
			
		}
		return videojuego;
		
	}
	
	public static Videojuego actualizar(String codigo,String nombre, String plataforma,double precio,boolean disponible,String genero) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE videojuegos SET nombre=?, plataforma=?,precio=?,disponible=?,genero=? WHERE codigo=?";
		ResultSet rs = null;
		Videojuego videojuego = null;
		
		
		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);
			
			ps.setString(1, nombre);
			ps.setString(2, plataforma);
			ps.setDouble(3, precio);
			ps.setBoolean(4, disponible);
			ps.setString(5,genero);
			ps.setString(6, codigo);
			
			int fila =ps.executeUpdate();
			videojuego =new Videojuego (codigo,nombre,plataforma,precio,disponible,genero);
		
			if (con != null && !con.getAutoCommit()) {
				con.commit();
				log.info("Commit ejecutado con exito en actualizar.");
			}
			
			
		}catch(Exception e) {
			log.error("Error al actualizar: ", e);
			throw new RuntimeException("error al actualizar: " + e.getMessage());
			
		}finally {
			try {
				if(rs !=null) {
					rs.close();
				}
					
				
			}catch(Exception e) {
				log.error("Error al actualizar: ", e);

				
			}
			try {
				if(ps !=null) {
					ps.close();
				}
					
				
			}catch(Exception e) {
				log.error("Error al actualizar: ", e);

				
			}
			try {
				if(con !=null) {
					con.close();
				}
					
				
			}catch(Exception e) {
				log.error("Error al actualizar: ", e);

				
			}
			
		}
		return videojuego;
		
	}
	public static boolean eliminar(String codigo) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM videojuegos WHERE codigo=?";
		ResultSet rs = null;
		
		try {
			con = Conexion.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, codigo);
			int fila =ps.executeUpdate();
			
			if (con != null && !con.getAutoCommit()) {
				con.commit();
				log.info("Commit ejecutado con éxito al eliminar.");
			}
		
			
		}catch(Exception e) {
			log.error("error al eliminar: ", e);
			throw new RuntimeException("error al eliminar: "+ e.getMessage());

			
			
		}finally {
			try {
				if(rs !=null) {
					rs.close();
					
				}
				
			}catch(Exception e) {
				log.error("error al eliminar: ", e);

				
			}
			try {
				if(ps !=null) {
					ps.close();
					
				}
				
			}catch(Exception e) {
				log.error("error al eliminar: ", e);

				
			}
			try {
				if(con !=null) {
					con.close();
					
				}
				
			}catch(Exception e) {
				log.error("error al eliminar: ", e);

				
			}
			
		}
		return true;
		
	}
	

}

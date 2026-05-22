package com.krakedev.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConexionPostgres {

	private static final Logger log = LoggerFactory.getLogger(ConexionPostgres.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tallerjdbc", "postgres", "1004034391");
			log.info("¡¡¡Conexion exitosa a vj !!!");

		} catch (SQLException e) {

			log.error("¡¡¡Error de conexion a vj !!! " + e.getMessage());

			// cerramos la conexion
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

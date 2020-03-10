package src.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import src.modelo.Conector;
import src.modelo.bean.Libro;

public class LibroModelo extends Conector {

	public Libro select(int id) {

		try {
			PreparedStatement pst = super.conexion.prepareStatement("select * from libros where id = ?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				Libro libro = new Libro();
				libro.setId(rs.getInt("id"));
				libro.setTitulo(rs.getString("titulo"));
				libro.setAutor(rs.getString("autor"));
				libro.setNumeroPaginas(rs.getInt("num_pag"));

				return libro;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public boolean exist(int idLibro) {

		try {
			PreparedStatement pst = super.conexion.prepareStatement("select * from libros where id = ?");
			pst.setInt(1, idLibro);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public ArrayList<Libro> selectAll(){
		
	ArrayList <Libro> libros = new ArrayList<Libro>();
	try {
		Statement st = super.conexion.createStatement();
		ResultSet rs = st.executeQuery("select * from libros");
		while(rs.next()){
			Libro libro = new Libro();
			libro.setId(rs.getInt("id"));
			libro.setTitulo(rs.getString("titulo"));
			libro.setAutor(rs.getString("autor"));
			libro.setNumeroPaginas(rs.getInt("num_pag"));
			
			libros.add(libro);
		}
		return libros;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return libros;
	}

	public ArrayList<Libro> selectAllConPrestamos(){
		ArrayList<Libro> libros = this.selectAll();
		PrestamoModelo prestamoModelo = new PrestamoModelo();
		
		Iterator<Libro> i = libros.iterator();
		while(i.hasNext()){
			Libro libro = i.next();
			libro.setPrestamos(modeloPrestamo.prestamosDeLibro(libro.getId()));
		}
		
		return libros;
	}


}

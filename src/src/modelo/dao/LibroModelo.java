package src.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

}

package src.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import src.modelo.Conector;
import src.modelo.bean.Libro;
import src.modelo.bean.Socio;

public class SocioModelo extends Conector {

	public Socio select(int id) {

		try {
			PreparedStatement pst = conexion.prepareStatement("select * from socios where id=?");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			Socio socio;
			if (rs.next()) {

				socio = new Socio();
				socio.setId(rs.getInt("id"));
				socio.setNombre(rs.getString("nombre"));
				socio.setApellido(rs.getString("apellido"));
				socio.setDni(rs.getString("dni"));
				socio.setDireccion(rs.getString("direccion"));
				socio.setPoblacion(rs.getString("poblacion"));
				socio.setProvincia(rs.getString("provincia"));

				return socio;
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}

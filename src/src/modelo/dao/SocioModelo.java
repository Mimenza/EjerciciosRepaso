package src.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public boolean exist(int idSocio) {

		try {
			PreparedStatement pst = super.conexion.prepareStatement("select * from socios where id = ?");
			pst.setInt(1, idSocio);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public ArrayList<Socio> selectAll(){

		ArrayList<Socio> socios = new ArrayList<Socio>();
		Statement st;
		try {
			st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from socios");

			Socio socio;
			while (rs.next()) {
				
				socio = new Socio();
				socio.setId(rs.getInt("id"));
				socio.setNombre(rs.getString("nombre"));
				socio.setApellido(rs.getString("apellido"));
				socio.setDni(rs.getString("dni"));
				socio.setDireccion(rs.getString("direccion"));
				socio.setPoblacion(rs.getString("poblacion"));
				socio.setProvincia(rs.getString("provincia"));
				
				socios.add(socio);
				
			}
			return socios;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return socios;
		
	}

}


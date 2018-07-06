package pe.tp1.hdpeta.jalame.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.SedeBean;
import pe.tp1.hdpeta.jalame.dao.DataAccess;

public class SedeDto {


	public ArrayList<SedeBean> listarSede() {

		DataAccess dAccess = new DataAccess();
		ArrayList<SedeBean> sedeList = new ArrayList<SedeBean>();

		try {
			String sqlSt =  " SELECT CODSEDE,SEDE,LATITUD,LONGITUD,DIRECCION,ESTADOR,TSUPDATE FROM SEDE ORDER BY SEDE "  ;

			Statement st = dAccess.getConnection().createStatement();
		    ResultSet rs = st.executeQuery(sqlSt);

			while (rs.next()) {
				sedeList.add(prepareSede(rs));
			}

		} catch (SQLException se) {
			System.out.println("SQL Error: " + se.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				dAccess.closeConnection();
			} catch (Exception e) {
				System.out.println("Error Dto/close: " + e.getMessage());
			}
		}

		return sedeList;
	}

	
	private SedeBean prepareSede(ResultSet rs) {
		SedeBean sede = new SedeBean();
		// CODSEDE,SEDE,LATITUD,LONGITUD,DIRECCION,ESTADOR,TSUPDATE

		try {
			sede.setCodSede(rs.getInt("CODSEDE"));
			sede.setSede(rs.getString("SEDE"));
			sede.setLatitud(rs.getString("LATITUD"));
			sede.setLongitud(rs.getString("LONGITUD"));
			sede.setDireccion(rs.getString("DIRECCION"));
			sede.setTsupdate(rs.getDate("TSUPDATE"));
			sede.setEstadoR(rs.getString("ESTADOR"));

		} catch (Exception e) {
			System.out.println("Error prepareSede: " + e.getMessage());
		}
 		
		return sede;
	}
}

package pe.tp1.hdpeta.jalame.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import pe.tp1.hdpeta.jalame.bean.TarifaBean;
import pe.tp1.hdpeta.jalame.dao.DataAccess;

public class TarifaDto {

	public TarifaBean getTarifa(int distancia) {

		DataAccess dAccess = new DataAccess();
		TarifaBean tarifa = new TarifaBean();

		try {
			String sqlSt =  " SELECT CODTARIFA,TARIFA,DISTANCIABASE,DISTANCIATOPE,IMPORTE,ESTADOR,TSUPDATE FROM TARIFA " + 
							" WHERE (DISTANCIABASE <= ? AND ? <= DISTANCIATOPE ) ORDER BY IMPORTE LIMIT 1  " ;

			  PreparedStatement pcst = dAccess.getConnection().prepareCall(sqlSt);
		      
		      pcst.setInt(1, distancia);
		      pcst.setInt(2, distancia);
		      
		      ResultSet rs = pcst.executeQuery();

			if (rs.next()) {
				tarifa = prepareTarifa(rs);
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

		return tarifa;
	}

	private TarifaBean prepareTarifa(ResultSet rs) {
		TarifaBean tarifa = new TarifaBean();
		// CODTARIFA,TARIFA,DISTANCIABASE,DISTANCIATOPE,IMPORTE,ESTADOR,TSUPDATE

		try {
			tarifa.setCodTarifa(rs.getInt("CODTARIFA"));
			tarifa.setTarifa(rs.getString("TARIFA"));
			tarifa.setDistanciaBase(rs.getInt("DISTANCIABASE"));
			tarifa.setDistanciaTope(rs.getInt("DISTANCIATOPE"));
			tarifa.setImporte(rs.getDouble("IMPORTE"));
			tarifa.setTsupdate(rs.getDate("TSUPDATE"));
			tarifa.setEstadoR(rs.getString("ESTADOR"));

		} catch (Exception e) {
			System.out.println("Error prepareTarifa: " + e.getMessage());
		}

		return tarifa;
	}

}

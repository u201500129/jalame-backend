package pe.tp1.hdpeta.jalame.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.FormaPagoBean;
import pe.tp1.hdpeta.jalame.dao.DataAccess;

public class FormaPagoDto {


	public ArrayList<FormaPagoBean> listarFormaPago() {

		DataAccess dAccess = new DataAccess();
		ArrayList<FormaPagoBean> formaPagoList = new ArrayList<FormaPagoBean>();

		try {
			String sqlSt =  " SELECT CODFORMAPAGO,FORMAPAGO,ESTADOR,TSUPDATE FROM FORMAPAGO ORDER BY FORMAPAGO "  ;

			Statement st = dAccess.getConnection().createStatement();
		    ResultSet rs = st.executeQuery(sqlSt);

			while (rs.next()) {
				formaPagoList.add(prepareFormaPago(rs));
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

		return formaPagoList;
	}

	
	private FormaPagoBean prepareFormaPago(ResultSet rs) {
		FormaPagoBean formaPago = new FormaPagoBean();
		// CODFORMAPAGO,FORMAPAGO,ESTADOR,TSUPDATE

		try {
			formaPago.setCodFormaPago(rs.getInt("CODFORMAPAGO"));
			formaPago.setFormaPago(rs.getString("FORMAPAGO"));  
			formaPago.setTsupdate(rs.getDate("TSUPDATE"));
			formaPago.setEstadoR(rs.getString("ESTADOR"));

		} catch (Exception e) {
			System.out.println("Error prepareFormaPago: " + e.getMessage());
		}
 		
		return formaPago;
	}
	
	
}

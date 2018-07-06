package pe.tp1.hdpeta.jalame.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat; 
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.ServicioBean;
import pe.tp1.hdpeta.jalame.bean.ServicioEstadoBean;
import pe.tp1.hdpeta.jalame.dao.DataAccess;

public class ServicioDto {

	public ServicioBean getServicio(int codigoServicio) {

		DataAccess dAccess = new DataAccess();
		ServicioBean servicio = new ServicioBean();

		try {

			String sqlSt = "SELECT CODSERVICIO,CODCONDUCTOR,CODUSUARIO,CODVEHICULO,CODTARIFA,CODFORMAPAGO, "
					+ " FECREGISTRO,INICIOSERV,FINSERV,ORIGENDES,ORIGENLAT,ORIGENLON,DESTINODES, "
					+ " DESTINOLAT,DESTINOLON,CALIFICACIONUSUARIO,CALIFICACIONCONDUCTOR, "
					+ " CALIFICACIONVEHICULO,COMMTUSUA,COMMTCOND,ESTADOSERV,ESTADOR,TSUPDATE "
					+ " FROM SERVICIOS WHERE ESTADOR <> '0' AND CODSERVICIO = " + codigoServicio;

			Statement st = dAccess.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sqlSt);

			if (rs.next()) {
				servicio = prepareServicio(rs);
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
		return servicio;
	}

	public ArrayList<ServicioBean> getServicioList(String perfil, int codigo) {

		    DataAccess dAccess = new DataAccess();
		    ArrayList<ServicioBean> servicioList = new ArrayList<ServicioBean>();
		    
		    try { 
		    	String sqlSt = "SELECT CODSERVICIO,CODCONDUCTOR,CODUSUARIO,CODVEHICULO,CODTARIFA,CODFORMAPAGO, " + 
			      		" FECREGISTRO,INICIOSERV,FINSERV,ORIGENDES,ORIGENLAT,ORIGENLON,DESTINODES, " + 
			      		" DESTINOLAT,DESTINOLON,CALIFICACIONUSUARIO,CALIFICACIONCONDUCTOR, " + 
			      		" CALIFICACIONVEHICULO,COMMTUSUA,COMMTCOND,ESTADOSERV,ESTADOR,TSUPDATE " +
			      		" FROM SERVICIOS WHERE ESTADOR <> '0' " ; 
		    	
		    	if (perfil.toUpperCase().trim().equals("U")){
		    		sqlSt += " AND CODUSUARIO = ? " ;
		    	}else {
		    		sqlSt += " AND CODCONDUCTOR = ? " ;
		    	}
		      
		    	sqlSt += " ORDER BY FECREGISTRO DESC " ;
		    		  
		      PreparedStatement pcst = dAccess.getConnection().prepareCall(sqlSt);
		      
		      pcst.setInt(1, codigo); 
		      
		      ResultSet rs = pcst.executeQuery();
		      
		      while (rs.next()){ 	        
		    	  servicioList.add(prepareServicio(rs));
			  }
		            
		    }    catch (SQLException se)    {
		      System.out.println("SQL Error: " + se.getMessage());
		    }    catch (Exception e)    {
		      System.out.println("Error: " + e.getMessage());
		    }finally {
		    	try {
		            dAccess.closeConnection();    		
		    	}catch(Exception e)    {
		    		System.out.println("Error Dto/close: " + e.getMessage());
		    	}

		    }
		    
		    return servicioList;
		  }

	public ServicioBean addServicio(ServicioBean servicio) {

		DataAccess dAccess = new DataAccess();
		ServicioBean servicioResp = new ServicioBean();
		ResultSet rs = null;
		int codigoServicio = -1;

		try {
			// CODSERVICIO,CODCONDUCTOR,CODUSUARIO,CODVEHICULO,CODTARIFA,CODFORMAPAGO,
			// FECREGISTRO,INICIOSERV,FINSERV,ORIGENDES,ORIGENLAT,ORIGENLON,DESTINODES,
			// DESTINOLAT,DESTINOLON,CALIFICACIONUSUARIO,CALIFICACIONCONDUCTOR,
			// CALIFICACIONVEHICULO,COMMTUSUA,COMMTCOND,ESTADOSERV,ESTADOR,TSUPDATE

			String sqlSt = " INSERT INTO SERVICIOS(CODCONDUCTOR,CODUSUARIO,CODVEHICULO,CODTARIFA,CODFORMAPAGO, " +
   		  		 		   " ORIGENDES,ORIGENLAT,ORIGENLON,  DESTINODES,DESTINOLAT,DESTINOLON ) VALUES( " + 
	    		  		 servicio.getCodConductor() + ", " +
	    		  		 servicio.getCodUsuario() + ", " +
	    		  		 servicio.getCodVehiculo() + ", " +
	    		  		 servicio.getCodTarifa() + ", " +
	    		  		 servicio.getCodFormaPago() + ", '" + 
                         servicio.getOrigenDes().trim() + "', '" +
                         servicio.getOrigenLat().trim() + "', '" +
                         servicio.getOrigenLon().trim() + "', '" +
                         servicio.getDestinoDes().trim() + "', '" +
                         servicio.getDestinoLat().trim() + "', '" +
                         servicio.getDestinoLon().trim() + "' ) " ;
			

			Statement st = dAccess.getConnection().createStatement();
			st.executeUpdate(sqlSt, Statement.RETURN_GENERATED_KEYS);
			rs = st.getGeneratedKeys();

			if (rs.next()) {
				codigoServicio = rs.getInt(1);
				servicioResp = getServicio(codigoServicio);
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

		return servicioResp;
	}

	 
	
	public int updateEstadoServicio(String estado, int codigoServicio) {
		DataAccess dAccess = new DataAccess();

		try {
			
/* ESTADO_SERVICIO
	 		S:        Solicitado
			A:        Aceptado
			R:        Rechazado
			C:        Completado
			D:        Desistido x Usuario	
*/
	
			String sqlSt = " UPDATE SERVICIOS SET ESTADOSERV = '" + estado.toUpperCase().trim() + "' "; 

			switch (estado.toUpperCase().trim()) {
				case "A":
					sqlSt += ", INICIOSERV = CURRENT_TIMESTAMP() " ;
					break;
				case "C":
					sqlSt += ", FINSERV = CURRENT_TIMESTAMP() " ;
					break;
				default:
					break;
			}
			
			sqlSt += "WHERE CODSERVICIO = " + codigoServicio;
			
			
			Statement st = dAccess.getConnection().createStatement();
			st.executeUpdate(sqlSt);

			return st.getUpdateCount();

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
		return -1;
	}

	 
	
	public ServicioEstadoBean getEstadoServicio(int codigoServicio) {

		DataAccess dAccess = new DataAccess();
		ServicioEstadoBean servicio = new ServicioEstadoBean();

		try {

			String sqlSt = "SELECT CODSERVICIO, FECREGISTRO,INICIOSERV,FINSERV, ESTADOSERV,ESTADOR,TSUPDATE "
					+ " FROM SERVICIOS WHERE ESTADOR <> '0' AND CODSERVICIO = " + codigoServicio;

			Statement st = dAccess.getConnection().createStatement();
			ResultSet rs = st.executeQuery(sqlSt);

			if (rs.next()) {
				servicio = prepareEstadoServicio(rs);
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
		return servicio;
	}

	private ServicioEstadoBean prepareEstadoServicio(ResultSet rs) {
		ServicioEstadoBean servicioEstado = new ServicioEstadoBean();
		// CODSERVICIO, FECREGISTRO,INICIOSERV,FINSERV,ESTADOSERV,ESTADOR,TSUPDATE
 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			servicioEstado.setCodServicio(rs.getInt("CODSERVICIO")); 

			servicioEstado.setFecRegistro(dateFormat.format(rs.getTimestamp("FECREGISTRO")));
			try {
				servicioEstado.setInicioServ(dateFormat.format(rs.getTimestamp("INICIOSERV")));
				servicioEstado.setFinServ(dateFormat.format(rs.getTimestamp("FINSERV")));
			}catch (Exception e) {
				System.out.println("Error Convertir FECHA: " + e.getMessage());
			}
			 
			servicioEstado.setEstadoServ(rs.getString("ESTADOSERV"));

			servicioEstado.setEstadoR(rs.getString("ESTADOR"));
			servicioEstado.setTsupdate(dateFormat.format(rs.getTimestamp("TSUPDATE"))); 

		} catch (Exception e) {
			System.out.println("Error prepareEstadoServicio: " + e.getMessage());
		}

		return servicioEstado;
	}

	
	
	private ServicioBean prepareServicio(ResultSet rs) {
		ServicioBean servicio = new ServicioBean();
		// CODSERVICIO,CODCONDUCTOR,CODUSUARIO,CODVEHICULO,CODTARIFA,CODFORMAPAGO,
		// FECREGISTRO,INICIOSERV,FINSERV,ORIGENDES,ORIGENLAT,ORIGENLON,DESTINODES,
		// DESTINOLAT,DESTINOLON,CALIFICACIONUSUARIO,CALIFICACIONCONDUCTOR,
		// CALIFICACIONVEHICULO,COMMTUSUA,COMMTCOND,ESTADOSERV,ESTADOR,TSUPDATE

		//Format dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			servicio.setCodServicio(rs.getInt("CODSERVICIO"));
			servicio.setCodConductor(rs.getInt("CODCONDUCTOR"));
			servicio.setCodUsuario(rs.getInt("CODUSUARIO"));
			servicio.setCodVehiculo(rs.getInt("CODVEHICULO"));
			servicio.setCodTarifa(rs.getInt("CODTARIFA"));
			servicio.setCodFormaPago(rs.getInt("CODFORMAPAGO"));

			servicio.setFecRegistro(dateFormat.format(rs.getTimestamp("FECREGISTRO")));
			try {
				servicio.setInicioServ(dateFormat.format(rs.getTimestamp("INICIOSERV")));
				servicio.setFinServ(dateFormat.format(rs.getTimestamp("FINSERV")));
			}catch (Exception e) {
				System.out.println("Error Convertir FECHA: " + e.getMessage());
			}
			

			servicio.setOrigenDes(rs.getString("ORIGENDES"));
			servicio.setOrigenLat(rs.getString("ORIGENLAT"));
			servicio.setOrigenLon(rs.getString("ORIGENLON"));
			servicio.setDestinoDes(rs.getString("DESTINODES"));
			servicio.setDestinoLat(rs.getString("DESTINOLAT"));
			servicio.setDestinoLon(rs.getString("DESTINOLON"));

			servicio.setCalificacionUsuario(rs.getInt("CALIFICACIONUSUARIO"));
			servicio.setCalificacionConductor(rs.getInt("CALIFICACIONCONDUCTOR"));
			servicio.setCalificacionVehiculo(rs.getInt("CALIFICACIONVEHICULO"));

			servicio.setCommtUsua(rs.getString("COMMTUSUA"));
			servicio.setCommtCond(rs.getString("COMMTCOND"));
			servicio.setEstadoServ(rs.getString("ESTADOSERV"));

			servicio.setEstadoR(rs.getString("ESTADOR"));
			servicio.setTsupdate(dateFormat.format(rs.getTimestamp("TSUPDATE")));
			//servicio.setTsupdate(DateFormatUtils.format(rs.getDate("TSUPDATE"), "yyyy-MM-dd HH:mm:SS"));

		} catch (Exception e) {
			System.out.println("Error prepareServicio: " + e.getMessage());
		}

		return servicio;
	}

}

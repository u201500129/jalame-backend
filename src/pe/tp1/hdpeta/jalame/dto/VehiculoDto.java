package pe.tp1.hdpeta.jalame.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.VehiculoBean; 
import pe.tp1.hdpeta.jalame.dao.DataAccess;

public class VehiculoDto {
 
	  public VehiculoBean getVehiculo(int codigoVehiculo) {

		    DataAccess dAccess = new DataAccess();
	        VehiculoBean vehiculo = new VehiculoBean();
	        
		    try    {
		     
		      String sqlSt = " SELECT CODVEHICULO,CODPERSONA,POLIZASOAT,MARCA,MODELO,AFABRICA,MATRICULA,COLOR,ASIENTOSTOTAL,ASIENTOSDISP, " + 
		    		  		 " LATITUD,LONGITUD,VISIBLE,CALIFICACION,ESTADOR,FOTO,TSUPDATE, 0 DISTANCIA " + 
		    		  		 " FROM VEHICULO WHERE ESTADOR <> '0' AND CODVEHICULO = " + codigoVehiculo + "";
		      
		      Statement st = dAccess.getConnection().createStatement();
		      ResultSet rs = st.executeQuery(sqlSt);
		      
		      	if (rs.next()){
		      		vehiculo = prepareVehiculo(rs); 		        	      
		      	}
		            
		    } catch (SQLException se)    {
		    	System.out.println("SQL Error: " + se.getMessage());
		    } catch (Exception e)    {
		    	System.out.println("Error: " + e.getMessage());
		    }finally {
		    	try {
		            dAccess.closeConnection();   		
		    	}catch(Exception e)    {
		    		System.out.println("Error Dto/close: " + e.getMessage());
		    	}
		    }	    
		    return vehiculo;
		  }
	   
	  public ArrayList<VehiculoBean> listarVehiculo(int codigo, String latitud, String longitud) {

		    DataAccess dAccess = new DataAccess();
		    ArrayList<VehiculoBean> vehiculoList = new ArrayList<VehiculoBean>();
		    
		    try    {	       
		      String sqlSt = " CALL getVehiculo(?, ?, ? ) " ;
		    		  
		      PreparedStatement pcst = dAccess.getConnection().prepareCall(sqlSt);
		      
		      pcst.setInt(1, codigo);
		      pcst.setString(2, latitud);
		      pcst.setString(3, longitud);
		      
		      ResultSet rs = pcst.executeQuery();
		      
		      while (rs.next()){ 	        
		    	  vehiculoList.add(prepareVehiculo(rs));
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
		    
		    return vehiculoList;
		  }

	  public VehiculoBean addVehiculo(VehiculoBean vehiculo) {

		    DataAccess dAccess = new DataAccess();
	        VehiculoBean vehiculoResp = new VehiculoBean();
	        ResultSet rs = null; 
	        int codigoVehiculo = -1 ; 

	        
	        try    {
	    		// CODVEHICULO,CODPERSONA,POLIZASOAT,MARCA,MODELO,AFABRICA,MATRICULA,COLOR,ASIENTOSTOTAL,ASIENTOSDISP,
	    		// LATITUD,LONGITUD,VISIBLE,CALIFICACION,ESTADOR,FOTO,TSUPDATE

	        	String sqlSt =  " INSERT INTO VEHICULO (CODPERSONA,POLIZASOAT,MARCA,MODELO,AFABRICA,MATRICULA, " + 
	        					" COLOR,ASIENTOSTOTAL,ASIENTOSDISP,LATITUD, LONGITUD, VISIBLE ) VALUES( " + 
	    		  		 " " + vehiculo.getCodPersona() + ", " +
	    		  		 " '" + vehiculo.getPolizaSoat().trim() + "', " +
	    		  		 " '" + vehiculo.getMarca().trim() + "', " +
	    		  		 " '" + vehiculo.getModelo().trim() + "', " +
	    		  		 " '" + vehiculo.getaFabrica().trim() + "', " +
	    		  		 " '" + vehiculo.getMatricula().trim() + "', " +
	    		  		 " '" + vehiculo.getColor().trim() + "', " +
	    		  		 "  " + vehiculo.getAsientosTotal() + ", " +
	    		  		 "  " + vehiculo.getAsientosDisp() + ", " +
	    		  		 " '" + vehiculo.getLatitud().trim() + "', " +
	    		  		 " '" + vehiculo.getLongitud().trim() + "', " +
	    		  		 " '" + vehiculo.getVisible().trim() + "') " ; 

	        	
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt,Statement.RETURN_GENERATED_KEYS);
	        	rs = st.getGeneratedKeys();
	        	
	        	if (rs.next()) {
	        		codigoVehiculo = rs.getInt(1);
	                vehiculoResp = getVehiculo(codigoVehiculo);
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
		    
		    return vehiculoResp;
		  }
	  	  
	  public VehiculoBean updateVehiculo(VehiculoBean vehiculo) {

		    DataAccess dAccess = new DataAccess();
	        VehiculoBean vehiculoResp = new VehiculoBean();
	        
	        try    {
		    
	        	String sqlSt = " UPDATE VEHICULO SET " + 
	        			 " CODPERSONA = " + vehiculo.getCodPersona() + ", " +
	    		  		 " POLIZASOAT = '" + vehiculo.getPolizaSoat().trim() + "', " +
	    		  		 " MARCA = '" + vehiculo.getMarca().trim() + "', " +
	    		  		 " MODELO = '" + vehiculo.getModelo().trim() + "', " +
	    		  		 " AFABRICA = '" + vehiculo.getaFabrica().trim() + "', " +
	    		  		 " MATRICULA = '" + vehiculo.getMatricula().trim() + "', " +
	    		  		 " COLOR = '" + vehiculo.getColor().trim() + "', " +
	    		  		 " ASIENTOSTOTAL =  " + vehiculo.getAsientosTotal() + ", " +
	    		  		 " ASIENTOSDISP =  " + vehiculo.getAsientosDisp() + ", " +
	    		  		 " LATITUD = '" + vehiculo.getLatitud().trim() + "', " +
	    		  		 " LONGITUD = '" + vehiculo.getLongitud().trim() + "', " +
	    		  		 " VISIBLE = '" + vehiculo.getVisible().trim() + "' " + 
	        			 " WHERE CODVEHICULO = " + vehiculo.getCodVehiculo() + " " ;

	        	//System.out.println("SQL: " + sqlSt);
	        	
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt);
	        	
	        	if (st.getUpdateCount() > 0 ) {
	                vehiculoResp = getVehiculo(vehiculo.getCodVehiculo());
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
		    
		    return vehiculoResp;
		  }
	  	   
	  public int deleteVehiculo(int codigoVehiculo) {
		    DataAccess dAccess = new DataAccess();
	        
	        try    {
	        	String sqlSt = " UPDATE VEHICULO SET ESTADOR = '0' WHERE CODVEHICULO = " + codigoVehiculo;
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt);

	        	return st.getUpdateCount();

		         		            
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
		    return -1 ;
		  }
	  
	  public int visibleVehiculo(int codigoVehiculo, String visible) {
		    DataAccess dAccess = new DataAccess();
	        
	        try    {
	        	String sqlSt = " UPDATE VEHICULO SET ASIENTOSDISP = ASIENTOSTOTAL, VISIBLE = '" + visible.toUpperCase().trim() + "' WHERE CODVEHICULO = " + codigoVehiculo;
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt);

	        	return st.getUpdateCount();

		         		            
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
		    return -1 ;
		  }
	  
	  public int locationVehiculo(int codigoVehiculo, String latitud, String longitud ) {
		    DataAccess dAccess = new DataAccess();
	        
	        try    {
	        	String sqlSt = " UPDATE VEHICULO SET LATITUD = '" + latitud.trim() + "',  LONGITUD = '" + longitud.trim() + "' WHERE CODVEHICULO = " + codigoVehiculo;
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt);

	        	return st.getUpdateCount();

		         		            
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
		    return -1 ;
		  }
	  
	  public int asientoVehiculo(int codigoVehiculo, int cantidad) {
		    DataAccess dAccess = new DataAccess();
	        
	        try    {
	        	String sqlSt = " UPDATE VEHICULO SET ASIENTOSDISP = ASIENTOSDISP + (" + cantidad + ")  WHERE CODVEHICULO = " + codigoVehiculo;
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt);

	        	return st.getUpdateCount();

		         		            
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
		    return -1 ;
		  }
	  
	  
	  private VehiculoBean prepareVehiculo(ResultSet rs) {
		  VehiculoBean vehiculo = new VehiculoBean();
		// CODVEHICULO,CODPERSONA,POLIZASOAT,MARCA,MODELO,AFABRICA,MATRICULA,COLOR,ASIENTOSTOTAL,ASIENTOSDISP,
		// LATITUD,LONGITUD,VISIBLE,CALIFICACION,ESTADOR,FOTO,TSUPDATE
		  
		  try {
			  vehiculo.setCodVehiculo(rs.getInt("CODVEHICULO"));
			  vehiculo.setCodPersona(rs.getInt("CODPERSONA"));
			  vehiculo.setPolizaSoat(rs.getString("POLIZASOAT"));
			  vehiculo.setMarca(rs.getString("MARCA"));
			  vehiculo.setModelo(rs.getString("MODELO"));
			  vehiculo.setaFabrica(rs.getString("AFABRICA"));
			  vehiculo.setMatricula(rs.getString("MATRICULA"));
			  vehiculo.setColor(rs.getString("COLOR"));
			  vehiculo.setAsientosTotal(rs.getInt("ASIENTOSTOTAL"));
			  vehiculo.setAsientosDisp(rs.getInt("ASIENTOSDISP"));
			  vehiculo.setLatitud(rs.getString("LATITUD"));
			  vehiculo.setLongitud(rs.getString("LONGITUD"));
			  vehiculo.setVisible(rs.getString("VISIBLE"));
			  vehiculo.setCalificacion(rs.getInt("CALIFICACION"));
			  vehiculo.setEstadoR(rs.getString("ESTADOR"));
			  //vehiculo.setFoto(rs.getBlob("FOTO"));
			  vehiculo.setTsupdate(rs.getDate("TSUPDATE"));
			  vehiculo.setDistancia(rs.getInt("DISTANCIA"));

		  }catch(Exception e) {  
			  System.out.println("Error prepareVehiculo: " + e.getMessage());
		  }
		  
		  return vehiculo; 
	  }
	  

}

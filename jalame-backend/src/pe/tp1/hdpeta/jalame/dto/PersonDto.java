package pe.tp1.hdpeta.jalame.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.PersonBean;
import pe.tp1.hdpeta.jalame.dao.DataAccess;

public class PersonDto {

	  public PersonBean login(String user, String passw) {

		    DataAccess dAccess = new DataAccess();
	        PersonBean person = new PersonBean();
	        
		    try    {
 
		    	String sqlSt = " SELECT COD_PERSONA,NOMBRE,APELLIDO,SEXO,DNI," + 
		    		  		 " CARRERA, CORREO, P_ESTADO, TELEFONO " + 
		    		  		 " FROM PERSONA WHERE P_ESTADO <> '0' AND CORREO = ? AND CLAVE = ? " ;
		    	 
		      PreparedStatement pcst = dAccess.getConnection().prepareCall(sqlSt);
		      
		      pcst.setString(1, user);
		      pcst.setString(2, passw);
		      
		      ResultSet rs = pcst.executeQuery();
		      
		      
		      if(rs.next()){        
		    	  person = preparePerson(rs);
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
		    
		    return person;
		  }

	  public PersonBean getPersona(int codigoPersona) {

		    DataAccess dAccess = new DataAccess();
	        PersonBean person = new PersonBean();
	        
		    try    {
		     
		      String sqlSt = " SELECT COD_PERSONA,NOMBRE,APELLIDO,SEXO,DNI," + 
		    		  		 " CARRERA, CORREO, P_ESTADO, TELEFONO " + 
		    		  		 " FROM PERSONA WHERE  P_ESTADO <> '0' AND COD_PERSONA = " + codigoPersona ;
		      Statement st = dAccess.getConnection().createStatement();
		      ResultSet rs = st.executeQuery(sqlSt);
		      
		      if (rs.next()){
		        person = preparePerson(rs); 		        	      
		      }
		            
		    }    catch (SQLException se)    {
		      System.out.println("SQL Error: " + se.getMessage());
		    }    catch (Exception e)    {
		      System.out.println("Error: " + e.getMessage());
		    }finally {
		    	try {
		            //cerrando la conexion.
		            dAccess.closeConnection();  
		            //System.out.println("Conexion Cerrada");    		
		    	}catch(Exception e)    {
		    		System.out.println("Error Dto/close: " + e.getMessage());
		    	}

		    }
		    
		    return person;
		  }
	  
	  public ArrayList<PersonBean> listarPersona(String apellido) {

		    DataAccess dAccess = new DataAccess();
		    ArrayList<PersonBean> personList = new ArrayList<PersonBean>();
		    
		    try    {
		    	
		      System.out.println("Debug listarPersona/apellido: "  +  apellido);	
		       
		      String sqlSt = " SELECT COD_PERSONA,NOMBRE,APELLIDO,SEXO,DNI," + 
		    		  		 " CARRERA, CORREO, P_ESTADO, TELEFONO " + 
		    		  		 " FROM PERSONA WHERE P_ESTADO <> '0' AND APELLIDO LIKE '%" + apellido.trim().replace(" ", "%") + "%' " ;
		      sqlSt = sqlSt.replace("%%", "%");
		    		  
		      Statement st = dAccess.getConnection().createStatement();
		      ResultSet rs = st.executeQuery(sqlSt);

		      
		      while (rs.next()){
		        personList.add(preparePerson(rs));	        
		      }
		            
		    }    catch (SQLException se)    {
		      System.out.println("SQL Error: " + se.getMessage());
		    }    catch (Exception e)    {
		      System.out.println("Error: " + e.getMessage());
		    }finally {
		    	try {
		            //cerrando la conexion.
		            dAccess.closeConnection();  
		            //System.out.println("Conexion Cerrada");    		
		    	}catch(Exception e)    {
		    		System.out.println("Error Dto/close: " + e.getMessage());
		    	}

		    }
		    
		    return personList;
		  }


	  public PersonBean addPersona(PersonBean persona) {

		    DataAccess dAccess = new DataAccess();
	        PersonBean personResp = new PersonBean();
	        ResultSet rs = null; 
	        int codigoPersona = -1 ; 

	        
	        try    {
		    
	        	String sqlSt = " INSERT INTO PERSONA(NOMBRE,APELLIDO,SEXO,DNI,CARRERA,CORREO,P_ESTADO,CLAVE,TELEFONO) VALUES( " + 
	    		  		 " '" + persona.getNombre().trim() + "', " +
	    		  		 " '" + persona.getApellido().trim() + "', " +
	    		  		 " '" + persona.getSexo().trim() + "', " +
	    		  		 " '" + persona.getDni().trim() + "', " +
	    		  		 " '" + persona.getCarrera().trim() + "', " +
	    		  		 " '" + persona.getCorreo().trim() + "', " +
	    		  		 " '" + persona.getEstadop().trim() + "', " +
	    		  		 " '" + persona.getClave().trim() + "', " +
	    		  		 " '" + persona.getTelefono().trim() + "') " ; 

	        	
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt,Statement.RETURN_GENERATED_KEYS);
	        	rs = st.getGeneratedKeys();
	        	
	        	if (rs.next()) {
	        		codigoPersona = rs.getInt(1);
	                personResp = getPersona(codigoPersona);
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
		    
		    return personResp;
		  }
	  
	  
	  
	  public PersonBean updatePersona(PersonBean persona) {

		    DataAccess dAccess = new DataAccess();
	        PersonBean personResp = new PersonBean();
	        
	        try    {
		    
	        	String sqlSt = " UPDATE PERSONA SET " + 
	    		  		 " NOMBRE = '" + persona.getNombre().trim() + "', " +
	    		  		 " APELLIDO = '" + persona.getApellido().trim() + "', " +
	    		  		 " SEXO = '" + persona.getSexo().trim() + "', " +
	    		  		 " DNI = '" + persona.getDni().trim() + "', " +
	    		  		 " CARRERA = '" + persona.getCarrera().trim() + "', " +
	    		  		 " CORREO = '" + persona.getCorreo().trim() + "', " +
	    		  		 " P_ESTADO = '" + persona.getEstadop().trim() + "', " +
	    		  		 " CLAVE = '" + persona.getClave().trim() + "', " +
	    		  		 " TELEFONO = '" + persona.getTelefono().trim() + "' " + 
	        			 " WHERE COD_PERSONA = " + persona.getCodpersona() + " " ;

	        	//System.out.println("SQL: " + sqlSt);
	        	
	        	
	        	Statement st = dAccess.getConnection().createStatement();      	
	        	st.executeUpdate(sqlSt);
	        	
	        	if (st.getUpdateCount() > 0 ) {
	                personResp = getPersona(persona.getCodpersona());
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
		    
		    return personResp;
		  }
	  
	  
	  public int deletePersona(int codigoPersona) {
		    DataAccess dAccess = new DataAccess();
	        
	        try    {
		    
	        	String sqlSt = " UPDATE PERSONA SET P_ESTADO = '0' WHERE COD_PERSONA = " + codigoPersona;
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
	  
	  private PersonBean preparePerson(ResultSet rs) {
		  PersonBean person = new PersonBean();
		  
		  try {
		        person.setCodpersona(rs.getInt("COD_PERSONA"));
		        person.setNombre(rs.getString("NOMBRE"));
		        person.setApellido(rs.getString("APELLIDO"));
		        person.setSexo(rs.getString("SEXO"));
		        person.setDni(rs.getString("DNI"));
		        person.setCarrera(rs.getString("CARRERA"));
		        person.setCorreo(rs.getString("CORREO"));
		        person.setEstadop(rs.getString("P_ESTADO"));
		        //person.setClave(rs.getString("CLAVE"));
		        person.setTelefono(rs.getString("TELEFONO"));
		        
		  }catch(Exception e) {  
		  }
		  
		  return person; 
	  }
	  
	
}

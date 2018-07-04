package pe.tp1.hdpeta.jalame.dao;

import java.sql.Connection;

import javax.sql.DataSource;

import pe.tp1.hdpeta.jalame.dao.DataSourceMySQL;

public class DataAccess {

	  Connection appConnection = null;
	  DataSource appDataSource = null;
	  
	  public DataAccess()  {
	    try    {
	      //MySQL
	      DataSourceMySQL oDataSource = new DataSourceMySQL();
	      
	      //MySQL
	      //DataSourceDB2 oDataSource = new DataSourceDB2();
	      
	      
	      this.appDataSource = oDataSource.getDataSource();
	      this.appConnection = this.appDataSource.getConnection();
	      
	    }catch (Exception e){
	      System.out.println("DA new:" + e.getMessage());
	    }
	  }
	  
	  public Connection getConnection()  {
	    return this.appConnection;
	  }


	  public void closeConnection()  {
		try    {
		  this.appConnection.close();
		  this.appConnection = null;
		  this.appDataSource = null;
		  
		  //System.out.println("DA closeConnection ok");
		  
	  	}catch (Exception e){
	      System.out.println("Error DA closeConnection:" + e.getMessage());
	    }
	  }

	  
	
}

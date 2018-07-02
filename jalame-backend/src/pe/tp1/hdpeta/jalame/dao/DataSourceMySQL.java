package pe.tp1.hdpeta.jalame.dao;

import javax.naming.InitialContext;

public class DataSourceMySQL {

	  InitialContext context;
	  javax.sql.DataSource mysqlds;
	  
	  public DataSourceMySQL() {
	    try {
	      context = new InitialContext();
	      mysqlds = ((javax.sql.DataSource)context.lookup("java:/MYSQL"));
	      
	    } catch (Exception e) {
	      System.out.println(e.getMessage());
	    }
	  }
	  
	  public javax.sql.DataSource getDataSource()  {
	    return mysqlds;
	  }
	  
	  
}

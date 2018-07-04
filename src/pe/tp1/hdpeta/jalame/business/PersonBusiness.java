package pe.tp1.hdpeta.jalame.business;

//import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.dto.PersonDto;

import java.util.ArrayList;
import pe.tp1.hdpeta.jalame.bean.PersonBean;

public class PersonBusiness {

	PersonDto personDto = new PersonDto();
	  
	  public PersonBean login(String user, String passw){
		  //ArrayList<PersonBean> personList = new ArrayList<PersonBean>(); 
	    return this.personDto.login(user, passw);
	  }
	
	  
	  public PersonBean addPerson(PersonBean person){
	    return this.personDto.addPersona(person);
	  }
	  
	  public PersonBean getPersona(int codigoPersona) {
		  return this.personDto.getPersona(codigoPersona);  
	  }
	  
	  public PersonBean updatePersona(PersonBean person){
		  return this.personDto.updatePersona(person);
	  }
	  
	  public int deletePersona(int codigoPersona) {
		  return this.personDto.deletePersona(codigoPersona);  
	  }
	  
	  public ArrayList<PersonBean> getPersonList(String apellido){
		    return this.personDto.listarPersona(apellido);
	  }
	   
	  
	  
	  
	  
	  
}

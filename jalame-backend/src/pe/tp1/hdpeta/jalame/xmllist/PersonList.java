package pe.tp1.hdpeta.jalame.xmllist;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pe.tp1.hdpeta.jalame.bean.PersonBean;

@XmlRootElement(name="PersonList")
public class PersonList {
	
	  private List<PersonBean> items;
	  
	  public PersonList() {}
	  
	  
	  public PersonList(List<PersonBean> items){
	    this.items = items;
	  }
	  
	  
	  @XmlElement(name="person")
	  public List<PersonBean> getItems(){
	    return this.items;
	  }
	  
	 
}


 
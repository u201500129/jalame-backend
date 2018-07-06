package pe.tp1.hdpeta.jalame.xmllist;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pe.tp1.hdpeta.jalame.bean.SedeBean;

@XmlRootElement(name="SedeBean")
public class SedeList {
	  private List<SedeBean> items;
	  
	  public SedeList() {}
	  
	  
	  public SedeList(List<SedeBean> items){
	    this.items = items;
	  }
	  	  
	  
	  @XmlElement(name="sede")
	  public List<SedeBean> getItems(){
	    return this.items;
	  }
	  
	  
	  
}

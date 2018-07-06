package pe.tp1.hdpeta.jalame.xmllist;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pe.tp1.hdpeta.jalame.bean.ServicioBean;


@XmlRootElement(name="ServicioBean")
public class ServicioList {
	  private List<ServicioBean> items;
	  
	  public ServicioList() {}
	  
	  
	  public ServicioList(List<ServicioBean> items){
	    this.items = items;
	  }
	  	  
	  @XmlElement(name="servicio")
	  public List<ServicioBean> getItems(){
	    return this.items;
	  }
}

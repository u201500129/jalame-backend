package pe.tp1.hdpeta.jalame.xmllist;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pe.tp1.hdpeta.jalame.bean.VehiculoBean; 

@XmlRootElement(name="VehiculoBean")
public class VehiculoList {
	  private List<VehiculoBean> items;
	  
	  public VehiculoList() {}
	  
	  
	  public VehiculoList(List<VehiculoBean> items){
	    this.items = items;
	  }
	  	  
	  @XmlElement(name="vehiculo")
	  public List<VehiculoBean> getItems(){
	    return this.items;
	  }
}

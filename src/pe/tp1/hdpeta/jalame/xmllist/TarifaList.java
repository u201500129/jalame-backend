package pe.tp1.hdpeta.jalame.xmllist;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pe.tp1.hdpeta.jalame.bean.TarifaBean;

@XmlRootElement(name="TarifaBean")
public class TarifaList {
	  private List<TarifaBean> items;
	  
	  public TarifaList() {}
	  
	  
	  public TarifaList(List<TarifaBean> items){
	    this.items = items;
	  }
	  	  
	  @XmlElement(name="tarifa")
	  public List<TarifaBean> getItems(){
	    return this.items;
	  }
}

package pe.tp1.hdpeta.jalame.xmllist;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pe.tp1.hdpeta.jalame.bean.FormaPagoBean;

@XmlRootElement(name="FormaPagoBean")
public class FormaPagoList {
	  private List<FormaPagoBean> items;
	  
	  public FormaPagoList() {}
	  
	  
	  public FormaPagoList(List<FormaPagoBean> items){
	    this.items = items;
	  }
	  	  
	  @XmlElement(name="formapago")
	  public List<FormaPagoBean> getItems(){
	    return this.items;
	  }
}

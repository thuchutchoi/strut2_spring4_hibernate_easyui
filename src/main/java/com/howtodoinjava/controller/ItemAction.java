package com.howtodoinjava.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.howtodoinjava.dto.Item;
import com.opensymphony.xwork2.ActionSupport;

public class ItemAction extends ActionSupport{
	// {"total":28,"rows":[
	// {"productid":"FI-SW-01","productname":"Koi","unitcost":10.00,"status":"P","listprice":36.50,"attr1":"Large","itemid":"EST-1"},
	// {"productid":"K9-DL-01","productname":"Dalmation","unitcost":12.00,"status":"P","listprice":18.50,"attr1":"Spotted
	// Adult Female","itemid":"EST-10"},
	// {"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":38.50,"attr1":"Venomless","itemid":"EST-11"},
	// {"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":26.50,"attr1":"Rattleless","itemid":"EST-12"},
	// {"productid":"RP-LI-02","productname":"Iguana","unitcost":12.00,"status":"P","listprice":35.50,"attr1":"Green
	// Adult","itemid":"EST-13"},
	// {"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":158.50,"attr1":"Tailless","itemid":"EST-14"},
	// {"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":83.50,"attr1":"With
	// tail","itemid":"EST-15"},
	// {"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":23.50,"attr1":"Adult
	// Female","itemid":"EST-16"},
	// {"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":89.50,"attr1":"Adult
	// Male","itemid":"EST-17"},
	// {"productid":"AV-CB-01","productname":"Amazon
	// Parrot","unitcost":92.00,"status":"P","listprice":63.50,"attr1":"Adult
	// Male","itemid":"EST-18"}
	// ]}

	// [
	// {"productid":"FI-SW-01","productname":"Koi"},
	// {"productid":"K9-DL-01","productname":"Dalmation"},
	// {"productid":"RP-SN-01","productname":"Rattlesnake"},
	// {"productid":"RP-LI-02","productname":"Iguana"},
	// {"productid":"FL-DSH-01","productname":"Manx"},
	// {"productid":"FL-DLH-02","productname":"Persian"},
	// {"productid":"AV-CB-01","productname":"Amazon Parrot"}
	// ]
	public ItemAction() {
		System.out.println("Action ItemAction");
	}
	private int total;
	private List<Item> rows = new ArrayList<Item>();

	public List<Item> getRows() {
		return rows;
	}

	public int getTotal() {
		return total;
	}
	public void setRows(List<Item> rows) {
		this.rows = rows;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String execute() {
		rows=new ArrayList<Item>();
		for (int i = 0; i < 10; i++) {
			Item item = new Item.Builder()
					    .productid("FI-SW-"+i)
					    .productname("Koi"+i)
					    .unitcost(0.0+i)
					    .status("P"+i)
					    .listprice(0.0+i)
					    .attr1("Adult Male"+i)
					    .itemid("EST-"+i).build();
			rows.add(item);
		}
		total = 10;
		return "success";
	}
}

package com.howtodoinjava.dto;

public class Item {
	private String productid;
	private String productname;
	private Double unitcost;
	private String status;
	private Double listprice;
	private String attr1;
	private String itemid;
	public String getProductid() {
		return productid;
	}
	public String getProductname() {
		return productname;
	}
	public Double getUnitcost() {
		return unitcost;
	}
	public String getStatus() {
		return status;
	}
	public Double getListprice() {
		return listprice;
	}
	public String getAttr1() {
		return attr1;
	}
	public String getItemid() {
		return itemid;
	}
	private Item(Builder builder) {
		this.productid=builder.productid;
		this.productname=builder.productname;
		this.unitcost=builder.unitcost;
		this.status=builder.status;
		this.listprice=builder.listprice;
		this.attr1=builder.attr1;
		this.itemid=builder.itemid;
	}

	public static class Builder {
		private String productid;
		private String productname;
		private Double unitcost;
		private String status;
		private Double listprice;
		private String attr1;
		private String itemid;
		public Builder productid(String productid) {
			this.productid = productid;
			return this;
		}
		public Builder productname(String productname) {
			this.productname = productname;
			return this;
		}

		public Builder unitcost(Double unitcost) {
			this.unitcost = unitcost;
			return this;
		}

		public Builder status(String status) {
			this.status = status;
			return this;
		}

		public Builder listprice(Double listprice) {
			this.listprice = listprice;
			return this;
		}
		public Builder attr1(String attr1) {
			this.attr1 = attr1;
			return this;
		}
		public Builder itemid(String itemid) {
			this.itemid = itemid;
			return this;
		}

		public Item build() {
			if (itemid == null) {
                throw new IllegalArgumentException("itemid cannot be null");
            }
			return new Item(this);
		}
	}
}

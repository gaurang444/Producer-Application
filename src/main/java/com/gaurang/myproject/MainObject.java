package com.gaurang.myproject;

import java.util.List;

public class MainObject {
	private String orderid;
	private List<Products> products;
	private Recipent rp;
	public MainObject(String orderid, List<Products> products, Recipent receipient) {
		super();
		this.orderid = orderid;
		this.products = products;
		this.rp = receipient;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
	}
	public Recipent getRp() {
		return rp;
	}
	public void setRp(Recipent rp) {
		this.rp = rp;
	}



}

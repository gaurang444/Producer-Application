package com.gaurang.myproject;

import java.util.*;

public class Recipent {
	private Contact contacts;
	private List<Address> adres;
	
	public Recipent(Contact contacts, List<Address> adres) {
		super();
		this.contacts = contacts;
		this.adres = adres;
	}
	public Contact getContacts() {
		return contacts;
	}
	public void setContacts(Contact contacts) {
		this.contacts = contacts;
	}
	public List<Address> getAdres() {
		return adres;
	}
	public void setAdres(List<Address> adres) {
		this.adres = adres;
	}
	
	

}

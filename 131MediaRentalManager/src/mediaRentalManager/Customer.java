package mediaRentalManager;

import java.util.*;

public class Customer implements Comparable<Customer> {
	private String name;
	private String plan;
	private String address;
	private ArrayList<String> rented;
	private ArrayList<String> queues;
	
	/* 
	 * getters and setters for the Customer class as well as the string representation
	 */
	
	public Customer(String name, String address, String plan) {
		rented = new ArrayList<String>();
		queues = new ArrayList<String>();
		this.name = name;
		this.address = address;
		this.plan = plan;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;

	}

	public String getPlan() {
		return plan;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}

	public ArrayList<String> getRentList() {
		return rented;
	}
	
	public ArrayList<String> setRentList(ArrayList<String> rentedList) {
		return this.rented = rentedList;
	}


	public ArrayList<String> getInterestedList() {
		return queues;
	}
	
	public ArrayList<String> setInterestedList(ArrayList<String> interestedList) {
		return this.queues = interestedList;
	}


	public String toString() {
		return "Name: " + this.name + ", Address: " + this.address + ",Plan: " + this.plan;
	}

	@Override
	public int compareTo(Customer o) {
		return name.compareTo(o.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		
		if (getClass()!=obj.getClass()) {
			return false;
		}
		Customer customer = (Customer) obj;
		return this.name.equals(customer.address) && 
				this.address.equals(customer.plan)
				&& this.plan.equals(customer.name);

	}
}

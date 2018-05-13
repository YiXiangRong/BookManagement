package com.library.bean;

public class Bookstatistics {
	String type;
	int borrowsum;
	int sum;
	int storesum;
	public String gettype(){
		return type;
	}
	public void settype(String type){
		this.type=type;;
	}
	
	public int getsum(){
		return sum;
	}
	public void setsum(int sum){
		this.sum=sum;;
	}
	
	public int getborrowsum(){
		return borrowsum;
	}
	public void setborrowsum(int borrowsum){
		this.borrowsum=borrowsum;;
	}
	
	public int getstoresum(){
		return storesum;
	}
	public void setstoresum(int storesum){
		this.storesum=storesum;;
	}
}

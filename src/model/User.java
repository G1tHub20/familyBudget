package model;

import java.io.Serializable;

public class User implements Serializable {
	private int id;
	private String userName;
	private String pass;
	private int sumMoney;

	public User() {}
	public User(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}
	public User(int id, String userName, String pass) {
		this.id = id;
		this.userName = userName;
		this.pass = pass;
	}
	public User(int id, String userName, String pass, int sumMoney) {
		this.id = id;
		this.userName = userName;
		this.pass = pass;
		this.sumMoney = sumMoney;
	}

	public int getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}
	public String getPass() {
		return pass;
	}
	public int getSumMoey() {
		return sumMoney;
	}
}

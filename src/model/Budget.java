package model;

import java.io.Serializable;

public class Budget implements Serializable {
		private int id;
		private int money;
		private String category;
		private String date;

		public Budget() {}
		public Budget(int money, String category, String date) {
			this.money = money;
			this.category = category;
			this.date = date;
		}
		public Budget(int id, int money, String category, String date) {
			this.id = id;
			this.money = money;
			this.category = category;
			this.date = date;
		}

		public int getId() {
			return id;
		}
		public int getMoney() {
			return money;
		}
		public String getCategory() {
			return category;
		}
		public String getDate() {
			return date;
		}
	}

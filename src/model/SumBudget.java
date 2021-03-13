package model;

import java.io.Serializable;

public class SumBudget implements Serializable {
		private int sumMoney;
		private int ofFood;
		private int ofCommodity;
		private int ofAmusument;
		private int ofSpecial;
		private int ofFixed;
		private int ofOther;

		public SumBudget() {}
		public SumBudget(int sumMoney, int ofFood, int ofCommodity, int ofAmusument, int ofSpecial, int ofFixed, int ofOther) {
			this.sumMoney = sumMoney;
			this.ofFood = ofFood;
			this.ofCommodity = ofCommodity;
			this.ofAmusument = ofAmusument;
			this.ofSpecial = ofSpecial;
			this.ofFixed = ofFixed;
			this.ofOther = ofOther;
		}

		public int getSumMoney() {
			return sumMoney;
		}
		public int getOfFood() {
			return ofFood;
		}
		public int getOfCommodity() {
			return ofCommodity;
		}
		public int getOfAmusument() {
			return ofAmusument;
		}
		public int getOfSpecial() {
			return ofSpecial;
		}
		public int getOfFixed() {
			return ofFixed;
		}
		public int getOfOther() {
			return ofOther;
		}

	}

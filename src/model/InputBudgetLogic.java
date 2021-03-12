package model;

import dao.BudgetDAO;

public class InputBudgetLogic {
	public boolean execute(Budget budget) { // DAOで利用できるように、ここでloginUserを渡す！！

		BudgetDAO dao = new BudgetDAO();

		return dao.addMoney(budget);
	}
}

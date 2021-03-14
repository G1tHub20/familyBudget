package model;

import dao.BudgetDAO;


public class CalcCategoryExpenseLogic {
	public SumBudget execute(User user) {

		User loginUser = user;
		SumBudget sumBudget = new SumBudget();

		// ★
		if (loginUser.getId() != 0) {
			BudgetDAO dao = new BudgetDAO();
			sumBudget = dao.calcCategoryExpense(loginUser);
		}
		return sumBudget;

	}
}

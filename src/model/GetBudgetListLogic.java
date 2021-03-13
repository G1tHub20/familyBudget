package model;

import java.util.List;

import dao.BudgetDAO;

public class GetBudgetListLogic {
	public List<Budget> execute(User loginUser) { // DAOで利用できるように、ここでloginUserを渡す！！

		System.out.println("GetBudgetListLogic内" + loginUser);
		System.out.println(loginUser.getUserName() + loginUser.getPass() + loginUser.getId());
		BudgetDAO dao = new BudgetDAO();
		List<Budget> budgetList = dao.findAll(loginUser);

		return budgetList;
	}

}

package model;

import java.util.List;

import dao.BudgetDAO;

public class GetBudgetListLogic {
	public List<Budget> execute(User loginUser) { // DAOで利用できるように、ここでloginUserを渡す！！

		System.out.println("GetBudgetListLogic内" + loginUser);
//		User infoLoginUser = loginUser;
		System.out.println("GetBudgetListLogic内" + loginUser.getUserName());
		System.out.println("GetBudgetListLogic内" + loginUser.getPass());
		System.out.println("GetBudgetListLogic内" + loginUser.getId());

		BudgetDAO dao = new BudgetDAO();
		List<Budget> budgetList = dao.findAll(loginUser);

		return budgetList;
	}
}

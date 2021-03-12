package test;

import java.util.List;

import dao.BudgetDAO;
import model.Budget;
import model.User;

public class BudgetDAOtest {
	public static void main(String[] args) {
		User loginUser = new User("yuko", "1234");
		testFindAll1(loginUser);
	}

	public static void testFindAll1(User loginUser) {
		BudgetDAO dao = new BudgetDAO();
		System.out.println("BudgetDAOをインスタンス化"); //★

		List<Budget> budgetList = dao.findAll(loginUser);

	    for (Budget budget : budgetList) {
	    	System.out.println(budget.getId());
	    	System.out.println(budget.getMoney());
		    System.out.println(budget.getCategory());
		    System.out.println(budget.getDate());
	    }

	}

}

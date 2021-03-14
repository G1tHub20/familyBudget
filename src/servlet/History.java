package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Budget;
import model.CalcCategoryExpenseLogic;
import model.GetBudgetListLogic;
import model.SumBudget;
import model.User;

@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		GetBudgetListLogic getBudgetListLogic = new GetBudgetListLogic();
		System.out.println("getBudgetListLogic = " + getBudgetListLogic); //★

		List<Budget> budgetList = getBudgetListLogic.execute(loginUser);

		//		HttpSession session = request.getSession();

		CalcCategoryExpenseLogic calcCategoryExpenseLogic = new CalcCategoryExpenseLogic();
		SumBudget sumBudget	= calcCategoryExpenseLogic.execute(loginUser);


		session.setAttribute("budgetList", budgetList);
		session.setAttribute("sumBudget", sumBudget);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/history.jsp");
		dispatcher.forward(request, response);
	}

}

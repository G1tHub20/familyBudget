package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Budget;
import model.CalcSumMoneyLogic;
import model.InputBudgetLogic;
import model.User;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


//		int money = Integer.parseInt(request.getParameter("inputValue"));
		int money = 0;

		String inputIncome = request.getParameter("inputIncome");
		String inputOutgo = request.getParameter("inputOutgo");


		if(inputIncome == null && inputOutgo != null) {
			money = -1 * Integer.parseInt(request.getParameter("inputOutgo"));
			System.out.println("支出の入力(= " + money + ")");
		}
		if(inputIncome != null && inputOutgo == null) {
			money = Integer.parseInt(request.getParameter("inputIncome"));
			System.out.println("収入の入力(= " + money + ")");
		}

		String category = request.getParameter("category");
		String date = request.getParameter("date");

		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		Budget budget = new Budget(loginUser.getId(), money, category, date);

//		if (inputValue >= 0) {
//			System.out.println("収入の入力");
//			System.out.println(inputValue + "円");
//
//		} else {
//			System.out.println("支出の入力");
//			System.out.println(inputValue + "円");
//		}

		InputBudgetLogic inputBudgetLogic = new InputBudgetLogic();

		boolean isInputMoney = inputBudgetLogic.execute(budget);

		if(isInputMoney) {
			System.out.println("支出入の入力成功");
		} else {
			System.out.println("支出入の入力しっぱい");
		}

		// 支出入の入力後に、更新した資産総額を取得
		CalcSumMoneyLogic calcSumMoneyLogic = new CalcSumMoneyLogic();
		User loginUser2 = calcSumMoneyLogic.execute(loginUser);

		session.setAttribute("loginUser", loginUser2);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);

	}

}

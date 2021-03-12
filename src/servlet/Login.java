package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");

		// Userインスタンの生成
		User user = new User(userName, pass);

		LoginLogic loginLogic = new LoginLogic();
		User loginUser = loginLogic.execute(user);

		if (loginUser.getId() != 0) {
			System.out.println("DBにユーザー情報が存在→ログイン成功");
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);

			System.out.println("loginUserインスタンスをセッションに格納"); //★
			System.out.println("id=" + loginUser.getId() + "\r\nuserName=" + loginUser.getUserName() + "\r\npass=" + loginUser.getPass() + "\r\nsumMoey=" + loginUser.getSumMoey()); //★

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);

		} else {
			System.out.println("DBにユーザー情報が存在しない→ログイン失敗");
			System.out.println("ログイン失敗画面へフォワード");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginFailure.jsp");
			dispatcher.forward(request, response);
		}

	}

}

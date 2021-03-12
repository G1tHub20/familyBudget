package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.RegisterLogic;
import model.User;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String pass = request.getParameter("pass");

		User user = new User(userName, pass);
		UserDAO dao = new UserDAO();
		User loginUser = dao.findUser(user);

		// 登録情報がなければ
		if (loginUser == null) {
			System.out.println("入力情報でアカウントを新規登録");
			RegisterLogic registerLogic = new RegisterLogic();
			registerLogic.execute(user);
			request.setAttribute("loginUser", loginUser);
			request.setAttribute("errorMsg", "アカウントを新規登録しました");
		} else {
			System.out.println("別のユーザー名を使用してください");
			request.setAttribute("errorMsg", "使用できません。別のユーザー名を入力してください");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
		dispatcher.forward(request, response);

	}

}

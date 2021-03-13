package model;

import dao.BudgetDAO;
import dao.UserDAO;

public class LoginLogic {
	public User execute(User user) {

		UserDAO dao = new UserDAO();
		User loginUser = dao.findUser(user);
		User loginUser2 = new User();

		// ★
		if (loginUser != null && loginUser.getId() != 0) {
			System.out.println("ログイン成功");

			System.out.println("id=" + loginUser.getId() + "\r\nuserName=" + loginUser.getUserName() + "\r\nuserPAss=" + loginUser.getPass()); //★
			// 登録済みユーザーならついでに資産総額も取得
			BudgetDAO dao2 = new BudgetDAO();

			SumBudget sumBudget = dao2.calcSumMoney(loginUser);
			int sumMoney = sumBudget.getSumMoney();
			System.out.println("資産総額=" + sumMoney);

			loginUser2 = new User(loginUser.getId(), loginUser.getUserName(), loginUser.getPass(), sumMoney);


		}
		return loginUser2;

//		if (loginUser.getId() == 0) {  //★なぜゼロになるのか？？？？
//			System.out.println("DBにユーザー情報が無いです");
//			System.out.println("ログイン:false");
//			return null;
//
//		} else {
//			System.out.println("DBにユーザー情報あり");
//			System.out.println(loginUser.getId());
//			System.out.println(loginUser.getUserName());
//			System.out.println(loginUser.getPass());
//
//		// 登録情報と一致すれば、IDも取得し、loginUserインスタンスを作成
//		if (user.getUserName().equals(loginUser.getUserName()) && user.getPass().equals(loginUser.getPass())) {
//
//			System.out.println(user.getUserName());
//			System.out.println(loginUser.getUserName());
//			System.out.println(user.getPass());
//			System.out.println(loginUser.getPass());
//
//			System.out.println("DBに登録済みのユーザ情報と一致");
//			System.out.println("ログイン:true");
//
//			String userName = loginUser.getUserName();
//			String pass = loginUser.getPass();
//			int id = loginUser.getId();
//
//			//★
//			System.out.println(id + userName + pass);
//			//★
//
//			return loginUser;
//		}

	}
}

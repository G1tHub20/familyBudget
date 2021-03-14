package model;

import dao.BudgetDAO;

public class CalcCategoryMoney {
	public User execute(User user) {

		User loginUser = user;
		System.out.println("id=" + loginUser.getId() + "\r\nuserName=" + loginUser.getUserName() + "\r\nuserPAss=" + loginUser.getPass()); //★

		// ★
		if (loginUser.getId() != 0) {
			System.out.println("ログイン成功");

			// 登録済みユーザーならついでに資産総額も取得
			BudgetDAO dao = new BudgetDAO();

			int sumMoney = dao.calcSumMoney(loginUser);
			System.out.println("資産総額=" + sumMoney);

			loginUser = new User(loginUser.getId(), loginUser.getUserName(), loginUser.getPass(), sumMoney);

		}
		return loginUser;

	}
}

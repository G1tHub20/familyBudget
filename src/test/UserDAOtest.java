package test;

import dao.UserDAO;
import model.User;

public class UserDAOtest {
	public static void main(String[] args) {
		User user = new User("TARO", "1234");
		User loginUser = findUser1(user);
		System.out.println("ID=" + loginUser.getId());
	}

	public static User findUser1(User user) {
		UserDAO dao = new UserDAO();
		System.out.println("UserDAOをインスタンス化"); //★

		User loginUser = dao.findUser(user);

		if (user.getUserName().equals(loginUser.getUserName()) && user.getPass().equals(loginUser.getPass())) {
			System.out.println("ログイン:true");

			String userName = loginUser.getUserName();
			String pass = loginUser.getPass();
			int id = loginUser.getId();

			//★
			System.out.println(id + userName + pass);
			return loginUser;
	    } else {
	    	System.out.println("ログイン:false");
			return null;
	    }

	}

}

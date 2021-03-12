package model;

import dao.UserDAO;

public class RegisterLogic {
	public boolean execute(User user) {
		UserDAO dao = new UserDAO();

		return dao.createUser(user);
	}
}
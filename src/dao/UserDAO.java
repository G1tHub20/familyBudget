package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysqlpa55";

	// ◆Userテーブルからレコードを取得するメソッド
	public User findUser(User inputUser) {
		User user = inputUser;
		User loginUser = new User();

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");
			System.out.println("DB接続成功(findUser)"); //★

			String sql = "SELECT * FROM USER WHERE USER_NAME = \"" + user.getUserName() + "\" AND PASS = \"" + user.getPass() + "\"";

			PreparedStatement pStmt = conn.prepareStatement(sql);

//			pStmt.setString(1, user.getUserName());
//			pStmt.setString(2, user.getPass());

			System.out.println("DBにユーザー情報があるかチェック"); //★
			System.out.println("SQL=" + sql); //★

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()){
				System.out.println("ResultSetから一致するユーザー情報を取得");
				int id = rs.getInt("ID");
				String registeredPass = rs.getString("PASS");
				String registeredUserName = rs.getString("USER_NAME");

				System.out.println("loginUserインスタンスを生成");
				System.out.println(id + registeredUserName + registeredPass);
				loginUser = new User(id, registeredUserName, registeredPass);
			} else {
				System.out.println("rs.next()=false");
				loginUser = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい"); //★
			return null;
		}
		System.out.println("▲▲----------------------------------------------------------------");
		return loginUser;
	}


	// ◆Userテーブルに新規アカウントを登録するメソッド
	public boolean createUser(User user) {

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");
			System.out.println("DB接続成功(createUser)"); //★

			String sql = "INSERT INTO USER(USER_NAME, PASS) VALUES(?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setString(1, user.getUserName());
			pStmt.setString(2, user.getPass());

			// INSERTを実行
			int result = pStmt.executeUpdate();

			if (result != 1) {
				System.out.println("追加できませんでした");
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい"); //★
			return false;
		}
		System.out.println("DBにユーザー情報を追加");
		System.out.println("▲▲----------------------------------------------------------------");
		return true;

	}

}

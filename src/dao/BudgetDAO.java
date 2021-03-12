package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Budget;
import model.User;

public class BudgetDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysqlpa55";

	// ◆Budgetテーブルからレコードを取得するメソッド
	public List<Budget> findAll(User loginUser) {
		List<Budget> budgetList = new ArrayList<>();

		User infoLoginUser = loginUser;
		System.out.println(infoLoginUser.getId()); //★

		// DB接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");

			System.out.println("DB接続成功(findAll)"); //★

			String sql = "SELECT * FROM BUDGET WHERE USER_ID = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
//			HttpSession session = request.getSession(); // sessionやrequestはServlet・JSPからじゃないと使えない！！
//			User loginUser = (User) session.getAttribute("loginUser");

			pStmt.setInt(1, infoLoginUser.getId()); //★ 取得したユーザーのIDで指定したい

//			pStmt.setInt(1, 2);

			System.out.println("sql = SELECT * FROM BUDGET WHERE USER_ID = " + infoLoginUser.getId()); //★


			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();


			while (rs.next()) {
				int id = rs.getInt("USER_ID");
				int money = rs.getInt("MONEY");
				String category = rs.getString("CATEGORY");
				String date = rs.getString("DATE");
				Budget budget = new Budget(id, money, category, date);

				budgetList.add(budget);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい"); //★
			return null;
		}
		System.out.println("▲▲----------------------------------------------------------------");
		return budgetList;
	}


	// ◆Budgetテーブルから資産総額を算出するメソッド
	public int calcSumMoney(User loginUser) {

		User infoLoginUser = loginUser;

		int sumMoney = 0;
		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");
			System.out.println("DB接続成功(calcSumMoney)"); //★

//			String sql = "SELECT SUM(MONEY) FROM BUDGET WHERE USER_ID = ?";
			String sql = "SELECT SUM(MONEY) AS MONEY FROM BUDGET WHERE USER_ID = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, infoLoginUser.getId()); //★ 取得したユーザーのIDで指定したい

			System.out.println("pStmt=" + pStmt);

			// SELECTを実行
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()){
				System.out.println("true？");

				sumMoney = rs.getInt("MONEY");

				System.out.println("sumMoney=" + sumMoney); //★
			} else {
				System.out.println("false？");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい"); //★
		}

		System.out.println("▲▲----------------------------------------------------------------");
		return sumMoney;
	}


	// ◆Budgetテーブルに支出入を追加するメソッド
	public boolean addMoney(Budget budget) {

		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");
			System.out.println("DB接続成功(addMoney)"); //★

			String sql = "INSERT INTO BUDGET(USER_ID, MONEY, CATEGORY, DATE) VALUES(?, ?, ?, ?);";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, budget.getId());
//			pStmt.setInt(1, 3); //★userからIDを取得する必要
			pStmt.setInt(2, budget.getMoney());
			pStmt.setString(3, budget.getCategory());
			pStmt.setString(4, budget.getDate());

			System.out.println("pStmt=" + pStmt);

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

		System.out.println("▲▲----------------------------------------------------------------");
		return true;

	}


}

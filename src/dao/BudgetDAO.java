package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Budget;
import model.SumBudget;
import model.User;

public class BudgetDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
	private final String DB_USER = "root";
	private final String DB_PASS = "mysqlpa55";

	// ◆レコードを取得するメソッド
	public List<Budget> findAll(User loginUser) {
		List<Budget> budgetList = new ArrayList<>();

		User infoLoginUser = loginUser;
		System.out.println(infoLoginUser.getId()); //★

		// DB接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");

			System.out.println("DB接続成功(findAll)"); //★

			String sql = "SELECT * FROM BUDGET WHERE USER_ID = ? ORDER BY DATE DESC";

			PreparedStatement pStmt = conn.prepareStatement(sql);
//			HttpSession session = request.getSession(); // sessionやrequestはServlet・JSPからじゃないと使えない！！
//			User loginUser = (User) session.getAttribute("loginUser");

			pStmt.setInt(1, infoLoginUser.getId()); //★ 取得したユーザーのIDで指定したい

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


	// ◆資産総額を算出するメソッド
	public int calcSumMoney(User loginUser) {

		User infoLoginUser = loginUser;

		int sumMoney = 0;
		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");
			System.out.println("DB接続成功(calcSumMoney)"); //★

//			String sql = "SELECT SUM(MONEY) FROM BUDGET WHERE ID = ?";
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

	// ◆カテゴリごとの支出合計を算出するメソッド
	public SumBudget calcCategoryExpense(User loginUser) {

		User infoLoginUser = loginUser;

		SumBudget sumBudget = new SumBudget();
		// DB接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			System.out.println("▼▼----------------------------------------------------------------");
			System.out.println("DB接続成功(calcCategoryExpense)"); //★

			String[] sql;
			sql = new String[6]; //初期化していない可能性エラーのため追加

			int intRs[];
			intRs = new int[6];

			PreparedStatement[] pStmt;
			pStmt = new PreparedStatement[6];

			String[] categoryForExpense = {"食費", "日用品費", "娯楽費", "特別費", "固定費", "その他"};

			for (int i = 0; i < 6; i++) {
			sql[i] = "SELECT SUM(MONEY) FROM budget WHERE USER_ID = ? AND CATEGORY = " + "\"" + categoryForExpense[i] + "\"";
			pStmt[i] = conn.prepareStatement(sql[i]);
			pStmt[i].setInt(1, infoLoginUser.getId());
			System.out.println("sql = SELECT SUM(MONEY) FROM budget WHERE USER_ID = " + infoLoginUser.getId() + " AND CATEGORY = " + "\"" + categoryForExpense[i] + "\""); //★

//			rs[i] = pStmt[i].executeQuery().getInt("MONEY");
			ResultSet rs = pStmt[i].executeQuery();

				while (rs.next()) {
					System.out.println("ResultSetここから");
//					intRs[i] = rs.getInt(i); // SELECT 要素名 ― rs.getInt("要素名") // SELECT 要素名 ― rs.getInt("要素名")
					intRs[i] = rs.getInt("SUM(MONEY)"); // SELECT 要素名 ― rs.getInt("要素名") // SELECT 要素名 ― rs.getInt("要素名")
					System.out.println("ResultSetから取得");
				}
			}

			sumBudget = new SumBudget(intRs[0], intRs[1], intRs[2], intRs[3], intRs[4], intRs[5]);
			System.out.println("食費=" + sumBudget.getOfFood() + ",日用品費=" + sumBudget.getOfCommodity() + ",娯楽費=" + sumBudget.getOfAmusument() + ",固定費=" + sumBudget.getOfFixed() + ",特別費=" + sumBudget.getOfSpecial() +  ",その他=" + sumBudget.getOfOther()); //★

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続しっぱい"); //★
		}

		System.out.println("▲▲----------------------------------------------------------------");
		return sumBudget;
	}


	// ◆支出入を追加するメソッド
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

			String inputInfo = budget.getDate() + "\r\n" + budget.getCategory() + "：￥" + budget.getMoney(); //★入力後にメッセージを渡したい



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

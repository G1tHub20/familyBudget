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
	public SumBudget calcSumMoney(User loginUser) {

		User infoLoginUser = loginUser;

		SumBudget sumBudget = new SumBudget();
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

			ResultSet rs1 = pStmt.executeQuery();

			if (rs1.next()) {
			sumMoney = rs1.getInt("MONEY");
			}

			System.out.println("sumMoney=" + sumMoney);

//			int rs2[];
//			rs2 = new int[6];

			String[] sql2;
			sql2 = new String[6]; //初期化していない可能性エラーのため追加

			int intRs2[];
			intRs2 = new int[6];

			PreparedStatement[] pStmt2;
			pStmt2 = new PreparedStatement[6];


			String[] categoryForExpense = {"食費", "日用品", "娯楽費", "特別費", "固定費", "その他"};

			for (int i = 0; i < 6; i++) {
			sql2[i] = "SELECT SUM(MONEY) FROM budget WHERE USER_ID = ? AND CATEGORY = " + "\"" + categoryForExpense[i] + "\"";
			pStmt2[i] = conn.prepareStatement(sql2[i]);
			pStmt2[i].setInt(1, infoLoginUser.getId());
			System.out.println("sql2 = SELECT SUM(MONEY) FROM budget WHERE USER_ID = " + infoLoginUser.getId() + " AND CATEGORY = カテゴリー名"); //★

//			rs2[i] = pStmt2[i].executeQuery().getInt("MONEY");
			ResultSet rs2 = pStmt2[i].executeQuery();

			while (rs2.next()) {
					intRs2[i] = rs2.getInt("MONEY");
				}
			}


//			sumBudget = new SumBudget(sumMoney, rs2[0], rs2[1], rs2[1], rs2[3], rs2[4], rs2[5]);
			sumBudget = new SumBudget(sumMoney, intRs2[0], intRs2[1], intRs2[2], intRs2[3], intRs2[4], intRs2[5]);
			System.out.println("sumMoney=" + sumBudget.getSumMoney()); //★

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

package beni.momentum.bkalcul.controlers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beni.momentum.bkalcul.models.Record;

public class RecordDAO implements DbDAO<Record> {

	public static RecordDAO recordDAO;

	public static RecordDAO get() {
		if (recordDAO == null) {
			recordDAO = new RecordDAO();
		}
		return recordDAO;
	}

	private RecordDAO() {
	}

	@Override
	public boolean create(Record r) {
		int result = 0;
		try {

			String prepStmt = "insert into records(username,calcRequest,answer,calcTime) values(?,?,?,?)";
			PreparedStatement stmt = DbConnection.getConnection().prepareStatement(prepStmt);
			stmt.setString(1, r.getUsername());
			stmt.setString(2, r.getCalcRequest());
			stmt.setString(3, r.getAnswer());
			stmt.setLong(4, r.getTimeCalc());

			result = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

		return result == 0;
	}

	@Override
	public Record read(int id) {
		try {

			Statement stmt = DbConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from records where id = '" + id + "'");

			if (rs == null)
				return null;

			rs.next();
			Record r = fromResultSet(rs);

			return r;

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Record read(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Record> readAll() {
		return readAllBySQL("select * from records ORDER BY id Desc");
	}

	public ArrayList<Record> readAllByUsername(String username) {
		String sql = "select * from records where username='" + username + "' ORDER BY id Desc" ;
		return readAllBySQL(sql);
	}

	public ArrayList<Record> readAllByDate(String from, String to) {
		String sql = "select * from records where dateInserted between '" + from + "' AND '" + to + "' ORDER BY id Desc";
		return readAllBySQL(sql);
	}

	public ArrayList<Record> readAllByUsernameDate(String username, String from, String to) {
		String sql = "select * from records where username = '" + username + "' AND dateInserted between '" + from
				+ "' AND '" + to + "' ORDER BY id Desc" ;
		return readAllBySQL(sql);
	}

	private ArrayList<Record> readAllBySQL(String sql) {

		ArrayList<Record> records = new ArrayList();

		try {
			Statement stmt = DbConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				records.add(fromResultSet(rs));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Requ "+sql);

		System.out.println(records);
		
		return records;

	}

	private Record fromResultSet(ResultSet rs) {
		try {
			Record r = new Record();
			r.setId(rs.getInt("id"));
			r.setUsername(rs.getString("username"));
			r.setCalcRequest(rs.getString("calcRequest"));
			r.setAnswer(rs.getString("answer"));
			r.setTimeCalc(rs.getLong("calcTime"));
			r.setDateInserted(rs.getDate("dateInserted"));
			r.setId(rs.getInt("id"));
			return r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(int id) {
		
		try {
			String sql = "delete from records where id = '" + id+"'";
			PreparedStatement stmt = DbConnection.getConnection().prepareStatement(sql);
			return stmt.executeUpdate() != 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRecord(Record t) {
		// TODO Auto-generated method stub
		return false;
	}
}

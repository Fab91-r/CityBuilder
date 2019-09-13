package it.dstech.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnessioneDb {
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/world?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "dstech";

	public static Connection getConnessione() throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		return conn;
	}

	public static List<String> getContinenti() throws ClassNotFoundException, SQLException {
		String query = "select distinct Continent from country;";
		PreparedStatement statement = getConnessione().prepareStatement(query);
		ResultSet result = statement.executeQuery();
		List<String> listaContinenti = new ArrayList<String>();
		while (result.next()) {
			String continente = result.getString(1);
			listaContinenti.add(continente);
		}
		return listaContinenti;
	}
	
	public static List<String> getStati(String continente) throws ClassNotFoundException, SQLException {
		String query = "SELECT Name from country where Continent = ?;";
		PreparedStatement ps = getConnessione().prepareStatement(query);
		ps.setString(1, continente);
		ResultSet result = ps.executeQuery();
		List<String> listaStati = new ArrayList<String>();
		while (result.next()) {
			String stato = result.getString(1);
			listaStati.add(stato);
		}
		return listaStati;
	}
}

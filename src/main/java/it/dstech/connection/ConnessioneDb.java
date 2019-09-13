package it.dstech.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.dstech.object.Citta;

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

	public static String getCode(String stato) throws ClassNotFoundException, SQLException {
		String query = "select Code from country where Name = ?;";
		PreparedStatement statement1 = getConnessione().prepareStatement(query);
		statement1.setString(1, stato);
		String code = null;
		ResultSet result = statement1.executeQuery();
		while (result.next()) {
			code = result.getString(1);
		}

		return code;
	}

	public static List<String> getCitta(String stato) throws ClassNotFoundException, SQLException {

		String code = ConnessioneDb.getCode(stato);
		String query = "select Name from city where CountryCode = ?;";
		PreparedStatement statement = getConnessione().prepareStatement(query);
		statement.setString(1, code);
		List<String> listaCitta = new ArrayList<String>();
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			String citta = result.getString(1);
			listaCitta.add(citta);
		}
		return listaCitta;
	}

	public static int getPopolazione(String stato) throws ClassNotFoundException, SQLException {
		String code = ConnessioneDb.getCode(stato);
		String query = "select max(Population), min(Population) from city where CountryCode = ?;";
		PreparedStatement ps = getConnessione().prepareStatement(query);
		ps.setString(1, code);
		ResultSet result = ps.executeQuery();
		int max = 0;
	    int min = 0;
		while (result.next()) {
			max = result.getInt(1);
			min = result.getInt(2);
		}
		System.out.println(min);
		System.out.println(max);
		Random random = new Random();
		int pop = random.nextInt(max - min) + min;
		return pop;
	}
	
	public static void insertCity(Citta citta) throws ClassNotFoundException, SQLException {
		
		String query = "insert into city (Name, CountryCode, District, Population) values(?,?,?,?);";
		PreparedStatement ps = getConnessione().prepareStatement(query);
		ps.setString(1, citta.getName());
		ps.setString(2, citta.getCode());
		ps.setString(3, citta.getDistrict());
		ps.setInt(4, citta.getPop());
		ps.executeUpdate();

	}

}

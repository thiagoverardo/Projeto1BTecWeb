package mvc.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.sql.*;
import java.util.*;


public class DAO {
	private Connection connection = null;

	public DAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/teste", "root", "Padrinhos123");
	}

	public boolean verifica(Cadastro pessoa) throws SQLException {
		boolean st = false;
		String sql = "SELECT * FROM cadastro WHERE user=? and password=?" ;
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getPassword());
		ResultSet rs = stmt.executeQuery();
		st = rs.next();
		stmt.execute();
		stmt.close();
		return st;
	}

	public void adiciona(Cadastro pessoa) throws SQLException {
		String sql = "INSERT INTO cadastro" + "(user, password) values (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getPassword());
		stmt.execute();
		stmt.close();
	}

	public void close() throws SQLException {
		connection.close();
	}
	
	public List<Cadastro> getLista() throws SQLException {
		
		List<Cadastro> pessoas = new ArrayList<Cadastro>();

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cadastro");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Cadastro pessoa = new Cadastro();

			pessoa.setId(rs.getInt("id"));
			pessoa.setUser(rs.getString("user"));
			pessoa.setPassword(rs.getString("password"));
			pessoas.add(pessoa);
		}

		rs.close();
		stmt.close();
		return pessoas;
	}
}
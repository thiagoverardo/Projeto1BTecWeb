package mvc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;


public class JogosDAO {
	private Connection connection = null;
		
	public JogosDAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/teste", "root", "Padrinhos123");
	}
	
	public void close() throws SQLException {
		connection.close();
	}
	
	public List<Jogos> getJogos() throws SQLException {
		
		List<Jogos> jogos = new ArrayList<Jogos>();

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM jogos");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Jogos jogo = new Jogos();

			jogo.setId(rs.getInt("id"));
			jogo.setUser(rs.getString("user"));
			jogo.setJogo(rs.getString("jogo"));
			jogo.setGenero(rs.getString("genero"));
			jogo.setPreco(rs.getFloat("preco"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("compra"));
			jogo.setData(data);
			jogos.add(jogo);
		}

		rs.close();
		stmt.close();
		return jogos;
	}

	public void adicionaJogo(Jogos jogo) throws SQLException {
		String sql = "INSERT INTO jogos " + "(user, jogo, genero, preco, compra) values (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, jogo.getUser());
		stmt.setString(2, jogo.getJogo());
		stmt.setString(3, jogo.getGenero());
		stmt.setFloat(4, jogo.getPreco());
		stmt.setDate(5, new java.sql.Date(jogo.getData().getTimeInMillis()));
		stmt.execute();
		stmt.close();
	}
	
	public void removeJogo(Integer id) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM jogos WHERE id=?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();
		
		if (id == 0) {
			PreparedStatement reset = connection.prepareStatement("ALTER TABLE jogos AUTO_INCREMENT = 1");
			reset.execute();
			reset.close();
		}
	}
	public void atualizaJogo(Jogos jogo) throws SQLException {
		String sql = "UPDATE jogos SET user=?, jogo=?, genero=?, preco=?, compra=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, jogo.getUser());
		stmt.setString(2, jogo.getJogo());
		stmt.setString(3, jogo.getGenero());
		stmt.setFloat(4, jogo.getPreco());
		stmt.setDate(5, new java.sql.Date(jogo.getData().getTimeInMillis()));
		stmt.setInt(6,  jogo.getId());
		stmt.execute();
		stmt.close();
	 }
	public List<Jogos> getOrdem(String obj) throws SQLException {
		List<Jogos> jogos = new ArrayList<Jogos>();
		PreparedStatement stmt;
		if (obj.contentEquals("user")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY user");
		} else if (obj.contentEquals("jogo")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY jogo");
		} else if (obj.contentEquals("genero")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY genero");
		} else if (obj.contentEquals("preco")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY preco");
		} else if (obj.contentEquals("date")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY compra");
		} else {
			stmt = connection.prepareStatement("SELECT * FROM jogos");
		}
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			Jogos jogo = new Jogos();

			jogo.setId(rs.getInt("id"));
			jogo.setUser(rs.getString("user"));
			jogo.setJogo(rs.getString("jogo"));
			jogo.setGenero(rs.getString("genero"));
			jogo.setPreco(rs.getFloat("preco"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("compra"));
			jogo.setData(data);
			jogos.add(jogo);
		}
		rs.close();
		stmt.close();
		return jogos;
	}
}



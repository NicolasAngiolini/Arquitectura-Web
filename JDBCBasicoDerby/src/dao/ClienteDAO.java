package dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ClienteDAO {

	public void createTable(Connection conn) {
		try {
			String table = "CREATE TABLE cliente(" + "idCliente INT, " + "nombre VARCHAR(500)," + "email VARCHAR(500),"
					+ "PRIMARY KEY (idCliente))";

			conn.prepareStatement(table).execute();
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void addCliente(Connection conn) throws SQLException {

		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader()
					.parse(new FileReader("C:\\Users\\Punto Digital\\eclipse-workspace\\inputs\\clientes.csv"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		for (CSVRecord row : parser) {
			String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES(?, ?, ?)";
			// System.out.println(row.get("idCliente"));
			// System.out.println(row.get("nombre"));
			// System.out.println(row.get("email"));

			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(row.get("idCliente")));
			ps.setString(2, row.get("nombre"));
			ps.setString(3, row.get("email"));
			ps.executeUpdate();
			conn.commit();

		}
	}

	public static void mostrarCliente(Connection conn) {
		String select = "SELECT * FROM cliente";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

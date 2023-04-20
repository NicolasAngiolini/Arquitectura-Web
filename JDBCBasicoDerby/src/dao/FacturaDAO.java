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

public class FacturaDAO {

	
	public void createTable(Connection conn) {
		try {
			String table = "CREATE TABLE factura(" + "idFactura INT, " + "id_cliente_fk INT,"
						+ "PRIMARY KEY (idFactura),"
						+ "FOREIGN KEY(id_cliente_fk) references cliente (idCliente))";

			conn.prepareStatement(table).execute();
			conn.commit();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void addFactura(Connection conn) throws SQLException {

		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader()
					.parse(new FileReader("C:\\Users\\Punto Digital\\eclipse-workspace\\inputs\\facturas.csv"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		for (CSVRecord row : parser) {
			String insert = "INSERT INTO factura (idFactura,id_cliente_fk) VALUES(?, ?)";
			// System.out.println(row.get("idCliente"));
			// System.out.println(row.get("nombre"));
			// System.out.println(row.get("email"));

			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(row.get("idFactura")));
			ps.setInt(2, Integer.parseInt(row.get("idCliente")));
			ps.executeUpdate();
			conn.commit();

		}
	}

	public static void mostrarFacturas (Connection conn) {
		String select = "SELECT * FROM factura";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}

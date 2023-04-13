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

public class ProductoDAO {

	
	public void createTable(Connection conn) {
		try {
			String table = "CREATE TABLE producto(" + "idProducto INT, " + "nombre VARCHAR(45)," + "valor FLOAT,"
					+ "PRIMARY KEY (idProducto))";

			conn.prepareStatement(table).execute();
			conn.commit();
		
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void addProducto(Connection conn) throws SQLException {

		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader()
					.parse(new FileReader("C:\\Users\\Punto Digital\\eclipse-workspace\\inputs\\productos.csv"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		for (CSVRecord row : parser) {
			String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
			// System.out.println(row.get("idProducto"));
			 //System.out.println(row.get("nombre"));
			 //System.out.println(row.get("valor"));

			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(row.get("idProducto")));
			ps.setString(2, row.get("nombre"));
			ps.setString(3, row.get("valor"));
			ps.executeUpdate();
		
			conn.commit();

		}
	}

	public static void mostrarCliente(Connection conn) {
		String select = "SELECT * FROM producto";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getFloat(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////	
	
	public void createTableFacturaProducto(Connection conn) {
		try {
			String table ="CREATE TABLE factura_producto( "+"id_factura_fk"+"id_producto_fk"+"cantidad INT" +"FOREIGN KEY(id_factura_fk) references factura (idFactura)"+ 
							"FOREIGN KEY(id_producto_fk) references producto (idProducto))";

			conn.prepareStatement(table).execute();
			conn.commit();
		
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void addFacturaProducto(Connection conn) throws SQLException {

		CSVParser parser = null;
		try {
			parser = CSVFormat.DEFAULT.withHeader()
					.parse(new FileReader("C:\\Users\\Punto Digital\\eclipse-workspace\\inputs\\facturas-productos.csv"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (CSVRecord row : parser) {
			String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
			// System.out.println(row.get("idProducto"));
			 //System.out.println(row.get("nombre"));
			 //System.out.println(row.get("valor"));

			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, Integer.parseInt(row.get("idProducto")));
			ps.setString(2, row.get("nombre"));
			ps.setString(3, row.get("valor"));
			ps.executeUpdate();
		
			conn.commit();

		}
	}
	

	
	
}

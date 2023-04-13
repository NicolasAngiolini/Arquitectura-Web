package edu.isistan.db;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import factory.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import dao.*;

public class BaseDeDatos {
	
	
	public static void main(String[] args) throws SQLException {
	
		
		
		FactoryDerby fa = new FactoryDerby();
		Connection conn = fa.conexion();
		ClienteDAO cd= fa.getClienteDAO();
		ProductoDAO pd = fa.getProductoDAO();
		FacturaDAO fd = fa.getFacturaDAO();
		cd.createTable(conn);
		cd.addCliente(conn);
		cd.mostrarCliente(conn);
		pd.createTable(conn);
		pd.addProducto(conn);
		pd.mostrarCliente(conn);
		
		fd.createTable(conn);
		fd.addFactura(conn);
		fd.mostrarFacturas(conn);
		
		
	
	}


}

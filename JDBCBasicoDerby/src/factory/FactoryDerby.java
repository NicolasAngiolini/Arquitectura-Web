package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.*;

public class FactoryDerby implements Factory {

	private String uri = "jdbc:derby:tp1;create=true";
	private static Connection conexion = null;

	public Connection conexion() {
		if(conexion==null) {
			conexion2();
		}
		return conexion;
	}
	
	private void conexion2() {
		
		try {
			conexion = DriverManager.getConnection(this.uri);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String driver = "org.apache.derby.jdbc.EmbeddedDriver"; 
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public ClienteDAO getClienteDAO() {
		return new ClienteDAO();
	}

	@Override
	public ProductoDAO getProductoDAO() {
		return new ProductoDAO();
	}

	@Override
	public FacturaDAO getFacturaDAO() {
		return new FacturaDAO();
	}


	
	
}

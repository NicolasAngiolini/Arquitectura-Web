package factory;

import java.sql.Connection;

import dao.*;

public interface Factory {


	public Connection conexion();
	
	public ClienteDAO getClienteDAO();
	
	public ProductoDAO getProductoDAO();
	
	public FacturaDAO getFacturaDAO();
	
}

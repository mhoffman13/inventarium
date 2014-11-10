package inventarium.data;

import inventarium.entity.Category;
import inventarium.entity.Inventory;
import inventarium.entity.Product;
import inventarium.entity.Vendor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public final class DataRequest {
	private DataRequest() {}
	private static Scanner in = new Scanner(System.in); // DEBUG
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String dbName="DB";
	private static Connection conn = null;
	private static Statement stmt = null;
	public static void initialize(){
		//Load DB driver
		try {
				Class.forName(driver);
				System.out.println("Successfully loaded DB driver"); // DEBUG
		} catch (ClassNotFoundException e) {
				System.out.println("Failed to load DB driver"); // DEBUG
				e.printStackTrace();
				System.out.print("Press Enter to exit"); // DEBUG
				in.nextLine(); // DEBUG
				System.exit(0);
		}
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		System.out.println("Attempting to connect to database " + dbName + "..."); // DEBUG
		//Connect to DB, create one if given DB doesn't exist
		try {
			conn = DriverManager.getConnection(connectionURL);		 
			System.out.println("Connected to database " + dbName); // DEBUG
		} catch (Throwable e) {
			System.out.println("Failed to connect to database" + dbName); // DEBUG
			e.printStackTrace();
			System.out.print("Press Enter to exit"); // DEBUG
			in.nextLine(); // DEBUG
			System.exit(0);
		}
		// meredith:
			// should this part of initialization just be a script we run?
			// we wouldn't want this to ever run once the initial database
			// setup is complete, would  we?
		//Create tables, ignore "table already exists" errors
		try{
			stmt = conn.createStatement();
			stmt.execute("CREATE TABLE Category("
				+ "id BIGINT NOT NULL PRIMARY KEY, "
				+ "name VARCHAR(100), "
				+ "description VARCHAR(200))");
			stmt.execute("CREATE TABLE Vendor("
				+ "id BIGINT NOT NULL PRIMARY KEY, "
				+ "name VARCHAR(50), "
				+ "status VARCHAR(10), "
				+ "description VARCHAR(200), "
				+ "address_line1 VARCHAR(100), "
				+ "address_line2 VARCHAR(100), "
				+ "address_city VARCHAR(100), "
				+ "address_state VARCHAR(2), "
				+ "address_zip VARCHAR(5), "
				+ "primary_phone VARCHAR(10), "
				+ "secondary_phone VARCHAR(10), "
				+ "email VARCHAR(100), "
				+ "contact_name VARCHAR(100))");
			stmt.execute("CREATE TABLE Product("
				+ "id BIGINT NOT NULL PRIMARY KEY, "
				+ "name VARCHAR(100), "
				+ "description VARCHAR(200), "
				+ "status VARCHAR(10), "
				+ "quantity INT, "
				+ "low_quantity INT, "
				+ "is_low VARCHAR(1))");
			stmt.execute("CREATE TABLE Inventory("
				+ "id BIGINT NOT NULL PRIMARY KEY, "
				+ "product_id BIGINT REFERENCES Product(id), "
				+ "vendor_id BIGINT REFERENCES Vendor(id), "
				+ "date VARCHAR(50), "
				+ "adjustment INT)");
			stmt.execute("CREATE TABLE Product_Category("
				+ "product_id BIGINT REFERENCES Product(id), "
				+ "category_id BIGINT REFERENCES Category(id), "
				+ "PRIMARY KEY (product_id, category_id))");
		} catch (SQLException e) {
			// Ignore "table already exists" errors
			System.out.println(e); // DEBUG
			return;
		}
	}
	public static boolean insertRecord( Vendor vend ) {
		return true;
		// TODO complete insertRecord(Vendor vend) method
	}
	public static boolean insertRecord( Product prod ) {
		return true;
		// TODO complete insertRecord(Product prod) method
	}
	public static boolean insertRecord( Category cat ) {
		return true;
		// TODO complete insertRecord(Category cat) method
	}
	public static boolean insertRecord( Inventory inv ) {
		return true;
		// TODO complete insertRecord(Inventory inv) method
	}
	public static boolean updateRecord(Vendor vend) {
		return true;
		// TODO complete updateRecord(Vendor vend) method
	}
	public static boolean updateRecord(Product prod) {
		return true;
		// TODO complete updateRecord(Product prod) method
	}
	public static boolean updateRecord(Category cat) {
		return true;
		// TODO complete updateRecord(Category cat) method
	}
	public static boolean updateRecord(Inventory inv) {
		return true;
		// TODO complete updateRecord(Inventory inv) method
	}
	public static List<Object> search( String searchTerm, String columnName, 
			String tableName  ) throws SQLException {
		// meredith: added throws SQLException here, may need to tweak that
		// but eclipse was throwing up errors without it
		List<Object> result = new ArrayList<Object>();
		String query = "SELECT * FROM " + tableName + " WHERE " + columnName + "='" + searchTerm + "'";
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData metaData = rs.getMetaData();
		int columns = metaData.getColumnCount();
		while (rs.next()) {
			String printMe = "";
			for(int i=1; i<=columns; i++) {
				// TO DO: create object from column values
			}
			// TO DO: add object to list
		}
		return result;
	}
}

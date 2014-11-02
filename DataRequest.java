import java.sql.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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
				String s = in.nextLine(); // DEBUG
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
			String s = in.nextLine();
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
		// TODO: complete insertRecord(Vendor vend) method
	}
	public static boolean insertRecord( Product prod ) {
		// TO DO
	}
	public static boolean insertRecord( Category cat ) {
		// TO DO
	}
	public static boolean insertRecord( Inventory inv ) {
		// TO DO
	}
	public static boolean updateRecord( Integer id, Vendor vend ) {
		// TO DO
	}
	public static boolean updateRecord( Integer id, Product prod ) {
		// TO DO
	}
	public static boolean updateRecord( Integer id, Category cat ) {
		// TO DO
	}
	public static boolean updateRecord( Integer id, Inventory inv ) {
		// TO DO
	}
	public static List<Object> search( searchTerm, columnName, tableName  ) {
		List<Object> result = new ArrayList<Object>();
		String query = "SELECT * FROM " + tableName + " WHERE " + columnName + "=" + searchTerm;
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData metaData = rs.getMetaData();
		int columns = metaData.getColumnCount();
		while (rs.next()) {
			printMe = "";
			for(int i=1; i<=columns; i++) {
				// TO DO: create object from column values
			}
			// TO DO: add object to list
		}
		return result;
	}
}

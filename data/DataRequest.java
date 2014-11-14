package inventarium.data;

import inventarium.entity.Category;
import inventarium.entity.Inventory;
import inventarium.entity.Product;
import inventarium.entity.Vendor;
import inventarium.helper.Address;
import inventarium.helper.EntityStatus;

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
import java.util.Set;

public final class DataRequest {
	private DataRequest() {}
	private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static String dbName="DB";
	private static Connection conn = null;
	private static Statement stmt = null;
	public static void initialize(){
		//Load DB driver
		try {
				Class.forName(driver);
				System.out.println("Successfully loaded DB driver");
		} catch (ClassNotFoundException e) {
				System.out.println("Failed to load DB driver");
				e.printStackTrace();
				System.exit(0);
		}
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		System.out.println("Attempting to connect to database " + dbName + "...");
		//Connect to DB, create one if given DB doesn't exist
		try {
			conn = DriverManager.getConnection(connectionURL);		 
			System.out.println("Connected to database " + dbName);
		} catch (Throwable e) {
			System.out.println("Failed to connect to database" + dbName);
			e.printStackTrace();
			System.exit(0);
		}
		if(!tablesExist()) {
			try{
				stmt = conn.createStatement();
				stmt.execute("CREATE TABLE Category("
					+ "id INT NOT NULL PRIMARY KEY, "
					+ "name VARCHAR(100), "
					+ "description VARCHAR(200), "
					+ "status VARCHAR(10))");
				stmt.execute("CREATE TABLE Vendor("
					+ "id INT NOT NULL PRIMARY KEY, "
					+ "name VARCHAR(50), "
					+ "status VARCHAR(10), "
					+ "description VARCHAR(200), "
					+ "address_line1 VARCHAR(100), "
					+ "address_line2 VARCHAR(100), "
					+ "address_city VARCHAR(100), "
					+ "address_state VARCHAR(2), "
					+ "address_zip VARCHAR(5), "
					+ "phone VARCHAR(10), "
					+ "email VARCHAR(100), "
					+ "contact_name VARCHAR(100))");
				stmt.execute("CREATE TABLE Product("
					+ "id INT NOT NULL PRIMARY KEY, "
					+ "name VARCHAR(100), "
					+ "description VARCHAR(200), "
					+ "status VARCHAR(10), "
					+ "sku VARCHAR(100), "
					+ "quantity INT, "
					+ "low_quantity INT, "
					+ "is_low SMALLINT, "
					+ "category_id INT REFERENCES Category(id), "
					+ "vendor_id INT REFERENCES Vendor(id))");
				stmt.execute("CREATE TABLE Inventory("
					+ "id INT NOT NULL PRIMARY KEY, "
					+ "product_id INT REFERENCES Product(id), "
					+ "date DATE, "
					+ "adjustment INT)");
			} catch (SQLException e) {
				System.out.println(e); 
			}
		}
	}
	private static boolean tablesExist() {
		try {
			stmt = conn.createStatement();
			stmt.execute("SELECT * FROM Product");
			return true;
		}
		catch (SQLException e) {
			return false;
		}
	}
	public static boolean insertRecord( Vendor vend ) {
		String query = "INSERT INTO Product VALUES ("
				+ vend.getUniqueId() + ", '"
				+ vend.getName() + "', '"
				+ vend.getStatus().getEntityStatus() + "', '"
				+ vend.getDescription() + "', '"
				+ vend.getAddress().getLine1() + "', '"
				+ vend.getAddress().getLine2() + "', '"
				+ vend.getAddress().getCity() + "', '"
				+ vend.getAddress().getState() + "', '"
				+ vend.getAddress().getZip() + "', '"
				+ vend.getPhone() + "', '"
				+ vend.getEmail() + "', '"
				+ vend.getContactName() + "')";
		return runQuery(query);
	}
	public static boolean insertRecord( Product prod ) {
		String query = "INSERT INTO Vendor VALUES ("
				+ prod.getUniqueId() + ", '"
				+ prod.getName() + "', '"
				+ prod.getDescription() + "', '"
				+ prod.getStatus().getEntityStatus() + "', '"
				+ prod.getSku() + "', "
				+ prod.getQuantity() + ", "
				+ prod.getlowQuantity() + ", "
				+ prod.getCategory().getUniqueId() + ", "
				+ prod.getVendor().getUniqueId() + ", "
				+ (prod.isLow() ? 1 : 0) + ")";
		return runQuery(query);
	}
	public static boolean insertRecord( Category cat ) {
		String query = "INSERT INTO Category VALUES ("
				+ cat.getUniqueId() + ", '"
				+ cat.getName() + "', '"
				+ cat.getDescription() + "', '"
				+ cat.getStatus().getEntityStatus() + "')";
		return runQuery(query);
	}
	public static boolean insertRecord( Inventory inv ) {
		String query = "INSERT INTO Inventory VALUES ("
				+ inv.getUniqueId() + ", "
				+ inv.getProductId() + ", '"
				+ inv.getDate().toString() + "', "
				+ inv.getAdjustment() + ")";
		return runQuery(query);
	}
	public static boolean updateRecord( Vendor vend ) {
		String query = "UPDATE Vendor SET "
				+ "name='" + vend.getName() + "', "
				+ "status='" + vend.getStatus().getEntityStatus() + "', "
				+ "description='" + vend.getDescription() + "', "
				+ "address_line1='" + vend.getAddress().getLine1() + "', "
				+ "address_line2='" + vend.getAddress().getLine2() + "', "
				+ "address_city='" + vend.getAddress().getCity() + "', "
				+ "address_state='" + vend.getAddress().getState() + "', "
				+ "address_zip='" + vend.getAddress().getZip() + "', "
				+ "phone='" + vend.getPhone() + "', "
				+ "email='" + vend.getEmail() + "', "
				+ "contact_name='" + vend.getContactName() + "' "
				+ "WHERE id = " + vend.getUniqueId();
		return runQuery(query);
	}
	public static boolean updateRecord( Product prod ) {
		String query = "UPDATE Product SET "
				+ "name='" + prod.getName() + "', "
				+ "description='" + prod.getDescription() + "', "
				+ "status='" + prod.getStatus().getEntityStatus() + "', "
				+ "sku='" + prod.getSku() + "', "
				+ "quantity=" + prod.getQuantity() + ", "
				+ "low_quantity=" + prod.getlowQuantity() + ", "
				+ "is_low=" + (prod.isLow() ? 1 : 0) + ", "
				+ "category_id=" + prod.getCategory().getUniqueId() + ", "
				+ "vendor_id=" + prod.getVendor().getUniqueId() + " "
				+ "WHERE id = " + prod.getUniqueId();
		return runQuery(query);
	}
	public static boolean updateRecord( Category cat ) {
		String query = "UPDATE Category SET "
				+ "name='" + cat.getName() + "', "
				+ "description='" + cat.getDescription() + "', "
				+ "status='" + cat.getStatus().getEntityStatus() + "' "
				+ "WHERE id = " + cat.getUniqueId();
		return runQuery(query);
	}
	public static boolean updateRecord( Inventory inv ) {
		String query = "UPDATE Inventory SET "
				+ "product_id=" + inv.getProductId() + ", "
				+ "date='" + inv.getDate().toString() + "', "
				+ "adjustment=" + inv.getAdjustment() + " "
				+ "WHERE id = " + inv.getUniqueId();
		return runQuery(query);
	}
	public static boolean runQuery( String query ) {
		try {
			System.out.println("Executing query: " + query); 
			stmt = conn.createStatement();
			stmt.execute(query);
			return true;
		}
		catch (SQLException e) {
			System.out.println(e); 
			return false;
		}
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
			for(int i=1; i<=columns; i++) {
				// TODO: create object from column values
			}
			// TODO: add object to list
		}
		return result;
	}
	
	public static Set<Product> search(Product product, List<String> searchTerms) throws SQLException {
		Set<Product> results = new HashSet<Product>();
		String query = "SELECT * FROM Product WHERE ";// + columnName + "='" + searchTerm + "'";
		for (String term : searchTerms) {
		    // iterate through searchTerms
			if(term.equalsIgnoreCase("uniqueId")){
				query += "id=" + product.getUniqueId();
			}else if(term.equalsIgnoreCase("name")){
				query += "name LIKE '" + product.getName() + "'";
			}else if(term.equalsIgnoreCase("vendor")){
				query += "vendor_id=" + product.getVendor().getUniqueId();
			}else if(term.equalsIgnoreCase("category")){
				query += "category_id=" + product.getCategory().getUniqueId();
			}
			query += " and ";
		}
		// remove the last stray " and "
		query = query.substring(0, query.length() - 5);
		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		// Product fields
		Integer prodId;
		String prodName;
		EntityStatus prodStatus;
		String prodDescription;
		String prodSku;
		int prodQuantity;
		int prodLowQuantity;
		Category prodCategory;
		Vendor prodVendor;
		// Category fields
		ResultSet catRs;
		Integer catId;
		String catName;
		String catDescription;
		EntityStatus catStatus;
		// Vendor fields
		ResultSet vendRs;
		Integer vendId;
		String vendName;
		String vendDescription;
		String vendContactName;
		String vendPhone;
		Address vendAddress;
		String vendEmail;
		EntityStatus vendStatus;
		// Address fields
		String line1;
		String line2;
		String city;
		String state;
		String zip;
		while(rs.next()){
			prodId = Integer.valueOf(rs.getInt("id"));
			prodName = rs.getString("name");
			prodStatus = rs.getString("status").equals("A") ? EntityStatus.ACTIVE : EntityStatus.ARCHIVED;
			prodDescription = rs.getString("description");
			prodSku = rs.getString("sku");
			prodQuantity = rs.getInt("quantity");
			prodLowQuantity = rs.getInt("low_quantity");
			// Fetch product's category
			query = "SELECT * FROM Category WHERE id = " + rs.getInt("category_id");
			catRs = stmt.executeQuery(query);
			catId = Integer.valueOf(catRs.getInt("id"));
			catName = catRs.getString("name");
			catDescription = catRs.getString("description");
			catStatus = catRs.getString("status").equals("A") ? EntityStatus.ACTIVE : EntityStatus.ARCHIVED;
			prodCategory = new Category(catId, catName, catDescription, catStatus);
			// Fetch product's vendor
			query = "SELECT * FROM Category WHERE id = " + rs.getInt("vendor_id");
			vendRs = stmt.executeQuery(query);
			vendId = Integer.valueOf(vendRs.getInt("id"));
			vendName = vendRs.getString("name");
			vendDescription = vendRs.getString("description");
			vendContactName = vendRs.getString("contact_name");
			vendPhone = vendRs.getString("phone");
			line1 = vendRs.getString("address_line1");
			line2 = vendRs.getString("address_line2");
			city = vendRs.getString("address_city");
			state = vendRs.getString("address_state");
			zip = vendRs.getString("address_zip");
			vendAddress = new Address(line1, line2, city, state, zip);
			vendEmail = vendRs.getString("email");
			vendStatus = vendRs.getString("status").equals("A") ? EntityStatus.ACTIVE : EntityStatus.ARCHIVED;
			prodVendor = new Vendor(vendId, vendName, vendDescription, vendContactName, 
					vendPhone, vendAddress, vendEmail, vendStatus);
			// TODO:Currently passes null for inventory list
			results.add(new Product(prodId, prodName, prodStatus, prodDescription, prodSku, 
					prodQuantity, prodLowQuantity, prodCategory, prodVendor, null));
		}
		return results;
	}
}

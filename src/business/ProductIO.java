/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author ryan
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Map;

public class ProductIO {

    private static String configProperties = "liquorapp.configuration";    
    private static String dbDriver = null;
    private static String dbUrl = null;

    /**
     * Constructor for ProductIO.
     */
    public ProductIO() {

    }

    public Map<Integer, Product> createProductList() {
        Map<Integer, Product> productList = new HashMap<>();
        Connection dbConn;
        Integer productID;

        // get properties
        ResourceBundle bundle = ResourceBundle.getBundle(configProperties);
        dbDriver = bundle.getString("dbDriver");
        dbUrl = bundle.getString("dbUrl");
        String selectSQL = bundle.getString("selectProductList");

        // get database connection to the report log
        dbConn = openDatabase(dbDriver, dbUrl);
        if (dbConn == null) {
            System.out.println("Unable to open Database Connection to " + dbUrl);
            System.exit(1);
        }

        try {

            // prepare statement
            PreparedStatement selectColumns = dbConn.prepareStatement(selectSQL);
            // execute the statement
            ResultSet rs = selectColumns.executeQuery();

            //SELECT productID, brand, cost, full_weight  FROM product
            while (rs.next()) {
                Product prd = new Product();
                productID = (rs.getInt(1));
                prd.setProductID(productID);
                prd.setBrand(rs.getString(2));
                prd.setCost(rs.getDouble(3));
                prd.setFullWeight(rs.getDouble(4));

                //Hash Map put
                productList.put(productID, prd);
            }
            rs.close();
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;
    }
    public String insertProduct(Product prd) {
        Connection dbConn;
        Integer productID;
        String eMessage = null;

        // get properties
        ResourceBundle bundle = ResourceBundle.getBundle(configProperties);
        dbDriver = bundle.getString("dbDriver");
        dbUrl = bundle.getString("dbUrl");
        String insertSQL = bundle.getString("insertProduct");

        // get database connection to the report log
        dbConn = openDatabase(dbDriver, dbUrl);
        if (dbConn == null) {
            eMessage = ("Can't connect to database");
            return eMessage;
        }

        try {

            // prepare statement
            //insert into product (productID, brand, cost, full_weight) values (?, ?, ?); 
            PreparedStatement ps = dbConn.prepareStatement(insertSQL);
            ps.setString(1, prd.getBrand());
            ps.setDouble(2,prd.getCost());
            ps.setDouble(3, prd.getFullWeight());
            ps.executeUpdate();
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        eMessage = ("Update successful");
        return eMessage;
    }
    /**
     * openDatabase - Open the database connection
     */
    private Connection openDatabase(String wDriver, String wUrl) {
        Connection dbConn = null;
        try {

            String userName = "liquorUser", password = "123";

            // load driver and prepare to access
            Class.forName(wDriver).newInstance();

            dbConn = DriverManager.getConnection(wUrl, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;

    }

}

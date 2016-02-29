/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquorapp;

/**
 *
 * @author ryan
 */
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import business.Product;
import java.util.HashMap;
import java.util.Map;

public class ListProduct2 {

    private static final String programName = "ListProduct";
    private static String dbDriver = null;
    private static String dbUrl = null;
    private static String selectSQL = null;

    /**
     * Constructor for CreateSql.
     */
    public ListProduct2() {

    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        ListProduct2 cc = new ListProduct2();
        cc.createProductList(args);
    }

    private static void getProperties() {
        ResourceBundle bundle = ResourceBundle.getBundle("liquorapp.configuration");
        dbDriver = bundle.getString("dbDriver");
        dbUrl = bundle.getString("dbUrl");
        selectSQL = bundle.getString("selectProductList");
    }

    public void createProductList(String[] args) {
        Map<Integer, Product> productlist = new HashMap<>();
        Connection dbConn = null;
        Integer productID;
        String outFile = "productlist.csv";
        boolean append = false;

        // Start Sql creation
        long runTime = System.currentTimeMillis();
        System.out.println(programName + " --> Begins");

        // get properties
        getProperties();
        System.setProperty("line.separator", "\r\n");

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

            // if outfile is not specified use table name
            //SELECT productID, brand, cost, full_weight  FROM product
            while (rs.next()) {
                Product prd = new Product();
                productID = (rs.getInt(1));
                prd.setProductID(productID);
                prd.setBrand(rs.getString(2));
                prd.setCost(rs.getDouble(3));
                prd.setFullWeight(rs.getDouble(4));

                //Hash Map put
                productlist.put(productID, prd);
            }
            rs.close();
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Write HashMap to CSv
        //Need this code
        try {
            FileOutputStream fileOut = new FileOutputStream(outFile, append);
            PrintWriter prtWrt = new PrintWriter(fileOut);
            Product prd = new Product();

            for (Map.Entry<Integer, Product> entry : productlist.entrySet()) {
                productID = entry.getKey();
                prd = entry.getValue();
                
                // Write out csv data
                prtWrt.printf("%d,\"%s\",%f,%f\n", productID, prd.getBrand(), prd.getCost(), prd.getFullWeight());

            }

            prtWrt.close();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * openDatabase - Open the database connection
     */
    public Connection openDatabase(String wDriver, String wUrl) {
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

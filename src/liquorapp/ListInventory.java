/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquorapp;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

/**
 *
 * @author ryan
 */
public class ListInventory {

    private static final String programName = "ListInventory";
    private static String dbDriver = null;
    private static String dbUrl = null;
    private static String selectSQL = null;

    /**
     * Constructor for CreateSql.
     */
    public ListInventory() {

    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        ListInventory cc = new ListInventory();
        cc.createInventoryList(args);
    }

    private static void getProperties() {
        ResourceBundle bundle = ResourceBundle.getBundle("liquorapp.configuration");
        dbDriver = bundle.getString("dbDriver");
        dbUrl = bundle.getString("dbUrl");
        selectSQL = bundle.getString("selectInventory");
    }

    public void createInventoryList(String[] args) {
        Connection dbConn = null;
        int i, productID = 0, lineCount = 0, inventoryID = 0, fullBottles = 0;
        double partial_weight = 0;
        String tstamp=" ";
        String brand = " ";
        String outFile = "inventorylist.csv";
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
            FileOutputStream fileOut = new FileOutputStream(outFile, append);
            PrintWriter prtWrt = new PrintWriter(fileOut);

            //SELECT inventoryID, fullBottles, tstamp, productID, partial_weight FROM inventory
            while (rs.next()) {
                productID = rs.getInt(1);
                brand = rs.getString(2);
                fullBottles = rs.getInt(3);
                partial_weight = rs.getDouble(3);

                // Write out csv data
                prtWrt.printf("%d,%s,%d,%f\n", productID, brand, fullBottles, partial_weight);
                lineCount++;
            }
            rs.close();
            prtWrt.close();
            fileOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // close the connection
        try {
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        runTime = (System.currentTimeMillis() - runTime) / 1000;

        System.out.println(lineCount
                + " records written");
        System.out.println(programName
                + " --> End - Time: " + runTime + " secs");
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



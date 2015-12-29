package com.oreillyauto.utilities.createCSV;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.oreillyauto.util.PasswordManager;

/**
 * @author rob
 * 
 * Builds a SQL script to create a table based on the values of the SysColumns
 * table on an AS/400
 */
public class CreateStoreList {

    private static final String programName = "com.oreillyauto.utilities.createCSV Ver 1.1";
	private static String dbDriver = null;
	private static String dbUrl = null;
	private static String selectSQL = null;
	
    /**
     * Constructor for CreateSql.
     */
    public CreateStoreList() {
        super();
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        CreateStoreList cc = new CreateStoreList();
        cc.createStoreList(args);
    }
    
    private static void getProperties() {
		ResourceBundle bundle = ResourceBundle.getBundle("com.oreillyauto.utilities.createCSV.configuration");
		dbDriver = bundle.getString("dbDriver");
		dbUrl = bundle.getString("dbUrl");
		selectSQL = bundle.getString("selectStoreList");
	}
    
    public void createStoreList(String[] args) {
        Connection dbConn = null;
        int i, store, district, lineCount = 0;
        String storeManager, firstName, lastName, city, state;
        String outFile = "storelist.csv";
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

            // Retrieve columns from SysColumns
            //Select store, district, strmgr, city, state, zip FROM  dwqrydata.stores where store > 100 order by 1
            while (rs.next()) {
                store = rs.getInt(1);
                district = rs.getInt(2);
                storeManager = rs.getString(3).trim();
                city = rs.getString(4).trim();
                state = rs.getString(5);
                
                //split name into first and last
                i = storeManager.indexOf(' ');
                if(i >= 0) {
                	firstName = storeManager.substring(0,i);
                	lastName = storeManager.substring(i+1);
                } else {
                	firstName = "";
                	lastName = "";
                }

                // Write out csv data
                prtWrt.printf("%d,%d,\"%s\",\"%s\",%s,%s\n",store,district,firstName,lastName,city,state);
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
        System.out.println(lineCount + " records written");
        System.out.println(programName + " --> End - Time: " + runTime + " secs");
    }

    /**
     * openDatabase - Open the database connection
     */
    public Connection openDatabase(String wDriver, String wUrl) {
        Connection dbConn = null;
        try {
            
			PasswordManager pm = new PasswordManager();
			String userName= null, password=null;
			
			userName = pm.getSQLQueryUserName();
			password = pm.getSQLQueryPassword();

            // load driver and prepare to access
            Class.forName(wDriver).newInstance();

            dbConn = DriverManager.getConnection(wUrl, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConn;
    }
}
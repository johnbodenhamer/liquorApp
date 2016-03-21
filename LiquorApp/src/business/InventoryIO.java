/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.sql.Timestamp;

/**
 *
 * @author ryan
 */
public class InventoryIO {
    private static String configProperties = "liquorapp.configuration";    
    private static String dbDriver = null;
    private static String dbUrl = null;
    
    public InventoryIO(){
        
    }
    public Map<Integer, Inventory> createInventory1(){
         Map<Integer, Inventory> inventory1 = new HashMap<>();
        Connection dbConn;
        Integer inventoryID;

        // get properties
        ResourceBundle bundle = ResourceBundle.getBundle(configProperties);
        dbDriver = bundle.getString("dbDriver");
        dbUrl = bundle.getString("dbUrl");
        String selectSQL = bundle.getString("selectInventoryList");

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

          //SELECT i.productID, p.brand, fullBottles, partialWeight FROM liquor_db.inventory as i join liquor_db.product as p ON i.productID=p.productID ORDER BY p.productID
            while (rs.next()) {
                Inventory inv = new Inventory();
                inv.setProductID(rs.getInt(1));
                inv.setPartialWeight(rs.getDouble(2));


                //Hash Map put
                inventory1.put(inventoryID, inv);
            }
            rs.close();
            dbConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventory1;
        
    }
    
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

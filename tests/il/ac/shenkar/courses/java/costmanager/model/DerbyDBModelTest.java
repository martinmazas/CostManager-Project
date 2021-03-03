package il.ac.shenkar.courses.java.costmanager.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;




public class DerbyDBModelTest {

    private DerbyDBModel model = new DerbyDBModel();
    CostItem item;
    Category cat;
    int id;
//    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
//    private static String protocol = "jdbc:derby:gagamoDB;create=true";

    @Before
    public void setUp() throws Exception {
        // Connection connection = null;
        // Statement statement = null;
        // ResultSet rs = null;

        // try {
        //     Class.forName(driver);
        //     //getting connection by calling get connection
        //     connection = DriverManager.getConnection(protocol);
        //     statement = connection.createStatement();

        // } catch (SQLException | ClassNotFoundException e) {
        //     throw new CostManagerException("Problem with the connection", e);
        // }
        cat = new Category(1,"Water");
        item = new CostItem(10, cat,45.5, Currency.ILS,"Salary","2020-02-20");

    }

    @After
    public void tearDown() throws Exception {
        cat = null;
        item = null;
        // model.stopConnection();
    }
    // @Test
    // public void createConnection(){
    //     String expected = "Problem with the connection";
    //     try {
    //         model.createConnection();
    //     } catch (CostManagerException e) {
    //         assertEquals(expected,e.getMessage());
    //     }
    // }


    @Test
    public void addCostItem(){
        String expected = "Problem with adding cost!";
        try {
            model.addCostItem(item);
        } catch (CostManagerException e) {
            assertEquals(expected,e.getMessage());
        }
    }

    @Test
    public void deleteCostItem() {
        String expected = "Problem with deleting cost!";
        try{
            model.deleteCostItem(id);
        } catch (CostManagerException e) {
            assertEquals(expected,e.getMessage());
        }
    }

    @Test
    public void addNewCategory() {
        String expected = "Problem with adding cost!";
        try {
            model.addNewCategory(cat);
        } catch (CostManagerException e) {
            assertEquals(expected,e.getMessage());
        }
    }

    @Test
    public void getCostReport() {

        String expected = "Problem getting data from specified dates";
        try {
            model.getCostReport("2020-01-01","2020-03-31");
        } catch (CostManagerException e) {
            assertEquals(expected,e.getMessage());
        }

    }

    @Test
    public void getPieChart() {
        String expected = "Problem getting data from specified dates";
        try {
            model.getCostReport("2020-01-01","2020-03-31");
        } catch (CostManagerException e) {
            assertEquals(expected,e.getMessage());
        }
    }
}
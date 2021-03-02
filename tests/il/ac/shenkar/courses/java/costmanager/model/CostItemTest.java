package il.ac.shenkar.courses.java.costmanager.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CostItemTest {
    CostItem item;
    Category cat;
    @Before
    public void setUp() {
        cat = new Category(1,"Water");
        item = new CostItem(10, cat,45.5, Currency.ILS,"Salary","2020-02-20");

    }

    @After
    public void tearDown() throws Exception {
        cat = null;
        item = null;
    }

    @Test
    public void getId() {
        int expected = 10;
        int actual = item.getId();
        assertEquals(expected,actual);
    }

    @Test
    public void getDescription() {
        String expected = "Salary";
        String actual = item.getDescription();
        assertEquals(expected,actual);
    }

    @Test
    public void getAmount() {
        Double expected = 45.5;
        Double actual = item.getAmount();
        assertEquals(expected,actual);
    }

    @Test
    public void getCurrency() {
        String  expected = "ILS";
        Currency actual = item.getCurrency();
        assertEquals(expected,actual.name());
    }

    @Test
    public void getCategory() {
        String expected = "Water";
        Category actual = item.getCategory();
        assertEquals(expected,actual.getName());
    }

    @Test
    public void getDate() {
        String expected = "2020-02-20";
        String actual = item.getDate();
        assertEquals(expected,actual);
    }
}
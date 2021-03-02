package il.ac.shenkar.courses.java.costmanager.model;


import org.jfree.chart.JFreeChart;

import java.util.List;

public interface IModel {
    /**
     * add a new cost to inventory table in database
     */
    public void addCostItem(CostItem item) throws CostManagerException;

    /**
     * delete a cost from inventory table in database
     */
    public void deleteCostItem(CostItem item) throws CostManagerException;

    /**
     * add a new cost to categories table in database
     */
    public void addNewCategory(Category category) throws CostManagerException;

    /**
     * get report from all the costs between specific dates
     */
    public CostItem[] getCostReport(String start, String end) throws CostManagerException;

    /**
     * get pie chartr from all the costs between specific dates
     */
    public JFreeChart getPieChart(String start, String end) throws CostManagerException;

    public CostItem[] getCostItems() throws CostManagerException;

    List<String> getCategoryList() throws CostManagerException;

}

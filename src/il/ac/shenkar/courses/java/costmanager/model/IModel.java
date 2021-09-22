package il.ac.shenkar.courses.java.costmanager.model;

import org.jfree.chart.JFreeChart;
import java.util.List;

/**
 *  Model interface of CostManager
 * @methods
 * addCostItem- add a new cost to inventory table in database
 * deleteCostItem- delete a cost from inventory table in database
 * addNewCategory- add a new cost to categories table in database
 * getCostReport - return report from all the costs between specific dates
 * getPieChart - return a pie chart from all the costs between specific dates
 * getCostItems - return all the expenses from the database
 * getCategoryList - return all the categories from the database
 */
public interface IModel {
    public void addCostItem(CostItem item) throws CostManagerException;
    public void deleteCostItem(int id) throws CostManagerException;
    public void addNewCategory(Category category) throws CostManagerException;
    public CostItem[] getCostReport(String start, String end) throws CostManagerException;
    public JFreeChart getPieChart(String start, String end) throws CostManagerException;
    public CostItem[] getCostItems() throws CostManagerException;
    List<Category> getCategoryList() throws CostManagerException;
}

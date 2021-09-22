// Niv Swisa 307929257
// Martin Mazas 329834857
package il.ac.shenkar.courses.java.costmanager.viewmodel;

import il.ac.shenkar.courses.java.costmanager.model.Category;
import il.ac.shenkar.courses.java.costmanager.model.CostItem;
import il.ac.shenkar.courses.java.costmanager.model.IModel;
import il.ac.shenkar.courses.java.costmanager.view.IView;

/**
 * ViewModel interface of CostManager
 * @methods
 * setView- connect the ViewModel interface to View interface
 * setModel- connect the ViewModel interface to Model interface
 * addCostItem- request from the view to add a new cost item into the model
 * deleteCostItem- request from the view to delete an item from the model
 * getReport- request from the view to get a report from the model
 * addNewCategory- request from the view to add a new category into the model
 * getCategoryList- request from the view to get all the categories from the model
 * getPieChart- request from the view to get a pie chart from the model
 */
public interface IViewModel {
    public void setView(IView view);
    public void setModel(IModel model);
    public void addCostItem(CostItem item);
    public void deleteCostItem(int id);
    public void getReport(String initDate, String endDate);
    public void addNewCategory(Category category);
    public void getCategoryList();
    public void getPieChart(String initDate, String endDate);
}

// Niv Swisa 307929257
// Martin Mazas 329834857
package il.ac.shenkar.courses.java.costmanager.view;

import il.ac.shenkar.courses.java.costmanager.model.Category;
import il.ac.shenkar.courses.java.costmanager.model.CostItem;
import il.ac.shenkar.courses.java.costmanager.viewmodel.IViewModel;
import org.jfree.chart.JFreeChart;
import java.util.List;

/**
 * View interface of CostManager
 * @methods
 * showMessage- show the action message to the user
 * showItems- show the items to the user
 * setViewModel- connect the View interface to ViewModel interface
 * showCategories- show the categories to the user
 * showPieChart- show the pie chart to the user
 */
public interface IView {
    public void showMessage(String text, String area);
    public void showItems(CostItem[] vec, String area);
    public void setViewModel(IViewModel vm);
    public void showCategories(List<Category> category);
    public void showPieChart(JFreeChart chart);
}

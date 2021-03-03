package il.ac.shenkar.courses.java.costmanager.viewmodel;

import il.ac.shenkar.courses.java.costmanager.model.Category;
import il.ac.shenkar.courses.java.costmanager.model.CostItem;
import il.ac.shenkar.courses.java.costmanager.model.IModel;
import il.ac.shenkar.courses.java.costmanager.view.IView;
import org.jfree.chart.JFreeChart;

import java.util.List;

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

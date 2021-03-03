package il.ac.shenkar.courses.java.costmanager.view;


import il.ac.shenkar.courses.java.costmanager.model.Category;
import il.ac.shenkar.courses.java.costmanager.model.CostItem;
import il.ac.shenkar.courses.java.costmanager.viewmodel.IViewModel;
import org.jfree.chart.JFreeChart;

import java.util.List;

public interface IView {
    /**
     * Display the GUI for add a new cost item layout
     */
//    public void displayAddCostItem();
    /**
     * Display the GUI for add a new category layout
     */
//    public void displayAddNewCategory();
    /**
     * Display the GUI for get the selected report(pie, regular report)
     */
//    public void displayGetCostReport();
    //    public void displayPieChart(Map map);
    public void showMessage(String text);
    public void showItems(CostItem[] vec);
    public void setViewModel(IViewModel vm);
    public void showCategories(List<Category> category);
    public void showPieChart(JFreeChart chart);
}

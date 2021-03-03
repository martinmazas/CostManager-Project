package il.ac.shenkar.courses.java.costmanager.viewmodel;

import il.ac.shenkar.courses.java.costmanager.model.Category;
import il.ac.shenkar.courses.java.costmanager.model.CostItem;
import il.ac.shenkar.courses.java.costmanager.model.CostManagerException;
import il.ac.shenkar.courses.java.costmanager.model.IModel;
import il.ac.shenkar.courses.java.costmanager.view.IView;
import org.jfree.chart.JFreeChart;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ViewModel implements IViewModel {

    private IModel model;
    private IView view;
    private ExecutorService pool;


    public ViewModel() {
        pool = Executors.newFixedThreadPool(10);
    }

    @Override
    public void setView(IView view) {
        this.view = view;
    }

    @Override
    public void setModel(IModel model) {
        this.model = model;
    }

    @Override
    public void addCostItem(CostItem item) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addCostItem(item);
                    view.showMessage("Cost item added successfully!!", "costItem");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items, "costItem");
                } catch (CostManagerException e) {
                    view.showMessage((e.getMessage()), "costItem");
                }
            }
        });
    }

    @Override
    public void deleteCostItem(int id) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.deleteCostItem(id);
                    view.showMessage("Cost item deleted successfully", "report");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items, "report");
                } catch (CostManagerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getReport(String initDate, String endDate) {
        pool.submit(new Runnable() {
            CostItem[] items = null;
            @Override
            public void run() {
                try {
                    items = model.getCostReport(initDate, endDate);
                    view.showMessage("Get display report successfully!!", "report");
                    view.showItems(items, "reports");
                } catch (CostManagerException e) {
                    view.showMessage((e.getMessage()), "report");
                }
            }
        });
    }

    @Override
    public void addNewCategory(Category category) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    model.addNewCategory(category);
                    List<Category> categories = model.getCategoryList();
                    view.showCategories(categories);
                    view.showMessage("Added new category successfully!!", "costItem");
                } catch (CostManagerException e) {
                    view.showMessage((e.getMessage()), "costItem");
                }
            }
        });
    }

    @Override
    public void getCategoryList() {
        pool.submit(new Runnable() {
            List<Category> categories = null;
            @Override
            public void run() {
                try {
                    categories = model.getCategoryList();
                    view.showCategories(categories);
                } catch (CostManagerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getPieChart(String initDate, String endDate) {
        pool.submit(new Runnable() {
            JFreeChart chart = null;
            @Override
            public void run() {
                try {
                    chart = model.getPieChart(initDate, endDate);
                    view.showMessage("Pie chart report successfully!!", "report");
                    view.showPieChart(chart);
                } catch (CostManagerException e) {
                    view.showMessage((e.getMessage()), "report");
                }
            }
        });
    }
}
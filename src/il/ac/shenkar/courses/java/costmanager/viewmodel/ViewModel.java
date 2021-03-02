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
                    view.showMessage("Cost item added successfully!!");
                    CostItem[] items = model.getCostItems();
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage((e.getMessage()));
                }
            }
        });
    }

    @Override
    public void getReport(String initDate, String endDate) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    CostItem[] items = model.getCostReport(initDate, endDate);
                    view.showMessage("Get display report successfully!!");
                    view.showItems(items);
                } catch (CostManagerException e) {
                    view.showMessage((e.getMessage()));
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
                    getCategoryList();
//                    List<String> categories = model.getCategoryList();
                    view.showMessage("Added new category successfully!!");
                } catch (CostManagerException e) {
                    view.showMessage((e.getMessage()));
                }
            }
        });
    }

    @Override
    public void getCategoryList() {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                List<Category> categories = null;
                try {
                    categories = model.getCategoryList();
                } catch (CostManagerException e) {
                    e.printStackTrace();
                }
                view.showCategories(categories);
            }
        });
    }


    @Override
    public JFreeChart getPieChart(String initDate, String endDate) {
        JFreeChart chart = null;
        try {
            chart = model.getPieChart(initDate, endDate);
            view.showMessage("Pie chart report successfully!!");
        } catch (CostManagerException e) {
            view.showMessage((e.getMessage()));
        } finally {
            return chart;
        }
    }

}


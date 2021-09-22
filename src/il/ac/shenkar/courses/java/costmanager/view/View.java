// Niv Swisa 307929257
// Martin Mazas 329834857
package il.ac.shenkar.courses.java.costmanager.view;

import il.ac.shenkar.courses.java.costmanager.model.Category;
import il.ac.shenkar.courses.java.costmanager.model.CostItem;
import il.ac.shenkar.courses.java.costmanager.model.CostManagerException;
import il.ac.shenkar.courses.java.costmanager.model.Currency;
import il.ac.shenkar.courses.java.costmanager.viewmodel.IViewModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import static java.lang.Integer.parseInt;

/**
 * @class View - View class that implements the IView interface.
 * @members
 * vm - connection to ViewModel interface
 * ui - View UI.
 * items - Array of expenses in database
 */
public class View implements IView {

    private IViewModel vm;
    private ApplicationUI ui;

    @Override
    public void showMessage(String text, String area) {
        ui.showMessage(text, area);
    }

    @Override
    public void showItems(CostItem[] vec, String area) {
        ui.showItems(vec, area);
    }

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void showCategories(List<Category> categories) {
        ui.showCategories(categories);
    }

    @Override
    public void showPieChart(JFreeChart chart) {
        ui.showPieChart(chart);
    }

    /**
     * This is a constructor for initialize and display the UI
     */
    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.init();
            }
        });
    }

    /**
     * @class ApplicationUI- Class for the ApplicationUI. There are three different frames. Main, Cost item and Reports.
     */
    public class ApplicationUI
    {
        /**
         * Main frame
         */
        private JFrame initialFrame;
        private JPanel initialFramePanelBottom, initialFramePanelMain;
        private JButton btGoCostItem, btGoReports;
        private JLabel lbWelcome;

        /**
         * Cost item frame
         */
        private JFrame costItemFrame;
        private JPanel costItemPanelTop, costItemPanelBottom, costItemPanelMain, costItemPanelMessage;
        private JTextField tfItemSum, tfItemCurrency,tfItemDescription,tfMessage, tfNewCategory;
        private JButton btAddCostItem, btNewCategory, backButtonCost;
        private JScrollPane scrollPane;
        private JComboBox categoryBox;
        private JTextArea textArea;
        private JLabel lbItemSum,lbItemCurrency,lbItemDescription,lbMessage,lbCategory,lbDate;
        private JDatePickerImpl datePicker;

        /**
         * Reports frame
         */
        private JFrame reportsFrame;
        private JPanel reportsPanelTop, reportsPanelBottom, reportsPanelMain, reportsPanelMessage;
        private JTextField tfReportsMessage, tfDeleteCost;
        private JButton backButtonReport, btGetReport, deleteButton;
        private JScrollPane scrollPaneReport;
        private JComboBox reportsBox;
        private JTextArea reportsTextArea;
        private JLabel lbDateInit, lbDateEnd, lbReportsMessage, lbReport, lbDelete;
        private JDatePickerImpl initDatePicker, endDatePicker;
        private ChartPanel chartPanel;

        // List of categories
        private List<Category> categories = new ArrayList<>();

        public ApplicationUI() {
            //Initialize each element of the UI

            /**
             * Initial frame
             */
            //creating the window
            initialFrame = new JFrame("CostManager");

            /**
             * Initial frame panels
             */
            initialFramePanelMain = new JPanel();
            initialFramePanelMain.setBackground(Color.WHITE);
            initialFramePanelBottom = new JPanel();
            initialFramePanelBottom.setBackground(Color.GRAY);
            btGoCostItem = new JButton("Add cost item");
            btGoReports = new JButton("Get reports");
            lbWelcome = new JLabel("Welcome to the Cost Manager application",SwingConstants.CENTER);
            lbWelcome.setFont(new Font("Verdana", Font.PLAIN, 20));


            /**
             * costItem frame
             */
            // creating the window
            costItemFrame = new JFrame("CostManager");

            /**
             * costItem frame panels
             */
            costItemPanelMain = new JPanel();
            costItemPanelBottom = new JPanel();
            costItemPanelTop = new JPanel();
            costItemPanelMessage = new JPanel();

            //creating the costItem UI components
            tfItemSum = new JTextField(8);
            tfItemCurrency = new JTextField(8);
            tfItemDescription = new JTextField(10);
            btAddCostItem = new JButton("Add Cost Item");
            textArea = new JTextArea();
            scrollPane = new JScrollPane(textArea);
            lbItemCurrency = new JLabel("Item Currency:");
            lbItemDescription = new JLabel("Item Description:");
            lbItemSum = new JLabel("Item Sum:");
            lbCategory = new JLabel("Item Category:");
            lbDate = new JLabel("Select the date:");
            lbMessage = new JLabel("Message: ");
            tfMessage = new JTextField(30);
            btNewCategory = new JButton("Add a new Category");
            tfNewCategory = new JTextField(25);
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());


            /**
             * Reports frame
             */
            //creating the window
            reportsFrame = new JFrame("CostManager");
            reportsPanelTop = new JPanel();
            reportsPanelBottom = new JPanel();
            reportsPanelBottom.setBackground(Color.WHITE);
            reportsPanelMain = new JPanel();
            reportsPanelMessage = new JPanel();
            reportsPanelMessage.setBackground(Color.GREEN);
            tfReportsMessage = new JTextField(30);
            tfDeleteCost = new JTextField(30);
            String[] reportType = {"Reports", "Pie Chart"};
            reportsBox = new JComboBox(reportType);
            reportsTextArea = new JTextArea();
            scrollPaneReport = new JScrollPane(reportsTextArea);
            lbDateInit = new JLabel("Select start date:");
            lbDateEnd = new JLabel("Select end date:");
            lbReportsMessage = new JLabel("Message:");
            lbReport = new JLabel("Choose the report type:");
            lbDelete = new JLabel("Delete by id");
            UtilDateModel initialDateReportModel = new UtilDateModel();
            UtilDateModel endDateReportModel = new UtilDateModel();
            JDatePanelImpl initDatePanel = new JDatePanelImpl(initialDateReportModel, p);
            JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateReportModel, p);
            initDatePicker = new JDatePickerImpl(initDatePanel, new DateLabelFormatter());
            endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
            btGetReport = new JButton("Get");
            backButtonCost = new JButton("Back");
            backButtonReport = new JButton("Back");
            deleteButton = new JButton("Delete");
        }

        /**
         * Initialize the view Layout
         */
        public void init() {
            // Ask for categories list and wait for the categories response
            vm.getCategoryList();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Adding components to the bottom panel
            initialFramePanelBottom.add(btGoCostItem);
            initialFramePanelBottom.add(btGoReports);

            //setting BorderLayout as the LayoutManager for panelMain
            initialFramePanelMain.setLayout(new BorderLayout());
            initialFramePanelMain.add(lbWelcome);

            //setting the window layout manager
            initialFrame.setLayout(new BorderLayout());

            //adding the main panel to the window
            initialFrame.add(initialFramePanelMain, BorderLayout.CENTER);

            //adding the message panel to the window
            initialFrame.add(initialFramePanelBottom, BorderLayout.SOUTH);

            //handling window closing
            initialFrame.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when a window is in the process of being closed.
                 * The close operation can be overridden at this point.
                 *
                 * @param e
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            //displaying the window
            initialFrame.setSize(600, 300);
            initialFrame.setVisible(true);

            // Initialize the reports and costItem frames
            getReports();
            costItem();

            // Action buttons to go to next page
            btGoCostItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialFrame.setVisible(false);
                    costItemFrame.setVisible(true);
                }
            });
            btGoReports.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialFrame.setVisible(false);
                    reportsFrame.setVisible(true);
                }
            });
        }

        /**
         * Calling the costItem frame
         */
        public void costItem() {
            //adding the components to the top panel
            costItemPanelTop.add(lbItemSum);
            costItemPanelTop.add(tfItemSum);
            costItemPanelTop.add(lbCategory);
            costItemPanelTop.add(categoryBox);
            costItemPanelTop.add(lbItemDescription);
            costItemPanelTop.add(tfItemDescription);
            costItemPanelTop.add(lbItemCurrency);
            costItemPanelTop.add(tfItemCurrency);
            costItemPanelTop.add(lbDate);
            costItemPanelTop.add(datePicker);
            costItemPanelTop.add(btAddCostItem);

            //setting BorderLayout as the LayoutManager for panelMain
            costItemPanelMain.setLayout(new BorderLayout());

            //setting GridLayout 1x1 as the LayoutManager for panelBottom
            costItemPanelBottom.setLayout(new GridLayout(1, 1));

            //adding the components to the bottom panel
            costItemPanelBottom.add(scrollPane);

            //adding the components to the messages panel
            costItemPanelMessage.add(backButtonCost);
            costItemPanelMessage.add(lbMessage);
            costItemPanelMessage.add(tfMessage);
            costItemPanelMessage.add(tfNewCategory);
            costItemPanelMessage.add(btNewCategory);

            //setting a different color for the panel message
            costItemPanelMessage.setBackground(Color.GREEN);

            //setting the window layout manager
            costItemFrame.setLayout(new BorderLayout());

            //adding the two panels to the main panel
            costItemPanelMain.add(costItemPanelBottom, BorderLayout.CENTER);

            //adding the main panel to the window
            costItemFrame.add(costItemPanelMain, BorderLayout.CENTER);

            //adding top panel to the window
            costItemFrame.add(costItemPanelTop, BorderLayout.NORTH);

            //adding the message panel to the window
            costItemFrame.add(costItemPanelMessage, BorderLayout.SOUTH);

            //handling window closing
            costItemFrame.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when a window is in the process of being closed.
                 * The close operation can be overridden at this point.
                 *
                 * @param e
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            //handling cost item adding button click, manipulate the input data and sending to the model through view model
            btAddCostItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        String description = tfItemDescription.getText();
                        if(description==null || description.length()==0) {
                            throw new CostManagerException("description cannot be empty");
                        }
                        double sum = Double.parseDouble(tfItemSum.getText());
                        String currencyStr = tfItemCurrency.getText();
                        Currency currency = switch (currencyStr) {
                            case "EURO" -> Currency.EURO;
                            case "ILS" -> Currency.ILS;
                            case "GBP" -> Currency.GBP;
                            default -> Currency.USD;
                        };
                        String cat = Objects.requireNonNull(categoryBox.getSelectedItem()).toString();
                        int id=0;
                        for (Category category : categories) {
                            if (cat.equals(category.getName())) {
                                id = category.getId();
                            }
                        }
                        Category finalCategory = new Category(id,cat);
                        Date selectedDate = (Date) datePicker.getModel().getValue();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String reportDate = df.format(selectedDate);

                        CostItem item = new CostItem(0,finalCategory,sum,currency ,description,reportDate );
                        vm.addCostItem(item);

                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered sum... "+ex.getMessage(), "costItem");
                    } catch(CostManagerException ex){
                        View.this.showMessage("problem with entered data... problem with description... "
                                +ex.getMessage(), "costItem");
                    }
                }
            });

            // adding a new Category to the database through the view model
            btNewCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cat = tfNewCategory.getText();
                    Category newCategory = new Category(0,cat);
                    vm.addNewCategory(newCategory);
                    tfNewCategory.setText("");
                }
            });

            // Back button, goes to main frame
            backButtonCost.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textArea.setText("");
                    tfMessage.setText("");
                    tfItemSum.setText("");
                    tfItemDescription.setText("");
                    tfItemCurrency.setText("");
                    datePicker.getJFormattedTextField().setText("");
                    costItemFrame.setVisible(false);
                    initialFrame.setVisible(true);
                }
            });

            //displaying the window
            costItemFrame.setSize(1500, 600);
        }

        public void getReports() {
            /**
             * Calling the costItem frame
             */
            //adding the components to the top panel
            reportsPanelTop.add(lbReport);
            reportsPanelTop.add(reportsBox);
            reportsPanelTop.add(lbDateInit);
            reportsPanelTop.add(initDatePicker);
            reportsPanelTop.add(lbDateEnd);
            reportsPanelTop.add(endDatePicker);
            reportsPanelTop.add(btGetReport);

            //set BorderLayout to reportsPanelMain
            reportsPanelMain.setLayout(new BorderLayout());

            // add scrollPaneReport to the reportsPanelBottom
            reportsPanelBottom.setLayout(new GridLayout(1,1));
            reportsPanelBottom.add(scrollPaneReport);

            // Prepare the reportsPanelMessage
            reportsPanelMessage.add(backButtonReport);
            reportsPanelMessage.add(lbReportsMessage);
            reportsPanelMessage.add(tfReportsMessage);
            reportsPanelMessage.add(lbDelete);
            reportsPanelMessage.add(tfDeleteCost);
            reportsPanelMessage.add(deleteButton);

            //set BorderLayout to reportsFrame
            reportsFrame.setLayout(new BorderLayout());

            //add reportsPanelBottom to reportsPanelMain
            reportsPanelMain.add(reportsPanelBottom, BorderLayout.CENTER);

            reportsFrame.add(reportsPanelMain, BorderLayout.CENTER);

            reportsFrame.add(reportsPanelTop, BorderLayout.NORTH);

            reportsFrame.add(reportsPanelMessage, BorderLayout.SOUTH);

            reportsFrame.addWindowListener(new WindowAdapter() {
                /**
                 * Invoked when a window is in the process of being closed.
                 * The close operation can be overridden at this point.
                 *
                 * @param e
                 */
                @Override
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

            //handling report button click, manipulate the input data and sending the report request to the model
            // through view model and showing to the user
            btGetReport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String reportType = reportsBox.getSelectedItem().toString();

                    Date initialSelectedDate = (Date) initDatePicker.getModel().getValue();
                    Date endSelectedDate = (Date) endDatePicker.getModel().getValue();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String initialReportDate = df.format(initialSelectedDate);
                    String endReportDate = df.format(endSelectedDate);

                    // Check which report the user wants and display it
                    if(reportType.equals("Reports")) {
                        vm.getReport(initialReportDate, endReportDate);
                    }
                    else if(reportType.equals("Pie Chart")) {
                        vm.getPieChart(initialReportDate, endReportDate);
                    }
                }
            });

            // Back button, goes to main frame
            backButtonReport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reportsTextArea.setText("");
                    tfReportsMessage.setText("");
                    initDatePicker.getJFormattedTextField().setText("");
                    endDatePicker.getJFormattedTextField().setText("");
                    if(chartPanel != null) {
                        reportsPanelBottom.remove(chartPanel);
                    }
                    reportsFrame.setVisible(false);
                    initialFrame.setVisible(true);
                }
            });

            // The user choose the item to delete and send request to database through the view model
            deleteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int deleteId = parseInt(tfDeleteCost.getText());
                    vm.deleteCostItem(deleteId);
                    tfDeleteCost.setText("");
                }
            });

            reportsFrame.setSize(1200, 600);
        }

        /**
         * The function shows the message to the user
         * @param text- The message that the user will see after the activity
         * @param area- Panel to add the message(costItem or reports)
         */
        public void showMessage(String text, String area) {
            if (SwingUtilities.isEventDispatchThread()) {
                if(area.equals("costItem")) {
                    tfMessage.setText(text);
                }
                else {
                    tfReportsMessage.setText(text);
                }
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if(area.equals("costItem")) {
                            tfMessage.setText(text);
                        }
                        else {
                            tfReportsMessage.setText(text);
                        }
                    }
                });
            }
        }

        /**
         * The function show the items that user request from database in a string representation
         * @param items- The items that the user will see
         * @param area- Panel to add the message(costItem or reports)
         */
        public void showItems(CostItem[] items, String area) {
            StringBuilder sb = new StringBuilder();
            for(CostItem item : items) {
                sb.append(item.toString());
                sb.append("\n");
            }
            String text = sb.toString();

            if (SwingUtilities.isEventDispatchThread()) {
                if(area.equals("costItem")) {
                    textArea.setText(text);
                }
                else {
                    reportsTextArea.setText("");
                    reportsTextArea.setText(text);
                }
            } else {
                SwingUtilities.invokeLater(() -> {
                    if(area.equals("costItem")) {
                        textArea.setText(text);
                    }
                    else {
                        reportsTextArea.setText("");
                        reportsTextArea.setText(text);
                    }
                });
            }
        }

        /**
         * The function request from database through view model all the categories to show them to the user
         * @param categories- list of the categories in database
         */
        public void showCategories(List<Category> categories) {
            this.categories = categories;
            List<String> categoriesName = new ArrayList<>();
            for (Category category : categories) {
                categoriesName.add(category.getName());
            }

            DefaultComboBoxModel modComboBox = new DefaultComboBoxModel(categoriesName.toArray());

            if(categoryBox != null) {
                categoryBox.setModel( modComboBox );
            }
            else {
                categoryBox = new JComboBox(modComboBox);
            }
        }

        /**
         * The function shows to the user the user request report in a pie chart
         * @param chart- user request report in pie chart form
         */
        public void showPieChart(JFreeChart chart) {
            if(chartPanel != null) {
                reportsPanelBottom.remove(chartPanel);
            }
            chartPanel = new ChartPanel(chart);
            if (SwingUtilities.isEventDispatchThread()) {
                SwingUtilities.updateComponentTreeUI(reportsPanelBottom);
                reportsPanelBottom.add(chartPanel, BorderLayout.CENTER);
            } else {
                SwingUtilities.invokeLater(() -> {
                    SwingUtilities.updateComponentTreeUI(reportsPanelBottom);
                    reportsPanelBottom.add(chartPanel, BorderLayout.CENTER);
                });
            }
        }
    }
}
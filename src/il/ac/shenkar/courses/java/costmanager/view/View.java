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

public class View implements IView {

    private IViewModel vm;
    private ApplicationUI ui;

    @Override
    public void showMessage(String text) {
        ui.showMessage(text);
    }

    @Override
    public void showItems(CostItem[] vec) {
        ui.showItems(vec);
    }

    @Override
    public void setViewModel(IViewModel vm) {
        this.vm = vm;
    }

    @Override
    public void showCategories(List<Category> categories) {
        ui.showCategories(categories);
    }

    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.init();
            }
        });
    }

    public class ApplicationUI
    {
        /**
         * Class for the ApplicationUI. There are three different frames. Main, Cost item and Reports.
         */

        //initial frame
        private JFrame initialFrame;
        private JPanel initialFramePanelTop, initialFramePanelBottom, initialFramePanelMain;
        private JButton btGoCostItem, btGoReports;
        private JScrollPane initialFrameScrollPane;
        private JLabel lbWelcome;

        //cost item frame
        private JFrame costItemFrame;
        private JPanel costItemPanelTop, costItemPanelBottom, costItemPanelMain, costItemPanelMessage;
        private JTextField tfItemSum, tfItemCurrency,tfItemDescription,tfMessage, tfNewCategory;
        private JButton btAddCostItem, btNewCategory, backButton;
        private JScrollPane scrollPane;
        private JComboBox categoryBox;
        private JTextArea textArea;
        private JLabel lbItemSum,lbItemCurrency,lbItemDescription,lbMessage,lbCategory,lbDate;
        private JDatePickerImpl datePicker;
        private DefaultComboBoxModel mod;

        //reports frame
        private JFrame reportsFrame;
        private JPanel reportsPanelTop, reportsPanelBottom, reportsPanelMain, reportsPanelMessage;
        private JTextField tfDateInit, tfDateEnd, tfReportsMessage;
        private JButton btReports, btPieChart;
        private JComboBox reportsBox;
        private JTextArea reportsTextArea;
        private JLabel lbDateInit, lbDateEnd, lbReportsMessage, lbReport;
        private JDatePickerImpl initDatePicker, endDatePicker;
        private JButton btGetReport;

        private List<Category> categories = new ArrayList<>();

        public ApplicationUI() {
            /**
             * Initialize each element of the UI
             */

            /**
             * Initial frame
             */
            //creating the window
            initialFrame = new JFrame("CostManager");

            //creating the panels
            initialFramePanelMain = new JPanel();
            initialFramePanelMain.setBackground(Color.WHITE);
            initialFramePanelBottom = new JPanel();
            initialFramePanelBottom.setBackground(Color.GRAY);
            btGoCostItem = new JButton("Add cost item");
            btGoReports = new JButton("Get reports");
            lbWelcome = new JLabel("Welcome to the Cost Manager application",SwingConstants.CENTER);
            lbWelcome.setFont(new Font("Verdana", Font.PLAIN, 20));


            /**
             * Categories frame
             */
            // creating the window
            costItemFrame = new JFrame("CostManager");

            //creating panels
            costItemPanelMain = new JPanel();
            costItemPanelBottom = new JPanel();
            costItemPanelTop = new JPanel();
            costItemPanelMessage = new JPanel();

            //creating the main ui components
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
            //creating the messages ui components
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
            String[] reportType = {"Reports", "Pie Chart"};
            reportsBox = new JComboBox(reportType);
            reportsTextArea = new JTextArea();
            lbDateInit = new JLabel("Select start date:");
            lbDateEnd = new JLabel("Select end date:");
            lbReportsMessage = new JLabel("Message:");
            lbReport = new JLabel("Choose the report type:");
            UtilDateModel initialDateReportModel = new UtilDateModel();
            UtilDateModel endDateReportModel = new UtilDateModel();
            JDatePanelImpl initDatePanel = new JDatePanelImpl(initialDateReportModel, p);
            JDatePanelImpl endDatePanel = new JDatePanelImpl(endDateReportModel, p);
            initDatePicker = new JDatePickerImpl(initDatePanel, new DateLabelFormatter());
            endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
            btGetReport = new JButton("Get");
            backButton = new JButton("Back");
        }

        public void init() {
            /**
             * Initialize the view Layout
             */

            vm.getCategoryList();
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

            // Action buttons to go to next page
            btGoCostItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialFrame.setVisible(false);
                    costItem();
                }
            });

            btGoReports.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialFrame.setVisible(false);
                    getReports();
                }
            });
        }

        public void costItem() {
            /**
             * Calling the costItem frame
             */

            //adding the components to the top panel
            costItemPanelTop.add(lbItemSum);
            costItemPanelTop.add(tfItemSum);
            costItemPanelTop.add(lbCategory);
            costItemPanelTop.add(categoryBox);  //Need to fix
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
            costItemPanelMessage.add(backButton);
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

            //handling cost item adding button click
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

                        CostItem item = new CostItem(44,finalCategory,sum,currency ,description,reportDate );//needs to change id to be dynamic
                        vm.addCostItem(item);

                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered sum... "+ex.getMessage());
                    } catch(CostManagerException ex){
                        View.this.showMessage("problem with entered data... problem with description... "+ex.getMessage());
                    }
                }
            });

            btNewCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cat = tfNewCategory.getText();
                    Category newCategory = new Category(1,cat); //Need changes
                    vm.addNewCategory(newCategory);
//                    mod.addElement(vm.getCategoryList());

//                    categoryBox.setModel(mod);
//                    mod.addElement(cat);
//                    categoryBox.setModel(mod);


//                    categoryBox = null;
//                    categories = vm.getCategoryList();
//                    categoryBox = new JComboBox(categories.toArray());


//                    categoryBox.add(vm.getCategoryList().toArray());
//                    mod.addElement(cat);
//                    categoryBox.setModel(mod);
                }
            });

            backButton.addActionListener(new ActionListener() {
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
            costItemFrame.setVisible(true);
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

            //
            reportsPanelMain.setLayout(new BorderLayout());

            //
            reportsPanelBottom.setLayout(new GridLayout(1,1));
            reportsPanelBottom.add(scrollPane);

            //
            reportsPanelMessage.add(backButton);
            reportsPanelMessage.add(lbReportsMessage);
            reportsPanelMessage.add(tfMessage);

            //
            reportsFrame.setLayout(new BorderLayout());

            //
            reportsPanelMain.add(reportsPanelBottom, BorderLayout.CENTER);

            //
            reportsFrame.add(reportsPanelMain, BorderLayout.CENTER);

            //
            reportsFrame.add(reportsPanelTop, BorderLayout.NORTH);

            //
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

            btGetReport.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String reportType = reportsBox.getSelectedItem().toString();

                    Date initialSelectedDate = (Date) initDatePicker.getModel().getValue();
                    Date endSelectedDate = (Date) endDatePicker.getModel().getValue();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String initialReportDate = df.format(initialSelectedDate);
                    String endReportDate = df.format(endSelectedDate);

                    if(reportType.equals("Reports")) {
                        vm.getReport(initialReportDate, endReportDate);
                    }
                    else if(reportType.equals("Pie Chart")) {
                        JFreeChart chart= vm.getPieChart(initialReportDate, endReportDate);
                        ChartPanel chartPanel = new ChartPanel(chart);
                        reportsPanelBottom.add(chartPanel, BorderLayout.CENTER);
                    }
                }
            });

            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textArea.setText("");
                    tfMessage.setText("");
                    reportsFrame.setVisible(false);
                    initialFrame.setVisible(true);
                }
            });

            reportsFrame.setSize(1200, 600);
            reportsFrame.setVisible(true);
        }

        public void showMessage(String text) {
            if (SwingUtilities.isEventDispatchThread()) {
                tfMessage.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        tfMessage.setText(text);
                    }
                });

            }
        }

        public void showItems(CostItem[] items) {
            StringBuilder sb = new StringBuilder();
            for(CostItem item : items) {
                sb.append(item.toString());
                sb.append("\n");
            }
            String text = sb.toString();

            if (SwingUtilities.isEventDispatchThread()) {
                textArea.setText(text);
            } else {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textArea.setText(text);
                    }
                });

            }
        }

        public void showCategories(List<Category> categories) {
            this.categories = categories;
            List<String> categoriesName = new ArrayList<>();
            for (Category category : categories) {
                categoriesName.add(category.getName());
            }
            DefaultComboBoxModel modComboBox = new DefaultComboBoxModel(categoriesName.toArray());
            mod = new DefaultComboBoxModel(categoriesName.toArray());
            categoryBox = new JComboBox(modComboBox);
        }
    }
}
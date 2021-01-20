package costManager.view;

import costManager.model.Category;
import costManager.model.CostItem;
import costManager.model.CostManagerException;
import costManager.model.Currency;
import costManager.viewmodel.IViewModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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

    public View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View.this.ui = new ApplicationUI();
                View.this.ui.init();
            }
        });
    }

    public class ApplicationUI //
    {
        //initial frame
        private JFrame initialFrame;
        private JPanel initialFramePanelTop,initialFramepanelBottom, initialFramePanelMain;
        private JButton btGoCostItem, btGoReports;
        private JScrollPane initialFrameScrollPane;
//        private JTextArea textArea;
        private JLabel lbWelcome;


        //cost item frame
        private JFrame frame;
        private JPanel panelTop,panelBottom,panelMain,panelMessage;
        private JTextField tfItemSum, tfItemCurrency,tfItemDescription,tfMessage;
        private JButton btAddCostItem;
        private JScrollPane scrollPane;
        private JComboBox categoryBox;
        private JTextArea textArea;
        private JLabel lbItemSum,lbItemCurrency,lbItemDescription,lbMessage,lbCategory,lbDate;
        private JDatePickerImpl datePicker;



        public ApplicationUI() {
            //initial
            //creating the window
            initialFrame = new JFrame("CostManager");
            //creating the panels
            initialFramePanelMain = new JPanel();
            initialFramePanelMain.setBackground(Color.WHITE);
            initialFramepanelBottom = new JPanel();
            initialFramepanelBottom.setBackground(Color.GRAY);
            btGoCostItem = new JButton("Add cost item");
            btGoReports = new JButton("Get reports");
//            textArea = new JTextArea();
//            initialFrameScrollPane = new JScrollPane(textArea);
            lbWelcome = new JLabel("Welcome to the Cost Manager application",SwingConstants.CENTER);
            lbWelcome.setFont(new Font("Verdana", Font.PLAIN, 20));



            //cost item
            List<String> categories = vm.getCategoryList();
            categoryBox = new JComboBox(categories.toArray());
            //creating the window
            frame = new JFrame("CostManager");
            //creating the four panels
            panelMain = new JPanel();
            panelBottom = new JPanel();
            panelTop = new JPanel();
            panelMessage = new JPanel();
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
            UtilDateModel model = new UtilDateModel();
            Properties p = new Properties();
            p.put("text.today", "Today");
            p.put("text.month", "Month");
            p.put("text.year", "Year");
            JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
            datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        }

        public void init() {
            // Adding components to the bottom panel
            initialFramepanelBottom.add(btGoCostItem);
            initialFramepanelBottom.add(btGoReports);

            //setting BorderLayout as the LayoutManager for panelMain
            initialFramePanelMain.setLayout(new BorderLayout());
            initialFramePanelMain.add(lbWelcome);

            //setting the window layout manager
            initialFrame.setLayout(new BorderLayout());


            //adding the main panel to the window
            initialFrame.add(initialFramePanelMain, BorderLayout.CENTER);


            //adding the message panel to the window
            initialFrame.add(initialFramepanelBottom, BorderLayout.SOUTH);

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

            btGoCostItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    initialFrame.setVisible(false);
                    costItem();
                }
            });
        }

        public void costItem() {
            //adding the components to the top panel
            panelTop.add(lbItemSum);
            panelTop.add(tfItemSum);
            panelTop.add(lbCategory);
            panelTop.add(categoryBox);
            panelTop.add(lbItemDescription);
            panelTop.add(tfItemDescription);
            panelTop.add(lbItemCurrency);
            panelTop.add(tfItemCurrency);
            panelTop.add(lbDate);
            panelTop.add(datePicker);
            panelTop.add(btAddCostItem);
            //setting BorderLayout as the LayoutManager for panelMain
            panelMain.setLayout(new BorderLayout());

            //setting GridLayout 1x1 as the LayoutManager for panelBottom
            panelBottom.setLayout(new GridLayout(1, 1));

            //adding the components to the bottom panel
            panelBottom.add(scrollPane);

            //adding the components to the messages panel
            panelMessage.add(lbMessage);
            panelMessage.add(tfMessage);

            //setting a different color for the panel message
            panelMessage.setBackground(Color.GREEN);

            //setting the window layout manager
            frame.setLayout(new BorderLayout());

            //adding the two panels to the main panel
            //panelMain.add(panelTop);
            panelMain.add(panelBottom, BorderLayout.CENTER);

            //adding the main panel to the window
            frame.add(panelMain, BorderLayout.CENTER);

            //adding top panel to the window
            frame.add(panelTop, BorderLayout.NORTH);

            //adding the message panel to the window
            frame.add(panelMessage, BorderLayout.SOUTH);

            //handling window closing
            frame.addWindowListener(new WindowAdapter() {
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
                        String cat = categoryBox.getSelectedItem().toString();
                        Category finallCategory = new Category(1,cat); // needs to change id to be dynamic
                        Date selectedDate = (Date) datePicker.getModel().getValue();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String reportDate = df.format(selectedDate);

                        CostItem item = new CostItem(44,finallCategory,sum,currency ,description,reportDate );//needs to change id to be dynamic
                        vm.addCostItem(item);


                    } catch (NumberFormatException ex) {
                        View.this.showMessage("problem with entered sum... "+ex.getMessage());
                    } catch(CostManagerException ex){
                        View.this.showMessage("problem with entered data... problem with description... "+ex.getMessage());
                    }
                }
            });

            //displaying the window
            frame.setSize(1200, 600);
            frame.setVisible(true);
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

    }
}



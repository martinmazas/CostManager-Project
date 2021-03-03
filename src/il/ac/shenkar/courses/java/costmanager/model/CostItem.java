package il.ac.shenkar.courses.java.costmanager.model;


public class CostItem {
    /**
     * CostItem class , used to hold information about the expense
     * and got getters and setters for all members of the class.
     */
    private int id;
    private Category category;
    private double amount;
    private Currency currency;
    private String description;
    private String date;

    public CostItem(int id,Category category, double amount, Currency currency, String description, String date) {
        /**
         * Constructor for the object, use the methods to assign the values inside the members.
         */
        setId(id);
        setCategory(category);
        setAmount(amount);
        setCurrency(currency);
        setDescription(description);
        setDate(date);
    }

    /**
     * Setters and Getter for the functionality of the object Cost Item
     */
    public void setId(int id) {this.id = id; }

    public int getId() { return this.id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getAmount() { return amount; }

    public void setAmount(double amount) { this.amount = amount; }

    public Currency getCurrency() { return currency; }

    public void setCurrency(Currency currency) { this.currency = currency; }

    public Category getCategory() {return category;}

    public void setCategory(Category category) { this.category = category; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    @Override
    public String toString() {
        return  id + "\t" +
                date + "\t" +
                currency + "\t" +
                amount + "\t" +
                category.getName() + "\t" +
                description;

    }
}

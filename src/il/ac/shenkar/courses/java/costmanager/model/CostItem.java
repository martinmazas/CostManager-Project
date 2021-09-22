package il.ac.shenkar.courses.java.costmanager.model;

/**
 * @class CostItem - Class for the user costs, used to hold information about the expense.
 * @members
 * id - id representation for the cost in the DB.
 * category - category of the cost.
 * amount - amount of the cost.
 * currency - currency of the cost.
 * description - user cost description.
 * date - date of the cost.
 * @methods
 * setId() - set the id of the costItem to a new one.
 * getId() - return the id of the costItem.
 * getDescription()- return the description of the costItem
 * setDescription()- set the description of the new costItem
 * getAmount()- return the amount of the costItem
 * setAmount()- set the amount of the new costItem
 * getCurrency()- return the currency of the costItem
 * setCurrency()- set the currency of the new costItem
 * getCategory()- return the category of the costItem
 * setCategory()- set the category of the new costItem
 * getDate()- return the date of the costItem
 * setDate()- set the date of the new costItem
 * toString() - return string representation of the class.
 */
public class CostItem {

    private int id;
    private Category category;
    private double amount;
    private Currency currency;
    private String description;
    private String date;

    /**
     * Constructor for the object, use the methods to assign the values inside the members.
     * @param id- id of the cost in DB
     * @param category- category of the cost
     * @param amount- amount of the cost
     * @param currency- currency of the cost
     * @param description- description of the cost
     * @param date- date of the cost
     */
    public CostItem(int id,Category category, double amount, Currency currency, String description, String date) {
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

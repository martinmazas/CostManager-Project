// Niv Swisa 307929257
// Martin Mazas 329834857
package il.ac.shenkar.courses.java.costmanager.model;

/**
 * @class Category - Class for the categories of the expense.
 * @members id - id representation for the Category in the DB.
 * name - name of the Category.
 * @methods getId() - return the id of the category.
 * setId() - set the id of the category to a new one.
 * getName() - return the name of the category.
 * setName() - set the name of the category to new one.
 * toString() - return string representation of the class.
 */
public class Category {

    private int id;
    private String name;

    /**
     * Constructor of a new Category
     *
     * @param id-   id of the category in DB
     * @param name- name of the category
     */
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package il.ac.shenkar.courses.java.costmanager.model;

public class Demo {
    public static void main(String[] args) throws CostManagerException {
        DerbyDBModel model = new DerbyDBModel();
        try {
//            model.createConnection();
//            model.deleteTable("categories");
//            model.createTable("categories", "(id int generated always as identity PRIMARY KEY , name varchar(256), " +
//                    "CONSTRAINT UQ_Name UNIQUE(name))");
            model.createTable("inventory", "(id int generated always as identity PRIMARY KEY,categoryId int,amount double, " +
                    "currency varchar(250),description varchar(250),date DATE, FOREIGN KEY (categoryId) REFERENCES categories(id))");
//
//
//            model.deleteTable("inventory");
//            Category cat = new Category(5,"Super");
//            model.addNewCategory(cat);
//            List<Category> categoryList = model.getCategoryList();
//            for(int i = 0; i<categoryList.size(); i++) {
//                System.out.println(categoryList.get(i));
//            }
//            CostItem item = new CostItem(10, categoryList.get(1),45.5, Currency.EURO,"Salary","2020-03-01");
//            CostItem item1 = new CostItem(45, categoryList.get(1),21, Currency.GBP,"Salary","2020-03-20");
//            CostItem item2 = new CostItem(4, categoryList.get(0),45.5, Currency.ILS,"Pay","2020-04-12");
//            CostItem item3 = new CostItem(1, categoryList.get(1),12, Currency.USD,"Pay","2020-04-12");
//
//            model.addCostItem(item);
//            model.addCostItem(item1);
//            model.addCostItem(item2);
//            model.addCostItem(item3);
//            model.getCostReport("2020-01-01", "2020-03-31");
//
//            model.getPieChart("2020-01-01", "2021-12-31");
//            model.getCostReport("2020-01-01", "2021-01-01");
//            List<String> cat = model.getCategoryList();
//            System.out.println(cat);


        }catch (CostManagerException e) {
            throw new CostManagerException("cant", e);
        }
//        finally {
//            try {
//                model.stopConnection();
//            }catch (CostManagerException e){
//                throw new CostManagerException("Error closing connection");
//            }
//        }

    }
}

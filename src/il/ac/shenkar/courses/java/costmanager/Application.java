package il.ac.shenkar.courses.java.costmanager;

import il.ac.shenkar.courses.java.costmanager.model.DerbyDBModel;
import il.ac.shenkar.courses.java.costmanager.model.IModel;
import il.ac.shenkar.courses.java.costmanager.view.IView;
import il.ac.shenkar.courses.java.costmanager.view.View;
import il.ac.shenkar.courses.java.costmanager.viewmodel.IViewModel;
import il.ac.shenkar.courses.java.costmanager.viewmodel.ViewModel;

public class Application {
    public static void main(String args[]) {

        IModel model = new DerbyDBModel();
        IView view = new View();
        IViewModel vm = new ViewModel();

        //connecting components together
        view.setViewModel(vm);
        vm.setModel(model);
        vm.setView(view);
    }
}

package javers;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class UsersTable extends CustomComponent {

    private GridLayout form;
    private Table table;
    private HorizontalLayout tableControls;
    private HorizontalLayout formControls;


    public UsersTable() {
        VerticalLayout mainLayout = new VerticalLayout();
        setCompositionRoot(mainLayout);
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);

        mainLayout.addComponent(buildTableControls());
        mainLayout.addComponent(buildTable());
        mainLayout.addComponent(buildForm());
        mainLayout.addComponent(buildFormControls());
    }

    private Component buildTable() {
        table = new Table(null);
        table.setWidth("500px");
        table.setSelectable(true);
        table.setImmediate(true);
        return table;
    }

    private Component buildForm() {
        form = new GridLayout(2, 3);

        TextField id = new TextField("Id:");
        TextField login = new TextField("Login:");

        form.addComponent(id);
        form.addComponent(login);
        return form;
    }


    private Component buildTableControls() {
        tableControls = new HorizontalLayout();
        Button add = new Button("Add");
        Button delete = new Button("Delete");
        tableControls.addComponent(add);
        tableControls.addComponent(delete);
        return tableControls;
    }

    private Component buildFormControls() {
        formControls = new HorizontalLayout();
        Button save = new Button("Save");
        Button discard = new Button("Discard");
        formControls.addComponent(save);
        formControls.addComponent(discard);
        return formControls;
    }
}

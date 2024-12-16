package gr.uop;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


//Κλάση αναπαράστασης του διεπαφής.
public class Gui {
    private Button payButton = new Button("Πληρωμή");
    private Button deleteButton = new Button("Διαγραφή");
    private TableView tableView = new TableView();
    private VBox vbox = new VBox();
    private Label label = new Label("Hello, JavaFX Server");
    private Scene scene = new Scene(vbox, 1024, 768);
    private Stage stage;
    public Gui(Stage stage){
        this.stage = stage;
    }

    //Ετοιμάζει το gui και το εμφανίζει
    public void prepareGuiAndShow(){
        TableColumn<Customer,String> column1 = new TableColumn<>("Πινακίδα");
        column1.setCellValueFactory(new PropertyValueFactory<>("pinakida"));
        TableColumn<Customer,String> column2 = new TableColumn<>("Τελικό Ποσό");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Customer,String> column3 = new TableColumn<>("Ημερομηνία");
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Customer,String> column4 = new TableColumn<>("Υπηρεσίες");
        column4.setCellValueFactory(new PropertyValueFactory<>("services"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        

        
        payButton.setStyle("-fx-background-radius: 5em;-fx-background-color:green;");
        deleteButton.setStyle("-fx-background-radius: 5em;-fx-background-color:red");
        vbox.getChildren().addAll(tableView,payButton,deleteButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        payButton.setOnMouseClicked(new PayEvent(tableView));
        deleteButton.setOnMouseClicked(new DeleteEvent(tableView));
        stage.setScene(scene);
        stage.setScene(scene);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        setEvents();
        stage.show();
    } 

    //Θέτει τα events στα κουμπία της εφαρμογής.
    private void setEvents(){

        payButton.setOnMouseClicked(new PayEvent(tableView));
        deleteButton.setOnMouseClicked(new DeleteEvent(tableView));
        stage.setOnCloseRequest(event -> {
            
            if(tableView.getItems().size()>0){
                event.consume();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Υπάρχουν ακόμα καταχωρήσεις στον πίνακα!!!");
                alert.show();
            }
            else{
            System.exit(0);
            }
          });
    }
    //Προσθέτει μια εγγραφή στο tableview.
    public void setTableView(Customer a){
        tableView.getItems().add(a);
    }
}

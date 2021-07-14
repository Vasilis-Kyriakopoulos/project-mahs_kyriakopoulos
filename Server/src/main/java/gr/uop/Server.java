package gr.uop;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Server extends Application {
    Button payButton = new Button("Πληρωμή");
    Button deleteButton = new Button("Διαγραφή");
    TableView tableView = new TableView();
    @Override
    public void start(Stage stage) {
        
            
       
       
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
        

        VBox vbox = new VBox();
        payButton.setStyle("-fx-background-radius: 5em;-fx-background-color:green;");
        deleteButton.setStyle("-fx-background-radius: 5em;-fx-background-color:red");
        vbox.getChildren().addAll(tableView,payButton,deleteButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        payButton.setOnMouseClicked(new PayEvent(tableView));
        deleteButton.setOnMouseClicked(new DeleteEvent(tableView));
        
        var label = new Label("Hello, JavaFX Server");
        var scene = new Scene(vbox, 1024, 768);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        stage.show();


        Thread t = new Thread() {
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(6666);
                Socket connectionSocket = serverSocket.accept();
                Scanner fromClient = new Scanner(connectionSocket.getInputStream());
                PrintWriter toClient = new PrintWriter(connectionSocket.getOutputStream(), true);) 
                {
                    
                   while(true){
                       if(fromClient.hasNextLine()){
                       BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
                       String line = fromClient.nextLine();
                       String arr[] = line.split(";");
                       for(int i = 0 ; i< arr.length;i++){
                           if(i!=1){
                            bw.write(arr[i]+" ");
                           }
                        }
                        bw.write("\n");
                        String services = "";
                        for(int i = 3;i< arr.length;i++){
                            services +=arr[i]+" \n";
                        }
                        tableView.getItems().add(new Customer(arr[0],Integer.valueOf(arr[1]),arr[2],services));
                       bw.close();
                       }
                       
                    } 
                }
                catch (IOException e) {
                    System.out.println(e);
                }
            }
        };
    t.start();
    stage.setOnCloseRequest(event -> {
      System.exit(0);
    });
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}

package gr.uop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystemNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;



import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
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
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
       
        

        VBox vbox = new VBox();
        payButton.setStyle("-fx-background-radius: 5em;-fx-background-color:green;");
        deleteButton.setStyle("-fx-background-radius: 5em;-fx-background-color:red");
        vbox.getChildren().addAll(tableView,payButton,deleteButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        



        payButton.setOnMouseClicked(event -> {
            int i = tableView.getFocusModel().getFocusedCell().getRow();
            Customer c = (Customer)tableView.getItems().get(i);
            Alert  alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Επιβαίωση πληρώμης: " +c.getPrice()+" Ευρώ και κατάχωρηση στο βίβλιο πληρωμών για το όχημα με πινακίδα:" +c.getPinakida()+"\nΗμερομηνία άφιξης:"+c.getDate());
            Optional<ButtonType> result = alert.showAndWait();

            ButtonType button = result.orElse(ButtonType.CANCEL); 
            if(button ==ButtonType.OK){
                Date date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                try( FileReader reader = new FileReader("users.txt");
                    BufferedReader br = new BufferedReader(reader);
                    FileWriter writer = new FileWriter("users2.txt");
                    BufferedWriter wr = new BufferedWriter(writer);
                    ) {
                   String line;
                   while ((line = br.readLine()) != null) {
                    if (c.getPinakida().equals(line.split(" ")[0])) {
                        wr.write(c.getPinakida()+" "+c.getPrice()+" "+c.getDate()+" ,"+dateFormat.format(date)+"\n");
                    }
                    else{
                        wr.write(line+"\n");
                    }  
                }
                    br.close();
                    wr.close();
                    File realName = new File("users.txt");
                    realName.delete(); // remove the old file
                    new File("users2.txt").renameTo(realName); // Rename temp file
                    tableView.getItems().remove(i);
                } 
                catch (Exception e) {
                    
                }
                 
            }
            else if (button == ButtonType.CANCEL){

            }
        });
        
        
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
                PrintWriter toClient = new PrintWriter(connectionSocket.getOutputStream(), true);) {
                    
                   while(true){
                       if(fromClient.hasNextLine()){
                       BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true));
                       String line = fromClient.nextLine();
                       bw.write(line.split(" ")[0]+" "+ line.split(" ")[2]+" "+ line.split(" ")[3]+"\n");
                       tableView.getItems().add(new Customer(line.split(" ")[0],Integer.valueOf(line.split(" ")[1]),line.split(" ")[2]+" "+line.split(" ")[3]));
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

        
    }

    public static void main(String[] args) {
        launch(args);
    }

}

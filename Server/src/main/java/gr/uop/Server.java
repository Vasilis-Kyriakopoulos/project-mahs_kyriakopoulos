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
    static Gui gui ;
    @Override
    public void start(Stage stage) {
        
            
       gui =  new Gui(stage);
       gui.prepareGuiAndShow();
        


    Thread t = new Thread() {
            public void run() {
                getFromClient();
            }
        };
    t.start();
    stage.setOnCloseRequest(event -> {
      System.exit(0);
    });
        
    }

    public static void getFromClient(){
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

                        gui.setTableView(new Customer(arr[0],Integer.valueOf(arr[1]),arr[2],services));
                        bw.close();
                       }
                       
                    } 
                }
                catch (IOException e) {
                    System.out.println(e);
                }
    }
    public static void main(String[] args) {
        launch(args);
    }

}

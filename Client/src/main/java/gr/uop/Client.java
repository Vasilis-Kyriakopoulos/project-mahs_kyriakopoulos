package gr.uop;


import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Date;

/**
 * JavaFX App
 */
public class Client extends Application {
    
    static PrintWriter toServer;
    static Scanner fromServer;
    @Override
    public void start(Stage stage) {
        
        Gui gui = new Gui(stage);
        gui.prepareGuiAndShow();
        try 
        {
            Socket clientSocket = new Socket("localhost", 6666);
            toServer = new PrintWriter(clientSocket.getOutputStream(), true);
            fromServer = new Scanner(clientSocket.getInputStream());   
        }
        catch (Exception e) {
            System.out.println(e);       
        }
            
    }
    public static void submit(String line){
        toServer.println(line);
        toServer.flush();
    }         
    public static void main(String[] args) {
        launch(args);
    }
    

}

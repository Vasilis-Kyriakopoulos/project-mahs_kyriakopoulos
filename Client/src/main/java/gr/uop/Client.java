package gr.uop;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Client extends Application {
    
    

    public static void SendClientInfo(Socket clientSocket ,PrintWriter toServer,Scanner fromServer,String customer){
        Scanner keyboard = new Scanner(System.in);
       
            String line = keyboard.nextLine();
            toServer.println(customer);
            // toServer.flush();
            String response = fromServer.nextLine();
            System.out.println("Response: " + response);
    }

    public static TreeMap<String,Button> treeMapKeyboardCreate(){
        TreeMap buttonsMap = new TreeMap<String,Button>();
        
        for(char c = 'A'; c <= 'Z'; ++c){
            Button a = new Button(Character.toString(c));
            a.setPrefWidth(100);
            a.setPrefHeight(50);
            buttonsMap.put(Character.toString(c), a);
        }
        for(char c = '0'; c <= '9'; ++c){
            
            Button a = new Button(Character.toString(c));
            a.setPrefWidth(100);
            a.setPrefHeight(50);
            buttonsMap.put(Character.toString(c),a);
        }
        Button enter = new Button("Enter");
        enter.setPrefWidth(100);
        enter.setPrefHeight(50);
        Button backspace = new Button("BackSpace");
        backspace.setPrefWidth(205);
        backspace.setPrefHeight(50);
        Button space = new Button("Space");
        space.setPrefWidth(Double.MAX_VALUE);
        space.setPrefHeight(50);
        buttonsMap.put("Enter",  enter);
        buttonsMap.put("BackSpace",backspace);
        buttonsMap.put("Space",space);

        return buttonsMap;
    }

    public static void treemapToPane(TreeMap<String,Button> s1 , GridPane s2){
        //First row of keyboard
        s2.add((Button)s1.get("Q"),0,0);
        s2.add((Button)s1.get("W"),1,0);
        s2.add((Button)s1.get("E"),2,0);
        s2.add((Button)s1.get("R"),3,0);
        s2.add((Button)s1.get("T"),4,0);
        s2.add((Button)s1.get("Y"),5,0);
        s2.add((Button)s1.get("U"),6,0);
        s2.add((Button)s1.get("I"),7,0);
        s2.add((Button)s1.get("O"),8,0);
        s2.add((Button)s1.get("P"),9,0);
        s2.add((Button)s1.get("7"),14,0);
        s2.add((Button)s1.get("8"),15,0);
        s2.add((Button)s1.get("9"),16,0);
        //Second row of keyboard
        s2.add((Button)s1.get("A"),0,1);
        s2.add((Button)s1.get("S"),1,1);
        s2.add((Button)s1.get("D"),2,1);
        s2.add((Button)s1.get("F"),3,1);
        s2.add((Button)s1.get("G"),4,1);
        s2.add((Button)s1.get("H"),5,1);
        s2.add((Button)s1.get("J"),6,1);
        s2.add((Button)s1.get("K"),7,1);
        s2.add((Button)s1.get("L"),8,1);
        s2.add((Button)s1.get("Enter"),9,1);
        s2.add((Button)s1.get("4"),14,1);
        s2.add((Button)s1.get("5"),15,1);
        s2.add((Button)s1.get("6"),16,1);
        //Third row of keyboard
        s2.add((Button)s1.get("Z"),0,2);
        s2.add((Button)s1.get("X"),1,2);
        s2.add((Button)s1.get("C"),2,2);
        s2.add((Button)s1.get("V"),3,2);
        s2.add((Button)s1.get("B"),4,2);
        s2.add((Button)s1.get("N"),5,2);
        s2.add((Button)s1.get("M"),6,2);

        s2.add((Button)s1.get("BackSpace"),7,2,3,1);
        s2.add((Button)s1.get("1"),14,2);
        s2.add((Button)s1.get("2"),15,2);
        s2.add((Button)s1.get("3"),16,2);
        //Fourth row of keyboard
        s2.add((Button)s1.get("0"),14,3);
        s2.add((Button)s1.get("Space"),0,3,10,1);
       
    }




    @Override
    public void start(Stage stage) {
        Stage newWindow = new Stage();
        HBox nameHbox = new HBox();
        TextField nameTextField  = new TextField();
        HBox vehicleBox = new HBox();
        vehicleBox.getChildren().add(new Text(""));
        vehicleBox.getChildren().add(new Text("Αυτόκίνητο"));
        vehicleBox.getChildren().add(new RadioButton());
        vehicleBox.getChildren().add(new Text("Τζιπ"));
        vehicleBox.getChildren().add(new RadioButton());
        vehicleBox.getChildren().add(new Text("Μοτοσυκλέτα"));
        vehicleBox.getChildren().add(new RadioButton());
        vehicleBox.setStyle("-fx-padding: 15;-fx-spacing: 15;");
        VBox servicesBox = new VBox();
        Services services = new Services();
        for(int i =0;i<services.list.size();i++){
            servicesBox.getChildren().add(new Text(services.list.get(i).getName()));
        }
        servicesBox.setStyle("-fx-padding: 15;-fx-spacing: 10;");
        VBox carBox = new VBox();
       
        for(int i =0;i<services.list.size();i++){
            carBox.getChildren().add(new Text(services.list.get(i).getPriceCar().toString()));
        }
        carBox.setStyle("-fx-padding: 15;-fx-spacing: 10;");

        VBox radio1Box = new VBox();
        for(int i =0;i<services.list.size();i++){
            radio1Box.getChildren().add(new RadioButton());
            radio1Box.getChildren().get(i).setOnMouseClicked(event -> {   
            });
            
        }
        
        radio1Box.setStyle("-fx-padding: 15;-fx-spacing: 10;");

        VBox jeepBox = new VBox();
        VBox radio2Box = new VBox();
        for(int i =0;i<services.list.size();i++){
            radio2Box.getChildren().add(new RadioButton());
            radio2Box.getChildren().get(i).setOnMouseClicked(event -> {   
            });
            
        }
        
        radio2Box.setStyle("-fx-padding: 15;-fx-spacing: 10;");
        for(int i =0;i<services.list.size();i++){
            jeepBox.getChildren().add(new Text(services.list.get(i).getPriceJeep().toString()));
        }
        jeepBox.setStyle("-fx-padding: 15;-fx-spacing: 10;");
        
        VBox motoBox = new VBox();
       
        for(int i =0;i<services.list.size();i++){
            motoBox.getChildren().add(new Text(services.list.get(i).getPriceMoto().toString()));
        }
        motoBox.setStyle("-fx-padding: 15;-fx-spacing: 10;");
        VBox radio3Box = new VBox();
        for(int i =0;i<services.list.size();i++){
            radio3Box.getChildren().add(new RadioButton());
            radio3Box.getChildren().get(i).setOnMouseClicked(event -> {   
            });
            
        }
        
        radio3Box.setStyle("-fx-padding: 15;-fx-spacing: 10;");
        HBox formBox = new HBox();


        formBox.getChildren().addAll(servicesBox,carBox,radio1Box,jeepBox,radio2Box,motoBox,radio3Box);
        formBox.setStyle("-fx-border-style: dashed");

        nameTextField.setPrefWidth(600);
        nameTextField.setPrefHeight(40);
        nameTextField.setPromptText("Πινακίδα οχήματος");
        nameHbox.getChildren().addAll(nameTextField);
        nameHbox.setAlignment(Pos.CENTER);
        nameHbox.setSpacing(10);
        HBox.setMargin(nameHbox, new Insets(1000));
        GridPane keyboardPane = new GridPane();
        keyboardPane.setAlignment(Pos.BOTTOM_CENTER);
        keyboardPane.setPadding(new Insets(100,100,100,100));
		keyboardPane.setHgap(5);
		keyboardPane.setVgap(5);
        TreeMap<String,Button> buttonsMap = treeMapKeyboardCreate();
        treemapToPane(buttonsMap, keyboardPane);
        for (Map.Entry<String, Button> entry : buttonsMap.entrySet()){
            Button tmp  = entry.getValue();
            
            tmp.setOnMouseClicked(event -> {
                String txt =nameTextField.getText();
                String s = tmp.getText();
                if(s == "Enter"){
                    String txt2 = txt.trim();
                    if(txt2.length()<2){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        //Setting the title
                        alert.setTitle("Alert");
                        alert.setContentText("Please give correct input(at least two characters)");
                        alert.show();    
                    }
                else{
                    newWindow.show();
                }
                }
                else if(s == "BackSpace"){
                    if(txt.length()>0){
                        nameTextField.setText(txt.substring(0,txt.length()-1));
                    }
                }
                else if(s == "Space"){
                    nameTextField.setText(txt + " ");
                }
                else{
                nameTextField.setText(txt + tmp.getText());
                }
            });
        }
        Scene scene = new Scene(new StackPane(nameHbox,keyboardPane), 1024, 768);
        stage.setScene(scene);
        stage.setMaxHeight(1080);
        stage.setMaxWidth(1920);
        stage.setMinHeight(768);
        stage.setMinWidth(1024);
        VBox  secondaryLayout = new VBox();
        secondaryLayout.getChildren().addAll(vehicleBox,formBox);
        secondaryLayout.setAlignment(Pos.CENTER);
        secondaryLayout.setPadding(new Insets(1000));
        

        Scene secondScene = new Scene(secondaryLayout, 1024, 768);
    
        newWindow.setTitle("Second Stage");           
        newWindow.setScene(secondScene);           
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(stage);
        newWindow.setX(stage.getX() + 200);
        newWindow.setY(stage.getY() + 100);
        newWindow.setMaxHeight(1080);
        newWindow.setMaxWidth(1920);
        newWindow.setMinHeight(768);
        newWindow.setMinWidth(1024);
        stage.show();



        //try (Socket clientSocket = new Socket("localhost", 6666);
        //PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        //Scanner fromServer = new Scanner(clientSocket.getInputStream())) {
        //    SendClientInfo(clientSocket, toServer, fromServer,"160");
        //}
        // catch (Exception e) {
        //    System.out.println(e);       
        //}
        
    }
   
    public static void main(String[] args) {
        launch(args);
    }
    

}

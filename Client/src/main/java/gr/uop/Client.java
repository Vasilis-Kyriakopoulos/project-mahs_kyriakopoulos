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
    PrintWriter toServer ;
    Scanner fromServer ;
    Button submit = new Button("Submit");
    Button cancel = new Button("Cancel");
    Text timi = new Text("0");
    Text keimeno = new Text("Τελική τιμή: ");
    GridPane keyboardPane = new GridPane();
    TreeMap<String,Button> buttonsMap = treeMapKeyboardCreate();
    HBox sumBox = new HBox();
    VBox main2Box = new VBox();
    HBox formBox = new HBox();
    VBox radio3Box = new VBox();
    VBox motoBox = new VBox();
    VBox jeepBox = new VBox();
    VBox radio2Box = new VBox();
    VBox radio1Box = new VBox();
    VBox carBox = new VBox();
    Stage newWindow = new Stage();
    HBox nameHbox = new HBox();
    TextField nameTextField  = new TextField();
    VBox servicesBox = new VBox();
    Services services = new Services();

    private class SelectServices implements EventHandler<MouseEvent>{
        ObservableList<RadioButton> rd ;
        ObservableList<Text> times ;
        Integer pos;
        public SelectServices(ObservableList<RadioButton> rd,int pos,ObservableList<Text> times){
            this.rd = rd;
            this.pos = pos;
            this.times = times;
        }
        @Override
        public void handle(MouseEvent event) {
                boolean check =  rd.get(pos).isSelected();
                
                
                if(check){
                    timi.setText(String.valueOf(Integer.parseInt(timi.getText()) + Integer.parseInt(times.get(pos).getText())));
                    if(pos==1){
                        
                        for(int j =1 ; j< rd.size()-3;j++){
                            if(j!=pos && j!=5 && j!=7){
                                rd.get(j).setDisable(true);
                                rd.get(j).setSelected(false);
                            }
                        }
                    }
                    else if(pos == 2){
                        for(int j =1 ; j< rd.size()-3;j++){
                            if(j!=pos && j!=4){
                                rd.get(j).setDisable(true);
                                rd.get(j).setSelected(false);
                            }
                        }
                    }
                    else if(pos == 4){
                        for(int j =1 ; j< rd.size()-3;j++){
                            if(j!=pos && j!=2 && j!=7){
                                rd.get(j).setDisable(true);
                                rd.get(j).setSelected(false);
                            }
                        }
                    }

                    else if(pos == 5){
                        for(int j =1 ; j< rd.size()-3;j++){
                            if(j!=pos && j!=1){
                                rd.get(j).setDisable(true);
                                rd.get(j).setSelected(false);
                            }
                        }
                    }
                    else if(pos == 6|| pos == 3){
                        for(int j =1 ; j< rd.size()-3;j++){
                            if(j!=pos){
                                rd.get(j).setDisable(true);
                            }
                        }
                    }
                    else if(pos == 7){
                        for(int j =1 ; j< rd.size()-3;j++){
                            if(j!=pos && j!=1 && j!=4){
                                rd.get(j).setDisable(true);
                                rd.get(j).setSelected(false);
                            }
                        }
                    }
                }
                else{
                    timi.setText(String.valueOf(Integer.parseInt(timi.getText()) - Integer.parseInt(times.get(pos).getText())));
                    for(int j =1 ; j< rd.size();j++){
                            rd.get(j).setDisable(false);
                            rd.get(j).setSelected(false);
                            timi.setText("0");
                    }
                }

            
            
        }
    
    }
    private class SelectVehicle implements EventHandler<MouseEvent>{
        ObservableList<RadioButton> rd1 ;
        ObservableList<RadioButton> rd2 ;
        ObservableList<RadioButton> rd3 ;
        public SelectVehicle(ObservableList<RadioButton> rd1 ,ObservableList<RadioButton> rd2,ObservableList<RadioButton> rd3){
            this.rd1 = rd1;
            this.rd2 = rd2;
            this.rd3 = rd3;
        }
        @Override
        public void handle(MouseEvent event) {
            timi.setText("0");
            for(int i =1 ; i< rd1.size();i++){
                rd1.get(i).setDisable(false);
                rd1.get(i).setSelected(false);
            }
                RadioButton a =  (RadioButton)rd2.get(0);
                a.setSelected(false);
                for(int i =1 ; i< rd2.size();i++){
                    rd2.get(i).setSelected(false);
                    rd2.get(i).setDisable(true);
                }
                a =  (RadioButton) rd3.get(0);
                a.setSelected(false);
                for(int i =1 ; i< rd3.size();i++){
                    rd2.get(i).setSelected(false);
                    rd3.get(i).setDisable(true);
                }
        }
    
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
    public static void disableRadiobuttons(ObservableList<RadioButton> a){
        for(int i =1 ; i< a.size();i++){
            a.get(i).setDisable(true);
            a.get(i).setSelected(false);
        }
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
        
        servicesBox.getChildren().add(new Text("Τύπος Οχήματος"));
        for(int i =0;i<services.list.size();i++){
            servicesBox.getChildren().add(new Text(services.list.get(i).getName()));
        }
        servicesBox.setStyle("-fx-padding: 19;-fx-spacing: 10;-fx-border-style: solid");


        carBox.getChildren().add(new Text("Αυτόκίνητο"));
        for(int i =0;i<services.list.size();i++){
            carBox.getChildren().add(new Text(services.list.get(i).getPriceCar().toString()));
        }
        carBox.setStyle("-fx-padding: 17;-fx-spacing: 10;-fx-border-style: solid");

       
        radio1Box.getChildren().add(new RadioButton());
        for(int i =0;i<services.list.size();i++){
            radio1Box.getChildren().add(new RadioButton());
        }
        radio1Box.setStyle("-fx-padding: 14;-fx-spacing: 10;-fx-border-style: solid");

        
        radio2Box.getChildren().add(new RadioButton());
        for(int i =0;i<services.list.size();i++){
            radio2Box.getChildren().add(new RadioButton());
        }
        radio2Box.setStyle("-fx-padding: 14;-fx-spacing: 10;-fx-border-style: solid");

        jeepBox.getChildren().add(new Text("Τζιπ"));
        for(int i =0;i<services.list.size();i++){
            jeepBox.getChildren().add(new Text(services.list.get(i).getPriceJeep().toString()));
        }
        jeepBox.setStyle("-fx-padding: 17;-fx-spacing: 10;-fx-border-style: solid");
        
        
        motoBox.getChildren().add(new Text("Μοτοσυκλέτα"));
        for(int i =0;i<services.list.size();i++){
            motoBox.getChildren().add(new Text(services.list.get(i).getPriceMoto().toString()));
        }
        motoBox.setStyle("-fx-padding: 15;-fx-spacing: 10;-fx-border-style: solid");
        
        radio3Box.getChildren().add(new RadioButton());
        for(int i =0;i<services.list.size();i++){
            radio3Box.getChildren().add(new RadioButton());
        }
        radio3Box.setStyle("-fx-padding: 14;-fx-spacing: 10;-fx-border-style: solid");
        
        formBox.setAlignment(Pos.CENTER);
        formBox.getChildren().addAll(servicesBox,carBox,radio1Box,jeepBox,radio2Box,motoBox,radio3Box);
        
        sumBox.getChildren().addAll(keimeno,timi);



        nameTextField.setPrefWidth(600);
        nameTextField.setPrefHeight(40);
        nameTextField.setPromptText("Πινακίδα οχήματος");
        nameHbox.getChildren().addAll(nameTextField);
        nameHbox.setAlignment(Pos.CENTER);
        nameHbox.setSpacing(10);
        
        keyboardPane.setAlignment(Pos.BOTTOM_CENTER);
        keyboardPane.setPadding(new Insets(100,100,100,100));
		keyboardPane.setHgap(5);
		keyboardPane.setVgap(5);
        
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
                        alert.setContentText("Παρακαλώ δώστε τουλάχιστον δυο χαρακτήρες");
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
        sumBox.setAlignment(Pos.CENTER);
        sumBox.setSpacing(10);
        main2Box.setSpacing(10);
        main2Box.getChildren().addAll(formBox,sumBox,submit,cancel);
        main2Box.setAlignment(Pos.CENTER);
        main2Box.setPadding(new Insets(500));
        sumBox.setStyle("-fx-font-size:40px;");
        sumBox.setPadding(new Insets(10));
                
        submit.setMinWidth(150);
        submit.setStyle("-fx-background-radius: 5em;-fx-background-color:green;");
        cancel.setStyle("-fx-background-radius: 5em;-fx-background-color:red");
        cancel.setMinWidth(150);
        Scene secondScene = new Scene(main2Box, 1024, 768);
    
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

          
        ObservableList rd1 =radio1Box.getChildren();
        disableRadiobuttons(rd1);
        ObservableList rd2 =radio2Box.getChildren();
        disableRadiobuttons(rd2);
        ObservableList rd3 =radio3Box.getChildren();
        disableRadiobuttons(rd3);
        ObservableList cars = carBox.getChildren();
        ObservableList jeeps = jeepBox.getChildren();
        ObservableList motos = motoBox.getChildren();

        
        cancel.setOnMouseClicked(e -> {
            timi.setText("0");
            RadioButton a =(RadioButton) rd1.get(0);
            RadioButton b =(RadioButton) rd2.get(0);
            RadioButton c =(RadioButton) rd3.get(0);
            a.setSelected(false);
            b.setSelected(false);
            c.setSelected(false);
            disableRadiobuttons(rd1);
            disableRadiobuttons(rd2);
            disableRadiobuttons(rd3);
            newWindow.close();
        });
        radio1Box.getChildren().get(0).setOnMouseClicked(new SelectVehicle(rd1, rd2, rd3));
        radio2Box.getChildren().get(0).setOnMouseClicked(new SelectVehicle(rd2, rd1, rd3));   
        radio3Box.getChildren().get(0).setOnMouseClicked(new SelectVehicle(rd3, rd1, rd2));
        for ( int i =1;i<radio1Box.getChildren().size();i++){
            radio1Box.getChildren().get(i).setOnMouseClicked(new SelectServices(rd1,i,cars));
        }
        for ( int i =1;i<radio2Box.getChildren().size();i++){
            radio2Box.getChildren().get(i).setOnMouseClicked(new SelectServices(rd2,i,jeeps));
        }
        for ( int i =1;i<radio3Box.getChildren().size();i++){
            radio3Box.getChildren().get(i).setOnMouseClicked(new SelectServices(rd3,i,motos));
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        
        try 
        {
            Socket clientSocket = new Socket("localhost", 6666);
            PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner fromServer = new Scanner(clientSocket.getInputStream());

            submit.setOnAction(event ->{
                toServer.println(nameTextField.getText()+" "+timi.getText()+" "+dateFormat.format(date));
                toServer.flush();
                timi.setText("0");
                RadioButton a =(RadioButton) rd1.get(0);
                RadioButton b =(RadioButton) rd2.get(0);
                RadioButton c =(RadioButton) rd3.get(0);
                a.setSelected(false);
                b.setSelected(false);
                c.setSelected(false);
                disableRadiobuttons(rd1);
                disableRadiobuttons(rd2);
                disableRadiobuttons(rd3);
                nameTextField.setText("");
                newWindow.close();
                });
        }
        catch (Exception e) {
            System.out.println(e);       
        }
            
    }
            
    public static void main(String[] args) {
        launch(args);
    }
    

}

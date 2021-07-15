package gr.uop;

import java.io.PrintWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Date;
public class Gui {
    
    private Button submit = new Button("Submit");
    private Button cancel = new Button("Cancel");
    private Text timi = new Text("0");
    private Text keimeno = new Text("Τελική τιμή: ");
    private GridPane keyboardPane = new GridPane();
    private TreeMap<String,Button> buttonsMap = treeMapKeyboardCreate();
    private HBox sumBox = new HBox();
    private VBox main2Box = new VBox();
    private HBox formBox = new HBox();
    private VBox radio3Box = new VBox();
    private VBox motoBox = new VBox();
    private VBox jeepBox = new VBox();
    private VBox radio2Box = new VBox();
    private VBox radio1Box = new VBox();
    private VBox carBox = new VBox();
    private Stage newWindow = new Stage();
    private HBox nameHbox = new HBox();
    private TextField nameTextField  = new TextField();
    private VBox servicesBox = new VBox();
    private Services services = new Services();
    Scene scene = new Scene(new StackPane(nameHbox,keyboardPane), 1024, 768);
    Scene secondScene = new Scene(main2Box, 1024, 768);
    Stage stage;
    ArrayList<String> selectedServices= new ArrayList<String>();

    public Gui(Stage stage){
        this.stage = stage;
    }

    public String submit(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ObservableList rd1 =radio1Box.getChildren();
        disableRadiobuttons(rd1);
        ObservableList rd2 =radio2Box.getChildren();
        disableRadiobuttons(rd2);
        ObservableList rd3 =radio3Box.getChildren();
        disableRadiobuttons(rd3);
        String line ="";
        if(Integer.valueOf(timi.getText())>0){
            String services="";
            Services temp = new Services();
            RadioButton a =  (RadioButton)radio1Box.getChildren().get(0);
            
            if(a.isSelected()){
                services+="Αυτοκίνητο;";
            }
            RadioButton b =  (RadioButton)radio2Box.getChildren().get(0);
            if(b.isSelected()){
                services+="Τζίπ;";
            }
            RadioButton c =  (RadioButton)radio3Box.getChildren().get(0);
            if(c.isSelected()){
                services+="Μοτοσυκλέτα;";
            }
            for(String s : selectedServices ){
                if(a.isSelected()){
                    services += temp.getList().get(Integer.valueOf(s)-1).getName()+";"+ temp.getList().get(Integer.valueOf(s)-1).getPriceCar()+";";
                }
                else if(b.isSelected()){
                    services += temp.getList().get(Integer.valueOf(s)-1).getName()+";"+ temp.getList().get(Integer.valueOf(s)-1).getPriceJeep()+";";
                }
                else if(c.isSelected()){
                    services += temp.getList().get(Integer.valueOf(s)-1).getName()+";"+ temp.getList().get(Integer.valueOf(s)-1).getPriceMoto()+";";
                }
            line = nameTextField.getText()+";"+timi.getText()+";"+dateFormat.format(date)+";"+services;    
            }
            line = nameTextField.getText()+";"+timi.getText()+";"+dateFormat.format(date)+";"+services;
            timi.setText("0");
            }
            selectedServices.clear();
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
            return line;
            
    }
    private void setEvents(){
        treemapToPane(buttonsMap, keyboardPane);
        for (Map.Entry<String, Button> entry : buttonsMap.entrySet()){
            Button tmp  = entry.getValue();
            tmp.setOnMouseClicked(new KeyBoardEvent(nameTextField,tmp,newWindow));
        }
        ObservableList rd1 =radio1Box.getChildren();
        disableRadiobuttons(rd1);
        ObservableList rd2 =radio2Box.getChildren();
        disableRadiobuttons(rd2);
        ObservableList rd3 =radio3Box.getChildren();
        disableRadiobuttons(rd3);
        ObservableList cars = carBox.getChildren();
        ObservableList jeeps = jeepBox.getChildren();
        ObservableList motos = motoBox.getChildren();

       
        cancel.setOnMouseClicked(new CancelEvent(rd1, rd2, rd3, timi, newWindow));
        submit.setOnMouseClicked(new SubmitEvent(this));
        
        radio1Box.getChildren().get(0).setOnMouseClicked(new SelectVehicle(rd1, rd2, rd3,timi));
        radio2Box.getChildren().get(0).setOnMouseClicked(new SelectVehicle(rd2, rd1, rd3,timi));   
        radio3Box.getChildren().get(0).setOnMouseClicked(new SelectVehicle(rd3, rd1, rd2,timi));
        for ( int i =1;i<radio1Box.getChildren().size();i++){
            radio1Box.getChildren().get(i).setOnMouseClicked(new SelectServices(rd1,i,cars,timi,selectedServices));
        }
        for ( int i =1;i<radio2Box.getChildren().size();i++){
            radio2Box.getChildren().get(i).setOnMouseClicked(new SelectServices(rd2,i,jeeps,timi,selectedServices));
        }
        for ( int i =1;i<radio3Box.getChildren().size();i++){
            radio3Box.getChildren().get(i).setOnMouseClicked(new SelectServices(rd3,i,motos,timi,selectedServices));
        }
    }

    public void prepareGuiAndShow(){
        servicesBox.getChildren().add(new Text("Τύπος Οχήματος"));
        for(int i =0;i<services.getList().size();i++){
            servicesBox.getChildren().add(new Text(services.getList().get(i).getName()));
        }
        servicesBox.setStyle("-fx-padding: 19;-fx-spacing: 10;-fx-border-style: solid");


        carBox.getChildren().add(new Text("Αυτόκίνητο"));
        for(int i =0;i<services.getList().size();i++){
            carBox.getChildren().add(new Text(services.getList().get(i).getPriceCar().toString()));
        }
        carBox.setStyle("-fx-padding: 17;-fx-spacing: 10;-fx-border-style: solid");

       
        radio1Box.getChildren().add(new RadioButton());
        for(int i =0;i<services.getList().size();i++){
            radio1Box.getChildren().add(new RadioButton());
        }
        radio1Box.setStyle("-fx-padding: 14;-fx-spacing: 10;-fx-border-style: solid");

        
        radio2Box.getChildren().add(new RadioButton());
        for(int i =0;i<services.getList().size();i++){
            radio2Box.getChildren().add(new RadioButton());
        }
        radio2Box.setStyle("-fx-padding: 14;-fx-spacing: 10;-fx-border-style: solid");

        jeepBox.getChildren().add(new Text("Τζιπ"));
        for(int i =0;i<services.getList().size();i++){
            jeepBox.getChildren().add(new Text(services.getList().get(i).getPriceJeep().toString()));
        }
        jeepBox.setStyle("-fx-padding: 17;-fx-spacing: 10;-fx-border-style: solid");
        
        
        motoBox.getChildren().add(new Text("Μοτοσυκλέτα"));
        for(int i =0;i<services.getList().size();i++){
            motoBox.getChildren().add(new Text(services.getList().get(i).getPriceMoto().toString()));
        }
        motoBox.setStyle("-fx-padding: 15;-fx-spacing: 10;-fx-border-style: solid");
        
        radio3Box.getChildren().add(new RadioButton());
        for(int i =0;i<services.getList().size();i++){
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
        setEvents();
        stage.show();
    }

    private  void treemapToPane(TreeMap<String,Button> s1 , GridPane s2){
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

    private  void disableRadiobuttons(ObservableList<RadioButton> a){
        for(int i =1 ; i< a.size();i++){
            a.get(i).setDisable(true);
            a.get(i).setSelected(false);
        }
    }
    private  TreeMap<String,Button> treeMapKeyboardCreate(){
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
}

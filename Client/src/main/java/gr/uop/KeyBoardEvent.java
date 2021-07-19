package gr.uop;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


//Σε αυτή την κλάση χειριζόμαστε την  λειτουργικότητα του πληκτρολογίου
public class KeyBoardEvent implements EventHandler<MouseEvent>{
    TextField nameTextField;
    Button tmp;
    Stage newWindow;

    public KeyBoardEvent(TextField nameTextField,Button tmp,Stage newWindow){
        this.nameTextField = nameTextField;
        this.tmp = tmp;
        this.newWindow = newWindow;
    }

    @Override
    public void handle(MouseEvent arg0) {
        
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
        
    }
    
}

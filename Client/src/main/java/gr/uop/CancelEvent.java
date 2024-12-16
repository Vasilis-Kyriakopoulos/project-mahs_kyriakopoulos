package gr.uop;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;


//Σε αυτή την κλάση χειριζόμαστε την περίπτωση που ο χρήστης πατήσει cancel στην επιλογή των υπηρεσιών
// και εδώ γίνονται οι κατάλληλες ενέργειες για την επιστροφή στην αρχική οθόνη.

public class CancelEvent implements  EventHandler<MouseEvent>{
    ObservableList<RadioButton> rd1 ;
    ObservableList<RadioButton> rd2 ;
    ObservableList<RadioButton> rd3 ;
    Text timi;
    Stage newWindow;

    public CancelEvent(ObservableList<RadioButton> rd1 ,ObservableList<RadioButton> rd2,ObservableList<RadioButton> rd3,Text timi,Stage newWindow){
        this.rd1 = rd1;
        this.rd2 = rd2;
        this.rd3 = rd3;
        this.timi = timi;
        this.newWindow = newWindow;
    }

    @Override
    public void handle(MouseEvent arg0) {
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
    }
    public static void disableRadiobuttons(ObservableList<RadioButton> a){
        for(int i =1 ; i< a.size();i++){
            a.get(i).setDisable(true);
            a.get(i).setSelected(false);
        }
    }
    
}

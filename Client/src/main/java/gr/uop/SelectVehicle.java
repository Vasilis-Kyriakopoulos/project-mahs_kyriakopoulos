package gr.uop;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class SelectVehicle implements EventHandler<MouseEvent>{
    ObservableList<RadioButton> rd1 ;
    ObservableList<RadioButton> rd2 ;
    ObservableList<RadioButton> rd3 ;
    Text timi;
    public SelectVehicle(ObservableList<RadioButton> rd1 ,ObservableList<RadioButton> rd2,ObservableList<RadioButton> rd3,Text timi){
        this.rd1 = rd1;
        this.rd2 = rd2;
        this.rd3 = rd3;
        this.timi = timi;
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
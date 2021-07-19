

package gr.uop;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;


//Σε αυτή την κλάση χειριζόμαστε τους περιοσμούς για την επιλογή των υπηρεσιών.
public class SelectServices implements EventHandler<MouseEvent>{
    ObservableList<RadioButton> rd ;
    ObservableList<Text> times ;
    Integer pos;
    Text timi ;
    ArrayList<String> selectedServices;
    public SelectServices(ObservableList<RadioButton> rd,int pos,ObservableList<Text> times,Text timi,ArrayList<String> selectedServices){
        this.rd = rd;
        this.pos = pos;
        this.times = times;
        this.timi = timi;
        this.selectedServices = selectedServices;
    }
    @Override
    public void handle(MouseEvent event) {
            boolean check =  rd.get(pos).isSelected();
            
            
            if(check){
                timi.setText(String.valueOf(Integer.parseInt(timi.getText()) + Integer.parseInt(times.get(pos).getText())));
                selectedServices.add(String.valueOf(pos));
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
                selectedServices.clear();
                for(int j =1 ; j< rd.size();j++){
                        rd.get(j).setDisable(false);
                        rd.get(j).setSelected(false);
                        timi.setText("0");
                }
            }

        
        
    }

}
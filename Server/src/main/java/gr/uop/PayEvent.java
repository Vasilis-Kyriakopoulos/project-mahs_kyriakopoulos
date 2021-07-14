package gr.uop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class PayEvent implements EventHandler<MouseEvent>{

    TableView tableView;


    public PayEvent(TableView tableView){
        this.tableView = tableView;
    }
    @Override
    public void handle(MouseEvent arg0) {
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
                        wr.write(c.getPinakida()+" "+c.getPrice()+" "+c.getDate()+" ,"+dateFormat.format(date)+" "+c.getServices().replace("\n","")+"\n");
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
        
    }
    
}

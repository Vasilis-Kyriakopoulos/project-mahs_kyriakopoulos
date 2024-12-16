package gr.uop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;


//Στην κλάση αυτή χειριζόμαστε την περίπτωση που γίνει delete απο το tableview και συνεπώς διαγραφή 
//της προσωρινής εγγραφής στο αρχείο.

public class DeleteEvent implements EventHandler<MouseEvent> {
    TableView tableView;
    public DeleteEvent(TableView tableView){
        this.tableView = tableView;
    }
    @Override
    public void handle(MouseEvent arg0) {
        int i = tableView.getFocusModel().getFocusedCell().getRow();
        Customer c = (Customer)tableView.getItems().get(i);
        Alert  alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Επιβαίωση Διαγραφής: για το όχημα με πινακίδα:" +c.getPinakida());
        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL); 
        if(button ==ButtonType.OK){
            try( FileReader reader = new FileReader("users.txt");
                    BufferedReader br = new BufferedReader(reader);
                    FileWriter writer = new FileWriter("users2.txt");
                    BufferedWriter wr = new BufferedWriter(writer);
                    ) {
                   String line;
                   while ((line = br.readLine()) != null) {
                    if (!c.getPinakida().equals(line.split(" ")[0])) {
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
                    //TODO: handle exception
                } 

            
        }
        else if(button == ButtonType.CANCEL){

        }
    }
    
}

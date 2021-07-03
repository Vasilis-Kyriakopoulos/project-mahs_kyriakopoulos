package gr.uop;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Server extends Application {

    @Override
    public void start(Stage stage) {
        var label = new Label("Hello, JavaFX Server");
        var scene = new Scene(new StackPane(label), 1024, 768);
        stage.setScene(scene);
        try (ServerSocket serverSocket = new ServerSocket(6666);
             Socket connectionSocket = serverSocket.accept();
             Scanner fromClient = new Scanner(connectionSocket.getInputStream());
             PrintWriter toClient = new PrintWriter(connectionSocket.getOutputStream(), true)) {

            do {
                String line = fromClient.nextLine();
                System.out.println("Received: " + line);

                toClient.println(line);
            } while (fromClient.hasNextLine());
        }
        catch (IOException e) {
            System.out.println(e);
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

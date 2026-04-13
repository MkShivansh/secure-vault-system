package com.securevaultclient.util;

import com.securevaultclient.MainApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class SceneSwitcher {
    
    public static void switchTo(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                SceneSwitcher.class.getResource("/fxml/" + fxml + ".fxml")
            );
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(
                SceneSwitcher.class.getResource("/css/style.css").toExternalForm()
            );
            
            MainApp.getInstance().getPrimaryStage().setScene(scene);
            MainApp.getInstance().getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
            AlertUtil.showError("Navigation Error", "Could not load: " + fxml);
        }
    }
}
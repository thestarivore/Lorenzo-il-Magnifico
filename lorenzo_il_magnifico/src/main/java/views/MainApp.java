package views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by Mattia on 01/07/2017.
 */

    public class MainApp extends Application {

        private Stage primaryStage;
        private BorderPane rootLayout;

        @Override
        public void start(Stage primaryStage) throws Exception{
            this.primaryStage = primaryStage;
            this.primaryStage.setTitle("AddressApp");

            initRootLayout();

            showPersonOverview();
        }

        /**
         * Initializes the root layout.
         */
        public void initRootLayout() {
            try {
                // Load root layout from fxml file.
                FXMLLoader loader = new FXMLLoader();
                System.err.println("FXML resource: " + getClass().getResource("/main/java/resources/RootLayout.fxml"));
                loader.setLocation(MainApp.class.getResource("/views/RootLayout.fxml"));
                rootLayout = FXMLLoader.load(getClass().getResource("/src/main/java/views/RootLayout.fxml"));

                // Show the scene containing the root layout.
                Scene scene = new Scene(rootLayout);
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Shows the person overview inside the root layout.
         */
        public void showPersonOverview() {
            try {
                // Load person overview.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("/views/ClientOverview.fxml"));
                AnchorPane personOverview = loader.load();

                // Set person overview into the center of root layout.
                rootLayout.setCenter(personOverview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Returns the main stage.
         * @return
         */
        public Stage getPrimaryStage() {
            return primaryStage;
        }

        public static void main(String[] args) {
            launch(args);
        }
    }


package it.ial.termostato.gui;

import it.ial.termostato.Termostato;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Termostato termostato = new Termostato();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(TermostatoController.class.getResource("griglia.fxml"));
		Parent root = loader.load();

		TermostatoController grigliaController = loader.getController();
		grigliaController.setTermostato(termostato);
		
		Scene scene = new Scene(root, 1024, 768);
		scene.getStylesheets().add(TermostatoController.class.getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

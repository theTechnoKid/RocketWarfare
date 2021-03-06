package dev.thetechnokid.rw;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RocketWarfarePreloader extends Preloader {

	public static final String NEWS = "http://realahmedroach.github.io/RocketWarfare";
	public static boolean DONE = false;

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane parent = (BorderPane) FXMLLoader.load(getClass().getResource("fxml/Preloader.fxml"));
		Scene scene = new Scene(parent);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle("Rocket Warfare Launcher");
		stage.getIcons().add(new Image(RocketWarfare.class.getResourceAsStream("/images/rw_logo.jpg")));
		stage.show();
	}
}

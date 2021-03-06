package dev.thetechnokid.rw;

import java.util.ArrayList;
import java.util.List;

import dev.thetechnokid.rw.controllers.MainGameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RocketWarfare extends Application {

	public static final int FPS = 60;
	public static final String OS = (System.getProperty("os.name")).toUpperCase();
	private static Stage stage;

	private static List<String> themes = new ArrayList<String>();

	static {
		themes.add("Dark");
		themes.add("Light");
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage owner) throws Exception {
		AnchorPane parent = (AnchorPane) FXMLLoader.load(RocketWarfare.class.getResource("fxml/Game.fxml"));
		Scene scene = new Scene(parent);

		stage = owner;
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setTitle(Version.NAME + " | " + Version.FRIENDLY);
		stage.getIcons().add(new Image(RocketWarfare.class.getResourceAsStream("/images/rw_logo.jpg")));
	}

	@Override
	public void stop() throws Exception {
		if (MainGameController.get().USER != null) {
			MainGameController.get().USER.save();
		}
	}

	public static Stage getStage() {
		return stage;
	}

	public static String settingsFolder() {
		String thing = null;
		if (OS.contains("WIN"))
			thing = System.getenv("APPDATA");
		else if (OS.contains("MAC"))
			thing = System.getProperty("user.home") + "/Library/Application Support";
		else if (OS.contains("NUX"))
			thing = System.getProperty("user.home");
		else
			thing = System.getProperty("user.dir");
		return thing + "/RocketWarfare";
	}

	public static List<String> getThemes() {
		return themes;
	}

}

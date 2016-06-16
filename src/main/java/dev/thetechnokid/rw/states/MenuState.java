package dev.thetechnokid.rw.states;

import java.io.File;

import dev.thetechnokid.rw.RocketWarfare;
import dev.thetechnokid.rw.controllers.MainGameController;
import dev.thetechnokid.rw.net.User;
import dev.thetechnokid.rw.utils.*;
import javafx.geometry.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class MenuState extends State {

	private boolean textOn, showWelcome;
	private String name;
	private boolean gridded;
	private int i = 0;
	private int z = 0;
	private Animator logo;

	public MenuState(GraphicsContext g) {
		super(g);
	}

	@Override
	protected void init() {
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);

		logo = new Animator(150, () -> {
			i++;
			if (i == 10) {
				i = 0;
				z = 1;
			}
			if (i == 1 && z == 1) {
				i = 0;
				z = 0;
			}
		});

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Label nameLabel = new Label(Language.get("name") + ": ");
		TextField nameField = new TextField();
		nameField.setPromptText(Language.get("name"));
		nameField.setMaxWidth(100);

		Label pwLabel = new Label(Language.get("password") + ":");
		PasswordField pwField = new PasswordField();
		pwField.setPromptText(Language.get("password"));
		pwField.setMaxWidth(100);

		CheckBox register = new CheckBox("Register");

		Button start = new Button(Language.get("login"));
		start.setOnAction((event) -> {
			name = nameField.getText();
			if (login(name, pwField.getText(), register.isSelected())) {
				textOn = true;
				gridded = true;
			}
			g.getCanvas().requestFocus();
		});

		grid.add(nameLabel, 0, 1);
		grid.add(nameField, 1, 1);
		grid.add(pwLabel, 0, 2);
		grid.add(pwField, 1, 2);
		grid.add(start, 1, 3);
		grid.add(register, 0, 3);

		MainGameController.buttons().addAll(grid);
	}

	private boolean login(String name, String password, boolean register) {
		File file = new File(RocketWarfare.settingsFolder() + "/users/" + name);
		try {
			User user = User.load(name, password);
			if (user == null) {
				if (!register) {
					MainGameController.setStatus("Incorrect username or password.");
					return false;
				} else if (register) {
					if (file.exists()) {
						MainGameController.setStatus("User with that name already exists!");
						return false;
					}
					MainGameController.get().FIRST_TIME = true;
					User newUser = new User(name, password);
					MainGameController.get().USER = newUser;
					newUser.save();
					return true;
				}
			} else if (user != null) {
				MainGameController.get().USER = user;
				MainGameController.setStatus("Login Successfull!");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public void render() {
		g.setStroke(Color.RED);
		g.setFill(Color.BLUEVIOLET);

		if (showWelcome) {
			Utils.wait(1500);
			State.setCurrentState(new BuildingState(g));
		}

		if (textOn) {
			Utils.centerText(g, Language.get("welcome") + ", " + name, 20);
			showWelcome = true;
		} else {
			g.drawImage(Assets.crop(Assets.LOGO, i, z), MainGameController.getWidth() / 2 - Grid.SIZE,
					MainGameController.getHeight() / 2 - Grid.SIZE);
		}
		logo.tick();
		// Grid.render(g);
	}

	@Override
	public void tick() {
		if (MainGameController.getMouse().isMousePressed()) {
			int x = MainGameController.getMouse().getX();
			int y = MainGameController.getMouse().getY();
			Point2D p = Grid.getGridLocation(x, y);
			g.fillRect(p.getX(), p.getY(), Grid.SIZE, Grid.SIZE);
		}

	}

	@Override
	public boolean gridEnabled() {
		return gridded;
	}
}

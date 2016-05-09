package dev.thetechnokid.rw.net;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Preferences implements java.io.Serializable {
	private static final long serialVersionUID = 9435437432L;
	
	private Color color;
	private Image pic;
	private String displayName;
	private ArrayList<User> friends;
	private String status;
	private String locale;

	public Preferences(User user) {
		color = Color.RED;
		pic = null;
		displayName = user.getName();
		friends = new ArrayList<>();
		status = "Hi";
		locale = "en_US";
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Image getPic() {
		return pic;
	}

	public void setPic(Image pic) {
		this.pic = pic;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
}

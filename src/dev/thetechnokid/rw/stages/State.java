package dev.thetechnokid.rw.stages;

import javafx.scene.canvas.GraphicsContext;

public abstract class State {
	private static State currentState;

	public static State getCurrentStage() {
		return currentState;
	}

	public static void setCurrentStage(State state) {
		if (state != null)
			currentState = state;
	}

	protected GraphicsContext g;
	
	public State(GraphicsContext g) {
		this.g = g;
	}
	
	public abstract void render();
	public abstract void tick();
}

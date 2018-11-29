package application;

import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;

public class ToolBarWrapper {
	private String labelName;
	private Label label;
	private ToolBar toolBar;
	public ToolBarWrapper(String labelName) {
		this.labelName = labelName;
		label = new Label(this.labelName);
		toolBar = new ToolBar(label);
	}
	
	public ToolBar getComponent() {
		return this.toolBar;
	}
}

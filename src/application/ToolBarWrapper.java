package application;

import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.text.Font;

public class ToolBarWrapper {
	private String labelName;
	private Label label;
	private ToolBar toolBar;
	public ToolBarWrapper(String labelName, String fontFamily, int fontSize) {
		this.labelName = labelName;
		label = new Label(this.labelName);
		label.setFont(new Font(fontFamily, fontSize));
		toolBar = new ToolBar(label);
	}
	
	public ToolBar getComponent() {
		return this.toolBar;
	}
}

package editor;

import org.eclipse.swt.custom.StyledText;

import component.Component;
import component.ComponentAlign;
import component.ComponentUI;

public class EditorUI extends ComponentUI {
	
	/** Caixa de texto */
	protected StyledText text;
	
	public EditorUI(Component component) {
		super(component, ComponentAlign.CENTER);
	}
	
	@Override
	public void open() {
	}
	
	public StyledText getText() {
		return text;
	}
	
	public void setText(StyledText text) {
		this.text = text;
	}
	
	@Override
	public Editor getComponent() {
		return (Editor) super.getComponent();
	}
	
}

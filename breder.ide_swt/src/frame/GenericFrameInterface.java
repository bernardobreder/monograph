package frame;

public abstract class GenericFrameInterface<E extends GenericShell<?>> {
	
	protected E frame;
	
	public E getFrame() {
		return frame;
	}
	
	public void setFrame(E frame) {
		this.frame = frame;
	}
	
}

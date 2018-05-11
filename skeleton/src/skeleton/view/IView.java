package skeleton.view;

public interface IView<T> {
	public void sendMessage(T msg);
	public void receiveMessage(T msg);
}

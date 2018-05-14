package skeleton.view;

/**
 * Interface representing a view for sending and receiving messages.
 * @param <T> The type of the message.
 */
public interface IView<T> {
	/**
	 * @param msg A message to send.
	 */
	public void sendMessage(T msg);
	/**
	 * @param msg A message to receive.
	 */
	public void receiveMessage(T msg);
}

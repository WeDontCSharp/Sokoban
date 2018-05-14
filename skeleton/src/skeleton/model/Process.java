package skeleton.model;

/**
 * A process representing a not elementary happening that takes some time to finish.
 */
public interface Process {
	/**
	 * Starts the process.
	 */
	public void start();
	/**
	 * Updates the process.
	 */
	public void update();
	/**
	 * @return True if the process has finished, false otherwise.
	 */
	public boolean isOver();
	/**
	 * Finishes the process.
	 */
	public void end();
}

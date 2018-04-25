package progetto.network;

import java.io.Serializable;

/**
 * message that is executed at server level.
 */
abstract class AbstractServerRequest implements Serializable
{

	private transient ServerConnection author;

	/**
	 *
	 * @return the server connection that sent this request.
	 */
	ServerConnection getAuthor() {
		return author;
	}

	/**
	 * Sets the author of this message
	 * @param author the new author of this message
	 */
	void setAuthor(ServerConnection author) {
		this.author = author;
	}

	/**
	 * method that is called when the server desires to perform this request.
	 * @param state the server state that received this request.
	 * @param r the room where the author was when this request was received.
	 */
	abstract void execute(ServerState state, AbstractRoom r);
}

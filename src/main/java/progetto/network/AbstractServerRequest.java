package progetto.network;

import java.io.Serializable;

public abstract class AbstractServerRequest implements Serializable
{

	private transient ServerConnection author;

	public ServerConnection getAuthor() {
		return author;
	}

	public void setAuthor(ServerConnection author) {
		this.author = author;
	}

	abstract void execute(ServerState state, AbstractRoom r);
}

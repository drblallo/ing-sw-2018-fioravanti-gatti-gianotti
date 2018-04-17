package progetto.network.connectionstate;

import junit.framework.TestCase;
import progetto.utils.ObserverStub;

public class ServerStateTest extends TestCase {

	public void testServerState() {
		ServerState state = new ServerState();
		assertEquals(state.getRoomCount(), 0);
		assertEquals(state.playerExists(10), false);

		ObserverStub<Integer> playerCreationStub = new ObserverStub<Integer>();
		playerCreationStub.currentVal = -2;
		state.getPlayerChangedRoom().addObserver(playerCreationStub);

		ObserverStub<Room> roomCreationStub = new ObserverStub<Room>();
		roomCreationStub.currentVal = null;
		state.getCreateRoomEvent().addObserver(roomCreationStub);

		int plID = state.getUnusedID();
		assertEquals(false, state.playerExists(plID));
		assertEquals(false, state.playerExists(plID));

		Room r = state.createRoom("testRoom");
		assertEquals(roomCreationStub.currentVal, r);
		assertEquals(state.getRoomCount(), 1);
		assertEquals(state.getRoom(r.getRoomID()), r);
		state.placePlayer(plID, "testPlayer", r.getRoomID());

		assertEquals(state.getRoomOfPlayer(plID), r);
		assertEquals(r.getInfoFromID(plID).getPlayerName(), "testPlayer");
		assertEquals(playerCreationStub.currentVal.intValue(), plID);
		assertEquals(state.playerExists(plID), true);

		state.placePlayer(plID, "testPlayer", -1);
		assertEquals(state.getRoomOfPlayer(plID), null);
		assertEquals(state.playerExists(plID), false);

	}

	public void testRoom() {
		ServerState state = new ServerState();
		Room r = state.createRoom("testRoom");

		ObserverStub<String> changeNameStub = new ObserverStub<String>();
		changeNameStub.currentVal = "NONE";
		r.getNameChangedEvent().addObserver(changeNameStub);

		r.setName("newName");
		assertEquals(r.getName(), "newName");

		ObserverStub<Integer> playerJoinStub = new ObserverStub<Integer>();
		playerJoinStub.currentVal = -2;
		r.getPlayerJoinEvent().addObserver(playerJoinStub);

		ObserverStub<Integer> playerLeaveStub = new ObserverStub<Integer>();
		playerLeaveStub.currentVal = -2;
		r.getPlayerLeaveEvent().addObserver(playerLeaveStub);

		int val = state.getUnusedID();
		state.placePlayer(val, "playerName", r.getRoomID());
		assertEquals(val, playerJoinStub.currentVal.intValue());
		state.placePlayer(val, "playerName", -1);
		assertEquals(val, playerLeaveStub.currentVal.intValue());

		r.removePlayer(100);
	}

	public void testPlayerInfo() {
		ServerState state = new ServerState();
		Room r = state.createRoom("testRoom");
		int val = state.getUnusedID();
		state.placePlayer(val, "playerName", r.getRoomID());

		PlayerInfo info = r.getInfoFromID(val);
		assertEquals(true, info.isObserver());

		ObserverStub<Integer> chairChangedStub = new ObserverStub<Integer>();
		chairChangedStub.currentVal = 100;
		info.getChairChangedEvent().addObserver(chairChangedStub);

		r.setPlayerChair(info.getPlayerID(), 2);
		assertEquals(-1, chairChangedStub.currentVal.intValue());
		assertEquals(2, info.getChairID());
		assertEquals(false, info.isObserver());

		ObserverStub<Boolean> readyStateChanged = new ObserverStub<Boolean>();
		readyStateChanged.currentVal = false;
		info.setReady(true);
		info.getReadyStateChangedEvent().addObserver(readyStateChanged);
		assertEquals(false, readyStateChanged.currentVal.booleanValue());
		assertEquals(true, info.isReady());
	}


}

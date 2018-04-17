package progetto.network.rmi;

import org.junit.Assert;
import org.junit.Test;

import java.rmi.RemoteException;

public class RMISessionTest
{
	@Test
	public void testEquality()
	{
		try {
			RMIRemoteClientSession session1 = new RMIRemoteClientSession();
			RMIRemoteClientSession session2 = new RMIRemoteClientSession();
			Assert.assertTrue(!session1.equals( session2));
			session1.hashCode();
			session2.hashCode();


			RMIRemoteSession s1 = new RMIRemoteSession();
			RMIRemoteSession s2 = new RMIRemoteSession();
			Assert.assertTrue(!s1.equals(s2));
			s1.hashCode();
			s2.hashCode();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testClientHandler()
	{
		try {
			RMIClientHandler hand = new RMIClientHandler(null, new RMIRemoteSession());
			hand.sendMessage("m");
			hand.sendMessage("m");
			hand.sendEnforce(null);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}

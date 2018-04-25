package progetto.network.rmi;

import org.junit.Assert;
import org.junit.Test;
import progetto.utils.Waiter;

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


			RMIRemoteServerSession s1 = new RMIRemoteServerSession();
			RMIRemoteServerSession s2 = new RMIRemoteServerSession();
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
		Waiter paul = new Waiter();
		try
		{
			RMIRemoteServerSession session = new RMIRemoteServerSession();
			RMIHandler hand = new RMIHandler(null, session);

			hand.sendMessage("m");
			hand.sendMessage("m");
			hand.sendEnforce(null);
			paul.wait(50);
			Assert.assertFalse(hand.isRunning());
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
	}
}

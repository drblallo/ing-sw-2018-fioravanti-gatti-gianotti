package progetto.game;

import junit.framework.TestCase;
import org.json.JSONArray;

public class TestWindowFrame extends TestCase {

	public void test1() {

		JSONArray ja = new JSONArray();

		ja.put("Virtus");       //nome carta

		ja.put(5);              //numero token

		ja.put(9);              //numero vincoli valore

		ja.put(0);              //Posizione x primo vincolo valore
		ja.put(0);              //Posizione y primo vincolo valore
		ja.put(Value.FOUR);     //Vincolo valore

		ja.put(2);              //Altri vincoli valore
		ja.put(0);
		ja.put(Value.TWO);

		ja.put(3);
		ja.put(0);
		ja.put(Value.FIVE);

		ja.put(2);
		ja.put(1);
		ja.put(Value.SIX);

		ja.put(4);
		ja.put(1);
		ja.put(Value.TWO);

		ja.put(1);
		ja.put(2);
		ja.put(Value.THREE);

		ja.put(3);
		ja.put(2);
		ja.put(Value.FOUR);

		ja.put(0);
		ja.put(3);
		ja.put(Value.FIVE);

		ja.put(2);
		ja.put(3);
		ja.put(Value.ONE);

		ja.put(4);              //numero vincoli colore

		ja.put(4);              //Posizione x primo vincolo colore
		ja.put(0);              //Posizione y primo vincolo colore
		ja.put(Color.GREEN);    //Vincolo colore

		ja.put(3);              //Altri vincoli colore
		ja.put(1);
		ja.put(Color.GREEN);

		ja.put(2);
		ja.put(2);
		ja.put(Color.GREEN);

		ja.put(1);
		ja.put(3);
		ja.put(Color.GREEN);

		WindowFrame wf = new WindowFrame(ja);

		assertEquals(null, wf.getColorBond(0,0));
		assertEquals(Color.GREEN, wf.getColorBond(4,0));
		assertEquals(Value.THREE, wf.getValueBond(1,2));
		assertEquals(null, wf.getValueBond(1,1));
		assertEquals(null, wf.getColorBond(1,1));

		assertEquals(5, wf.getFavorToken());

		assertEquals("Virtus", wf.getName());



	}


}

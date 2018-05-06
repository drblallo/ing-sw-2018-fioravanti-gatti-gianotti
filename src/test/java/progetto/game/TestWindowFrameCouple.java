package progetto.game;

import junit.framework.TestCase;
import org.json.JSONArray;

public class TestWindowFrameCouple extends TestCase {

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



		JSONArray jb = new JSONArray();

		jb.put("Via Lux");       //nome carta

		jb.put(4);              //numero token

		jb.put(7);              //numero vincoli valore

		jb.put(2);              //Posizione x primo vincolo valore (colonna, da 0 a 4)
		jb.put(0);              //Posizione y primo vincolo valore (riga, da 0 a 3)
		jb.put(Value.SIX);      //Vincolo valore

		jb.put(1);              //Altri vincoli valore
		jb.put(1);
		jb.put(Value.ONE);

		jb.put(2);
		jb.put(1);
		jb.put(Value.FIVE);

		jb.put(4);
		jb.put(1);
		jb.put(Value.TWO);

		jb.put(0);
		jb.put(2);
		jb.put(Value.THREE);

		jb.put(2);
		jb.put(3);
		jb.put(Value.FOUR);

		jb.put(3);
		jb.put(3);
		jb.put(Value.THREE);


		jb.put(5);              //numero vincoli colore

		jb.put(0);              //Posizione x primo vincolo colore
		jb.put(0);              //Posizione y primo vincolo colore
		jb.put(Color.YELLOW);    //Vincolo colore

		jb.put(1);              //Altri vincoli colore
		jb.put(2);
		jb.put(Color.YELLOW);

		jb.put(2);
		jb.put(2);
		jb.put(Color.RED);

		jb.put(3);
		jb.put(2);
		jb.put(Color.PURPLE);

		jb.put(4);
		jb.put(3);
		jb.put(Color.RED);


		WindowFrameCouple wfc = new WindowFrameCouple(ja, jb);

		assertEquals(null, wfc.getWindowFrame(0).getColorBond(0, 1));
		assertEquals(null, wfc.getWindowFrame(1).getColorBond(0,1));
		assertEquals(null, wfc.getWindowFrame(0).getValueBond(0,1));
		assertEquals(null, wfc.getWindowFrame(1).getValueBond(0,1));

		assertEquals(Color.GREEN, wfc.getWindowFrame(0).getColorBond(2,2));
		assertEquals(Color.RED, wfc.getWindowFrame(1).getColorBond(2,2));
		assertEquals(null, wfc.getWindowFrame(0).getValueBond(2,2));
		assertEquals(null, wfc.getWindowFrame(1).getValueBond(2,2));

		assertEquals(null, wfc.getWindowFrame(0).getColorBond( 4,1));
		assertEquals(null, wfc.getWindowFrame(1).getColorBond(4,1));
		assertEquals(Value.TWO, wfc.getWindowFrame(0).getValueBond(4,1));
		assertEquals(Value.TWO, wfc.getWindowFrame(1).getValueBond(4,1));

		assertEquals(null, wfc.getWindowFrame(0).getColorBond(4,3));
		assertEquals(Color.RED, wfc.getWindowFrame(1).getColorBond(4,3));
		assertEquals(null, wfc.getWindowFrame(0).getValueBond(4,3));
		assertEquals(null, wfc.getWindowFrame(1).getValueBond(4,3));

		assertEquals(5, wfc.getWindowFrame(0).getFavorToken());
		assertEquals(4, wfc.getWindowFrame(1).getFavorToken());

		assertEquals("Virtus", wfc.getWindowFrame(0).getName());
		assertEquals("Via Lux", wfc.getWindowFrame(1).getName());

		assertEquals(null, wfc.getWindowFrame(2));

		WindowFrame windowFrame = new WindowFrame(ja);

		assertEquals(windowFrame.getFavorToken(), wfc.getWindowFrame(0).getFavorToken());

	}


}

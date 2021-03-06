package de.hskarlsruhe.vislab1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.annotations.Test;

import de.hska.vism.lab1.example.ws.BuchDO;
import de.hska.vism.lab1.example.ws.BuecherServiceImpl;
import de.hska.vism.lab1.example.ws.BuecherServiceIntf;
import de.hska.vism.lab1.example.ws.BuecherTO;


public class BuecherServiceImplTest {
	private static final BuecherServiceIntf buecherService = new BuecherServiceImpl();
	private static final int ANZAHL_BUECHER = 1000;
	private static final long START_ISBN = 3000000000L;

	static {
		for (int i = 0; i < ANZAHL_BUECHER; i++) {
			BuchDO bu = new BuchDO();
			bu.setIsbn(new Long(START_ISBN + i));
			bu.setTitel("Buch " + i);
			bu.setPreis(new Double(i));
			try {
				buecherService.createBuch(bu);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	@Test
	public void testCreateBuchDuplicateCreateException() {
		try {
			BuchDO bu = new BuchDO();
			bu.setIsbn(Long.valueOf(START_ISBN + ANZAHL_BUECHER - 1));
			bu.setTitel("Buch");
			bu.setPreis(new Double(0));
			buecherService.createBuch(bu);
			fail();
		} catch (Exception ex) {
		}
	}

	@Test
	public void testGetBuchByIsbn() {
		BuecherTO buTO = buecherService.getBuchByIsbn(null);
		assertEquals(0, buTO.getResults().size());
		buTO = buecherService.getBuchByIsbn(Long.valueOf(-1));
		assertEquals(0, buTO.getResults().size());
		buTO = buecherService.getBuchByIsbn(Long.valueOf(START_ISBN
				+ ANZAHL_BUECHER / 2));
		assertEquals(1, buTO.getResults().size());
	}

	@Test
	public void testFindeBuecher() {
		BuchDO bu = new BuchDO();
		BuecherTO buTO = buecherService.findeBuecher(bu);
		assertTrue(buTO.getResults() != null
				&& buTO.getResults().size() >= ANZAHL_BUECHER);
		bu.setTitel("buch");
		buTO = buecherService.findeBuecher(bu);
		assertTrue(buTO.getResults() != null
				&& buTO.getResults().size() >= ANZAHL_BUECHER);
		bu.setTitel(null);
		bu.setIsbn(Long.valueOf(-1));
		buTO = buecherService.findeBuecher(bu);
		assertEquals(0, buTO.getResults().size());
		bu.setIsbn(Long.valueOf(START_ISBN));
		buTO = buecherService.findeBuecher(bu);
		assertEquals(1, buTO.getResults().size());
	}
}
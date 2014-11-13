package de.hska.vism.lab1.ref;

import javax.swing.JOptionPane;
import javax.xml.ws.Endpoint;

import de.hska.vism.lab1.ref.ws.BuecherServiceImpl;

/** Testserver fuer den Webservice */
public class TestWsServer {
	public static void main(final String[] args) {
		String url = (args.length > 0) ? args[0]
				: "http://localhost:4434/buecherservice";
		Endpoint ep = Endpoint.publish(url, new BuecherServiceImpl());
		JOptionPane.showMessageDialog(null, "TestWsServer beenden");
		ep.stop();
	}
}

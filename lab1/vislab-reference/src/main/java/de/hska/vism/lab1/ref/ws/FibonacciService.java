package de.hska.vism.lab1.ref.ws;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FibonacciService {

	int getFibonacci(@WebParam(name = "number") int number);

}

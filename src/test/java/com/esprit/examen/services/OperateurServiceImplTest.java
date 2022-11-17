package com.esprit.examen.services;

import static org.junit.Assert.*;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.OperateurRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperateurServiceImplTest {
	
	@InjectMocks
	OperateurServiceImpl opretateurService;
	
	@Mock
	OperateurRepository operateurrepo;
	
	@Test
	public void testRetrieveOperateurBYid() {
	//	List<Stock> stocks = stockService.retrieveAllStocks();
	//	int expected=stocks.size();
		Operateur s = new Operateur(null, "test","test prenom","pass", null);
		Mockito.when(operateurrepo.findById(Mockito.anyLong()))
		.thenReturn(Optional.of(s));
		//Operateur savedoperateur= opretateurService.addOperateur(s);
		
	//	assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(opretateurService.retrieveOperateur(1l));
		//opretateurService.deleteOperateur(savedoperateur.getIdOperateur());
		
	} 
	
	/*@Test
	public void testAddStockOptimized() {
		Stock s = new Stock("stock test",10,100);
		Stock savedStock= stockService.addStock(s);
		assertNotNull(savedStock.getIdStock());
		assertSame(10, savedStock.getQte());
		assertTrue(savedStock.getQteMin()>0);
		stockService.deleteStock(savedStock.getIdStock());
		
	} 
	
	@Test
	public void testDeleteStock() {
		Stock s = new Stock("stock test",30,60);
		Stock savedStock= stockService.addStock(s);
		stockService.deleteStock(savedStock.getIdStock());
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}
*/
}
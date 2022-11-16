package com.esprit.examen.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.core.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Fournisseur;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CategorieProduitServiceImplTest {

	@Autowired
	IFournisseurService fornisseurservice;

	
	@Test
	public void retrieveAllFournisseurs() throws ParseException {
		List<Fournisseur> fournisseurs = (List<Fournisseur>) fornisseurservice.retrieveAllFournisseurs();
		int expected = fournisseurs.size();
		Fournisseur f = new Fournisseur();
		Fournisseur fournisseur = fornisseurservice.addFournisseur(f);
		assertEquals(expected + 1, fornisseurservice.retrieveAllFournisseurs().size());
		fornisseurservice.deleteFournisseur(fournisseur.getIdFournisseur());
	}
	
}
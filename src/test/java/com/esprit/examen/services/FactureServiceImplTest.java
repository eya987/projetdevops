package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.repositories.FactureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FactureServiceImplTest {

	FactureRepository factureRepository = Mockito.mock(FactureRepository.class);
	@InjectMocks
	private FactureServiceImpl factureService;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Facture f = Facture.builder().montantFacture(0).montantRemise(0).archivee(true).build();
	private List<Facture> list = new ArrayList<Facture>() {

		{
			add(Facture.builder().montantFacture(0).montantRemise(0).archivee(true).build());
			add(Facture.builder().montantFacture(10).montantRemise(0).archivee(false).build());

		}
	};

	@Test
	void addFactureTest() {
		Mockito.when(factureRepository.save(Mockito.any(Facture.class))).then(invocation -> {
			Facture model = invocation.getArgument(0, Facture.class);
			model.setIdFacture((long) 1);
			return model;
		});
		log.info("Avant" + f.toString());
		Facture facture = factureService.addFacture(f);
		assertSame(facture, f);
		log.info("apres" + f.toString());
	}

	@Test
	void retrieveAllFactures() {
		Mockito.when(factureRepository.findAll()).thenReturn(list);
		List<Facture> factures = factureService.retrieveAllFactures();
		assertNotNull(factures);
		for (Facture facture : factures) {
			log.info(facture.toString());
		}
	}

	@Test
	void retrieveFactureTest() {
		Mockito.when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(f));
		Facture facture = factureService.retrieveFacture((long) 1);
		assertNotNull(facture);
		log.info("facctures:" + facture.toString());
		verify(factureRepository).findById(Mockito.anyLong());

	}
}
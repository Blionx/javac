package com.mercadolibre.pens_luis_bootcamp_final.unit.beans;

import com.mercadolibre.pens_luis_bootcamp_final.dto.SampleDTO;
import com.mercadolibre.pens_luis_bootcamp_final.beans.RandomSampleBean;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomSampleBeanTest {

	@Test
	public void randomPositiveTestOK() {
		RandomSampleBean randomSample = new RandomSampleBean();

		SampleDTO sample = randomSample.random();
		
		assertTrue(sample.getRandom() >= 0);
	}
}

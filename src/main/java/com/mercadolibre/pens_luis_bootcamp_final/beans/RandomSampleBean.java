package com.mercadolibre.pens_luis_bootcamp_final.beans;

import com.mercadolibre.pens_luis_bootcamp_final.dto.SampleDTO;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomSampleBean {
	private Random random = new Random();

	public SampleDTO random() {
		return new SampleDTO(random.nextInt(Integer.MAX_VALUE));
	}
}


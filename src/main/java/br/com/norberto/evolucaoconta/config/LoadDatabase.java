package br.com.norberto.evolucaoconta.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.norberto.evolucaoconta.model.ContaEnergia;
import br.com.norberto.evolucaoconta.model.StatusConta;
import br.com.norberto.evolucaoconta.repository.ContaEnergiaRepository;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(ContaEnergiaRepository repository) {
	return args -> {
	    log.info("Preloading " + repository.save(createConta1()));
	    log.info("Preloading " + repository.save(createConta2()));
	    log.info("Preloading " + repository.save(createConta3()));
	};
    }

    private ContaEnergia createConta1() {
	return new ContaEnergia(LocalDate.of(2019, Month.JANUARY, 1), LocalDate.of(2020, Month.JANUARY, 1),
		new BigDecimal("54.3"), StatusConta.ABERTA, Month.FEBRUARY, "123", "321", 48.1);
    }

    private ContaEnergia createConta2() {
	return new ContaEnergia(LocalDate.of(2019, Month.MARCH, 21), LocalDate.of(2019, Month.MAY, 1),
		new BigDecimal("34.1"), StatusConta.ATRASADA, Month.APRIL, "456", "654", 23.1);
    }

    private ContaEnergia createConta3() {
	return new ContaEnergia(LocalDate.of(2019, Month.SEPTEMBER, 16), LocalDate.of(2019, Month.DECEMBER, 5),
		new BigDecimal("45.7"), StatusConta.PAGA, Month.OCTOBER, "687", "876", 11.1);
    }
}

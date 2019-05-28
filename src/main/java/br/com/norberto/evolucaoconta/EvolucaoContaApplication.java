package br.com.norberto.evolucaoconta;

import java.util.Locale;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import br.com.norberto.evolucaoconta.dto.ContaDTO;
import br.com.norberto.evolucaoconta.model.ContaEnergia;

@SpringBootApplication
public class EvolucaoContaApplication {

  public static void main(String[] args) {
    SpringApplication.run(EvolucaoContaApplication.class, args);
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver locale = new SessionLocaleResolver();
    locale.setDefaultLocale(new Locale("pt", "BR"));
    return locale;
  }

  @Bean
  public ModelMapper modelMapper() {

    ModelMapper modelMapper = new ModelMapper();
    modelMapper.createTypeMap(ContaEnergia.class, ContaDTO.class);
    return modelMapper;
  }
}

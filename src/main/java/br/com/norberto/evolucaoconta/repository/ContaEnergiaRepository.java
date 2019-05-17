package br.com.norberto.evolucaoconta.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.norberto.evolucaoconta.model.ContaEnergia;

@Transactional
public interface ContaEnergiaRepository extends JpaRepository<ContaEnergia, Long> {

}

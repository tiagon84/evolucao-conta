package br.com.norberto.evolucaoconta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import br.com.norberto.evolucaoconta.model.Conta;

@NoRepositoryBean
public interface ContaBaseRepository<T extends Conta> extends JpaRepository<T, Long> {

}

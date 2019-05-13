package br.com.norberto.evolucaoconta.repository;

import javax.transaction.Transactional;
import br.com.norberto.evolucaoconta.model.Conta;

@Transactional
public interface ContaEnergiaRepository extends ContaBaseRepository<Conta> {

}

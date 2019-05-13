package br.com.norberto.evolucaoconta.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import javax.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ContaEnergia extends Conta {

  private static final String UNIDADE = "kWh";

  private String numInstalacao;
  private String numCliente;
  private Double consumo;

  public ContaEnergia(LocalDate dataEmissao, LocalDate dataVencimento, BigDecimal valor,
      StatusConta status, Month mesReferente, String numInstalacao, String numCliente,
      Double consumo) {
    super(dataEmissao, dataVencimento, valor, status, mesReferente);
    this.numInstalacao = numInstalacao;
    this.numCliente = numCliente;
    this.consumo = consumo;
  }


  public ContaEnergia() {}



}

package br.com.norberto.evolucaoconta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import lombok.Data;

@Inheritance
@Data
@Entity
public abstract class Conta implements Serializable {

  @Id
  @GeneratedValue
  private Long id;

  private LocalDate dataEmissao;

  private LocalDate dataVencimento;

  private BigDecimal valor;

  @Enumerated(EnumType.STRING)
  private StatusConta status;

  @Enumerated(EnumType.STRING)
  private Month mesReferente;

  public Conta(LocalDate dataEmissao, LocalDate dataVencimento, BigDecimal valor,
      StatusConta status, Month mesReferente) {
    super();
    this.dataEmissao = dataEmissao;
    this.dataVencimento = dataVencimento;
    this.valor = valor;
    this.status = status;
    this.mesReferente = mesReferente;
  }

  public Conta() {
    super();
    // TODO Auto-generated constructor stub
  }



}

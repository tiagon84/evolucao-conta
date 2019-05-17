package br.com.norberto.evolucaoconta.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Month;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContaEnergia implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  private Date dataEmissao;

  private Date dataVencimento;

  private BigDecimal valor;

  @Enumerated(EnumType.STRING)
  private StatusConta status;

  @Enumerated(EnumType.STRING)
  private Month mesReferente;

  private String numInstalacao;

  private String numCliente;

  private Double consumo;

  public ContaEnergia() {}

  public ContaEnergia(Date dataEmissao, Date dataVencimento, BigDecimal valor, StatusConta status,
      Month mesReferente, String numInstalacao, String numCliente, Double consumo) {
    super();
    this.dataEmissao = dataEmissao;
    this.dataVencimento = dataVencimento;
    this.valor = valor;
    this.status = status;
    this.mesReferente = mesReferente;
    this.numInstalacao = numInstalacao;
    this.numCliente = numCliente;
    this.consumo = consumo;
  }

}

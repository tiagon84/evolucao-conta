package br.com.norberto.evolucaoconta.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ContaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Date dataEmissao;

  private Date dataVencimento;

  private BigDecimal valor;

  private boolean status;

  private int mesReferente;

  private String numInstalacao;
  private String numCliente;
  private Double consumo;


  public Date getDataEmissao() {
    return dataEmissao;
  }

  public void setDataEmissao(Date dataEmissao) {
    this.dataEmissao = dataEmissao;
  }

  public Date getDataVencimento() {
    return dataVencimento;
  }

  public void setDataVencimento(Date dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public int getMesReferente() {
    return mesReferente;
  }

  public void setMesReferente(int mesReferente) {
    this.mesReferente = mesReferente;
  }

  public String getNumInstalacao() {
    return numInstalacao;
  }

  public void setNumInstalacao(String numInstalacao) {
    this.numInstalacao = numInstalacao;
  }

  public String getNumCliente() {
    return numCliente;
  }

  public void setNumCliente(String numCliente) {
    this.numCliente = numCliente;
  }

  public Double getConsumo() {
    return consumo;
  }

  public void setConsumo(Double consumo) {
    this.consumo = consumo;
  }



}

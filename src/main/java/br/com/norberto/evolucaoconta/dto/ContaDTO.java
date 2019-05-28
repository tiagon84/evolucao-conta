package br.com.norberto.evolucaoconta.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ContaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String dataEmissao;

  private String dataVencimento;

  private String valor;

  private boolean status;

  private int mesReferente;

  private String numInstalacao;
  private String numCliente;
  private String consumo;


}

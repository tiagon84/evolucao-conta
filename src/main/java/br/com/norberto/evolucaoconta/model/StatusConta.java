package br.com.norberto.evolucaoconta.model;

public enum StatusConta {
  ABERTA("em aberto"), PAGA("paga"), ATRASADA("atrasada");

  private String texto;

  private StatusConta(String texto) {
    this.texto = texto;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

}

package br.com.norberto.evolucaoconta.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.google.common.base.Preconditions;
import br.com.norberto.evolucaoconta.dto.ContaDTO;
import br.com.norberto.evolucaoconta.model.Conta;
import br.com.norberto.evolucaoconta.model.ContaEnergia;
import br.com.norberto.evolucaoconta.model.StatusConta;
import br.com.norberto.evolucaoconta.repository.ContaEnergiaRepository;

@RestController
@RequestMapping("/conta")
class ContaController {

  private ContaEnergiaRepository contaEnergiaRepository;

  public ContaController(ContaEnergiaRepository contaEnergiaRepository) {
    this.contaEnergiaRepository = contaEnergiaRepository;
  }

  @GetMapping("/all")
  public List<Conta> getAll() {
    return null;
  }

  @GetMapping("/{id}")
  public Conta getOne(@PathVariable("id") Long id) {
    return contaEnergiaRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta Not Found"));
  }

  @GetMapping("/cadastrar")
  public String getAlla() {
    return "cadastrar";
  }

  @PostMapping("/add")
  public Conta cadastrarConta(@RequestBody ContaDTO contaEnergia) {
    Preconditions.checkNotNull(contaEnergia);

    ContaEnergia conta = createConta(contaEnergia);

    contaEnergiaRepository.save(conta);

    return conta;
  }

  private ContaEnergia createConta(ContaDTO contaEnergia) {
    ContaEnergia conta = new ContaEnergia(contaEnergia.getDataEmissao(),
        contaEnergia.getDataEmissao(), contaEnergia.getValor(), setContaStatus(contaEnergia),
        Month.of(contaEnergia.getMesReferente()), contaEnergia.getNumInstalacao(),
        contaEnergia.getNumCliente(), contaEnergia.getConsumo());
    return conta;
  }

  private StatusConta setContaStatus(ContaDTO contaEnergia) {
    if (contaEnergia.isStatus()) {
      return StatusConta.PAGA;
    }

    if (contaEnergia.getDataVencimento().isBefore(LocalDate.now())) {
      return StatusConta.ATRASADA;
    }

    return StatusConta.ABERTA;
  }
}

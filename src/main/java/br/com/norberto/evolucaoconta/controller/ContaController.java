package br.com.norberto.evolucaoconta.controller;

import java.time.LocalDate;
import java.time.Month;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.base.Preconditions;
import br.com.norberto.evolucaoconta.dto.ContaDTO;
import br.com.norberto.evolucaoconta.exception.NotFoundResponseException;
import br.com.norberto.evolucaoconta.model.ContaEnergia;
import br.com.norberto.evolucaoconta.model.StatusConta;
import br.com.norberto.evolucaoconta.repository.ContaEnergiaRepository;
import br.com.norberto.evolucaoconta.util.Utils;

@RestController
class ContaController {

  @Autowired
  private ContaEnergiaRepository contaEnergiaRepository;

  public ContaController(ContaEnergiaRepository contaEnergiaRepository) {
    this.contaEnergiaRepository = contaEnergiaRepository;
  }

  @GetMapping("/contas")
  public ModelAndView getAll(final ContaEnergia conta, final BindingResult bindingResult) {

    ModelAndView mv = new ModelAndView("/contas");
    mv.addObject("contas", contaEnergiaRepository.findAll());
    mv.addObject("conta", conta);

    return mv;
  }

  @GetMapping("/contas/{id}")
  public ContaEnergia getOne(@PathVariable("id") Long id) {
    return contaEnergiaRepository.findById(id)
        .orElseThrow(() -> new NotFoundResponseException("Conta Not Found"));
  }

  @GetMapping("/add")
  public ModelAndView add(final ContaEnergia conta, final BindingResult bindingResult) {
    ModelAndView mv = new ModelAndView("/add");
    mv.addObject("conta", conta);
    return mv;
  }

  @PostMapping("/contas")
  public ContaEnergia saveConta(@RequestBody ContaDTO contaEnergia) {
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

    if (Utils.convertToLocalDate(contaEnergia.getDataVencimento()).isBefore(LocalDate.now())) {
      return StatusConta.ATRASADA;
    }

    return StatusConta.ABERTA;
  }

}

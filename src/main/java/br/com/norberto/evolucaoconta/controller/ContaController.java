package br.com.norberto.evolucaoconta.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Month;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.google.common.base.Preconditions;
import br.com.norberto.evolucaoconta.dto.ContaDTO;
import br.com.norberto.evolucaoconta.exception.NotFoundResponseException;
import br.com.norberto.evolucaoconta.model.ContaEnergia;
import br.com.norberto.evolucaoconta.model.StatusConta;
import br.com.norberto.evolucaoconta.repository.ContaEnergiaRepository;
import br.com.norberto.evolucaoconta.util.Utils;

@Controller
@RequestMapping("/evolucao-conta")
class ContaController {

  @Autowired
  private ContaEnergiaRepository contaEnergiaRepository;

  @Autowired
  private ModelMapper modelMapper;

  public ContaController(ContaEnergiaRepository contaEnergiaRepository) {
    this.contaEnergiaRepository = contaEnergiaRepository;
  }

  @RequestMapping(path = "/contas", method = RequestMethod.GET)
  public String getAll(final ContaDTO conta, Model model) {

    model.addAttribute("contas", contaEnergiaRepository.findAll());
    model.addAttribute("conta", conta);

    return "contas";
  }

  @RequestMapping(path = "/contas/{id}/edit")
  @ResponseBody
  public ContaDTO editConta(@PathVariable("id") Long id) {
    ContaEnergia teste = contaEnergiaRepository.findById(id)
        .orElseThrow(() -> new NotFoundResponseException("Conta Not Found"));

    return converterToDTO(teste);
  }

  @RequestMapping(path = "/contas/{id}/delete")
  public String deleteConta(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
    contaEnergiaRepository.deleteById(id);
    redirectAttributes.addFlashAttribute("message",
        String.format("Conta {%d} foi deletada com sucesso ", id));
    return "redirect:/evolucao-conta/contas";
  }


  @RequestMapping(path = "/contas", method = RequestMethod.POST)
  public String saveConta(final ContaDTO contaEnergia, final BindingResult bindingResult,
      final ModelMap model) throws NumberFormatException, ParseException {
    Preconditions.checkNotNull(contaEnergia);

    ContaEnergia conta = createConta(contaEnergia);

    contaEnergiaRepository.save(conta);

    return "redirect:/evolucao-conta/contas";
  }

  private ContaEnergia createConta(ContaDTO contaDTO) throws NumberFormatException, ParseException {
    ContaEnergia conta = new ContaEnergia();

    conta.setId(contaDTO.getId());
    conta.setConsumo(new BigDecimal(
        NumberFormat.getNumberInstance().parse(contaDTO.getConsumo()).doubleValue()));
    conta.setDataEmissao(Utils.parseData(contaDTO.getDataEmissao()));
    conta.setDataVencimento(Utils.parseData(contaDTO.getDataVencimento()));
    conta.setMesReferente(Month.of(contaDTO.getMesReferente()));
    conta.setNumCliente(contaDTO.getNumCliente());
    conta.setNumInstalacao(contaDTO.getNumInstalacao());
    conta.setStatus(setContaStatus(contaDTO));
    conta.setValor(
        new BigDecimal(NumberFormat.getNumberInstance().parse(contaDTO.getValor()).doubleValue()));

    return conta;
  }

  private StatusConta setContaStatus(ContaDTO contaEnergia) throws ParseException {
    if (contaEnergia.isStatus()) {
      return StatusConta.PAGA;
    }

    if (Utils.convertToLocalDate(Utils.parseData(contaEnergia.getDataVencimento()))
        .isBefore(LocalDate.now())) {
      return StatusConta.ATRASADA;
    }

    return StatusConta.ABERTA;
  }

  private ContaDTO converterToDTO(ContaEnergia conta) {

    TypeMap<ContaEnergia, ContaDTO> typeMap =
        modelMapper.getTypeMap(ContaEnergia.class, ContaDTO.class);

    ContaDTO map = typeMap.addMappings(mapper -> {
      mapper.map(x -> conta.getConsumo(), ContaDTO::setConsumo);
      mapper.map(x -> Utils.dateToString(conta.getDataEmissao()), ContaDTO::setDataEmissao);
      mapper.map(x -> Utils.dateToString(conta.getDataVencimento()).toString(),
          ContaDTO::setDataVencimento);
      mapper.map(x -> conta.getMesReferente().getValue(), ContaDTO::setMesReferente);
      mapper.map(x -> conta.getNumCliente(), ContaDTO::setNumCliente);
      mapper.map(x -> conta.getNumInstalacao(), ContaDTO::setNumInstalacao);
      mapper.map(x -> extractVal(conta.getStatus()), ContaDTO::setStatus);
      mapper.map(x -> conta.getValor().toString(), ContaDTO::setValor);
    }).map(conta);
    return map;
  }

  private boolean extractVal(StatusConta status) {
    if (status.equals(StatusConta.PAGA)) {
      return true;
    }
    return false;
  }

}

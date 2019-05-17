package br.com.norberto.evolucaoconta.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

  public static LocalDate convertToLocalDate(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static Date convertToDate(LocalDate dateToConvert) {
    return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }
}

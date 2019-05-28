package br.com.norberto.evolucaoconta.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
  private final static String PATTERN = "dd/MM/yyyy";

  public static LocalDate convertToLocalDate(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static Date convertToDate(LocalDate dateToConvert) {
    return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date parseData(String dateValue) throws ParseException {
    return new SimpleDateFormat("dd/MM/yyyy").parse(dateValue);
  }

  public static String dateToString(Date dateToConvert) {
    return new SimpleDateFormat(PATTERN).format(dateToConvert);
  }
}

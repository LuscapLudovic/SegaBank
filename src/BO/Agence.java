package BO;

public class Agence {

  private Integer id;

  private String code;

  private String Adresse;

  public Agence(String code, String adresse) {
    this.code = code;
    Adresse = adresse;
  }

  public Integer getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getAdresse() {
    return Adresse;
  }

  public void setAdresse(String adresse) {
    Adresse = adresse;
  }
}
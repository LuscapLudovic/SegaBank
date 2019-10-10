package BO;

public class Agence {

  //region Properties
  private Integer id;

  private String code;

  private String Adresse;
  //endregion

  //region Constructors
  public Agence() {}

  public Agence(String code, String adresse) {
    this.code = code;
    Adresse = adresse;
  }
  //endregion

  //region Getter and Setter
  public void setId(Integer id) { this.id = id; }

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
  //endregion

  //region Methods
  @Override
  public String toString(){
    return "Id: " + id + " | Code: " + code + " | Adresse: " + Adresse;
  }
  //endregion

}
package BO;

public class TypeCompte {

  private Integer Id;

  private Integer libelle;

  public TypeCompte() { }

  public TypeCompte(Integer libelle) {
    this.libelle = libelle;
  }

  public Integer getId() {
    return Id;
  }

  public Integer getLibelle() {
    return libelle;
  }

  public void setLibelle(Integer libelle) {
    this.libelle = libelle;
  }
}
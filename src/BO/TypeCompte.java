package BO;

import java.sql.Connection;

public class TypeCompte {

  //region Properties
  private Integer Id;

  private String libelle;
  //endregion

  //region Constructors
  public TypeCompte() { }

  public TypeCompte(String libelle) {
    this.libelle = libelle;
  }
  //endregion

  //region Getter and Setter
  public void setId(Integer id) { this.Id = id;}

  public Integer getId() {
    return Id;
  }

  public String getLibelle() { return libelle; }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }
  //endregion

  //region Methods

  @Override
  public String toString(){
    return  "Id: " + Id +
            " | libelle: " + libelle;
  }

  //endregion

}
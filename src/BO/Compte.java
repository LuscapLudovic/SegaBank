package BO;

public class Compte {

  private Integer Id;

  private double Solde;

  private TypeCompte TypeCompte;

  private double decouvert;

  private double tauxInteret;

  private Agence agenceId;

  public Compte(double solde, BO.TypeCompte typeCompte, double decouvert, double tauxInteret, Agence agenceId) {
    Solde = solde;
    TypeCompte = typeCompte;
    this.decouvert = decouvert;
    this.tauxInteret = tauxInteret;
    this.agenceId = agenceId;
  }

  public Integer getId() {
    return Id;
  }

  public double getSolde() {
    return Solde;
  }

  public void setSolde(double solde) {
    Solde = solde;
  }

  public BO.TypeCompte getTypeCompte() {
    return TypeCompte;
  }

  public void setTypeCompte(BO.TypeCompte typeCompte) {
    TypeCompte = typeCompte;
  }

  public double getDecouvert() {
    return decouvert;
  }

  public void setDecouvert(double decouvert) {
    this.decouvert = decouvert;
  }

  public double getTauxInteret() {
    return tauxInteret;
  }

  public void setTauxInteret(double tauxInteret) {
    this.tauxInteret = tauxInteret;
  }

  public Agence getAgenceId() {
    return agenceId;
  }

  public void setAgenceId(Agence agenceId) {
    this.agenceId = agenceId;
  }

  public void ToString() {
  }

  public void CalculInteret() {
  }

}
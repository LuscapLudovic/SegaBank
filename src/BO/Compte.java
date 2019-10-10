package BO;

public class Compte {

  private Integer Id;

  private double Solde;

  private TypeCompte TypeCompte;

  private double decouvert;

  private double tauxInteret;

  private Agence agence;

  public Compte(){

  }

  public Compte(double solde, BO.TypeCompte typeCompte, double decouvert, double tauxInteret, Agence agenceId) {
    Solde = solde;
    TypeCompte = typeCompte;
    this.decouvert = decouvert;
    this.tauxInteret = tauxInteret;
    this.agence = agenceId;
  }

  public void setId(Integer id) { this.Id = id; }

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

  public Agence getAgence() {
    return agence;
  }

  public void setAgence(Agence agence) {
    this.agence = agence;
  }

  public void ToString() {
  }

  public void CalculInteret() {
  }

}
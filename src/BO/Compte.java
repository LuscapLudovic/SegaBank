package BO;

import Repository.CompteRepository;

public class Compte {

  //region Properties
  private Integer Id;

  private double Solde;

  private TypeCompte TypeCompte;

  private double decouvert;

  private double tauxInteret;

  private Agence agence;
  //endregion

  //region Constructors
  public Compte(){

  }

  public Compte(double solde, BO.TypeCompte typeCompte, double decouvert, double tauxInteret, Agence agenceId) {
    Solde = solde;
    TypeCompte = typeCompte;
    this.decouvert = decouvert;
    this.tauxInteret = tauxInteret;
    this.agence = agenceId;
  }
  //endregion

  //region Getter and Setter
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
  //endregion

  //region Methods
  @Override
  public String toString() {
    return  "Id: " + Id +
            " | Solde: " + Solde +
            (TypeCompte.getLibelle().equals("Simple")  ? " | Decouvert: " + decouvert : "") +
            (TypeCompte.getLibelle().equals("Epargne")  ? " | Taux Interet : " + tauxInteret : "") +
            " | Type Compte: " + TypeCompte.getLibelle() +
            " | Agence : " + agence.getCode();
  }

  public void CalculInteret() throws Exception {
    if (this.TypeCompte.getLibelle().equals("Payant")){
      Solde += tauxInteret*Solde;
      try (CompteRepository comptRepo = new CompteRepository()){
        comptRepo.Update(this);
      }
    }else{
      System.out.println("Votre compte ne vous permet pas d'avoir des interets, veuillez prendre un compte de type 'Payant'");
    }
  }
  //endregion

}
package BO;

import Repository.CompteRepository;
import Repository.OperationRepository;

public class Operation {

  //region Properties
  private Integer Id;

  private Compte compteDeb;

  private Compte compteBenef;

  private double montant;
  //endregion

  //region Constructors
  public Operation(){

  }

  public Operation(Compte compteDebId, Compte compteBenefId, double montant) {
    this.compteDeb = compteDebId;
    this.compteBenef = compteBenefId;
    this.montant = montant;
  }
  //endregion

  //region Getter and Setter
  public void setId( Integer id ) { this.Id = id; }

  public Integer getId() {
    return Id;
  }

  public Compte getCompteDeb() {
    return compteDeb;
  }

  public void setCompteDeb(Compte compteDeb) {
    this.compteDeb = compteDeb;
  }

  public Compte getCompteBenef() {
    return compteBenef;
  }

  public void setCompteBenef(Compte compteBenef) {
    this.compteBenef = compteBenef;
  }

  public double getMontant() {
    return montant;
  }

  public void setMontant(double montant) {
    this.montant = montant;
  }
  //endregion

  //region Methods
  @Override
  public String toString(){
    return "Id: " + Id +
            " | Compte Debitant: " + compteDeb.getId() +
            " | Compte Beneficiaire: " + compteBenef.getId() +
            " | Montant: " + montant;
  }

  public boolean ExecOpe(){
    boolean exec = false, compteValid = false;
    if (compteDeb.getTypeCompte().getLibelle().equals("simple")){
      if ((compteDeb.getSolde() - montant) >= compteDeb.getDecouvert() ){
        compteValid = true;
      }
    }else if(compteDeb.getTypeCompte().getLibelle().equals("payant")){
      compteDeb.setSolde(compteDeb.getSolde() - (montant*0.05));
      if (compteDeb.getSolde() - montant >= 0){
        compteValid = true;
      }
    }
    if (compteValid){
      compteDeb.setSolde(compteDeb.getSolde() - montant);
      compteBenef.setSolde(compteBenef.getSolde() + montant);
      try(OperationRepository opRepo = new OperationRepository(); CompteRepository comptRepo = new CompteRepository() ) {
        opRepo.Add(this);
        comptRepo.Update(compteDeb);
        comptRepo.Update(compteBenef);
        exec = true;
      }catch (Exception e){
        System.out.println("Une erreur est survenue lors de la transaction \n Exception: " + e);
      }
    }else{
      System.out.println("Le compte debitant ne permet pas d'effectuer la transaction");
    }

    return exec;
  }

  public boolean Retrait(){
    boolean exec = false, compteValid = false;
    if (compteDeb.getTypeCompte().getLibelle().equals("simple")){
      if ((compteDeb.getSolde() - montant) >= compteDeb.getDecouvert() ){
        compteValid = true;
      }
    }else if(compteDeb.getTypeCompte().getLibelle().equals("payant")){
      double tax = (montant*0.05);
      compteDeb.setSolde(compteDeb.getSolde() - tax);
      if (compteDeb.getSolde() - montant >= 0){
        compteValid = true;
      }else{
        compteDeb.setSolde(compteDeb.getSolde() + tax);
      }
    }
    if (compteValid){
      compteDeb.setSolde(compteDeb.getSolde() - montant);
      try(OperationRepository opRepo = new OperationRepository(); CompteRepository comptRepo = new CompteRepository() ) {
        opRepo.Add(this);
        comptRepo.Update(compteDeb);
        exec = true;
      }catch (Exception e){
        System.out.println("Une erreur est survenue lors de la transaction \n Exception: " + e);
      }
    }else{
      System.out.println("Le compte debitant ne permet pas d'effectuer la transaction");
    }

    return exec;
  }
  //endregion

}
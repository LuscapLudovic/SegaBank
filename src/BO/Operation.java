package BO;

public class Operation {

  private Integer id;

  private Compte compteDebId;

  private Compte compteBenefId;

  private double montant;

  public Operation(Compte compteDebId, Compte compteBenefId, double montant) {
    this.compteDebId = compteDebId;
    this.compteBenefId = compteBenefId;
    this.montant = montant;
  }

  public Integer getId() {
    return id;
  }

  public Compte getCompteDebId() {
    return compteDebId;
  }

  public void setCompteDebId(Compte compteDebId) {
    this.compteDebId = compteDebId;
  }

  public Compte getCompteBenefId() {
    return compteBenefId;
  }

  public void setCompteBenefId(Compte compteBenefId) {
    this.compteBenefId = compteBenefId;
  }

  public double getMontant() {
    return montant;
  }

  public void setMontant(double montant) {
    this.montant = montant;
  }
}
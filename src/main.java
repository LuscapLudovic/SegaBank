import BO.Agence;
import BO.TypeCompte;
import Repository.AgenceRepository;
import Repository.TypeCompteRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class main {

    private static final String INSERT = "ajouter";
    private static final String DELETE = "supprimer";
    private static final String UPDATE = "modifier";

    public static void main( String... args) throws Exception {

        FirstMenu();

    }

    private static void FirstMenu() throws Exception {
        boolean exit = false;

        do {

            System.out.println("------- Menu Choix -------");
            System.out.println("1 - " + INSERT + " des données");
            System.out.println("2 - " + UPDATE + " des données");
            System.out.println("3 - " + DELETE + " des données");
            System.out.println("4 - Effectuer des transactions");
            System.out.println("0 - Quitter le programme");

            int choice;
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    CRUDMenu(INSERT);
                    break;
                case 2:
                    CRUDMenu(UPDATE);
                    break;
                case 3:
                    CRUDMenu(DELETE);
                    break;
                case 4:
                    OperationMenu();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    break;
            }

        }while (!exit);
    }

    private static void CRUDMenu(String mode) throws Exception {

        boolean exit = false;

        do {

            System.out.println("------- Menu "+ mode +" -------");
            System.out.println("1 - " + mode + " des Agences");
            System.out.println("2 - " + mode + " des TypeComptes");
            System.out.println("3 - " + mode + " des Compte");
            System.out.println("0 - Quitter le menu " + mode );

            int choice;
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    System.out.println("formulaire pour " + mode + " des agences");
                    try(AgenceRepository repo = new AgenceRepository()){
                        ArrayList<Agence> listAgence = repo.getAll();
                        Agence agence = null;
                        String code = "";
                        String adresse = "";
                        switch (mode){
                            case INSERT:
                                boolean valid;
                                do {
                                    valid = true;
                                    System.out.println("Saississez le code de la nouvelle agence :");
                                    code = sc.nextLine();
                                    for (Agence tmpAgence : listAgence){
                                        if (tmpAgence.getCode().equals(code)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                System.out.println("Saississez l'adresse de la nouvelle agence :");
                                adresse = sc.nextLine();
                                agence = new Agence(code, adresse);
                                repo.Add(agence);
                                System.out.println("L'agence a bien ete ajouter");
                                break;
                            case UPDATE:
                                for (Agence tmpAgence : listAgence){
                                    System.out.println(tmpAgence.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saississez l'Id de l'agence a modifier");
                                    int selectAgence = sc.nextInt();
                                    sc.nextLine();
                                    for (Agence tmpAgence : listAgence){
                                        if (tmpAgence.getId() == selectAgence){
                                            agence = tmpAgence;
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                do {
                                    valid = true;
                                    System.out.println("Saississez le code de la nouvelle agence :");
                                    code = sc.nextLine();
                                    for (Agence tmpAgence : listAgence){
                                        if (agence.getId() != tmpAgence.getId() &&  tmpAgence.getCode().equals(code)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                System.out.println("Saississez l'adresse de la nouvelle agence :");
                                adresse = sc.nextLine();
                                agence.setCode(code);
                                agence.setAdresse(adresse);
                                repo.Update(agence);
                                System.out.println("L'agence a bien ete mise a jour");
                                break;
                            case DELETE:
                                for (Agence tmpAgence : listAgence){
                                    System.out.println(tmpAgence.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saississez l'Id de l'agence a supprimer");
                                    int selectAgence = sc.nextInt();
                                    sc.nextLine();
                                    for (Agence tmpAgence : listAgence){
                                        if (tmpAgence.getId() == selectAgence){
                                            agence = tmpAgence;
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                repo.Remove(agence);
                                System.out.println("L'agence a bien ete supprimer");
                                break;
                        }

                    }
                    break;
                case 2:
                    System.out.println("formulaire pour " + mode + " des types de compte");
                    try(TypeCompteRepository repo = new TypeCompteRepository()){
                        ArrayList<TypeCompte> listTypeCompte = repo.getAll();
                        TypeCompte typeCompte = null;
                        String libelle = "";
                        switch (mode){
                            case INSERT:
                                boolean valid;
                                do {
                                    valid = true;
                                    System.out.println("Saississez le libelle du nouveau type de compte :");
                                    libelle = sc.nextLine();
                                    for (TypeCompte tmptypeCompte : listTypeCompte){
                                        if (tmptypeCompte.getLibelle().equals(libelle)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                typeCompte = new TypeCompte(libelle);
                                repo.Add(typeCompte);
                                System.out.println("Le type de compte a bien ete ajouter");
                                break;
                            case UPDATE:
                                for (TypeCompte tmptypeCompte : listTypeCompte){
                                    System.out.println(tmptypeCompte.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saississez l'Id du type de compte a modifier");
                                    int selectAgence = sc.nextInt();
                                    sc.nextLine();
                                    for (TypeCompte tmptypeCompte : listTypeCompte){
                                        if (tmptypeCompte.getId() == selectAgence){
                                            typeCompte = tmptypeCompte;
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                do {
                                    valid = true;
                                    System.out.println("Saississez le libelle du nouveau type de compte :");
                                    libelle = sc.nextLine();
                                    for (TypeCompte tmptypeCompte : listTypeCompte){
                                        if (tmptypeCompte.getId() != typeCompte.getId() &&  tmptypeCompte.getLibelle().equals(libelle)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                typeCompte.setLibelle(libelle);
                                repo.Update(typeCompte);
                                System.out.println("Le type de compte a bien ete mis a jour");
                                break;
                            case DELETE:
                                for (TypeCompte tmpTypeCompte : listTypeCompte){
                                    System.out.println(tmpTypeCompte.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saississez l'Id du type de compte a supprimer");
                                    int selectTypeCompte = sc.nextInt();
                                    sc.nextLine();
                                    for (TypeCompte tmpTypeCompte : listTypeCompte){
                                        if (tmpTypeCompte.getId() == selectTypeCompte){
                                            typeCompte = tmpTypeCompte;
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                repo.Remove(typeCompte);
                                System.out.println("Le type de compte a bien ete supprimer");
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("formulaire pour " + mode + " des comptes");
                    switch (mode){
                        case INSERT:
                            break;
                        case UPDATE:
                            break;
                        case DELETE:
                            break;
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    break;
            }


        }while (!exit);

    }

    private static void OperationMenu(){

    }

}

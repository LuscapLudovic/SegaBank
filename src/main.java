import BO.Agence;
import BO.Compte;
import BO.Operation;
import BO.TypeCompte;
import Repository.AgenceRepository;
import Repository.CompteRepository;
import Repository.OperationRepository;
import Repository.TypeCompteRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

    private static final String INSERT = "ajouter";
    private static final String DELETE = "supprimer";
    private static final String UPDATE = "modifier";
    private static final String SEE = "regarder";

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
            System.out.println("4 - " + SEE + " des données");
            System.out.println("5 - Effectuer des transactions");
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
                    CRUDMenu(SEE);
                case 5:
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
            if (mode.equals(SEE)){
                System.out.println("4 - " + mode + " Operations");
            }
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
                        String code;
                        String adresse;
                        switch (mode) {
                            case INSERT:
                                boolean valid;
                                do {
                                    valid = true;
                                    System.out.println("Saisissez le code de la nouvelle agence :");
                                    code = sc.nextLine();
                                    for (Agence tmpAgence : listAgence) {
                                        if (tmpAgence.getCode().equals(code)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                } while (!valid);
                                System.out.println("Saisissez l'adresse de la nouvelle agence :");
                                adresse = sc.nextLine();
                                agence = new Agence(code, adresse);
                                repo.Add(agence);
                                System.out.println("L'agence a bien ete ajouter");
                                break;
                            case UPDATE:
                                for (Agence tmpAgence : listAgence) {
                                    System.out.println(tmpAgence.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saisissez l'Id de l'agence a modifier");
                                    int selectAgence = sc.nextInt();
                                    sc.nextLine();
                                    for (Agence tmpAgence : listAgence) {
                                        if (tmpAgence.getId() == selectAgence) {
                                            agence = tmpAgence;
                                            valid = true;
                                            break;
                                        }
                                    }
                                } while (!valid);
                                do {
                                    valid = true;
                                    System.out.println("Saisissez le code de la nouvelle agence :");
                                    code = sc.nextLine();
                                    for (Agence tmpAgence : listAgence) {
                                        if (!agence.getId().equals(tmpAgence.getId()) && tmpAgence.getCode().equals(code)) {
                                            valid = false;
                                            break;
                                        }
                                    }
                                } while (!valid);
                                System.out.println("Saisissez l'adresse de la nouvelle agence :");
                                adresse = sc.nextLine();
                                agence.setCode(code);
                                agence.setAdresse(adresse);
                                repo.Update(agence);
                                System.out.println("L'agence a bien ete mise a jour");
                                break;
                            case DELETE:
                                for (Agence tmpAgence : listAgence) {
                                    System.out.println(tmpAgence.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saisissez l'Id de l'agence a supprimer");
                                    int selectAgence = sc.nextInt();
                                    sc.nextLine();
                                    for (Agence tmpAgence : listAgence) {
                                        if (tmpAgence.getId() == selectAgence) {
                                            agence = tmpAgence;
                                            valid = true;
                                            break;
                                        }
                                    }
                                } while (!valid);
                                repo.Remove(agence);
                                System.out.println("L'agence a bien ete supprimer");
                                break;
                            case SEE:
                                System.out.println("Liste des Agences : ");
                                for (Agence tmpAgence: repo.getAll()) {
                                    System.out.println(tmpAgence.toString());
                                }
                                sc.nextLine();
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("formulaire pour " + mode + " des types de compte");
                    try(TypeCompteRepository repo = new TypeCompteRepository()){
                        ArrayList<TypeCompte> listTypeCompte = repo.getAll();
                        TypeCompte typeCompte = null;
                        String libelle;
                        switch (mode){
                            case INSERT:
                                boolean valid;
                                do {
                                    valid = true;
                                    System.out.println("Saisissez le libelle du nouveau type de compte :");
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
                                    System.out.println("Saisissez l'Id du type de compte a modifier");
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
                                    System.out.println("Saisissez le libelle du nouveau type de compte :");
                                    libelle = sc.nextLine();
                                    for (TypeCompte tmptypeCompte : listTypeCompte){
                                        if (!tmptypeCompte.getId().equals(typeCompte.getId()) &&  tmptypeCompte.getLibelle().equals(libelle)) {
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
                                    System.out.println("Saisissez l'Id du type de compte a supprimer");
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
                            case SEE:
                                System.out.println("Liste des types de Compte :");
                                for (TypeCompte tmpTypeCompte: repo.getAll()){
                                    System.out.println(tmpTypeCompte.toString());
                                }
                                sc.nextLine();
                                break;
                        }
                    }
                    break;
                case 3:
                    ArrayList<TypeCompte> listeTypeCompte;
                    ArrayList<Agence> listeAgence;
                    System.out.println("formulaire pour " + mode + " des comptes");
                    try(CompteRepository repo = new CompteRepository()){
                        ArrayList<Compte> listCompte = repo.getAll();
                        Compte compte = null;
                        double solde;
                        double decouvert;
                        double tauxInteret;
                        TypeCompte typeCompte = null;
                        Agence agence = null;
                        switch (mode){
                            case INSERT:
                                boolean valid;
                                System.out.println("Saisissez le solde du compte");
                                solde = sc.nextDouble();
                                try(TypeCompteRepository typeCompteRepo = new TypeCompteRepository()){
                                    listeTypeCompte = typeCompteRepo.getAll();
                                }
                                for (TypeCompte tmpTypeCompte : listeTypeCompte){
                                    System.out.println(tmpTypeCompte.toString());
                                }
                                do{
                                    valid = false;
                                    System.out.println("Saisissez l'id du type de compte" + SEE);
                                    int selectTypeCompte = sc.nextInt();
                                    sc.nextLine();
                                    for (TypeCompte tmpTypeCompte : listeTypeCompte){
                                        if (tmpTypeCompte.getId() == selectTypeCompte){
                                            typeCompte = tmpTypeCompte;
                                            valid = true;
                                            break;
                                        }
                                    }
                                } while(!valid);
                                try(AgenceRepository agenceRepo = new AgenceRepository()){
                                    listeAgence = agenceRepo.getAll();
                                }
                                for (Agence tmpAgence : listeAgence){
                                    System.out.println(tmpAgence.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saisissez l'id de l'agence" + SEE);
                                    int selectAgence = sc.nextInt();
                                    sc.nextLine();
                                    for (Agence tmpAgence : listeAgence){
                                        if(tmpAgence.getId() == selectAgence){
                                            agence = tmpAgence;
                                            valid = true;
                                            break;
                                        }
                                    }
                                } while(!valid);
                                do{
                                    valid = false;
                                    System.out.println("Saisissez le taux d'interet");
                                    tauxInteret = sc.nextDouble();
                                    if (agence.getId() != 3 && tauxInteret != 0){
                                        System.out.println("seul un compte épargne peu avoir un taux d'interet");
                                    } else {
                                        valid = true;
                                    }
                                } while (!valid);
                                do{
                                    valid = false;
                                    System.out.println("Saisissez le découvert");
                                    decouvert = sc.nextDouble();
                                    if(agence.getId() == 1){
                                        System.out.println("seul un compte avoir peut avoir un découvert");
                                    } else {
                                        valid = true;
                                    }
                                }while (!valid);
                                compte = new Compte(solde, typeCompte, decouvert, tauxInteret, agence);
                                repo.Add(compte);
                                System.out.println("Compte créer avec succès");
                                break;
                            case UPDATE:
                                for(Compte tmpCompte : listCompte){
                                    System.out.println(tmpCompte.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saisissez l'Id du compte à modifier");
                                    int selectCompte = sc.nextInt();
                                    sc.nextLine();
                                    for(Compte tmpCompte : listCompte){
                                        if(tmpCompte.getId() == selectCompte){
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while(!valid);
                                try(TypeCompteRepository typeCompteRepo = new TypeCompteRepository()){
                                    listeTypeCompte = typeCompteRepo.getAll();
                                }
                                for (TypeCompte tmpTypeCompte : listeTypeCompte){
                                    System.out.println(tmpTypeCompte.toString());
                                }
                                do{
                                    valid = false;
                                    System.out.println("Saisissez la nouvelle id du type de compte" + SEE);
                                    int selectTypeCompte = sc.nextInt();
                                    sc.nextLine();
                                    for (TypeCompte tmpTypeCompte : listeTypeCompte){
                                        if (tmpTypeCompte.getId() == selectTypeCompte){
                                            typeCompte = tmpTypeCompte;
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while(!valid);
                                try(AgenceRepository agenceRepository = new AgenceRepository()){
                                    listeAgence = agenceRepository.getAll();
                                }
                                for(Agence tmpAgence : listeAgence){
                                    System.out.println(tmpAgence.toString());
                                }
                                do{
                                    valid = false;
                                    System.out.println("Saisissez la nouvelle agence du compte" + SEE);
                                    int selectAgence = sc.nextInt();
                                    sc.nextLine();
                                    for(Agence tmpAgence : listeAgence){
                                        if(tmpAgence.getId() == selectAgence){
                                            agence = tmpAgence;
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                System.out.println("Saisissez le nouveau solde du compte");
                                solde = sc.nextDouble();
                                do{
                                    valid = false;
                                    System.out.println("Saisissez le nouveau taux d'interet");
                                    tauxInteret = sc.nextDouble();
                                    if (agence.getId() != 3 && tauxInteret != 0){
                                        System.out.println("seul un compte épargne peu avoir un taux d'interet");
                                    } else {
                                        valid = true;
                                    }
                                } while (!valid);
                                do{
                                    valid = false;
                                    System.out.println("Saisissez le nouveau découvert");
                                    decouvert = sc.nextDouble();
                                    if(agence.getId() == 1){
                                        System.out.println("seul un compte avoir peut avoir un découvert");
                                    } else {
                                        valid = true;
                                    }
                                }while (!valid);
                                compte.setAgence(agence);
                                compte.setDecouvert(decouvert);
                                compte.setSolde(solde);
                                compte.setTauxInteret(tauxInteret);
                                compte.setTypeCompte(typeCompte);
                                repo.Update(compte);
                                System.out.println("Le compte à bien ete mis a jour");
                                break;
                            case DELETE:
                                for(Compte tmpCompte : listCompte){
                                    System.out.println(tmpCompte.toString());
                                }
                                do {
                                    valid = false;
                                    System.out.println("Saisissez l'Id du compte a supprimer");
                                    int selectCompte = sc.nextInt();

                                    sc.nextLine();
                                    for (Compte tmpCompte : listCompte){
                                        if (tmpCompte.getId() == selectCompte){
                                            compte = tmpCompte;
                                            valid = true;
                                            break;
                                        }
                                    }
                                }while (!valid);
                                repo.Remove(compte);
                                System.out.println("Le compte a bien été supprimé");
                                break;
                            case SEE:
                                System.out.println("Liste des comptes :");
                                for (Compte tmpCompte : repo.getAll()){
                                    System.out.println(tmpCompte.toString());
                                }
                                sc.nextLine();
                                break;
                        }
                    }

                    break;
                case 4:
                    if (!mode.equals(SEE)) break;
                    ArrayList<Operation> listOpe;
                    ArrayList<Compte> listCompte;
                    Compte compte = null;
                    boolean valid;
                    try(CompteRepository compteRepo = new CompteRepository()){
                        listCompte = compteRepo.getAll();
                    }
                    for (Compte tmpCompte : listCompte){
                        System.out.println(tmpCompte.toStringShort());
                    }
                    do {
                        valid = false;
                        System.out.println("Saisissez l'Id du compte des operations a " + SEE );
                        int selectCompte = sc.nextInt();
                        sc.nextLine();
                        for (Compte tmpCompte : listCompte){
                            if (tmpCompte.getId() == selectCompte){
                                compte = tmpCompte;
                                valid = true;
                                break;
                            }
                        }
                    }while (!valid);

                    try(OperationRepository repo = new OperationRepository()){
                        listOpe = repo.getByCompte(compte);
                    }
                    for (Operation op : listOpe){
                        System.out.println(op.toString());
                    }
                    sc.nextLine();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    break;
            }


        }while (!exit);

    }

    private static void OperationMenu() throws Exception {
        boolean exit = false;

        do {
            System.out.println("------- Menu d'operation -------");
            System.out.println("1 - Faire un virement");
            System.out.println("2 - Faire un retrait");
            System.out.println("0 - Quitter le menu d'operation" );

            int choice;
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            sc.nextLine();


            Operation op;
            ArrayList<Compte> listCompte;
            Compte deb = null;
            Compte benef = null;
            double montant;
            boolean valid;

            switch (choice){
                case 1:
                    try(CompteRepository compteRepo = new CompteRepository()){
                        listCompte = compteRepo.getAll();
                    }
                    for (Compte compte : listCompte){
                        System.out.println(compte.toStringShort());
                    }

                    do {
                        valid = false;
                        System.out.println("Saisissez l'Id du compte a debiter");
                        int selectCompte = sc.nextInt();
                        sc.nextLine();
                        for (Compte tmpCompte : listCompte){
                            if (tmpCompte.getId() == selectCompte){
                                deb = tmpCompte;
                                valid = true;
                                break;
                            }
                        }
                    }while (!valid);
                    do {
                        valid = false;
                        System.out.println("Saisissez l'Id du compte beneficiaire");
                        int selectCompte = sc.nextInt();
                        sc.nextLine();
                        for (Compte tmpCompte : listCompte){
                            if (tmpCompte.getId() == selectCompte){
                                if (selectCompte == deb.getId()){
                                    System.out.println("Ce compte est deja choisie comme debitant, veuillez selectionner un autre compte");
                                    break;
                                }
                                benef = tmpCompte;
                                valid = true;
                                break;
                            }
                        }
                    }while (!valid);
                    System.out.println("Saisissez le montant de l'operation");
                    montant = sc.nextDouble();
                    sc.nextLine();

                    op = new Operation(deb, benef, montant);
                    if (op.ExecOpe()){
                        System.out.println("La transaction c'est bien executer");
                    }
                    break;
                case 2:

                    try(CompteRepository compteRepo = new CompteRepository()){
                        listCompte = compteRepo.getAll();
                    }
                    for (Compte compte : listCompte){
                        System.out.println(compte.toStringShort());
                    }

                    do {
                        valid = false;
                        System.out.println("Saisissez l'Id du compte a debiter");
                        int selectCompte = sc.nextInt();
                        sc.nextLine();
                        for (Compte tmpCompte : listCompte){
                            if (tmpCompte.getId() == selectCompte){
                                deb = tmpCompte;
                                valid = true;
                                break;
                            }
                        }
                    }while (!valid);

                    System.out.println("Saisissez le montant a retirer");
                    montant = sc.nextDouble();
                    sc.nextLine();

                    op = new Operation(deb, deb, montant);
                    if (op.Retrait()){
                        System.out.println("La transaction c'est bien executer");
                    }

                    break;
                case 0:
                    exit = true;
                    break;
            }
        }while (!exit);
    }

}

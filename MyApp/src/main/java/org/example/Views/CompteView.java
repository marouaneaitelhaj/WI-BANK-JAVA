package org.example.Views;

import org.example.Entity.Client;
import org.example.Entity.Compte;
import org.example.Entity.Courant;
import org.example.Entity.Epargne;
import org.example.Enums.CompteEtat;
import org.example.Implementations.CompteImpl;
import org.example.Implementations.CourantImpl;
import org.example.Implementations.EpargneImpl;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class CompteView {
    Scanner scanner = new Scanner(System.in);
    CompteImpl compteImpl = new CompteImpl();
    CourantImpl courantImpl = new CourantImpl();
    EpargneImpl epargneImpl = new EpargneImpl();

    public CompteView() {
        System.out.println("1- Créer un compte");
        System.out.println("2- Chercher un compte par client");
        System.out.println("3- Supprimer un compte");
        switch (scanner.nextLine()) {
            case "1" -> {
                System.out.println("1- Compte Courant");
                System.out.println("2- Compte Epargne");
                switch (scanner.nextLine()) {
                    case "1" -> {
                        this.saveCompteEpargne();
                    }
                    case "2" -> {
                        this.saveCompteCourant();
                    }
                    default -> {
                        System.out.println("Vous devez choisir un choix valide");
                        new CompteView();
                    }
                }
            }
            case "2" -> {
            }
            case "3" -> {
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new CompteView();
            }
        }
    }

    public Optional<Compte> createCompteView() {
        Compte compte = new Compte();
        Client client = new Client();
        System.out.println("Numero: ");
        compte.setNumero(scanner.nextLine());
        System.out.println("Solde ($): ");
        compte.setSolde(Integer.parseInt(scanner.nextLine()));
        compte.setCompteEtat(CompteEtat.Active);
        System.out.println("Client");
        client.setCode(scanner.nextLine());
        compte.setClient(client);
        compte.setDate(LocalDate.now());
        Optional<Compte> optionalCompte = compteImpl.save(compte);
        return optionalCompte;
    }

    public void findOneView() {
        System.out.println("Client: ");
        Client client = new Client();
        client.setCode(scanner.nextLine());
        Compte compte = new Compte();
        compte.setClient(client);
        Optional<Compte> compte1 = compteImpl.findOne(compte);
        if (compte1.isPresent()) {
            System.out.println(compte1.get().getNumero() + "    " + compte1.get().getSolde() + "  " + compte1.get().getDate() + "   " + compte1.get().getCompteEtat() + "   " + compte1.get().getClient());
        } else {
            System.out.println("Aucun employé trouvé");
        }
    }

    public void deleteView() {
    }

    public void saveCompteEpargne() {
        Optional<Compte> compte = this.createCompteView();
        if (compte.isPresent()) {
            System.out.println("Taux Dinteret :");
            Epargne epargne = new Epargne(compte.get(), Double.valueOf(scanner.nextLine()));
            Optional<Epargne> epargne1 = epargneImpl.save(epargne);
            if (epargne1.isPresent()) {
                System.out.println("Le client a été bein ajoutée");
            } else {
                System.out.println("Le client n'a pas ajouté");
            }
        } else {
            System.out.println("Le client n'a pas ajouté");
        }
        new CompteView();
    }

    public void saveCompteCourant() {
        Optional<Compte> compte = this.createCompteView();
        if (compte.isPresent()) {
            System.out.println("Decouvert :");
            Courant courant = new Courant(compte.get());
            courant.setDecouvert(Double.valueOf(scanner.nextLine()));
            Optional<Courant> courant1 = courantImpl.save(courant);
            if (courant1.isPresent()) {
                System.out.println("Le client a été bein ajoutée");
            } else {
                System.out.println("Le client n'a pas ajouté");
            }
        } else {
            System.out.println("Le client n'a pas ajouté");
        }
        new CompteView();
    }
}

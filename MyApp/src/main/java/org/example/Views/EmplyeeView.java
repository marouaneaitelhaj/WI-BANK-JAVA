package org.example.Views;

import org.example.Entity.Employe;
import org.example.Helpers.MyFunction;
import org.example.Implementations.EmployeImpl;
import org.example.Main;

import java.time.LocalDate;
import java.util.Scanner;

public class EmplyeeView {
    Scanner scanner = new Scanner(System.in);
    EmployeImpl employeImpl = new EmployeImpl();

    public EmplyeeView() {
        System.out.println("1- Ajouter un employé");
        System.out.println("2- Chercher un employé par matricule");
        System.out.println("3- Supprimer un employé");
        switch (scanner.nextLine()) {
            case "1" -> {
                this.saveView();
            }
            case "2" -> {
                this.findOneView();
            }
            case "3" -> {
                this.deleteView();
            }
            default -> {
                System.out.println("Vous devez choisir un choix valide");
                new EmplyeeView();
            }
        }

    }

    public void saveView() {
        Employe employe = new Employe();
        System.out.println("Nom :");
        employe.setNom(scanner.nextLine());
        System.out.println("Prenom :");
        employe.setPrenom(scanner.nextLine());
        System.out.println("Email :");
        employe.setEmail(scanner.nextLine());
        LocalDate DateDeNaissance = MyFunction.getDate("Date De Naissance (yyyy-mm-dd) :");
        employe.setDateDeNaissance(DateDeNaissance);
        System.out.println("Telephone :");
        employe.setTelephone(scanner.nextLine());
        System.out.println("Matricule :");
        employe.setMatricule(scanner.nextLine());
        LocalDate DateDeRecrutement = MyFunction.getDate("Date De Recrutement (yyyy-mm-dd) :");
        employe.setDateDeRecrutement(DateDeRecrutement);
        EmployeImpl employe1 = new EmployeImpl();
        if (employe1.save(employe) == null) {
            System.out.println("L'employé n'a pas ajouté");
        } else {
            System.out.println("L'employé est ajouté");
        }
        new EmplyeeView();
    }


    public void findOneView() {
        System.out.println("Matricule : ");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        Employe employe2 = employeImpl.findOne(employe);
        if (employe2 != null) {
            System.out.println(employe2.getNom() + "    " + employe2.getPrenom() + "    " + employe2.getTelephone() + "    " + employe2.getMatricule() + "    " + employe2.getEmail() + "    " + employe2.getDateDeRecrutement() + "    " + employe2.getDateDeNaissance());
        } else {
            System.out.println("Aucun employé trouvé");
        }
    }

    public void deleteView() {
        System.out.println("Matricule : ");
        Employe employe = new Employe();
        employe.setMatricule(scanner.nextLine());
        if (employeImpl.delete(employe) == 1) {
            System.out.println("La suppression a bien été effectuée");
            MainPage mainPage = new MainPage();
        } else {
            System.out.println("L'employé n'a pas supprimé");
            new EmplyeeView();
        }
    }
}

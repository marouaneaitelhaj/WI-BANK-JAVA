package org.example.Implementations;

import org.example.Entity.Employe;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.EmployeInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class EmployeImpl implements EmployeInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Employe save(Employe employe) {
        try {
            String query = "INSERT INTO public.employe( nom, prenom, datedenaissance, telephone, matricule, datederecrutement, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getNom());
            preparedStatement.setString(2, employe.getPrenom());
            preparedStatement.setString(3, employe.getDateDeNaissance().toString());
            preparedStatement.setString(4, employe.getTelephone());
            preparedStatement.setString(5, employe.getMatricule());
            preparedStatement.setString(6, employe.getDateDeRecrutement().toString());
            preparedStatement.setString(7, employe.getEmail());
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.printf(String.valueOf(e));
        }
        return null;
    }

    @Override
    public Employe update(Employe employe) {
        return null;
    }

    @Override
    public int delete(Employe employe) {
        return 0;
    }

    @Override
    public Employe findOne(Employe employe) {
        return null;
    }

    @Override
    public List<Employe> findAll() {
        return null;
    }
}

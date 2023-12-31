package org.example.Implementations;

import org.example.Entity.Agence;
import org.example.Entity.Employe;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.AgenceInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AgenceImpl implements AgenceInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Agence> save(Agence agence) {
        try {
            String query = "INSERT INTO agence(code, nom, adresse, numero) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agence.getCode());
            preparedStatement.setString(2, agence.getNom());
            preparedStatement.setString(3, agence.getAdresse());
            preparedStatement.setString(4, agence.getNumeroTelephone());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                return Optional.empty();
            } else {
                return Optional.of(agence);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Agence> update(Agence agence) {
        try {
            String query = "UPDATE agence SET nom=?, adresse=?, numero=? WHERE code=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agence.getNom());
            preparedStatement.setString(2, agence.getAdresse());
            preparedStatement.setString(3, agence.getNumeroTelephone());
            preparedStatement.setString(4, agence.getCode());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                return Optional.empty();
            } else {
                return Optional.of(agence);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public int delete(Agence agence) {
        try {
            String query = "DELETE FROM agence WHERE code=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agence.getCode());
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected == 0) {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public List<Agence> findByEmploye(Employe employe) {
        List<Agence> agenceList = new ArrayList<>();
        try {
            String query = "SELECT * FROM employeagencelogs where employe=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employe.getMatricule());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Agence agence = new Agence(resultSet.getString("agence"));
                this.findOne(agence).ifPresent(agenceList::add);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return agenceList;
    }

    @Override
    public Optional<Agence> findOne(Agence agence) {
        try {
            String query = "SELECT code, nom, adresse, numero FROM agence WHERE code=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agence.getCode());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                agence.setAdresse(resultSet.getString("adresse"));
                agence.setNom(resultSet.getString("nom"));
                agence.setNumeroTelephone(resultSet.getString("numero"));
            } else {
                return Optional.empty();
            }
            return Optional.of(agence);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Agence> findOneByAdresse(Agence agence) {
        try {
            String query = "SELECT code, nom, adresse, numero FROM agence WHERE adresse=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, agence.getAdresse());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                agence.setCode(resultSet.getString("code"));
                agence.setNom(resultSet.getString("nom"));
                agence.setNumeroTelephone(resultSet.getString("numero"));
            } else {
                return Optional.empty();
            }
            return Optional.of(agence);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Agence> findAll() {
        return null;
    }

    @Override
    public List<Agence> contact() {
        List<Agence> agenceList = new ArrayList<>();
        try {
            String query = "SELECT adresse, numero FROM agence;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Agence agence = new Agence();
                agence.setNumeroTelephone(resultSet.getString("numero"));
                agence.setAdresse(resultSet.getString("adresse"));
                agenceList.add(agence);
            }
        } catch (Exception e) {

        }
        return agenceList;
    }

}

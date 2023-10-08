package org.example.Implementations;

import org.example.Entity.Credit;
import org.example.Helpers.DatabaseConnection;
import org.example.Interfaces.CreditInter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class CreditImpl implements CreditInter {
    Connection connection = DatabaseConnection.getInstance().getConnection();

    @Override
    public Optional<Credit> save(Credit credit) {
        try {
            String query = "INSERT INTO public.credit(numero, client, agence, date, montant, duree, remarques) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, credit.getNumero());
            preparedStatement.setString(2, credit.getClient().getCode());
            preparedStatement.setString(3, credit.getAgence().getCode());
            preparedStatement.setDate(4, Date.valueOf(credit.getDate()));
            preparedStatement.setDouble(5, credit.getMontant());
            preparedStatement.setString(6, credit.getNumero());
            preparedStatement.setString(6, credit.getRemarques());
            if (preparedStatement.executeUpdate() != 0) {
                return Optional.of(credit);
            }
        } catch (Exception e) {

        }
        return Optional.empty();
    }

    @Override
    public Optional<Credit> update(Credit credit) {
        return Optional.empty();
    }

    @Override
    public int delete(Credit credit) {
        return 0;
    }

    @Override
    public Optional<Credit> findOne(Credit credit) {
        return Optional.empty();
    }

    @Override
    public List<Credit> findAll() {
        return null;
    }
}
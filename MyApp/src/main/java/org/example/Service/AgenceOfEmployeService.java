package org.example.Service;

import org.example.Entity.AgenceOfEmploye;
import org.example.Exceptions.MyException;
import org.example.Interfaces.AgenceOfEmployeInter;

import java.util.List;
import java.util.Optional;

public class AgenceOfEmployeService{
    private final AgenceOfEmployeInter agenceOfEmployeInter;

    public AgenceOfEmployeService(AgenceOfEmployeInter agenceOfEmployeInter) {
        this.agenceOfEmployeInter = agenceOfEmployeInter;
    }


    public Optional<AgenceOfEmploye> save(AgenceOfEmploye agenceOfEmploye) throws MyException {
        if (agenceOfEmploye.getAgence().getCode().isEmpty() || agenceOfEmploye.getEmploye().getMatricule().isEmpty())
            throw new MyException("Le champ de employe ou agence est vide");
        return this.agenceOfEmployeInter.save(agenceOfEmploye);
    }


    public Optional<AgenceOfEmploye> update(AgenceOfEmploye agenceOfEmploye) throws MyException {
        Optional<AgenceOfEmploye> agenceOfEmploye1 = this.agenceOfEmployeInter.update(agenceOfEmploye);
        if (agenceOfEmploye1.isEmpty()){
            throw new MyException("aucun affectation trouve");
        }
        return Optional.empty();
    }


    public int delete(AgenceOfEmploye agenceOfEmploye) {
        return 0;
    }


    public Optional<AgenceOfEmploye> findOne(AgenceOfEmploye agenceOfEmploye) {
        return Optional.empty();
    }


    public List<AgenceOfEmploye> findAll() {
        return agenceOfEmployeInter.findAll();
    }
}

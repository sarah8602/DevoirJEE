package com.mcommande.web.controller;

import com.mcommande.configurations.ApplicationPropertiesConfiguration;
import com.mcommande.dao.CommandeDao;
import com.mcommande.model.Commande;
import com.mcommande.web.exceptions.CommandeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CommandeController implements HealthIndicator {
    @Autowired
    CommandeDao commandeDao;
    @Autowired
    ApplicationPropertiesConfiguration appProperties;

    // Affiche la liste de tous les commnandes disponibles
    @GetMapping(value = "/Commandes")
    public List<Commande> listeDesCommandes() {

        System.out.println(" ********* CommandeController listeDesCommandes()/ appProperties.getLimitDeCommandes() " + appProperties.getLimitDeCommandes());
        List<Commande> commandes = commandeDao.findAll();

        if (commandes.isEmpty())
            throw new CommandeNotFoundException("Aucune commande n'est disponible ");

        List<Commande> listeLimitee = commandes.subList(0, appProperties.getLimitDeCommandes());

        return listeLimitee;

    }

    // Récuperer une commande par son id
    @GetMapping(value = "/Commandes/{id}")
    public Optional<Commande> recupererCommande(@PathVariable int id) {
        System.out.println(" ********* CommandeController recupererCommande(@PathVariable int id) ");

        Optional<Commande> commande = commandeDao.findById(id);

        if (!commande.isPresent())
            throw new CommandeNotFoundException("La commande correspondant à l'id " + id + " n'existe pas");

        return commande;
    }


    @Override
    public Health health() {
        List<Commande> commandes = commandeDao.findAll();
        if (commandes.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();

    }
}

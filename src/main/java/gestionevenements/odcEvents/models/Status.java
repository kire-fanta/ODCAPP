package gestionevenements.odcEvents.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    // ID unique pour chaque statut enregistré en base de données
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStatus;

    // Statut de l'événement (en cours, à venir, terminé)
    @Column(name = "status_evenement")
    private String statusEvenement;

    // Statut de la tâche (en cours, à faire, terminé)
    @Column(name = "status_tache")
    private String statusTache;

    // Relation many to one avec la classe Evenements
    @ManyToOne
    @JoinColumn(name = "id_evenements")
    private Evenements evenements;

    // Relation many to one avec la classe Tache
    @ManyToOne
    @JoinColumn(name = "id_tache")
    private Tache tache;}



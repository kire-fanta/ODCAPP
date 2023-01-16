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

    // Statut(en cours, à venir, terminé, à faire)
    private String status;



}



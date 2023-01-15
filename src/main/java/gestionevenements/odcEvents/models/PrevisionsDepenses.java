package gestionevenements.odcEvents.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "PDepense ")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrevisionsDepenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;
    private float prixUnitaire;
    private int quantite;

    @OneToOne
    private Evenements evenements;
}

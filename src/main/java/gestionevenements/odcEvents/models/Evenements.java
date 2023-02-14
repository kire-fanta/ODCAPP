package gestionevenements.odcEvents.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evenements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvenement;
    private String nomEvenement;
    private LocalDate datedebut;
    private LocalDate datefin;
    private LocalTime heuredebut;
    private LocalTime heurefin;
    private String typeEvenement;
    private int duree;
    private boolean etat;
    private String description;
    private String lieu;
    private  String image;

    @OneToOne
    private User user;
    // Relation many to one avec la classe Evenements
    @ManyToOne
    private Status status;
}

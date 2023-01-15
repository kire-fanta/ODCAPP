package gestionevenements.odcEvents.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;
    // Le nom de l'équipe, unique dans la base de données

    @Column(name = "nom_equipe", unique = true)
    private String nom;
    // Le nom de la tâche à laquelle l'équipe est assignée

    @Column(name = "nom_tache")
    private String nomTache;
    // La liste des membres de l'équipe, relation many to many avec la classe Utilisateur
    @ManyToMany
    @JoinTable(name = "equipe_utilisateur", joinColumns = @JoinColumn(name = "equipe_id"), inverseJoinColumns = @JoinColumn(name = "utilisateur_id"))
    private List<User> membres;

    @Column(name = "notification")
    private String notification;

    @ManyToOne
    @JoinColumn(name = "id_evenements")
    private Evenements evenements;
}

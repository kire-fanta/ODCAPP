package gestionevenements.odcEvents.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "tache")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTache;
    private String nomTache;
    private String descriptionTache;
    private Date dateEcheance;


    @ManyToMany
    @JoinColumn(name = "id_utilisateur")
    private List<User> users = new ArrayList<>();

    @JoinColumn(name = "id_evenements")
    @ManyToOne
    private Evenements evenements;



    public List<User> getUtilisateurs() {
        return users;
    }

    public void setUtilisateurs(List<User> utilisateurs) {
        this.users = utilisateurs;
    }

    public String getNomTache() {
        return nomTache;
    }

    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
    }

    public String getDescriptionTache() {
        return descriptionTache;
    }

    public void setDescriptionTache(String descriptionTache) {
        this.descriptionTache = descriptionTache;
    }

    public Date getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }
    public void modifierDate(Date dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public void setRoleUser() {
    }

    public Long getIdTache() {
        return idTache;
    }
    // Relation many to one avec la classe Tache
    @ManyToOne
    private Status status;
}

    

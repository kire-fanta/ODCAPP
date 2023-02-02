package gestionevenements.odcEvents.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Salles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long nombreplace;
    @Column(unique = true)
    private String libelle;
    private String etage;
    private Boolean disponibilite;
    private String description;
    private String image;
    private int numero;


    @ManyToOne
    private Evenements evenements;




}


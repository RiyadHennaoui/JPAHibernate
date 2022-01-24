package entites.simple;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Exemple simple :
 * JPA - Hibernate necessite la création d'une class java bean.
 *
 * ❓ pour qu'une class java soit consideré comme bean elle doit comporter :
 * 🔘 un constructeur vide. (existe automatiquement par le compliteur s'il n'y en a pas de crée)
 * 🔘 avoir des GETTERS et des SETTERS
 * 🔘 implementer SERIALIZABLE
 *
 * il faut définir des annotations
 *
 *
 */

@Entity
/**
 * l'annotation @Table peut avoir plusieurs parametres dont attribuer un nom différent en base de donnée que le nom de la classe mais aussi d'ajouter des contraintes
 dans l'exemple on ajoute une contrainte d'unicité sur le nom et prenom de la personne mais attention si on envoie deux fois la même personne en base nous aurons seulement une exception
 il faut traiter le cas d'unicité en java aussi.
 */
 @Table(name = "Personne", uniqueConstraints = {
        @UniqueConstraint(name = "nomPrenomUniques", columnNames = {
                "first_name",
                "last_name"
        })
})
public class User implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "last_name", length = 40)
    private String lasttName;

    @Column(name = "first_name", length = 60)
    private String firstName;
    /**
     * la gestion des Dates il faut ajouter une annontation @Temporal qui prend en param le type :
     *  seulement la date (dd/mm/aaaa) = TemporalType.DATE
     *  seulement l'heure (HH:MM:SS) = TemporalType.TIME
     *  la date et l'heure = TemporalType.TIMESTEMP
     */
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    /**
     * pour pouvoir enregistrer un ENUM il faut ajouter l'annotation @Enumerated qui prend un param :
     * suivant l'ordre dans l'enum (0 pour le permier élément) = EnumType.ORDINAL <== enregistre des entiers en BDD
     * sous forme de string = EnumType.STRING <== conseillé car meilleur visibilité.
     */
    @Enumerated (EnumType.STRING)
    private Civility civility;

    /**
     * la seule façon de mapper des tableau sérializable est d'ajouter l'annotation @Lob (Large Object Binary) Attention plus couteux que des type primitifs
     */
    @Lob
    private int[] securityKey;

    public User() {
    }

    public User(String firstName, String lasttName, Date teDeNaissance) {
        this.firstName = firstName;
        this.lasttName = lasttName;
        this.dateDeNaissance = teDeNaissance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLasttName() {
        return lasttName;
    }

    public void setLasttName(String lasttName) {
        this.lasttName = lasttName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
}

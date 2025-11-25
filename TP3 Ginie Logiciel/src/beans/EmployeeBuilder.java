package beans;

import java.util.Date;

public class EmployeeBuilder {
    // Required parameters
    public final int id;
    public final String nom;

    // Optional parameters - initialized to default values
    public String prenom = "";
    public String adresse = "";
    public String ville = "";
    public String codePostal = "";
    public String telephone = "";
    public String email = "";
    public String poste = "";
    public double salaire = 0.0;
    public Date dateEmbauche = new Date();

    public EmployeeBuilder(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public EmployeeBuilder prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public EmployeeBuilder adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public EmployeeBuilder ville(String ville) {
        this.ville = ville;
        return this;
    }

    public EmployeeBuilder codePostal(String codePostal) {
        this.codePostal = codePostal;
        return this;
    }

    public EmployeeBuilder telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public EmployeeBuilder email(String email) {
        this.email = email;
        return this;
    }

    public EmployeeBuilder poste(String poste) {
        this.poste = poste;
        return this;
    }

    public EmployeeBuilder salaire(double salaire) {
        this.salaire = salaire;
        return this;
    }

    public EmployeeBuilder dateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this;
    }

    public Employee build() {
        return new Employee(this);
    }
}
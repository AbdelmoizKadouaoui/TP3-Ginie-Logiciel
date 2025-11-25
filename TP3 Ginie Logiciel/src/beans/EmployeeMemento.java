package beans.memento;

import java.util.Date;

public class EmployeeMemento {
    private final int id;
    private final String nom;
    private final String prenom;
    private final String adresse;
    private final String ville;
    private final String codePostal;
    private final String telephone;
    private final String email;
    private final String poste;
    private final double salaire;
    private final Date dateEmbauche;

    public EmployeeMemento(int id, String nom, String prenom, String adresse, String ville, String codePostal, String telephone, String email, String poste, double salaire, Date dateEmbauche) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.ville = ville;
        this.codePostal = codePostal;
        this.telephone = telephone;
        this.email = email;
        this.poste = poste;
        this.salaire = salaire;
        this.dateEmbauche = dateEmbauche;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getAdresse() { return adresse; }
    public String getVille() { return ville; }
    public String getCodePostal() { return codePostal; }
    public String getTelephone() { return telephone; }
    public String getEmail() { return email; }
    public String getPoste() { return poste; }
    public double getSalaire() { return salaire; }
    public Date getDateEmbauche() { return dateEmbauche; }
}

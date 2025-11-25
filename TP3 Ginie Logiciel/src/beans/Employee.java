package beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import beans.memento.EmployeeMemento;
import dao.visitor.IVisitor;
public class Employee implements Cloneable {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String ville;
    private String codePostal;
    private String telephone;
    private String email;
    private String poste;
    private double salaire;
    private java.util.Date dateEmbauche;

    private final List<Employee> subordonnes = new ArrayList<>();

    public Employee(EmployeeBuilder builder) {
        this.id = builder.id;
        this.nom = builder.nom;
        this.prenom = builder.prenom;
        this.adresse = builder.adresse;
        this.ville = builder.ville;
        this.codePostal = builder.codePostal;
        this.telephone = builder.telephone;
        this.email = builder.email;
        this.poste = builder.poste;
        this.salaire = builder.salaire;
        this.dateEmbauche = builder.dateEmbauche;
    }

    // Getters
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
    public java.util.Date getDateEmbauche() { return dateEmbauche; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public void setVille(String ville) { this.ville = ville; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public void setEmail(String email) { this.email = email; }
    public void setPoste(String poste) { this.poste = poste; }
    public void setSalaire(double salaire) { this.salaire = salaire; }
    public void setDateEmbauche(java.util.Date dateEmbauche) { this.dateEmbauche = dateEmbauche; }
 

    public List<Employee> getSubordonnes() {
        return Collections.unmodifiableList(subordonnes);
    }

    public void addSubordonne(Employee e) {
        if (e != null) {
            subordonnes.add(e);
        }
    }

    public void removeSubordonne(Employee e) {
        subordonnes.remove(e);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        // Note: This is a shallow copy of subordinates.
        // For a deep copy, you would need to clone each subordinate as well.
        return cloned;
    }

    // Memento Pattern Methods
    public EmployeeMemento saveStateToMemento() {
        // Create a memento with the current state
        return new EmployeeMemento(id, nom, prenom, adresse, ville, codePostal, telephone, email, poste, salaire, dateEmbauche);
    }

    public void getStateFromMemento(EmployeeMemento memento) {
        this.id = memento.getId();
        this.nom = memento.getNom();
        this.prenom = memento.getPrenom();
        this.adresse = memento.getAdresse();
        this.ville = memento.getVille();
        this.codePostal = memento.getCodePostal();
        this.telephone = memento.getTelephone();
        this.email = memento.getEmail();
        this.poste = memento.getPoste();
        this.salaire = memento.getSalaire();
        this.dateEmbauche = memento.getDateEmbauche();
    }

    // Visitor Pattern Method
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}
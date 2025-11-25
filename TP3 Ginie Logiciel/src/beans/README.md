# Implémentation des Design Patterns - TP Génie Logiciel

Ce document explique les modifications apportées au projet pour intégrer plusieurs design patterns fondamentaux, conformément aux exigences de l'énoncé.

## Architecture MVC

Le projet a été structuré en trois couches principales pour séparer les responsabilités :
- **Modèle (DAO - Data Access Object)** : Gère l'accès aux données. Situé dans le package `dao`.
- **Service** : Contient la logique métier et sert d'intermédiaire entre la vue et le DAO. Situé dans le package `service`.
- **Vue** : Responsable de l'interaction avec l'utilisateur. Située dans le package `view`.

---

## Patterns de Création

### 1. Singleton

**Objectif** : S'assurer qu'une classe n'a qu'une seule instance et fournir un point d'accès global à celle-ci.

**Implémentation** :
- Les classes `EmployeeDaoImpl`, `EmployeeServiceImpl` et `EmployeeView` ont été transformées en Singletons.
- Elles ont maintenant un constructeur privé, une instance statique privée, et une méthode publique `getInstance()` qui retourne cette unique instance.
- Cela garantit que nous utilisons la même instance de service, de DAO ou de vue dans toute l'application, ce qui est idéal pour les classes sans état (`stateless`).

### 2. Factory

**Objectif** : Créer des objets sans exposer la logique de création à la classe qui les utilise.

**Implémentation** :
- Une interface `IConnection` a été créée dans `dao.connection` pour définir les opérations de base de données (`select`, `insert`, `delete`).
- La classe `ConnectionFactory` a été ajoutée. Son rôle est de créer et de fournir une instance d'un objet qui implémente `IConnection`.
- Le `EmployeeDaoImpl` utilise maintenant `ConnectionFactory.getConnection()` pour obtenir un objet de connexion, sans jamais connaître la classe concrète (par exemple, `MySqlConnection`). Cela découple la couche DAO de l'implémentation spécifique de la base de données.

### 3. Prototype

**Objectif** : Créer de nouveaux objets en clonant une instance existante.

**Implémentation** :
- La classe `Employee` implémente déjà l'interface `Cloneable`.
- La méthode `clone()` a été surchargée pour créer une copie de l'objet `Employee`.
- Dans `EmployeeServiceImpl`, la méthode `dupliquerEmploye(id)` utilise `employeeDao.findById(id).clone()` pour créer un duplicata d'un employé existant.

### 4. Builder

**Objectif** : Construire un objet complexe étape par étape, permettant différentes représentations de l'objet.

**Implémentation** :
- La classe `EmployeeBuilder` a été créée pour faciliter la construction d'un objet `Employee`, qui possède de nombreux attributs.
- Le constructeur de `Employee` est maintenant privé et n'accepte qu'un `EmployeeBuilder`.
- Cela rend la création d'un employé plus lisible et évite les constructeurs avec une longue liste de paramètres.

---

## Patterns de Structure

### 1. Proxy

**Objectif** : Fournir un substitut ou un intermédiaire pour un autre objet afin de contrôler l'accès à celui-ci.

**Implémentation** :
- La classe `EmployeeDaoProxy` a été créée. Elle implémente `IEmployeeDao` et contient une référence vers le vrai DAO (`EmployeeDaoImpl`).
- Avant d'exécuter des opérations de mise à jour (`save`, `delete`), le proxy vérifie si l'utilisateur a les droits nécessaires via une méthode `isGranted()`. Si ce n'est pas le cas, l'accès est refusé.
- L'application (la couche service) interagit avec le proxy, qui est interchangeable avec le DAO original.

### 2. Décorateur

**Objectif** : Attacher dynamiquement de nouvelles responsabilités à un objet.

**Implémentation** :
- La classe `LoggingEmployeeDaoDecorator` a été créée. Elle implémente `IEmployeeDao` et "décore" un autre objet `IEmployeeDao`.
- Chaque méthode du décorateur appelle d'abord une méthode de journalisation (`log()`) pour écrire l'opération dans un fichier `db.log`, puis exécute la méthode de l'objet décoré.
- Cela permet d'ajouter la fonctionnalité de logging sans modifier le code du DAO original.

### 3. Composite

**Objectif** : Composer des objets en structures arborescentes pour représenter des hiérarchies partie-tout.

**Implémentation** :
- La classe `Employee` contient une liste de subordonnés : `private final List<Employee> subordonnes`.
- Des méthodes `addSubordonne`, `removeSubordonne` et `getSubordonnes` permettent de gérer la hiérarchie.
- Cela permet de traiter un employé individuel et un chef d'équipe (avec ses subordonnés) de manière uniforme.

### 4. Memento

**Objectif** : Capturer et externaliser l'état interne d'un objet pour pouvoir le restaurer ultérieurement.

**Implémentation** :
- La classe `EmployeeMemento` a été créée pour sauvegarder l'état d'un objet `Employee`.
- La classe `Employee` possède des méthodes `saveStateToMemento()` pour créer un memento et `getStateFromMemento()` pour restaurer son état à partir d'un memento.
- Une classe `History` gère une liste de mementos pour conserver l'historique des états d'un employé.

### 5. Bridge

**Objectif** : Découpler une abstraction de son implémentation pour qu'elles puissent évoluer indépendamment.

**Implémentation** :
- Ce pattern est appliqué à travers l'utilisation systématique d'interfaces pour relier les couches :
  - **Pont Vue-Service** : La `EmployeeView` dépend de l'interface `IEmployeeService`, pas de son implémentation `EmployeeServiceImpl`.
  - **Pont Service-DAO** : Le `EmployeeServiceImpl` dépend de l'interface `IEmployeeDao`, pas de `EmployeeDaoImpl` ou de ses décorateurs/proxies.
- Ce découplage est la clé de notre architecture. Il permet de changer facilement une implémentation (par exemple, remplacer le DAO par un proxy ou un décorateur) sans affecter les couches qui l'utilisent.

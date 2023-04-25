# TP-ISI-GLSI-B-GROUPE-12-2023
Création d'une API de la  société bancaire(fictive) « Ega » qui voudrait mettre en place un système de gestion de ses clients et des comptes que ces derniers possèdent.


git clone https://github.com/Schosmiel/TP-ISI-GLSI-B-GROUPE-12-2023.git




Un client peut avoir plusieurs comptes. 
La banque met à disposition des clients deux types de comptes : un compte épargne et un compte courant. 
Un compte est caractérisé par le numéro de compte, le type de compte, la date de création du compte, le solde du compte et le propriétaire du compte. 
Pour le client on retiendra les informations suivantes : nom, prénom, date de naissance, sexe, adresse, numéro de téléphone, courriel et nationalité. 
Le numéro de compte est un ensemble de 5 caractères majuscules alphanumériques générés aléatoirement auquel on concatène l’année de création du compte. 
Le numéro de compte est unique pour un compte. 
Lors de la création du compte son solde est nul. 

### Objectifs:
1.	Mettre en place une API CRUD pour gérer les comptes et les clients.
2.	Ajouter les possibilités pour le client de : 
3-	Faire un versement sur son compte ;
5-	Faire un retrait sur son compte si le solde le permet ;
6-	Faire un virement d’un compte à un autre


## Modèle

### Client
| Champ | Type | Description |
|-------|------|-------------|
| id | int | Identifiant unique du client générer automatiquement |
| nom | string | Nom du client |
| prenom | string | Prenom du client |
| dateNaissance | date | Date de de naissance du client (format "yyyy-mm-dd") |
| adresse | string | Adresse du client |
| sexe | string | sexe du client, il doit obligatirement être "M" ou "F" |
| numTel | string | Numéro de télephone du client |
| courriel | string | Courriel du client |
| nationalite | string | Nationalité du client |

### Compte
| Champ | Type | Description |
|-------|------|-------------|
| numCompte | int | Numéro de compte générer automatiquement |
| typeCompte | string | Type de compte, il doit obligatoirement être "COURANT" ou "EPARGNE" |
| solde | double | Solde du compte, default=0 |
| dateCreation | String | Date de création générer automatiquement avec la date du jour |
| proprietaire | Client | Client proprietaire du compte |


### Système de gestion de BD utilisé : Postgres

### Créer une base de données en postgres: create database springapi


## Endpoints

### Opérations sur les clients

| Méthode | URL | Description | Exemple de corps de requête valide |
|---------|-----|-------------|------------------------------------|
| GET | /api/client/liste | Récupère tous les clients |  |
| GET | /api/client/{id} | Récupère un client spécifique |  |
| POST | /api/client/enregistrer | Créer un nouveau client | [JSON](#creerclient) |
| PUT | /api/client//modifier/{id} | Met à jour un client existant | [JSON](#modifierclient) |
| DELETE | /api/client/supprimer/{id} | Supprime un client existant |   |
| GET | /api/client/{id}/comptes | Récupère les comptes d'un client spécifique |   |

### Opérations sur les comptes

| Méthode | URL | Description | Exemple de corps de requête valide |
|---------|-----|-------------|------------------------------------|
| GET | /api/comptes/liste | Récupère tous les comptes |   |
| GET | /api/comptes/{numCompte} | Récupère un compte spécifique |   |
| POST | /api/comptes/enregistrer | Crée un nouveau compte | [JSON](#creercompte) |
| DELETE | /api/comptes/supprimer/{numCompte} | Supprime un compte existant |   |
| GET | /api/comptes/{numCpt}/proprietaire | Récupère le proprietaire du compte |    |
| PUT | /api/comptes/retrait| Faire le retrait d'un montant sur un compte | [JSON](#retrait)  |
| PUT | /api/comptes/depot | Faire le depot d'un montant sur un compte | [JSON](#depot) |
| PUT | /api/comptes/virement | Faire le virement d'un compte(numCompteSource) source à un compte de destination (numCompteDest) avec un montant sur un compte |  [JSON](#virement)   |
| GET | /api//comptes/{numCompte}/depots | Récupère les dépots effectués sur un compte |    |
| GET | /api/comptes/{numCompte}/retrait | Récupère les retraits effectués sur un compte |    |
| GET | /api/comptes/{numCompte}/virementsRecu | Récupère les virements reçus par un compte |    |
| GET | /api/comptes/{numCompte}/virementsEnvoye | Récupère les virements envoyés par un compte |    |

### Informations sur les opérations(dépot, retraits, virements)

| Méthode | URL | Description | Exemple de corps de requête valide |
|---------|-----|-------------|------------------------------------|
| GET | /api/operations/depots | Récupère tous les dépots effectués |   |
| GET | /api/operations/depots/{txnid} | Récupère un dépot effectué grâce à son identifiant de transaction|   |
| GET | /api/operations/retraits |  Récupère tous les retraits effectués ||
| GET | /api/operations/retraits/{txnid} | Récupère un retrait effectué grâce à son identifiant de transaction|   |
| GET | /api/operations/retraits |Récupère tous les virements effectués ||
| GET | /api/operations/virements/{txnid} | Récupère un virement effectué grâce à son identifiant de transaction|   |


## Exemples de corps de requêtes JSON valides

##### <a id="creerclient">Créer un client -> http://localhost:9000/api/client/enregister</a>
```json
{"nom": "Lefebvre", 
"prenom": "Marie", 
"date_naissance": "1995-02-28", 
"sexe": "F", 
"adresse": "26 avenue des Lilas",
 "telephone": "06 12 34 56 78", 
"courriel": "marie.lefebvre@exemple.com",
 "nationalite": "Française"}


```

##### <a id="modifierclient">Modifier un client -> http://localhost:9000/api/client/modifier/1</a>
```json
{"nom": "Dumont", 
"prenom": "Antoine", 
"date_naissance": "1992-06-14", 
"sexe": "M", 
"adresse": "12 rue de la Gare", 
"telephone": "01 23 45 67 89", 
"courriel": "antoine.dumont@exemple.com", 
"nationalite": "Française"}
```

##### <a id="creercompte">Créer un compte -> http://localhost:9000/comptes/enregistrer</a>
```json
{
    "typeCompte":"EPARGNE",
    "proprietaire":1,
}
```
Remarque: Impossible de créer un compte avec un solde, le solde par défaut est égale à 0

##### <a id="retrait">Faire un retrait -> PUT http://localhost:9000/api/compte/retrait </a>
```json
{
    "numCompte":"E64422023",
    "montant":"100"
}
```

##### <a id="depot">Faire un depot -> PUT http://localhost:9000/api/compte/depot </a>
```json
{
    "numCompte":"4OI6X2023",
    "montant":"100"
}
```

##### <a id="virement">Faire un virement -> PUT http://localhost:9000/comptes/virement</a>
```json
{
    "numeroCompteSource":"NZ9HR2023",
    "numeroCompteDest":"QX6Z32023",
    "montant":"1000"
}
```

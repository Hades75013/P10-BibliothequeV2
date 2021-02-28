# P10-Ameliorer le systeme d'information de la bibliotheque


## Architecture 

- Le projet repose sur une architecture API Rest 
- 3 modules (clientui, library et user) communiquent entre eux via Feign


## Développement

Cette application a été développée avec :
- Eclipse
- Java 11
- Tomcat 9
- MySQL (version 5.7)
- le framework Spring 


## Déploiement

* La source du projet est hérbergée sur GitHub à l'adresse : https://github.com/Hades75013/P10-BibliothequeV2
* Les dépendances du projet sont gérées par Apache Maven.
* L'application se builde par Maven au format .war.

Les frameworks utilisés sont : 
* Spring MVC/ SpringBoot/Spring Batch
* Bootstrap
* Junit
* Mockito

* Les infos de la base de données sont stockées sur un serveur MySQL.
* L'application est déployée sur un serveur Apache Tomcat v9


## Base de données

* Pour la base de données, vous trouverez toutes les infos utiles dans le fichier "application.properties"
<<<<<<< HEAD
  Lancer le script DumpCreationJeudedemoDB_P10.sql pour créer la bdd et insérer le jeu de données.
  Pour la mise à jour de la base de donnees, lancer le script de migration dumpMigrationP7_P10.txt.
=======
  Lancer le script DumpCreationJeudedemoDB_P10.sql pour créer la bdd et insérer les données.
>>>>>>> 7c9442c0f405eaa66be38070c1e76491fd3c3fca

* Utilisateurs créés au préalable  
 4 lecteurs lambda (role USER): 
  
  Email : p7bibliou1@gmail.com
  Mot de passe : user

  Email : p7bibliou2@gmail.com
  Mot de passe : foufou

  Email : p7bibliou3@gmail.com
  Mot de passe : bourriquet

  Email : p7bibliou4@gmail.com
  Mot de passe : moghez

  Un personnnel de bibliothèque (role ADMIN): 
  
  Email : p7biblioadm@gmail.com
  Mot de passe : admin

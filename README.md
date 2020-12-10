# P7-Bibliotheque


## Architecture 

Le projet repose sur une architecture API Rest 
3 modules (clientui, library et user) communiquent entre eux via Feign


## Développement

Cette application a été développée avec :
- Eclipse
- Java 11
- Tomcat 9
- MySQL (version 5.7)
- le framework Spring 


## Déploiement

* La source du projet est hérbergée sur GitHub à l'adresse : https://github.com/Hades75013/P7-Bibliotheque.git
* Les dépendances du projet sont gérées par Apache Maven.
* L'application se builde par Maven au format .war.

Les frameworks utilisés sont : 
* Spring MVC/ SpringBoot
* Bootstrap

* Les infos de la base de données sont stockées sur un serveur MySQL.
* L'application est déployée sur un serveur Apache Tomcat v9


## Base de données

* Pour la base de données, vous trouverez toutes les infos utiles dans le fichier "application.properties"
  Lancer le script "DumpCreateAndInsertTo.sql" pour créer la bdd et insérer les données.

* Utilisateurs créés au préalable  
 3 lecteurs lambda (role USER): 
  
  Email : p7bibliou1@gmail.com
  Mot de passe : user

  Email : p7bibliou2@gmail.com
  Mot de passe : foufou

  Email : p7bibliou3@gmail.com
  Mot de passe : bourriquet

 Un personnnel de bibliothèque (role ADMIN): 
  
  Email : p7biblioadm@gmail.com
  Mot de passe : admin

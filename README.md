# P7-Bibliotheque

## Développement

Cette application a été développé avec :
- Eclipse
- Java 11
- Tomcat 9
- MySQL (version 5.7)
- le framework Spring 


## Déploiement

* La source du projet est hérbergée sur GitHub à l'adresse : https://github.com/Hades75013/p6-ocr
* Le projet est géré par Apache Maven.
* L'application se builde par Maven au format .war.

Les frameworks utilisés sont : 
* Spring MVC/ SpringBoot
* Bootstrap

* La base de données est déployée sur un serveur MySQL.
* L'application est déployée sur un serveur Apache Tomcat v9


## Base de données

* Pour la base de données, vous trouverez toutes les infos utiles dans le fichier "application.properties"
* Lancer le script "DumpCreateAndInsertTo.sql" pour créer la bdd et insérer les données.

* Utilisateurs créés au préalable  
  3 usagers lambda (role USER): 
  
  Email : p7bibliou1@gmail.com
  Mot de passe : user

  Email : p7bibliou2@gmail.com
  Mot de passe : foufou

  Email : p7bibliou3@gmail.com
  Mot de passe : bourriquet

  Un personnnel de bibliothèque (role ADMIN): 
  
  Email : p7biblioadm@gmail.com
  Mot de passe : admin
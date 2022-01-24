package main;

import entites.simple.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;



        try{
            entityManagerFactory = Persistence.createEntityManagerFactory("Default");
            entityManager = entityManagerFactory.createEntityManager();



            User per = entityManager.find(User.class, 1);



            readData(entityManager);

            List<User> personnes = entityManager
                    .createQuery("from Personne", User.class)
                    .getResultList();
            for (User personne : personnes) {
                System.out.println(personne);
            }
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Date dateOfBirth = new Date();
            dateOfBirth.getTime();

            /** ==<Crud>== Insérer une nouvelle entité*/
            User newPersonne = new User(0, "Bahtat", "Jaouad", new Date().getTime());
//            entityManager.merge(newPersonne);
        //    entityManager.persist(newPersonne);





            /** ==<cRud>== Sélectionner plusieurs entités*/




            /** ==<crUd>== Update une entité*/

            per.setLasttName("toto");
            entityManager.persist(per);
            System.out.println(per.getLasttName());

            readData(entityManager);


            /** ==<cruD>== Supprimer une entité*/
            entityManager.remove(newPersonne);

            transaction.commit();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if ( entityManager != null) entityManager.close();
            if (entityManagerFactory != null) entityManagerFactory.close();
        }
    }

    private static void readData(EntityManager entityManager) throws InterruptedException{
        List<User> personnes = entityManager
                .createQuery("from Personne", User.class)
                .getResultList();
        for (User personne : personnes) {
            System.err.println(stringFormat(personne));
        }
    }
    public static String stringFormat(User p){

        StringBuilder builder = new StringBuilder();
        builder.append("nom : ")
                .append(p.getFirstName())
                .append(" | prenom : ")
                .append(p.getLasttName())
                .append(" | née le : ")
                .append(p.getDateDeNaissance());

        return builder.toString();

    }
}

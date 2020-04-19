package entity;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PreparationStepAdder {

    public Recipe addPreparationStep(int new_recipeId, int stepNumber, String desc){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Recipe recipe = new Recipe();

        try{
            tx = session.beginTransaction();

            Query query = session.createQuery("from Recipe where name = :name");
            query.setParameter("name", new_name);
            if (query.list().size() > 0){
                System.out.println("already exists");

            } else{
                Recipe recipe = new Recipe();
                recipe.setName(new_name);
                recipeId = (Short)session.save(recipe);
            }


            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return recipeId;

    }
}

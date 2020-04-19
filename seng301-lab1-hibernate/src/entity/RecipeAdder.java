package entity;

import org.hibernate.*;
import org.hibernate.query.criteria.internal.expression.function.LengthFunction;

import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Iterator;
import java.util.List;

public class RecipeAdder {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Recipe> selectAll(){
        Session session = essionFactory.openSession();
        Transaction tx = null;
        List<Recipe> data = null;
        try{
            tx = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Recipe> criteria = builder.createQuery(Recipe.class);
            criteria.from(entity.Recipe.class);
            data = session.createQuery(criteria).list();
            tx.commit();
    }catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return data;

    }

    public Short addRecipe(String new_name){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Short recipeId = null;

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

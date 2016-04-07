/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Bunna Kal
 */
@Interceptors({BusinessInterceptor.class})
@Stateful
public class BeanFacade implements BeanRemote{
    
    @PersistenceContext(unitName = "NewsApp-ejbPU")
    private EntityManager em; 
    
    @Override
    public void addCategory(CategoryEntity categoryEntity) {
       em.persist(categoryEntity);
    }

    @Override
    public int countCategory() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<UserEntity> rt = cq.from(CategoryEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author nb
 */
@Stateless
public class EntityBeanFacade implements EntityBeanLocal{

    @PersistenceContext(unitName = "NewsApp-ejbPU")
    private EntityManager em;

    
    @Override
    public void addUser(UserEntity userEntity) {
        em.persist(userEntity);
    }
    
    @Override
    public void updateUser(UserEntity userEntity) {
        em.merge(userEntity);
    }
    
    @Override
    public void deleteUser(UserEntity userEntity) {
        em.remove(em.merge(userEntity));
    }
    
    @Override
    public UserEntity findUser(Object id) {
        return em.find(UserEntity.class, id);
    }
    
    @Override
    public List<UserEntity> getAllUser() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserEntity.class));
        return em.createQuery(cq).getResultList();
    }
    
    @Override
    public List<UserEntity> getUserInRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserEntity.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    @Override
    public int countUser() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<UserEntity> rt = cq.from(UserEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    @Override
    public PostEntity findPost(Object id) {
        return em.find(PostEntity.class, id);
    }
    
    @Override
    public List<PostEntity> getPostInRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(UserEntity.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    @Override
    public int countPost() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<UserEntity> rt = cq.from(UserEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    @Override
    public void addPost(PostEntity postEntity) {
        em.persist(postEntity);
    }
    
    @Override
    public void updatePost(PostEntity postEntity) {
        em.merge(postEntity);
    }
    
    @Override
    public void deletePost(PostEntity postEntity) {
        em.remove(em.merge(postEntity));
    }
    
    @Override
    public void addCategory(CategoryEntity categoryEntity) {
        em.persist(categoryEntity);
    }
    
    @Override
    public void addComment(CommentEntity commentCategory) {
        em.persist(commentCategory);
    }
    
    
    
    public void create(CategoryEntity categoryEntity) {
        em.persist(categoryEntity);
    }

    public void edit(CategoryEntity categoryEntity) {
        em.merge(categoryEntity);
    }

    public void remove(CategoryEntity categoryEntity) {
        em.remove(em.merge(categoryEntity));
    }

    public CategoryEntity find(Object id) {
        return em.find(CategoryEntity.class, id);
    }

    public List<CategoryEntity> findAllCategory() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CategoryEntity.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<CategoryEntity> findRange(int[] range) {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(CategoryEntity.class));
        Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        Root<CategoryEntity> rt = cq.from(CategoryEntity.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<PostEntity> getAllPost() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PostEntity> q = criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> p = q.from(PostEntity.class);
        q.select(p);
        q.orderBy(criteriaBuilder.desc(p.get("postDate")));
        Query query = em.createQuery(q);
        query.setMaxResults(50);
        return query.getResultList();
    }
    
    @Override
    public List<PostEntity> getAllPostInCategory(Long id){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PostEntity> criteriaQuery= criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> c = criteriaQuery.from(PostEntity.class);
        criteriaQuery.select(c).where(criteriaBuilder.equal(c.get("inCategory"), id));
        criteriaQuery.orderBy(criteriaBuilder.desc(c.get("postDate")));
        Query query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    @Override
    public List<PostEntity> getAllPostByUser(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PostEntity> criteriaQuery= criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> c = criteriaQuery.from(PostEntity.class);
        criteriaQuery.select(c).where(criteriaBuilder.equal(c.get("postBy"), id));
        criteriaQuery.orderBy(criteriaBuilder.desc(c.get("postDate")));
        Query query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
    
    @Override
    public List<PostEntity> getAllRecentPostByUser(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PostEntity> criteriaQuery= criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> c = criteriaQuery.from(PostEntity.class);
        criteriaQuery.select(c).where(criteriaBuilder.equal(c.get("postBy"), id));
        criteriaQuery.orderBy(criteriaBuilder.desc(c.get("postDate")));
        Query query = em.createQuery(criteriaQuery);
        query.setMaxResults(20);
        return query.getResultList();
    }

    @Override
    public List<PostEntity> getAllRecentPost() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PostEntity> q = criteriaBuilder.createQuery(PostEntity.class);
        Root<PostEntity> c = q.from(PostEntity.class);
        q.select(c);
        q.orderBy(criteriaBuilder.desc(c.get("postDate")));
        Query qr = em.createQuery(q);
        qr.setMaxResults(200);
        return qr.getResultList();
    }

    @Override
    public List<CategoryEntity> getAllCategory() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CategoryEntity> q = cb.createQuery(CategoryEntity.class);
        Root<CategoryEntity> c = q.from(CategoryEntity.class);
        q.select(c);
        q.orderBy(cb.asc(c.get("category")));
        return em.createQuery(q).getResultList();
    }
    
    @Resource
    private SessionContext context;
    
    /**
     * This method use to handle the creation for timer to automatically run or callback
     * when the timer it timer out.
     * @param duration the time out duration for timer
     */
    @Override
    public void createTimer(long duration) {
        context.getTimerService().createTimer(duration, "Time out!!!");
    }
    
    @Interceptors({BusinessInterceptor.class })
    @Timeout
    public void timeOutHandler(Timer timer) {
        System.out.println("timeoutHandler : " + timer.getInfo());
        System.out.println("Some tasks are done automatically...!");
        timer.cancel();
    }

    @Override
    public List<TeamEntity> getTeam() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(TeamEntity.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<PlayerEntity> getPlayer() {
        CriteriaQuery cq= em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(PlayerEntity.class));
        return em.createQuery(cq).getResultList();
    }
    
}

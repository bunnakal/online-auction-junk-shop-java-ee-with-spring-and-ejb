/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Bunna Kal
 */
@Local
public interface EntityBeanLocal {
    
    public void addUser(UserEntity userEntity);
    
    public void updateUser(UserEntity userEntity);
    
    public void deleteUser(UserEntity userEntity);
    
    public UserEntity findUser(Object id);
    
    public List<UserEntity> getUserInRange(int[] range);
    
    public int countUser();
    
    public PostEntity findPost(Object id);
    
    public List<PostEntity> getPostInRange(int[] range);
    
    public int countPost();
    
    public void addPost(PostEntity postEntity);
    
    public void updatePost(PostEntity postEntity);
    
    public void deletePost(PostEntity postEntity);
    
    public void addCategory(CategoryEntity categoryEntity);
    
    public void addComment(CommentEntity commentCategory);
    
    public void create(CategoryEntity categoryEntity);
    
    public void edit(CategoryEntity categoryEntity);
    
    public void remove(CategoryEntity categoryEntity);
    
    public CategoryEntity find(Object id);
    
    public List<CategoryEntity> findAllCategory();
    
    public List<UserEntity> getAllUser();
    
    public List<CategoryEntity> findRange(int[] range);
    
    public int count();
    
    public List<PostEntity> getAllPost();
    
    public List<PostEntity> getAllPostInCategory(Long id);
    
    public List<PostEntity> getAllPostByUser(Long id);
    
    public List<PostEntity> getAllRecentPostByUser(Long id);
    
    public List<PostEntity> getAllRecentPost();
    
    public List<CategoryEntity> getAllCategory();
    
    public List<TeamEntity> getTeam();
    
    public List<PlayerEntity> getPlayer();
    
    public void createTimer(long milliseconds);
    
}

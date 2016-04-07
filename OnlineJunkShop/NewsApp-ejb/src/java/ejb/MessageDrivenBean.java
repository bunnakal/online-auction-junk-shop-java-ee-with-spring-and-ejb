/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nb
 */
@MessageDriven(mappedName = "jms/NewMessage", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageDrivenBean implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    @PersistenceContext(unitName = "NewsApp-ejbPU")
    private EntityManager em; 
    
    public MessageDrivenBean() {
    }
    
    @Override
    public void onMessage(Message message) {
        
        ObjectMessage objectMessage = null;
        try {
            if (message instanceof ObjectMessage) {
                objectMessage = (ObjectMessage) message;
                //System.out.println("Class :" + message.getClass().getName());
                if (objectMessage.getObject() instanceof UserEntity) {
                    //System.out.println("UserEntity");
                    UserEntity userEntity = (UserEntity) objectMessage.getObject();
                    save(userEntity);
                    System.out.println("Added user...!");
                } else if (objectMessage.getObject() instanceof PostEntity) {
                    //System.out.println("PostEntity");
                    PostEntity postEntity = (PostEntity) objectMessage.getObject();
                    save(postEntity);
                    System.out.println("Added post...!");
                } else if (objectMessage.getObject() instanceof CategoryEntity) {
                    CategoryEntity categoryEntity = (CategoryEntity) objectMessage.getObject();
                    save(categoryEntity);
                    System.out.println("Add category...!");
                } else if (objectMessage.getObject() instanceof CommentEntity) {
                    CommentEntity commentEntity = (CommentEntity) objectMessage.getObject();
                    save(commentEntity);
                    System.out.println("Add comment...!");
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        } catch (Throwable te) {
            te.printStackTrace();
        }
        
    }
    
    public void save(Object object) {
        em.persist(object);
    }
    
}

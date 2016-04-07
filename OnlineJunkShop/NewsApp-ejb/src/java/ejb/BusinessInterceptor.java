/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.interceptor.AroundInvoke;
import javax.interceptor.AroundTimeout;
import javax.interceptor.InvocationContext;
/**
 *
 * @author Bunna Kal
 */
public class BusinessInterceptor {
   
    @AroundTimeout
    @AroundInvoke
    public Object methodInterceptor(InvocationContext ctx) throws Exception
    {
       System.out.println("*** Intercepting call to Bean method: " 
       + ctx.getMethod().getName());
       return ctx.proceed();
    }
}

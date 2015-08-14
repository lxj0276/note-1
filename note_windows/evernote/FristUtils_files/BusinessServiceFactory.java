package com.itheima.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.itheima.service.BusinessService;
import com.itheima.service.impl.BusinessServiceImpl;

public class BeanFactory {
	public static BusinessService getBusinessService(){
		
		final String includeMethods[] = {"transfer"};//写到配置文件中
		
		final BusinessService s = new BusinessServiceImpl();
		//AOP
		BusinessService proxyS = (BusinessService)Proxy.newProxyInstance(s.getClass().getClassLoader(), 
				s.getClass().getInterfaces(), 
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						try{//用于拦截，跟（includeMethods[] = {"transfer"};//写到配置文件中）名字相同的方法
							boolean needInterceptor = false;
							for(String methodName:includeMethods){
								if(method.getName().equals(methodName)){
									needInterceptor = true;
								}
							}
							if(needInterceptor){
								long time = System.currentTimeMillis();
								TransactionManager.startTransaction();
								Object rtValue = method.invoke(s, args);
								TransactionManager.commit();
								System.out.println(method.getName()+"用时："+(System.currentTimeMillis()-time)+"毫秒");
								return rtValue;
							}else{
								return method.invoke(s, args);
							}
						}catch(Exception e){
							TransactionManager.rollback();
							throw new RuntimeException(e);
						}finally{
							TransactionManager.release();
						}
					}
		});
		
		return proxyS;
	}
}

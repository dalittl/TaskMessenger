package com.acnc.mm.util;


import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * @author Kunta L.
 *
 */


public class TestService
{
	public static void main(String[] args) throws Exception {
    new ClassPathXmlApplicationContext("spring-quartz.xml");
   
    
	}

}


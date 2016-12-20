package consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import schema.Lubbo;


public class Tester2 {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:consumer-spring.xml");
        Lubbo p = (Lubbo)ctx.getBean("Echo");
        System.out.println(p.getId());
    }
}

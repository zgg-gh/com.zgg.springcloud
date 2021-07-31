package org.example.domain;

//import com.google.common.collect.Maps;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.mvel2.MVEL;

import java.util.HashMap;
import java.util.Map;


public class TestUser {
    private static KieContainer container = null;
    private KieSession statefulKieSession = null;

    @Test
    public void test(){
        KieServices kieServices = KieServices.Factory.get();
        container = kieServices.getKieClasspathContainer();
        statefulKieSession = container.newKieSession("myAgeSession");
        User user = new User("duval yang",12);
        statefulKieSession.insert(user);
        statefulKieSession.fireAllRules();
        statefulKieSession.dispose();
    }

    @Test
    public void  testEval2(){
        String expression = "a == null && b == 'q' ";
        Map<String,Object> map = new HashMap<>();
        map.put("a",null);
        map.put("b","q");

        Object object = MVEL.eval(expression,map);
        System.out.println(object);

        String expression2 = "c > 2 && ( a == null && b == nil ) ";
        Map<String,Object> map2 = new HashMap<>();
        map2.put("a",null);
        map2.put("b",null);
        map2.put("c",3);
        map2.put("d",null);

        Object object2 = MVEL.eval(expression2,map2);
        System.out.println(object2);
    }
}

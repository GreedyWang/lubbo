package consumer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import provider.Echo;
import rpc.RpcProxy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Tester {

    @Autowired
    private RpcProxy rpcProxy;

    @Test
    public void helloTest() {
        Echo helloService = rpcProxy.create(Echo.class);
        String result = helloService.echo("hello world");
        Assert.assertEquals("hello world", result);
    }
}

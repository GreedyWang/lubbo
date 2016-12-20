package provider;

import Service.Echo;
import common.RpcService;

@RpcService(Echo.class)
public class EchoImpl implements Echo{

    @Override
    public String echo(String msg) {
        return "echo: " + msg;
    }
}

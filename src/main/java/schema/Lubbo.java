package schema;


import Service.Echo;
import org.springframework.beans.factory.annotation.Autowired;
import rpc.RpcProxy;

public class Lubbo implements Echo {

    private String id;
    private String _interface;
    private String _ref;

    @Autowired
    private RpcProxy rpcProxy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_interface() {
        return _interface;
    }

    public void set_interface(String _interface) {
        this._interface = _interface;
    }

    public String get_ref() {
        return _ref;
    }

    public void set_ref(String _ref) {
        this._ref = _ref;
    }

    public String echo(String arg) {
        Echo helloService = rpcProxy.create(Echo.class);
        return helloService.echo(arg);
    }
}

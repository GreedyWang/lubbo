package remote.exchange.msg;

import remote.exchange.BaseMsg;
import remote.exchange.MsgType;

public class AskMsg extends BaseMsg {
    public AskMsg() {
        super();
        setType(MsgType.ASK);
    }

    private String auth;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }
}

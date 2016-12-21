package remote.exchange.msg;

import remote.exchange.BaseMsg;
import remote.exchange.MsgType;


public class PingMsg extends BaseMsg {

    public PingMsg() {
        super();
        setType(MsgType.PING);
    }
}

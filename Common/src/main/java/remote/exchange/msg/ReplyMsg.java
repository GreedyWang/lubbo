package remote.exchange.msg;

import remote.exchange.BaseMsg;
import remote.exchange.MsgType;
import remote.exchange.ResponseBody;


public class ReplyMsg extends BaseMsg {
    public ReplyMsg() {
        super();
        setType(MsgType.REPLY);
    }

    private ResponseBody body;

    public ResponseBody getBody() {
        return body;
    }

    public void setBody(ResponseBody body) {
        this.body = body;
    }
}

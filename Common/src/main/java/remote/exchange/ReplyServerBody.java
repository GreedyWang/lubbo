package remote.exchange;


public class ReplyServerBody extends ResponseBody {
    private String serverInfo;

    public ReplyServerBody(String serverInfo) {
        this.serverInfo = serverInfo;
    }

    public String getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
    }
}

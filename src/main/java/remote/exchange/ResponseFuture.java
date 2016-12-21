package remote.exchange;

/**
 * todo: N -> 1:1 
 */
public interface ResponseFuture {

    /**
     * get result.
     * 
     * @return result.
     */
    Object get();

    /**
     * get result with the specified timeout.
     * 
     * @param timeoutInMillis timeout.
     * @return result.
     */
    Object get(int timeoutInMillis);

    /**
     * set callback.
     * 
     * @param
     */
    void setCallback();

    /**
     * check is done.
     * 
     * @return done or not.
     */
    boolean isDone();

}
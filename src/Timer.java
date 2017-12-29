/**
 * Created by Danny_Elliott on 2017-12-03.
 */
public class Timer {

    private long startTime;
    private long endTime;

    public Timer(){
        this.startTime = 0;
        this.endTime = 0;
    }

    public void start(){
        startTime = System.currentTimeMillis();
    }

    public long end(){
        endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        return elapsedTime;
    }

}

/**
 * 
 */
package WordsAnalysis;

/**
 * @author zfh1005
 *
 */


public class MonitorThread extends Thread{

    // free data
    private AllCountModel acm;
    
    public MonitorThread(AllCountModel acm){
        this.acm = acm;
    }
    
    public void run() {
        while(true){
            try {
                // per check time
                sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // thread all finished
            if(0 >= acm.getThreadCount()){
                // print result
                WordsAnalysis.show(acm);
                return;
            }
        }
    }
}
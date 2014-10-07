/**
 * 
 */
package WordsAnalysis;

/**
 * @author zfh1005
 *
 */


public class MonitorThread extends Thread{

    // ��������
    private AllCountModel acm;
    
    public MonitorThread(AllCountModel acm){
        this.acm = acm;
    }
    
    public void run() {
        while(true){
            try {
                // ����ʱ����һ��
                sleep(500); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // �߳�ȫ���������
            if(0 >= acm.getThreadCount()){
                // ��ӡ�����
                WordsAnalysis.show(acm);
                return;
            }
        }
    }
}
/**
 * 
 */
package WordsAnalysis;

/**
 * @author zfh1005
 *
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ThreadTest extends Thread{
    
    private List<File> files = new ArrayList<File>();
    private Map<String, Integer> wordsMap = new HashMap<String, Integer>();
    private AllCountModel allCountModel;
    
    // ÿһ���̶߳����벻һ����files�����Բ��õ�����������ͬ����ͻ
    public ThreadTest(List<File> files, AllCountModel allCountModel){
        this.files = files;
        this.allCountModel = allCountModel;
    }
    
    public void run() {
        WordsAnalysis wa = new WordsAnalysis();
        // ���������ȫ���ļ�
        for (File file : files) {
            try {
                // �����ļ�����
                wordsMap = wa.countWords(file, wordsMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // ��ס�������ݣ�������ô��������������ݻ����ң�
        synchronized (allCountModel) {
            // �����߳�����
            allCountModel.setThreadCount(allCountModel.getThreadCount() - 1);
            // ���½����
            allCountModel.getLastMap().put(files, wordsMap);
        }
    }
}
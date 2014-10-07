/**
 * 
 */
package WordsAnalysis;

/**
 * @author zfh1005
 *
 */

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllCountModel {

    // �����е��߳�����
    private int threadCount;

    // ÿ���̴߳�����ļ���Ӧ�Ľ����
    private HashMap<List<File>, Map<String, Integer>> lastMap = new HashMap<List<File>, Map<String, Integer>>();
    
    public int getThreadCount() {
        return threadCount;
    }
    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }
    public HashMap<List<File>, Map<String, Integer>> getLastMap() {
        return lastMap;
    }
    public void setLastMap(HashMap<List<File>, Map<String, Integer>> lastMap) {
        this.lastMap = lastMap;
    }
}
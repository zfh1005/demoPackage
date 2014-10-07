/**
 * 
 */
package WordsAnalysis;

/**
 * @author zfh1005
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WordsAnalysis {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
        File f = new File("d:\\�ҵ��ĵ�\\test");
        File[] fs = f.listFiles();
        // �ֳ�����
        List<File> files1 = new ArrayList<File>();
        for (int i = 0; i < fs.length/2; i++) {
            files1.add(fs[i]);
            
        }
        List<File> files2 = new ArrayList<File>();
        for (int i = fs.length/2; i < fs.length; i++) {
            files2.add(fs[i]);
            
        }
        // �����߳�����
        int threadCount = 0;
        // ��������
        AllCountModel acm = new AllCountModel();
        acm.setThreadCount(++threadCount);
        ThreadTest tt1 = new ThreadTest(files1, acm);
        // 1���߳�
        tt1.start();
        acm.setThreadCount(++threadCount);
        ThreadTest tt2 = new ThreadTest(files2, acm);
        // 2���߳�
        tt2.start();
        MonitorThread mt = new MonitorThread(acm);
        // �����߳�
        mt.start();
        
    }
    
    /**
     * 
     * @param file
     * @param wordsMap
     * @return
     * @throws IOException
     */
    public Map<String, Integer> countWords(File file, Map<String, Integer> wordsMap) throws IOException{
        
        // ����
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        // һ���ַ���
        String str;
        // ��ȡÿһ��
        while((str = reader.readLine()) != null ){
            str = str.trim();
            // ��������
            if(str.equals("") || str == null){
                continue;
            }
            // ���ո����ɵ���
            String[] strs = str.split(" ");
            for (int i = 0; i < strs.length; i++) {
                String word = strs[i].trim();
                // ���ֵĵ���
                if(wordsMap.containsKey(word)){
                    // ����
                    wordsMap.put(word, (wordsMap.get(word) + 1));
                }else{
                    // ��һ�γ��ֵ��µ���
                    wordsMap.put(word, 1);
                }
            }
        }
        // �ر���
        reader.close();
        return wordsMap;
    }
    
    /**
     * ��ӡ���
     * @param AllCountModel ����Ľ����
     */
    public static void show(AllCountModel acm){
        System.out.println(acm.getThreadCount());
        for (List<File> lists : acm.getLastMap().keySet()) {
            System.out.println(lists);
            for (String str : acm.getLastMap().get(lists).keySet()) {
                System.out.println(str + " : " + acm.getLastMap().get(lists).get(str));
            }
            System.out.println("------------------------------------------");
        }
        
    }
}
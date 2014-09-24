/**
 * 
 */
package DataTypeDemo;

/**
 * @author zfh1005
 *
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
public class MapDemo {
 
    public static void main(String[] args) {
 
        // ����Map
        Map<String, String> map = new HashMap<String, String>();
        // ����Ԫ��
        map.put("key1", "value1");
        map.put("key2", "value2");
        // �Ƴ�Ԫ��
        map.remove("key1");
        // ���map
        map.clear();
 
        map.put("key3", "value3");
        map.put("key4", "value4");
 
        // ����map ����1
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str + "/" + map.get(str));
        }
 
        // ����map ����2
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "--->" + entry.getValue());
        }
    }
}
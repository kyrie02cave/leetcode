package fundamentalsOfAlgorithms.dataStruture.hashtable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class leetcode332 {
    public List<String> findItinerary(List<List<String>> tickets) {
        /*分析：将所有机票存入hashMap中，然后依次弹出；
        * 待解决地问题，多种有效行程即存在起始地相同地机票（无法使用常规hashMap）；回溯解决*/

        // 卫语句
        if(tickets.size()==0){
            return null;
        }
        // 将所有元素都存入HashMap,
        HashMap<String, List<String>> ticketsMap = new HashMap<>();
        for (List<String> ticket:tickets) {
            // 先判断是否key已存在
            if(!ticketsMap.containsKey(ticket.get(0))){
                List<String> value = new ArrayList<>();
                value.add(ticket.get(1));
                ticketsMap.put(ticket.get(0), value);
            }else{
                ticketsMap.get(ticket.get(0)).add(ticket.get(1));
            }
        }

        List<String> ans = new ArrayList<>();
        return null;
    }

    public void getItinerary(HashMap<String, List<String>> ticketsMap, List<String> ans, String start){
        // 边界条件
        if(ticketsMap.size()==0){
            return;
        }
        ans.add(start);
        List<String> ends = ticketsMap.get(start);
        ends.sort(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                char[] chars1 = s1.toCharArray();
                char[] chars2 = s2.toCharArray();
                int index = 0;
                while (chars1[index]==chars2[index]&&index<chars1.length&&index<chars2.length){
                    index++;
                }

                return 0;
            }
        });

        //递归调用
        getItinerary(ticketsMap, ans, ends.remove(0));
    }
}

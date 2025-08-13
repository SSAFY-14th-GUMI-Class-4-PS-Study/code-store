package smj1513.boj1655;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> large = new PriorityQueue<>();
        PriorityQueue<Integer> small = new PriorityQueue<>(Comparator.reverseOrder());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < N ; i++){
            int n = Integer.parseInt(br.readLine());
            small.add(n);
            if(!large.isEmpty() && !small.isEmpty() && small.peek() > large.peek()){
                large.add(small.poll());
            }
            if(small.size() > large.size()+1){
                large.add(small.poll());
            }
            if(small.size() < large.size()){
                small.add(large.poll());
            }

            sb.append(small.peek()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

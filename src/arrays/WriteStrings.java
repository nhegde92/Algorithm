package arrays;

import java.util.*;

public class WriteStrings {

    static class Transform implements Comparable<Transform> {
        int start, end;
        String word;
        Transform(int s, int e, String w) {
            start = s; end = e; word = w;
        }
        public int compareTo(Transform o) {
            return Integer.compare(this.start, o.start);
        }
    }

    public List<String> mergeStrings(String fileName, String[] transforms){
        StringBuilder sb = new StringBuilder(fileName);
        List<String> res = new ArrayList<>();
        int[] metadata = new int[fileName.length()];  // Tracks which indices are modified

        // Parse and sort transforms by start index
        List<Transform> list = new ArrayList<>();
        for(String transform: transforms){
            String[] parts = transform.split(",");
            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);
            String word = parts[2];
            list.add(new Transform(start, end, word));
        }
        Collections.sort(list);

        int shift = 0; // Track index shift in StringBuilder due to replacements
        for(Transform t : list){
            int adjStart = t.start + shift;
            int adjEnd = t.end + shift;

            // Check overlap in original indices (unshifted)
            boolean overlap = false;
            for(int i = t.start; i < t.end; i++){
                if(i >= metadata.length || metadata[i] == 1) {
                    overlap = true;
                    break;
                }
            }
            if(overlap){
                res.add(sb.toString());
                continue;
            }

            // Mark original indices as modified
            for(int i = t.start; i < t.end; i++){
                metadata[i] = 1;
            }

            // Replace in StringBuilder with adjusted indices
            sb.replace(adjStart, adjEnd, t.word);

            // Update shift for next replacements
            shift += t.word.length() - (t.end - t.start);

            res.add(sb.toString());
        }
        return res;
    }

    public static void main(String[] args){
        WriteStrings obj = new WriteStrings();

        System.out.println(obj.mergeStrings("Hello world", new String[]{"0,5,fantastic"}));
        System.out.println(obj.mergeStrings("Hello world", new String[]{"0,5,Gigantic","6,11,Tree"}));
    }
}

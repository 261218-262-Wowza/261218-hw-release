package cpecmu.cpe218.sp2020.hw1.submit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import cpecmu.cpe218.sp2020.Graph;
import cpecmu.cpe218.sp2020.GraphAdjList;
import cpecmu.cpe218.sp2020.hw1.ShortestPathCounter;

public class ShortestPathCounterImpl implements ShortestPathCounter {

    /** input file name */
    protected static final String inFile = "hw1tests/spcounter01.in";

    @Override
    public <V> int numShortestPaths(Graph<V> g, V src, V dst) {
        // TODO Your code here
        int count = 0;
        List<V> gList = new ArrayList<>(g.vertices());
        if(g.adjacentFrom(src).contains(dst)){
            count++;
        }else {
            for (int i = 0 ;i < gList.size()  ;i++){
                if(g.adjacentFrom(dst).contains(gList.get(i)) && g.adjacentFrom(src).contains(gList.get(i))){
                    count++;
                }

            }
        }
        return count;
        
    }

    public static void main(String[] args) {
        try (FileReader fr = new FileReader(inFile);
             Scanner s = new Scanner(fr)) {
            Graph<String> g = new GraphAdjList<>();
            // read graph
            int n = s.nextInt();
            for (int i = 0; i < n; i++)
                g.addVertex(s.next());
            int m = s.nextInt();
            for (int i = 0; i < m; i++) {
                String src = s.next();
                String dst = s.next();
                // add edge in both directions
                g.addEdge(src, dst);
                g.addEdge(dst, src);
            }

            // read src-dst pairs
            int t = s.nextInt();
            for (int i = 0; i < t; i++) {
                String src = s.next();
                String dst = s.next();
                // invoke algorithm
                ShortestPathCounter sbm = new ShortestPathCounterImpl();
                System.out.println(sbm.numShortestPaths(g, src, dst));
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

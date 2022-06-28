import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * same question
 *
 *
 */
public class PrimsAlgo {
  public int solve(int A, ArrayList<ArrayList<Integer>> B) {
    int mod = 1000000007;
    List<List<Pair>> adjList = buildAdjList(A, B);
    PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> {return p1.weight-p2.weight;});

    int[] visited = new int[A+1];

    for(Pair each : adjList.get(1)){
      pq.add(each);
    }
    visited[1]=1;

    long ans = 0;

    while(!pq.isEmpty()){
      Pair min = pq.poll();
      if(visited[min.j]==1){
        continue;
      }
      if(visited[min.j]!=1) {
        ans = (ans + min.weight)%mod;
        visited[min.j]=1;
      }

      for(Pair pair: adjList.get(min.j)){
        if(visited[pair.j]==0) {
          pq.add(pair);
        }
      }
    }

    return (int)ans;
  }

  private List<List<Pair>> buildAdjList(int A, ArrayList<ArrayList<Integer>> B){
    List<List<Pair>> adjList = new ArrayList<>();
    for(int i=0;i<=A;i++){
      adjList.add(new ArrayList<>());
    }

    for(ArrayList<Integer> each: B){
      int x = each.get(0);
      int y = each.get(1);
      int w = each.get(2);
      adjList.get(x).add(new Pair(y, w));
      adjList.get(y).add(new Pair(x, w));
    }
    return adjList;
  }


  class Pair {
    int j;
    int weight;
    public Pair(int j, int w){
      this.j = j;
      this.weight = w;
    }
  }
}

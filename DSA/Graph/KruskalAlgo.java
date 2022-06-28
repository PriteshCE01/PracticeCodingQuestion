package DSA.Graph;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Problem Description
 * Given a graph with A nodes and C weighted edges.
 * Cost of constructing the graph is the sum of weights of all the edges in the graph.
 * Find the minimum cost of constructing the graph by selecting some given edges such that we can reach every
 * other node from the 1st node.
 *
 * NOTE: Return the answer modulo 109+7 as the answer can be large.
 *
 * Problem Constraints
 * 1 <= A <= 100000
 * 0 <= C <= 100000
 * 1 <= B[i][0], B[i][1] <= N
 * 1 <= B[i][2] <= 109
 *
 **
 * Input Format
 * First argument is an integer A.
 * Second argument is a 2-D integer array B of size C*3 denoting edges. B[i][0] and B[i][1] are connected by ith edge with weight B[i][2]
 **
 * Output Format
 * Return an integer denoting the minimum construction cost.

 * Example Input
 * Input 1:
 *
 * A = 3
 * B = [   [1, 2, 14]
 *         [2, 3, 7]
 *         [3, 1, 2]   ]
 * Input 2:
 *
 * A = 3
 * B = [   [1, 2, 20]
 *         [2, 3, 17]  ]
 *
 * Example Output
 * Output 1: 9
 * Output 2: 37
 */
public class KrushkalAlgo {
  /* krushkal algo is */
  /** Approach
   * Parent Array (initialize with number itSelf)
   * Height Array (initialize with zero)
   * PriorityQueue (contains edge with weight)
   *
   * pick every edge and find union of that if union possible then add that value in answer.
   *
   * [if union is false it means cycle ]
   *
   */
  int[] parent;
  int[] height;
  public int solve(int A, ArrayList<ArrayList<Integer>> B) {
    parent = new int[A+1];
    height = new int[A+1];
    for(int i=0;i<=A;i++){
      parent[i]=i;
    }
    PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2)-> {return p1.w-p2.w;});

    for(ArrayList<Integer> each: B){
      pq.add(new Pair(each.get(0), each.get(1), each.get(2)));
    }

    int mod = 1000000007;
    long ans = 0;
    while(!pq.isEmpty()){
      Pair top = pq.poll();
      if(union(top.x, top.y)){
        ans= (ans + top.w)%mod;
      }
    }
    return (int) (ans%mod);
  }

  /**
   * root find for number x
   * recursion function until x==parent[x]
   */
  private int findRoot(int x){
    if(x==parent[x]){
      return x;
    }
    parent[x] = findRoot(parent[x]);
    return parent[x];
  }

  /**
   * union of two number x and y.
   * first find root of x and y.
   * suppose u = root.x and v = root.y
   * if both root are same then no need to union since it's under same root. (this can happen in case of cycle)
   * to reduce the height of the result tree. we will compare root.x and root.y 's heights.
   * if height[u] > height[v] then make u as a parent of v
   * if height[v] > height[u] then make v as a parent of x
   * if height[u]==height[v] then make any of any parent and increase correspondent height
   */
  private boolean union(int x, int y){

    int u = findRoot(x);
    int v = findRoot(y);
    if(u==v){
      return false;
    }
    if(height[u]>height[v]){
      parent[v]=u;
    } else if(height[u]>height[v]){
      parent[u]=v;
    } else {
      parent[v]=u;
      height[u]++;
    }
    return true;
  }

  class Pair {
    int x;
    int y;
    int w;
    public Pair(int x, int y, int w){
      this.x = x;
      this.y = y;
      this.w = w;
    }
  }
}

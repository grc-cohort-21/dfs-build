import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Build {

  /**
   * Prints words that are reachable from the given vertex and are strictly shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints nothing.
   *
   * @param vertex the starting vertex
   * @param k the maximum word length (exclusive)
   */
  public static void printShortWords(Vertex<String> vertex, int k) {
    Set<Vertex<String>> reachable = new HashSet<>();
    printShortWords(vertex, k, reachable);
  }

  public static <T> void printShortWords(Vertex<String> vertex, int k, Set<Vertex<String>> reachable) {
    if(vertex == null || reachable.contains(vertex)) return;
    reachable.add(vertex);
    if(vertex.data.length() < k) System.out.println(vertex.data);
    for(Vertex<String> neighbor : vertex.neighbors) {
      printShortWords(neighbor, k, reachable);
    }
  }

  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {
    Set<Vertex<String>> reachable = new HashSet<>();
    String longest = "";
    return longestWord(vertex, reachable, longest);
  }

  public static String longestWord(Vertex<String> vertex, Set<Vertex<String>> reachable, String longest) {
    if(vertex == null || reachable.contains(vertex)) return longest;
    reachable.add(vertex);
    if(vertex.data.length() > longest.length()) longest = vertex.data;
    for(Vertex<String> neighbor : vertex.neighbors) {
      String temp = longestWord(neighbor, reachable, longest);
      if(temp.length() > longest.length()) longest = temp;
    }
  return longest;
  }

  /**
   * Prints the values of all vertices that are reachable from the given vertex and 
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T> the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) {
    Set<Vertex<T>> reachable = new HashSet<>();
    printSelfLoopers(vertex, reachable);
  }

  public static <T> void printSelfLoopers(Vertex<T> vertex, Set<Vertex<T>> reachable ) {
    if(vertex == null || reachable.contains(vertex)) return;
    reachable.add(vertex);
    if(vertex.neighbors.contains(vertex)) System.out.println(vertex.data);
    for(Vertex<T> neighbor : vertex.neighbors) {
      printSelfLoopers(neighbor, reachable);
    }
  }

  /**
   * Determines whether it is possible to reach the destination airport through a series of flights
   * starting from the given airport. If the start and destination airports are the same, returns true.
   *
   * @param start the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  public static boolean canReach(Airport start, Airport destination) {
    Set<Airport> reachable = new HashSet<>();
    return canReach(start, destination, reachable);
  }

   public static boolean canReach(Airport start, Airport destination, Set<Airport> reachable) {
    if(start == null || reachable.contains(start)) return false;
    if(start == destination ) return true;
    reachable.add(start);
    for(Airport airport : start.getOutboundFlights()) {
      if(canReach(airport, destination, reachable)) return true;
    }
    return false;
   }

  /**
   * Returns the set of all values in the graph that cannot be reached from the given starting value.
   * The graph is represented as a map where each vertex is associated with a list of its neighboring values.
   *
   * @param graph the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T> the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) {
    if(!graph.keySet().contains(starting)) return graph.keySet();
    Set<T> reachable = new HashSet<>();
    Set<T> unreachable = new HashSet<>();
    reachable(graph, starting, reachable);
    for(T value : graph.keySet()) {
      if(!reachable.contains(value)) unreachable.add(value); 
    }  
    return unreachable;
  }
  public static <T> void reachable(Map<T, List<T>> graph, T starting, Set<T> reachable) {
    if(starting == null || reachable.contains(starting)) return;
    reachable.add(starting);
    for(T value : graph.get(starting)) {
      reachable(graph, value, reachable);
    }
  }
}

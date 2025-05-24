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

     if (vertex == null) return;

    Set<Vertex<String>> visited = new HashSet<>();
    dfs(vertex, k, visited);
}

  public static void dfs(Vertex<String> current, int k, Set<Vertex<String>> visited) {
    if (visited.contains(current)) return;

    visited.add(current);

    if (current.data != null && current.data.length() < k) {
        System.out.println(current.data);
    }

    for (Vertex<String> neighbor : current.neighbors) {
        dfs(neighbor, k, visited);
    }
  }

  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {

    if (vertex == null) return "";

    Set<Vertex<String>> visited = new HashSet<>();
    return dfsLongest(vertex, visited);
}

  public static String dfsLongest(Vertex<String> current, Set<Vertex<String>> visited) {
    if (visited.contains(current)) return "";


    visited.add(current);

    String longest = current.data != null ? current.data : "";

    for (Vertex<String> neighbor : current.neighbors) {

        String candidate = dfsLongest(neighbor, visited);

        if (candidate.length() > longest.length()) {

            longest = candidate;
        }
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
    if (vertex == null) return;

    Set<Vertex<T>> visited = new HashSet<>();


    dfsSelfLoopers(vertex, visited);
}

  public static <T> void dfsSelfLoopers(Vertex<T> current, Set<Vertex<T>> visited) {


    if (visited.contains(current)) return;

    visited.add(current);

    if (current.neighbors.contains(current)) {

        System.out.println(current.data);  
    }

    for (Vertex<T> neighbor : current.neighbors) {
        dfsSelfLoopers(neighbor, visited);
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
    if (start == null || destination == null) return false;

    
    if (start == destination) return true;

    Set<Airport> visited = new HashSet<>();

    return dfs(start, destination, visited);
}

private static boolean dfs(Airport current, Airport destination, Set<Airport> visited) {
    if (current == destination) return true;
    visited.add(current);

    for (Airport next : current.getOutboundFlights()) {

        if (!visited.contains(next)) {

            if (dfs(next, destination, visited)) {

                return true;
            }
        }
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
    return new HashSet<>();
  }
}

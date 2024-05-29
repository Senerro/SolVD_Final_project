package com.solvd.pathfinding.graph;

import com.solvd.common.exceptions.RouteGraphException;
import com.solvd.entities.City;
import com.solvd.entities.Route;
import com.solvd.entities.RouteResult;
import com.solvd.pathfinding.helper.EdgeWeightHelper;
import com.solvd.pathfinding.helper.RouteResultHelper;
import com.solvd.pathfinding.helper.SelectedWeightType;

import java.util.*;

import static com.solvd.pathfinding.helper.SelectedWeightType.FAST;
import static com.solvd.pathfinding.helper.SelectedWeightType.SHORT;

public class Graph {
    private final HashMap<Long, Node> graph = new HashMap<>();
    private EdgeWeight edgeWeight;

    public Graph(final List<Route> routes) {
        for (var element : routes) {
            add(makeNode(element.getDepartureCity()), makeNode(element.getArrivalCity()), makeEdgeWeight(element));
        }
    }

    private Node makeNode(City city) {
        return new Node(city);
    }

    private EdgeWeight makeEdgeWeight(Route route) {
        return new EdgeWeight(route.getDistance(), route.getTransitTimeMinutes());
    }

    private Node getNode(Node node) {
        return graph.getOrDefault(node.getCityId(), null);
    }

    private Node findNodeOfCity(City city) {
        return getNode(makeNode(city));
    }

    private Node addNode(Node node) {
        graph.put(node.getCityId(), node);
        return node;
    }

    private Node addOrGet(Node node) {
        Node graphNode = getNode(node);
        if (graphNode == null) graphNode = addNode(node);
        return graphNode;
    }

    private void add(Node first, Node second, EdgeWeight weight) {
        fillUp(first, second, weight);
        fillUp(second, first, weight);
    }

    private void fillUp(Node first, Node second, EdgeWeight weight) {
        Node node = addOrGet(first);
        Node neighbour = addOrGet(second);

        Edge edge = new Edge(neighbour, weight);
        node.getEdges().add(edge);
        neighbour.getParents().put(node, edge);
    }

    public RouteResult makeShortPathWithStopPoint(City start, City finish, City additional) {
        return RouteResultHelper.resultSummation(makeShortPath(start, additional), makeShortPath(additional, finish));
    }

    public RouteResult makeFastPathWithStopPoint(City start, City finish, City additional) {
        return RouteResultHelper.resultSummation(makeFastPath(start, additional), makeFastPath(additional, finish));
    }

    public RouteResult makeShortPathWithStopPointOrderedCollection(City start, City finish, List<City> additionalCities) {
        LinkedList<RouteResult> routeResults = new LinkedList<>();
        for (int i = 0; i < additionalCities.size(); i++) {
            if (i + 1 >= additionalCities.size()) break;
            routeResults.add(makeShortPath(additionalCities.get(i), additionalCities.get(i + 1)));
        }
        routeResults.addFirst(makeShortPath(start, additionalCities.get(0)));
        routeResults.addLast(makeShortPath(additionalCities.get(additionalCities.size() - 1), finish));

        return RouteResultHelper.resultCollectionSummation(routeResults);
    }

    public RouteResult makeFastPathWithStopPointOrderedCollection(City start, City finish, List<City> additionalCities) {
        LinkedList<RouteResult> routeResults = new LinkedList<>();
        for (int i = 0; i < additionalCities.size(); i++) {
            if (i + 1 >= additionalCities.size()) break;
            routeResults.add(makeFastPath(additionalCities.get(i), additionalCities.get(i + 1)));
        }
        routeResults.addFirst(makeFastPath(start, additionalCities.get(0)));
        routeResults.addLast(makeFastPath(additionalCities.get(additionalCities.size() - 1), finish));

        return RouteResultHelper.resultCollectionSummation(routeResults);
    }

    private LinkedList<Node> makePath(Node start, Node finish, SelectedWeightType weightType) {
        HashSet<Node> unprocessed = new HashSet<>();
        HashMap<Node, EdgeWeight> weightMap = new HashMap<>();
        initHashTables(start, unprocessed, weightMap);
        calculateWeightForEachNode(unprocessed, weightMap, weightType);
        if (weightMap.get(finish).isMAX()) {
            throw new RouteGraphException("No route found between the selected nodes. " +
                    "Ensure that the graph is correctly constructed and contains the necessary data.");
        }
        return getPath(start, finish, weightMap, weightType);
    }

    public RouteResult makeShortPath(City start, City finish) {
        LinkedList<Node> list = makePath(findNodeOfCity(start), findNodeOfCity(finish), SHORT);
        return RouteResultHelper.makeResult(list, edgeWeight);
    }

    public RouteResult makeFastPath(City start, City finish) {
        LinkedList<Node> list = makePath(findNodeOfCity(start), findNodeOfCity(finish), FAST);
        return RouteResultHelper.makeResult(list, edgeWeight);
    }

    private void initHashTables(Node start, HashSet<Node> unprocessed, HashMap<Node, EdgeWeight> nodeWeight) {
        for (var element : graph.entrySet()) {
            Node node = element.getValue();
            unprocessed.add(node);
            nodeWeight.put(node, EdgeWeightHelper.MAX());
        }
        nodeWeight.put(start, new EdgeWeight());
    }

    private void calculateWeightForEachNode(HashSet<Node> unprocessed, HashMap<Node, EdgeWeight> nodeWeight, SelectedWeightType weightType) {
        while (!unprocessed.isEmpty()) {
            Node node = getNodeWithMinWeight(unprocessed, nodeWeight, weightType);
            if (nodeWeight.get(node).isMAX()) return;

            for (var element : node.getEdges()) {
                Node neighbour = element.getNeighbour();
                if (!unprocessed.contains(neighbour)) continue;

                EdgeWeight newWeight = EdgeWeightHelper.summation(nodeWeight.get(node), element.getWeight());
                if (EdgeWeightHelper.isLessWeight(newWeight, nodeWeight.get(neighbour), weightType)) {
                    nodeWeight.put(neighbour, newWeight);
                }
            }
            unprocessed.remove(node);
        }
    }

    private Node getNodeWithMinWeight(HashSet<Node> unprocessed, HashMap<Node, EdgeWeight> nodeWeight, SelectedWeightType weightType) {
        return unprocessed.stream().min(Comparator.comparingDouble(currentNode -> nodeWeight.get(currentNode).weightValue(weightType))).orElse(null);
    }

    public LinkedList<Node> getPath(Node start, Node finish, HashMap<Node, EdgeWeight> nodeWeight, SelectedWeightType weightType) {
        LinkedList<Node> path = new LinkedList<>();
        Node node = finish;
        this.edgeWeight = nodeWeight.get(finish);
        while (node != start) {
            double weight = nodeWeight.get(node).weightValue(weightType);
            path.addFirst(node);
            for (var element : node.getParents().entrySet()) {
                Node neighbour = element.getKey();
                Edge parentEdge = element.getValue();
                if (!nodeWeight.containsKey(neighbour)) continue;

                if ((parentEdge.getWeight().weightValue(weightType) + nodeWeight.get(neighbour).weightValue(weightType)) == weight) {
                    nodeWeight.remove(node);
                    node = neighbour;
                    break;
                }
            }
        }
        path.addFirst(node);
        return path;
    }
}

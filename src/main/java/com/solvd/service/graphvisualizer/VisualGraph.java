package com.solvd.service.graphvisualizer;

import com.solvd.common.exceptions.StylesheetNotFoundException;
import com.solvd.entities.Route;
import lombok.Getter;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.solvd.common.util.FileReader.readFile;

@Getter
public class VisualGraph {
    private static final String DEFAULT_STYLESHEET_LOCATION = "src/main/resources/graph_style.css";
    private static final String BUILTIN_STYLESHEET =
        "node {" +
            "  fill-color: black;" +
            "}" +
            "node.departure {" +
            "  fill-color: red;" +
            "  size: 100px;" +
            "}" +
            "node.arrival {" +
            "  fill-color: red;" +
            "  size: 100px;" +
            "}";
    private final Graph graph = new SingleGraph("");

    public static VisualGraph buildFromRoutes(List<Route> routeList, boolean fallbackToBuiltinStylesheet) {
        VisualGraph visualGraph = new VisualGraph();
        visualGraph.getGraph().setAttribute("ui.stylesheet", loadStylesheet(fallbackToBuiltinStylesheet));
        visualGraph.getGraph().setAttribute("ui.quality");
        visualGraph.getGraph().setAutoCreate(true);

        Set<String> addedNodes = new HashSet<>();
        for (var route : routeList) {
            String departureCity = route.getDepartureCityName();
            String arrivalCity = route.getArrivalCityName();

            checkIfNodeIsUniqueAndAddToGraph(addedNodes, departureCity, visualGraph);
            checkIfNodeIsUniqueAndAddToGraph(addedNodes, arrivalCity, visualGraph);

            visualGraph.addEdge(departureCity + arrivalCity, departureCity, arrivalCity);
            visualGraph.getNode(departureCity).setAttribute("ui.label", departureCity);
            visualGraph.getNode(arrivalCity).setAttribute("ui.label", arrivalCity);
        }

        return visualGraph;
    }

    public Node getNode(String id) {
        return graph.getNode(id);
    }

    public Edge getEdge(String id) {
        return graph.getEdge(id);
    }

    public void addNode(String id) {
        graph.addNode(id);
    }

    public void addEdge(String id, String from, String to) {
        graph.addEdge(id, from, to);
    }

    private static String loadStylesheet(boolean fallbackToBuiltinStylesheet) {

        try {
            return readFile(DEFAULT_STYLESHEET_LOCATION, StandardCharsets.UTF_8);
        } catch (IOException e) {
            if (!fallbackToBuiltinStylesheet) {
                throw new StylesheetNotFoundException("Unable to load required stylesheet file.");
            }
        }
        return BUILTIN_STYLESHEET;
    }

    private static void checkIfNodeIsUniqueAndAddToGraph(Set<String> addedNodes, String nodeId, VisualGraph visualGraph) {
        if (!addedNodes.contains(nodeId)) {
            visualGraph.addNode(nodeId);
            addedNodes.add(nodeId);
        }
    }
}

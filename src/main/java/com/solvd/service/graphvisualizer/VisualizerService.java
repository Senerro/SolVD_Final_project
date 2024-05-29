package com.solvd.service.graphvisualizer;

import com.solvd.entities.City;
import com.solvd.entities.RouteResult;
import com.solvd.pathfinding.helper.SelectedWeightType;
import lombok.AllArgsConstructor;
import org.graphstream.graph.Edge;
import org.graphstream.ui.view.Viewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class VisualizerService {
    private static final String UNIVERSAL_STYLE = "universal";
    private static final String UI_CLASS_ATTRIBUTE = "ui.class";
    private final VisualGraph visualGraph;

    public void markDepartureAndArrivalNodes(String departureCity, String arrivalCity) {
        visualGraph.getNode(departureCity).setAttribute(UI_CLASS_ATTRIBUTE, "departure");
        visualGraph.getNode(arrivalCity).setAttribute(UI_CLASS_ATTRIBUTE, "arrival");
    }

    public void colorGraph(SelectedWeightType criteria, RouteResult routeResult) {
        String edgeStyle;
        if (Objects.requireNonNull(criteria) == SelectedWeightType.FAST) {
            edgeStyle = "time";
        } else {
            edgeStyle = "distance";
        }

        List<String> edgeIds = getEdgeIds(routeResult.getCities());
        for (String edgeId : edgeIds) {
            Edge currentEdge = visualGraph.getEdge(edgeId);
            if (currentEdge != null && currentEdge.getAttribute(UI_CLASS_ATTRIBUTE) != null) {
                currentEdge.setAttribute(UI_CLASS_ATTRIBUTE, UNIVERSAL_STYLE);
            } else if (currentEdge != null && currentEdge.getAttribute(UI_CLASS_ATTRIBUTE) == null) {
                currentEdge.setAttribute(UI_CLASS_ATTRIBUTE, edgeStyle);
            }
        }
    }

    public void displayGraph() {
        System.setProperty("org.graphstream.ui", "swing");
        Viewer viewer = visualGraph.getGraph().display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }

    private List<String> getEdgeIds(List<City> cities) {
        List<String> edges = new ArrayList<>();

        String cityBuf = cities.get(0).getName();
        for (int i = 1; i < cities.size(); i++) {
            edges.add(cityBuf + cities.get(i).getName());
            edges.add(cities.get(i).getName() + cityBuf);
            cityBuf = cities.get(i).getName();
        }

        return edges;
    }
}

import java.util.*;

public class InterferenceNode {
    private String name;
    private List<InterferenceNode> neighbors;
    private List<Integer> livenessRange;
    private int color;
    private IRVar varObj;
    /*
    public InterferenceNode(String nameParam, List<InterferenceNode> neighborsParam) {
        this.name = nameParam;
        this.neighbors = neighborsParam;
        this.livenessRange = new ArrayList<Integer>();
        color = -1;
    }
    */
    public InterferenceNode(String nameParam) {
        this.name = nameParam;
        this.neighbors = new ArrayList<InterferenceNode>();
        color = -1;
    }
    public InterferenceNode(String nameParam, List<Integer> livenessRange, IRVar v) {
        this.name = nameParam;
        this.neighbors = new ArrayList<InterferenceNode>();
        color = -1;
        this.livenessRange = livenessRange;
        this.varObj = v;
    }

    public IRVar getVarObj() {
        return this.varObj;
    }

    public List<Integer> getLivenessRange() {
        return livenessRange;
    }
    public void setLivenessRange(List<Integer> livenessRangeParam) {
        this.livenessRange = livenessRangeParam;
    }

    public void setName(String nameParam) {
        name = nameParam;
    }
    public void setNeighbors(List<InterferenceNode> neighborsParam) {
        neighbors = neighborsParam;
    }
    public void setColor(int colorParam) {
        color = colorParam;
    }
    public String getName() {
        return name;
    }
    public List<InterferenceNode> getNeighbors() {
        return neighbors;
    }
    public List<String> getNeighborNames() {
        List<String> returnList = new ArrayList<String>();
        for (InterferenceNode neighbor : neighbors) {
            returnList.add(neighbor.getName());
        }
        return returnList;
    }
    public int getColor() {
        return color;
    }
    public int getDegree() {
        return neighbors.size();
    }


    public void addNeighbor(InterferenceNode neighborParam) {
        neighbors.add(neighborParam);
    }
    public void addAllNeighbors(List<InterferenceNode> neighborsParam) {
        neighbors.addAll(neighborsParam);
    }

    public void removeNeighbor(InterferenceNode neighborParam) {
        neighbors.remove(neighborParam);
    }
    public void removeColor() {
        color = -1;
    }
    public int isColorable(int numColors) {
        List<Integer> neighborColors = new ArrayList<Integer>();
        for (InterferenceNode neighbor : neighbors) {
            neighborColors.add(neighbor.color);
        }
        for (int i = 0; i < numColors; i++) {
            if (!neighborColors.contains(i)) {
                return i;
            }
        }
        return -1;
    }
}

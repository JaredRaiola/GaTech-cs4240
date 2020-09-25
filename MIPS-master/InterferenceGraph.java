import java.util.List;
import java.util.ArrayList;
public class InterferenceGraph {
    public List<InterferenceNode> nodes;
    public List<InterferenceNode> spilledNodes;
    public boolean isValid;

    public InterferenceGraph(List<InterferenceNode> nodes) {
        this.nodes = new ArrayList<InterferenceNode>(nodes);
        this.spilledNodes = new ArrayList<InterferenceNode>();
        this.isValid = false;
    }
}

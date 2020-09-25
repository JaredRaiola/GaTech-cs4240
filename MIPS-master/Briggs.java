import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.io.FileNotFoundException;


public class Briggs {
	public InterferenceGraph interferenceGraph;


  public Briggs(BlockMaker createBlocks) {
      List<InterferenceNode> nodes = new ArrayList<InterferenceNode>();
      for (IRVar v : createBlocks.getVars()) {
          nodes.add(new InterferenceNode(v.getVarName(), v.getVarLiveBlocks(), v));
      }
      this.interferenceGraph = createInterferenceGraph(nodes,createBlocks.getBlocks().size(), createBlocks);

      while(!interferenceGraph.isValid) {
      	interferenceGraph = briggs(interferenceGraph, 6);
      }
  }

  public InterferenceGraph getInteferenceGraph() {
  	return this.interferenceGraph;
  }

  private static InterferenceGraph createInterferenceGraph(List<InterferenceNode> rangeNodes, int numBlocks, BlockMaker createdBlocks) {
      for (int i = 1; i < numBlocks + 1; i++) {
        List<InterferenceNode> neighbors = new ArrayList<InterferenceNode>();
        List<Integer> startLinePerBlock = new ArrayList<Integer>();
        List<Integer> endLinePerBlock = new ArrayList<Integer>();
        //Find all nodes that share this line and list them as neighbors
        for (InterferenceNode rangeNode : rangeNodes) {
            if (rangeNode.getLivenessRange().contains(i)) {
                if (rangeNode.getVarObj().getVarStartBlock() == i) {
                    //add start line
                    startLinePerBlock.add(rangeNode.getVarObj().getVarStartLine());
                } else {
                    //add one
                    startLinePerBlock.add(1);
                }

                if (rangeNode.getVarObj().getVarEndBlock() == i) {
                    //add end line
                    endLinePerBlock.add(rangeNode.getVarObj().getVarEndLine());
                } else {
                    //add end
                    endLinePerBlock.add(createdBlocks.getBlocks().get(i-1).getBlockIR().size());
                }

                neighbors.add(rangeNode);
            }
        }
        for (int q = 0; q < neighbors.size(); q++) {
            for (int j = 0; j < neighbors.size(); j++) {
                if (j != q) {
                    if (startLinePerBlock.get(q) <= endLinePerBlock.get(j)) {
                        //add because they overlap
                        if (!neighbors.get(q).getNeighbors().contains(neighbors.get(j))){
                            neighbors.get(q).addNeighbor(neighbors.get(j));
                        }
                    } else if (endLinePerBlock.get(q) >= startLinePerBlock.get(j)) {
                        //add because they overlap
                        if (!neighbors.get(q).getNeighbors().contains(neighbors.get(j))){
                            neighbors.get(q).addNeighbor(neighbors.get(j));
                        }
                    }
                }
            }
        }
    }
    // for (InterferenceNode n : rangeNodes) {
    //     System.out.println(n.getName());
    //     System.out.println(n.getNeighborNames());
    // }
    return new InterferenceGraph(rangeNodes);
  }

  private static InterferenceGraph briggs(InterferenceGraph interferenceGraph, int numAvailableRegs) {
    Stack<InterferenceNode> nodeStack = new Stack<InterferenceNode>();
    List<InterferenceNode> removeThese = new ArrayList<InterferenceNode>();
    List<InterferenceNode> holdNodes = new ArrayList<InterferenceNode>();
    for (InterferenceNode n : interferenceGraph.nodes) {
    	holdNodes.add(n);
    }
    while (!interferenceGraph.nodes.isEmpty()) {
    	for (InterferenceNode n : interferenceGraph.nodes) {
    		if (n.getDegree() < numAvailableRegs) {
    			removeThese.add(n);
    		}
    	}
    	for (InterferenceNode node : removeThese) {
          nodeStack.push(node);
          interferenceGraph.nodes.remove(node);
      }
      if (!interferenceGraph.nodes.isEmpty()) {
      	InterferenceNode hdNode = interferenceGraph.nodes.get(0);

      	for (InterferenceNode node : interferenceGraph.nodes) {
          if (node.getDegree() > hdNode.getDegree()) {
              hdNode = node;
          }
         }

      	interferenceGraph.nodes.remove(hdNode);
      	nodeStack.push(hdNode);
      }
    }
    while (!nodeStack.isEmpty()) {
    	InterferenceNode topNode = nodeStack.pop();
    	int availableColor = topNode.isColorable(numAvailableRegs);
    	if (availableColor > -1) {
        topNode.setColor(availableColor);
        interferenceGraph.nodes.add(topNode);
      } else {
      	interferenceGraph.spilledNodes.add(topNode);
      	interferenceGraph.nodes = holdNodes;
      	interferenceGraph.nodes.remove(topNode);
      	// for (InterferenceNode nod : interferenceGraph.nodes) {
      	// 	System.out.println(nod.getName());
      	// }
      	// System.out.println("Spilled");
      	// System.out.println(topNode.getName());
      	interferenceGraph.isValid = false;
      	return interferenceGraph;
      }
    }

    interferenceGraph.isValid = true;
    return interferenceGraph;
  }
}

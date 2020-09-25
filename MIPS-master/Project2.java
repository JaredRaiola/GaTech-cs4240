
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.io.FileNotFoundException;

public class Project2 {
    public static void main(String[] args) throws FileNotFoundException {
        try { 
            String file = args[0];

            PrintStream out = new PrintStream(new FileOutputStream("Output.s"));

            System.setOut(out);


            try {
                Scanner inputFile = new Scanner(new File(file)).useDelimiter("\n");
                List<IRLine> unAllocatedLines = new ArrayList<IRLine>();

                while (inputFile.hasNext()) {
                    String[] splitLine = inputFile.next().trim().split(",");
                    splitLine[splitLine.length-1] = splitLine[splitLine.length-1].replaceAll(",","");
                    unAllocatedLines.add(new IRLine(splitLine));

                }

                
                try {
                    if (args[1].equals("-n")) {
                        System.out.println("Naive:\n");
                        System.out.println("MIPS:");
                        OldNaiveAllocator allocator = new OldNaiveAllocator(unAllocatedLines);
                        List<IRLine> allocatedLines = allocator.getAllocatedLines();

                        MIPSGenerator mipsGenerator = new MIPSGenerator(allocatedLines);
                        List<String> assemblyMIPS = mipsGenerator.getMIPS();
                        for (String row : assemblyMIPS) {
                            System.out.println(row);
                        }
                    } else if (args[1].equals("-b")) {
                        System.out.println("Briggs:\n");
                        BlockMaker createBlocks = new BlockMaker(unAllocatedLines);
                        Briggs brigg = new Briggs(createBlocks);
                        for (InterferenceNode n : brigg.interferenceGraph.nodes) {
                            int ind = createBlocks.getVarsNames().indexOf(n.getName());
                            for (IRLine line : unAllocatedLines) {
                                if (line.toString().contains(createBlocks.getVars().get(ind).getVarName())) {
                                    if (line.getOperationType() != "intList") {
                                        if (line.getSource0().equals(n.getName())) {
                                            line.setLoadSource0();
                                            line.setLoad0VarName(n.getName());
                                            line.setStoreSource0();
                                            line.setStoreVarName(n.getName());
                                            line.setTRegister0(n.getColor());
                                        } else if (line.getSource1().equals(n.getName())) {
                                            line.setLoadSource1();
                                            line.setLoad1VarName(n.getName());
                                            line.setTRegister1(n.getColor());
                                        } else {
                                            line.setTRegister2(n.getColor());
                                        }
                                    }
                                }
                            }
                        }
                        System.out.println("Liveness:");
                        System.out.println("====================");
                        for (IRVar v : createBlocks.getVars()) {
                            System.out.println(v.getVarName());
                            System.out.println("Start");
                            System.out.println(v.getVarStartLine());
                            System.out.println(v.getVarStartBlock());
                            System.out.println("End");
                            System.out.println(v.getVarEndLine());
                            System.out.println(v.getVarEndBlock());
                            System.out.println(v.getVarLiveBlocks());
                            System.out.println("====================");
                        }

                        System.out.println("\nBlocks:");
                        for (Block b : createBlocks.getBlocks()) {
                            System.out.println(b.getBlockNumber());
                            System.out.println(b.getBlockIR());
                            System.out.println(b.getCFGLabels());
                            System.out.println("-----------------------");
                        }

                        System.out.println("\nCFG:");
                        for (Block b : createBlocks.getBlocks()) {
                            System.out.println("Block #"+b.getBlockNumber()+" maps to:");
                            for (int i : b.getCFGBlockNext()) {
                                System.out.print(i + " ");
                            }
                            System.out.print("\n");
                            System.out.println("Prev Blocks:");
                            for (int i : b.getCFGBlockPrev()) {
                                System.out.print(i + " ");
                            }
                            System.out.print("\n\n");
                        }
                        NaiveAllocator allocator = new NaiveAllocator(unAllocatedLines);
                        List<IRLine> allocatedLines = allocator.getAllocatedLines();


                        MIPSGenerator mipsGenerator = new MIPSGenerator(allocatedLines);
                        List<String> assemblyMIPS = mipsGenerator.getMIPS();
                        System.out.println("\nMIPS:");
                        for (String row : assemblyMIPS) {
                            System.out.println(row);
                        }
                    } 

                } catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\nYou did not specify which algorithm to use!\n");
                    System.out.println("Please run with:\n\nmake run filename=\"YOUR FILE NAME (-n for naive OR -b for briggs)\"");
                    System.out.println("\nDO NOT FORGET THE QUOTES AROUND THE FILENAME ARGUMENT");

                }

    // //            System.out.println("\nAssembly MIPS:");

    //             for (String row : assemblyMIPS) System.out.println(row);


                /*
                int programLength = 9;
                InterferenceNode node1 = new InterferenceNode("var1",Arrays.asList(3,5,7));
                InterferenceNode node2 = new InterferenceNode("var2",Arrays.asList(3,4,5));
                InterferenceNode node3 = new InterferenceNode("var3",Arrays.asList(5,6,7));
                InterferenceNode node4 = new InterferenceNode("var4",Arrays.asList(7,8,9));
                List<InterferenceNode> nodes = Arrays.asList(node1,node2,node3,node4);
                InterferenceGraph interferenceGraph = createInterferenceGraph(nodes,programLength);
                interferenceGraph = briggs(interferenceGraph, 2);
                if (interferenceGraph.isValid) {
                    System.out.println("briggs worked");
                }
                else {
                    System.out.println("briggs didnt work");
                    System.out.println("spilled node: " + interferenceGraph.spilledNodes.get(0).getName());
                }
                */


            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException");
            }
        } catch (ArrayIndexOutOfBoundsException e){
        System.out.println("\nYou did not specify which algorithm to use!\n");
        System.out.println("Please run with:\n\nmake run filename=\"YOUR FILE NAME (-n for naive OR -b for briggs)\"");
        System.out.println("\nDO NOT FORGET THE QUOTES AROUND THE FILENAME ARGUMENT");
        }

    }
}

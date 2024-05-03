import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class MacroProcessor {
    
    public static void main(String[] args) {
        HashMap<String, String> macroTable = new HashMap<>();
        macroTable.put("ABC", "LOAD p\nSUB q");
        macroTable.put("ADD1", "LOAD X\nSTORE ARG");
        macroTable.put("ADD5", "STORE A2\nADD1 5\nADD1 10\nLOAD A1\nLOAD A3");
        
        List<String> sourceCode = new ArrayList<>();
        sourceCode.add("LOAD A");
        sourceCode.add("MACRO ABC");
        sourceCode.add("LOAD p");
        sourceCode.add("SUB q");
        sourceCode.add("MEND");
        sourceCode.add("STORE B");
        sourceCode.add("MULT D");
        sourceCode.add("MACRO ADD1 ARG");
        sourceCode.add("LOAD X");
        sourceCode.add("STORE ARG");
        sourceCode.add("MEND");
        sourceCode.add("LOAD B");
        sourceCode.add("MACRO ADD5 A1, A2, A3");
        sourceCode.add("STORE A2");
        sourceCode.add("ADD1 5");
        sourceCode.add("ADD1 10");
        sourceCode.add("LOAD A1");
        sourceCode.add("LOAD A3");
        sourceCode.add("MEND");
        sourceCode.add("ADD1 t");
        sourceCode.add("ABC");
        sourceCode.add("ADD5 D1, D2, D3");
        sourceCode.add("END");
        
        List<String> intermediateCode = new ArrayList<>();
        
        // First pass: Expand macros and generate intermediate code
        for (String line : sourceCode) {
            if (line.startsWith("MACRO")) {
                String macroName = line.split("\\s+")[1];

                String macroDefinition = macroTable.get(macroName);
                
                intermediateCode.add("// Macro: " + macroName);
                
                for (String macroLine : macroDefinition.split("\n")) {
                    intermediateCode.add(macroLine);
                }
            } 
            // else {
            //     intermediateCode.add(line);
            // }
        }
        
        // Print intermediate code
        System.out.println("Intermediate Code:");
        for (String code : intermediateCode) {
            System.out.println(code);
        }
    }
}

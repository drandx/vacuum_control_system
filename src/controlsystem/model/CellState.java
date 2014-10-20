package controlsystem.model;

import java.util.HashMap;
import java.util.Map;

public enum CellState {
    UNKNOWN(0, "Unknown", "Unkown objective"),
    OPEN(1, "Open", "Open Cell."),
    OBSTACLE(2, "Obstacle", "Obstacle in the Cell."),
    STAIRS(4, "Stairs", "Stairs in the Cell.");
 
    private int code;
    private String label;
    private String description;
 
    /**
     * A mapping between the integer code and its corresponding Status to facilitate lookup by code.
     */
    private static Map<Integer, CellState> codeToStatusMapping;
 
    private CellState(int code, String label, String description) {
        this.code = code;
        this.label = label;
        this.description = description;
    }
 
    public static CellState getStatus(int i) {
        if (codeToStatusMapping == null) {
            initMapping();
        }
        return codeToStatusMapping.get(i);
    }
 
    private static void initMapping() {
        codeToStatusMapping = new HashMap<Integer, CellState>();
        for (CellState s : values()) {
            codeToStatusMapping.put(s.code, s);
        }
    }
 
    public int getCode() {
        return code;
    }
 
    public String getLabel() {
        return label;
    }
 
    public String getDescription() {
        return description;
    }
 
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Status");
        sb.append("{code=").append(code);
        sb.append(", label='").append(label).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
 
    public static void main(String[] args) {
        System.out.println(CellState.OPEN);
        System.out.println(CellState.getStatus(1));
    }
}
package controlsystem.model;

import java.util.HashMap;
import java.util.Map;

public enum SurfaceType {
    BARE_FLOOR(1, "Bare Floor", "Bare Floor."),
    LOW_PILE_FLOOR(2, "Low Pile", "Low Pile."),
    HIGH_PILE_FLOOR(3, "High Pile", "High Pile.");
 
    private int code;
    private String label;
    private String description;
 
    /**
     * A mapping between the integer code and its corresponding Status to facilitate lookup by code.
     */
    private static Map<Integer, SurfaceType> codeToStatusMapping;
 
    private SurfaceType(int code, String label, String description) {
        this.code = code;
        this.label = label;
        this.description = description;
    }
 
    public static SurfaceType getStatus(int i) {
        if (codeToStatusMapping == null) {
            initMapping();
        }
        return codeToStatusMapping.get(i);
    }
 
    private static void initMapping() {
        codeToStatusMapping = new HashMap<Integer, SurfaceType>();
        for (SurfaceType s : values()) {
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
        System.out.println(SurfaceType.LOW_PILE_FLOOR);
        System.out.println(SurfaceType.getStatus(1));
        System.out.println(SurfaceType.getStatus(2).getCode());
    }
}
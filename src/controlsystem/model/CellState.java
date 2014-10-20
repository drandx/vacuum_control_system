package controlsystem.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CellState 
{
	UNKNOWN(0), 
	OPEN(1),
	OBSTACLE(2),
	STAIRS(4);

	private static final Map<Integer,CellState> lookup 
	= new HashMap<Integer,CellState>();

	static {
		for(CellState s : EnumSet.allOf(CellState.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private CellState(int code) {
		this.code = code;
	}

	public int getCode() { return code; }

	public static CellState get(int code) { 
		return lookup.get(code); 
	}
}
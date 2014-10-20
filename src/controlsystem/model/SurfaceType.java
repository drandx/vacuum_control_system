package controlsystem.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum SurfaceType 
{
	BARE_FLOOR(1), 
	LOW_PILE_CARPET(2),
	HIGH_PILE_CARPET(3);

	private static final Map<Integer,SurfaceType> lookup 
	= new HashMap<Integer,SurfaceType>();

	static {
		for(SurfaceType s : EnumSet.allOf(SurfaceType.class))
			lookup.put(s.getCode(), s);
	}

	private int code;

	private SurfaceType(int code) {
		this.code = code;
	}

	public int getCode() { return code; }

	public static SurfaceType get(int code) { 
		return lookup.get(code); 
	}
}

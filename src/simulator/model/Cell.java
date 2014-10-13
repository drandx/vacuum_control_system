package simulator.model;

public class Cell
{
	int xs;
	int ys;
	int ss;
	String ps;
	int ds;
	int cs;
	
	
	public Cell(int xs, int ys, int ss, String ps, int ds, int cs) {
		super();
		this.xs = xs;
		this.ys = ys;
		this.ss = ss;
		this.ps = ps;
		this.ds = ds;
		this.cs = cs;
	}



	public Cell() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getXs() {
		return xs;
	}



	public void setXs(int xs) {
		this.xs = xs;
	}



	public int getYs() {
		return ys;
	}



	public void setYs(int ys) {
		this.ys = ys;
	}



	public int getSs() {
		return ss;
	}



	public void setSs(int ss) {
		this.ss = ss;
	}



	public String getPs() {
		return ps;
	}



	public void setPs(String ps) {
		this.ps = ps;
	}



	public int getDs() {
		return ds;
	}



	public void setDs(int ds) {
		this.ds = ds;
	}



	public int getCs() {
		return cs;
	}



	public void setCs(int cs) {
		this.cs = cs;
	}
	
	@Override
    public String toString() {
        return this.xs+" " + this.ys + " " + this.ss + " " + this.ps + " " + this.cs;
    }
}


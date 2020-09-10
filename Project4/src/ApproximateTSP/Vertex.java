package ApproximateTSP;

public class Vertex {
	private int index;
	private double distance;
	private int parent;
    
	public Vertex(int index, double distance) {
		this.index = index;
		this.distance = distance;
		
	}
	
    public int compare(Vertex obj) {
        if (this.getVertexDis() < obj.getVertexDis()) {
            return -1;
        } else if (this.getVertexDis() == obj.getVertexDis()) {
            return 0;
        }
        return 1;
    }
	
	public double getVertexDis() {
		return distance;
		
	}
	public void setDis(double k) {
		distance = k;
		
	}
	public int getIndex() {
		return index;
		
	}
	public String toString() {
		return ""+distance;
		
	}
	
	
	

}
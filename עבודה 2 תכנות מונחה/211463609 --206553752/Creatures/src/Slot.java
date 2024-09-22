
public class Slot implements Comparable<Slot> {
	int x ;
	int y ;

	public Slot (int x,int y) {
		this.x = x ;
		this.y = y ;
		
	}
	public Slot (Slot p) {
		this.x = p.x ;
		this.y = p.y ;
		
	}
	
	public static Slot cloun (Slot p) {
		return (new Slot(p)) ;
	}

	@Override
	public int compareTo(Slot o) {
		if (x == o.x && y == o.y) {
			return 0;
		} 
		return -1;
	}
}
package StudentCode;

public class LocNode<T> {
	public Location loc;
	public List<T> dList;
	public LocNode<T> c1,c2,c3,c4;
	
	public LocNode(Location key, T data) {
		this.loc = key;
		this.dList = new LinkedList<T>();
		dList.insert(data);
		c1=c2=c3=c4=null;
	}



}

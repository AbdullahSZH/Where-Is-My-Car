package StudentCode;

public class BSTNode<K extends Comparable<K>,T> {

	public K key;
	public T data;
	public BSTNode<K,T> right,left;
	
	public BSTNode(K key, T data) {
		this.key = key;
		this.data = data;
		right=left=null;
	}
	
	
}

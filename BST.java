package StudentCode;
public class BST<K extends Comparable<K>, T> implements Map<K, T> {

	BSTNode<K, T> root,current;
	
	public BST() {
		root = current = null;
		}
	
	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return root == null;
	}

	@Override
	public boolean full() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T retrieve() {
		// TODO Auto-generated method stub
		return current.data;
	}

	@Override
	public void update(T e) {
		// TODO Auto-generated method stub
		current.data=e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		// TODO Auto-generated method stub
		int count=0;
		Pair<Boolean, Integer> pair ;
		
			BSTNode<K,T> p = root,q = root;
			if(empty()) {
				 pair = new Pair<Boolean, Integer>(false,count);
				 return pair;
			}
			while(p != null) {
				q = p;
				count++;
			
			if(p.key.compareTo(key)==0) {
				current = p;
				pair = new Pair<Boolean, Integer>(true,count);
				return pair;
			}
			else if(p.key.compareTo(key)>0)
				p = p.left;
			else
				p = p.right;
			}
			
			current = q;
			pair = new Pair<Boolean, Integer>(false,count);
			return pair;
	
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		// TODO Auto-generated method stub
		int count=0;
		Pair<Boolean, Integer> pair;
		BSTNode<K,T> p, q = current;
		
		
		if(find(key).first) {
			current = q; // findkey() modified current
			pair = new Pair<Boolean, Integer>(false,count);
			return pair; // key already in the BST
		}
		
		p = new BSTNode<K,T>(key,data);
		
		if (empty()) {
			root = current = p;
			pair = new Pair<Boolean, Integer>(true,count);
			return pair;
		}
		else {
		// current is pointing to parent of the new key
			if (key.compareTo(current.key)<0)
				current.left = p;
			else
				current.right = p;
			current = p;
			pair = new Pair<Boolean, Integer>(true,count);
			return pair;
		}

	}

	@Override
	public Pair<Boolean, Integer> remove(K key) {
		// TODO Auto-generated method stub
		int count=0;
		Pair<Boolean, Integer> pair;
		// Search for k
		 K k1 = key;
		 BSTNode<K,T> p = root;
		 BSTNode<K,T> q = null; // Parent of p
		
		 while (p != null) {
			 count++;
			 if (k1.compareTo(p.key)<0) {
				 q =p;
				 p = p.left;
			 } else if (k1.compareTo(p.key)>0) {
				 q = p;
				 p = p.right;
			 }
			 else { // Found the key
				 // Check the three cases
				 if ((p.left != null) && (p.right != null)) {
					 // Case 3: two children
					 // Search for the min in the right subtree
					 BSTNode<K,T> min = p.right;
//					 boolean looped = false;
					 q = p;
					 while (min.left != null) {
						 System.out.println("looped");
//						 looped =true;
						 q = min;
						 min = min.left;
					 }
					 p.key = min.key;
					 p.data = min.data;
					 k1 = min.key;
					p=min;
					//if(!looped)
					// min=null;
					 // Now fall back to either case 1 or 2
				 }
				 // The subtree rooted at p will change here
				 if (p.left != null) { // One child
					 p = p.left;
				 } else { // One or no children
					 p = p.right;
				 }
				 if (q == null) { // No parent for p, root must change
					 root = p;
				 } else if(p!=null) {
					 if (q.key.compareTo(p.key)>0) {
						 q.left = p;
					 } else {
						 q.right = p;
					 }
				 }
				 else {
					 if((q.right!=null)&&(q.right.key.compareTo(k1)==0))
						 q.right = null;
					 else
						 q.left =null;
						 
						 
				 }
				 current = root;
				 pair = new Pair<Boolean, Integer>(true,count);
				 return pair;
			 }// end of big else
		 }
	 pair = new Pair<Boolean, Integer>(false,count);
	 return pair; // Not found
	 }

	@Override
	public List<K> getAll() {
		// TODO Auto-generated method stub
		BSTNode<K,T> q = root;
		List<K> l = new LinkedList<K>();
		insertlist(q,l);
	
		return l;
	}
	
	
	private void insertlist(BSTNode<K,T> q,List<K> l) {
		if(q==null);
		else {
			if(q.left != null) {
				insertlist(q.left,l);
			}
		
			l.insert(q.key);
		
			if(q.right != null) {
				insertlist(q.right,l);
			}

		}
}

}

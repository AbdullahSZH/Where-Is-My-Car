package StudentCode;
public class TreeLocator<T> implements Locator<T> {

	
	LocNode<T> root,current;
	static int count;

	
	public TreeLocator() {
		root = current = null;
		}
	
	@Override
	public int add(T e, Location loc) {
		// TODO Auto-generated method stub
		int count=0;
		LocNode<T> p;
		Pair <Boolean, Integer>pair = find(loc);
		
		if(pair.first) {
			
				current.dList.insert(e);
				pair = new Pair <Boolean, Integer>(true,count);
				return pair.second;
			}
		
		p=new LocNode<T>(loc,e);
		
		if(root==null) {			
			root=current=p;
			pair = new Pair <Boolean, Integer>(true,count);
			return pair.second;
		}							
		else {
				if(p.loc.x<current.loc.x && p.loc.y<=current.loc.y)
					current.c1=p;
				else if(p.loc.x<=current.loc.x && p.loc.y>current.loc.y)
					current.c2=p;
				else if(p.loc.x>current.loc.x && p.loc.y>=current.loc.y)
					current.c3=p;
				else
					current.c4=p;
				
				current =p;
				pair = new Pair <Boolean, Integer>(true,count);
				return pair.second;
			}
					
		
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		boolean isfound = find(loc).first;
		int count = find(loc).second;
		
		if(isfound) {
			Pair<List<T>, Integer> p = new Pair<List<T>, Integer>(current.dList,count);
			return p;
		}
		else {
			List<T> l = new LinkedList<T>();
			Pair<List<T>, Integer> p = new Pair<List<T>, Integer>(l,count);
			return p;
		}
	
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		
		Pair<Boolean, Integer> p;
		boolean removed=false;
		
		if(find(loc).first) {
			if(!current.dList.empty()) {
				current.dList.findFirst();
			
				while(!current.dList.last()) {
					if(current.dList.retrieve().equals(e)) {	
						current.dList.remove();
						removed =true;
					}
					current.dList.findNext();
				}
				if(current.dList.retrieve().equals(e)) {
					current.dList.remove();
					removed =true;
				}
			}
		}
		
		p = new Pair<Boolean, Integer>(removed,find(loc).second);
		return p;
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> l = new LinkedList<Pair<Location, List<T>>>();
		LocNode<T> p = root;
		//getsAll(p,l);
		return getsAll(p,l);
	}

	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		count=0;
		
		Pair<List<Pair<Location, List<T>>>, Integer> pair;
		List<Pair<Location, List<T>>> l = new LinkedList<Pair<Location, List<T>>>();
		LocNode<T> p = root;
		pair = InRangeList(p,l,lowerLeft,upperRight);
		return pair;
	
	}
	
	
	//helper methods
	
private Pair<List<Pair<Location, List<T>>>,Integer> InRangeList(LocNode<T> p, List<Pair<Location, List<T>>> l,Location lowerLeft, Location upperRight){
		
		if(((p.loc.x < lowerLeft.x) || (p.loc.y < lowerLeft.y))|| (p.loc.x > upperRight.x || p.loc.y > upperRight.y )) {
			
			if(p.loc.y < lowerLeft.y) {
				if(p.loc.y>upperRight.y) {
					if(p.c3!=null)
						InRangeList(p.c3,l,lowerLeft,upperRight);
					}
				else if(p.loc.y>upperRight.y) {
					if(p.c2!=null)
						InRangeList(p.c2,l,lowerLeft,upperRight);
				}
				else {	
					if(p.c2!=null)
						InRangeList(p.c2,l,lowerLeft,upperRight);
					if(p.c3!=null)
						InRangeList(p.c3,l,lowerLeft,upperRight);}
				
			}
			else if(p.loc.y>upperRight.y) {
				if(p.loc.x<lowerLeft.x) {
					if(p.c4!=null)
						InRangeList(p.c4,l,lowerLeft,upperRight);
				}
				else if(p.loc.x > upperRight.x) {
					if(p.c1!=null)
						InRangeList(p.c1,l,lowerLeft,upperRight);
				}
				else {
				if(p.c1!=null)
					InRangeList(p.c1,l,lowerLeft,upperRight);
				if(p.c4!=null)
					InRangeList(p.c4,l,lowerLeft,upperRight);
			
				}
			}	
			else if(p.loc.x > upperRight.x) {
				if(p.loc.y>upperRight.y) {
					if(p.c1!=null)
						InRangeList(p.c1,l,lowerLeft,upperRight);
				}
				else if(p.loc.y < lowerLeft.y) {
					if(p.c2!=null)
						InRangeList(p.c2,l,lowerLeft,upperRight);
				}
				else {
					if(p.c1!=null)
						InRangeList(p.c1,l,lowerLeft,upperRight);
					if(p.c2!=null)
						InRangeList(p.c2,l,lowerLeft,upperRight);
				}
			}
			else if(p.loc.x<lowerLeft.x) {
				if(p.loc.y>upperRight.y) {
					if(p.c4!=null)
						InRangeList(p.c4,l,lowerLeft,upperRight);
				}
				else if(p.loc.y < lowerLeft.y) {
					if(p.c3!=null)
						InRangeList(p.c3,l,lowerLeft,upperRight);
				}
				else {
					if(p.c3!=null)
						InRangeList(p.c3,l,lowerLeft,upperRight);
					if(p.c4!=null)
						InRangeList(p.c4,l,lowerLeft,upperRight);
				}
			}
		}
		else {//count++;
			Pair<Location, List<T>> pair = new Pair<Location, List<T>>(p.loc,p.dList);
			l.insert(pair);	
			
			if(p.c1!=null) {
				InRangeList(p.c1,l,lowerLeft,upperRight);			
			}
			if(p.c2!=null) {			
				InRangeList(p.c2,l,lowerLeft,upperRight);			
			}
		 	if(p.c3!=null) {
			 	InRangeList(p.c3,l,lowerLeft,upperRight);
		 	}
		 	if(p.c4!=null) {
		 		InRangeList(p.c4,l,lowerLeft,upperRight);
		 	}
		}
		count++;
		Pair<List<Pair<Location, List<T>>>, Integer> lastpair = new Pair<List<Pair<Location, List<T>>>, Integer>(l,count);
			return lastpair;
	}
	
	private Pair<Boolean, Integer> find(Location loc) {
		
		int count=0;
		Pair<Boolean, Integer> pair ;
		LocNode<T> p = root, q = root;
		
		if(p==null) {
			pair = new Pair<Boolean, Integer>(false,count);
		 return pair;
		 }
		
		while(p!=null) {
			count++;
			q=p;
			
			if(p.loc.x==loc.x && p.loc.y == loc.y) {
				current = p;
				pair = new Pair<Boolean, Integer>(true,count);
				return pair;
			}
			else if(loc.x<p.loc.x && loc.y<=p.loc.y)
				p=p.c1;
			else if(loc.x<=p.loc.x && loc.y>p.loc.y)
				p=p.c2;
			else if(loc.x>p.loc.x && loc.y>=p.loc.y)
				p=p.c3;
			else
				p=p.c4;
		}
			
		current=q;
		pair = new Pair<Boolean, Integer>(false,count);
		return pair;
	}
	
	
private List<Pair<Location, List<T>>> getsAll(LocNode<T> p, List<Pair<Location, List<T>>> l){
		
		if(p.c1!=null) {
			
			getsAll(p.c1,l);
			//System.out.println("first data:"+p.dList.retrieve());
		}
		 if(p.c2!=null) {
			
			 getsAll(p.c2,l);
			//System.out.println("first data:"+p.dList.retrieve());
		}
		 if(p.c3!=null) {
			
			 getsAll(p.c3,l);
			//System.out.println("first data:"+p.dList.retrieve());
		}
		 if(p.c4!=null) {
			
			 getsAll(p.c4,l);
		//	System.out.println("first data:"+p.dList.retrieve());
		}
	
		 Pair<Location, List<T>> pair = new Pair<Location, List<T>>(p.loc,p.dList);
		 l.insert(pair);
		 return l;
	}

public void printAll() {
	
	
	List<Pair<Location, List<T>>> in =getAll();
	 
	
	in.findFirst();
	
		if(!in.empty()) {
			in.findFirst();
			while(!in.last()) {
				if(!in.retrieve().second.empty()) {
					in.retrieve().second.findFirst();
					while(!in.retrieve().second.last()) {
						System.out.print(in.retrieve().second.retrieve()+", ");
						in.retrieve().second.findNext();
					}
				
					System.out.print(in.retrieve().second.retrieve()+", ");
				}
					in.findNext();
			}
			//if(!in.retrieve().second.empty())
				System.out.print(in.retrieve().second.retrieve()+", ");
		}
		else System.out.println("No elements in range");
}

}

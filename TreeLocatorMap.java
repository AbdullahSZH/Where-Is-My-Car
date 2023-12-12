package StudentCode;
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {

	
	Map<K, Location> map;
	Locator<K> locator;
	
	public TreeLocatorMap() {
		map = new BST<K,Location>();
		locator = new TreeLocator<K>();
	}
	
	
	@Override
	public Map<K, Location> getMap() {
		// TODO Auto-generated method stub
		return map;
	}

	@Override
	public Locator<K> getLocator() {
		// TODO Auto-generated method stub
		return locator;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		// TODO Auto-generated method stub
		int count=0;
		Pair<Boolean, Integer> pair;
		
		pair = map.insert(k, loc);
		if(pair.first) {
			locator.add(k, loc);
			//pair = new Pair<Boolean, Integer>(true,count);
			return pair;
		}
		//pair = new Pair<Boolean, Integer>(false,count);
		return pair;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		// TODO Auto-generated method stub
		//int count=0;
		Pair<Boolean, Integer> pair=map.find(k);
		if(pair.first) {
			locator.remove(k,map.retrieve());
			map.remove(k);
			
			return pair = new Pair<Boolean, Integer>(true,add(k,loc).second+pair.second);
					
		}
		return pair = new Pair<Boolean, Integer>(false,pair.second);
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		// TODO Auto-generated method stub
		
		Pair<Location, Integer> pair;
		
		if(map.find(k).first) {
			pair = new Pair<Location, Integer>(map.retrieve(),map.find(k).second);
			return pair;}
		
		return pair = new Pair<Location, Integer>(null,map.find(k).second);
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		// TODO Auto-generated method stub

		Pair<Boolean, Integer> pair=map.find(k);
		if(pair.first) {
			locator.remove(k,map.retrieve());
			map.remove(k);
			pair = new Pair<Boolean, Integer>(true,pair.second);
			}
		pair = new Pair<Boolean, Integer>(false,pair.second);
		return pair;
	}

	@Override
	public List<K> getAll() {
		// TODO Auto-generated method stub		
		return map.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		// TODO Auto-generated method stub
		int count=0;
		Pair<List<K>, Integer> pair;
		List<Pair<Location, List<K>>> in =locator.inRange(lowerLeft,upperRight).first;
		List<K> l = new LinkedList<K>();
		
		//in.findFirst();
		
			if(!in.empty()) {
				in.findFirst();
				while(!in.last()) {
					if(!in.retrieve().second.empty()) {
						in.retrieve().second.findFirst();
						while(!in.retrieve().second.last()) {
							l.insert(in.retrieve().second.retrieve());
							in.retrieve().second.findNext();
						}
						l.insert(in.retrieve().second.retrieve());
					}
					in.findNext();
				}
				//if(!in.retrieve().second.empty())
				l.insert(in.retrieve().second.retrieve());
					
				
			}
			else System.out.println("No elements in range"); // to be deleted 
		
	
			pair = new Pair<List<K>, Integer>(l,count);
		return pair;
	}

}

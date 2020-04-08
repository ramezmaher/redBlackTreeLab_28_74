package eg.edu.alexu.csd.filestructure.redblacktree;

public class MyRBT<T extends Comparable<T>,V> implements IRedBlackTree<T, V> {
	private INode<T,V> root;
	private int size;
	
	public MyRBT(){
		size = 0 ;
	}
	
	public INode<T, V> getRoot() {
		if (size>0)
		return root;
		else throw new IllegalAccessError("Tree is empty,no root to fetch");
	}

	
	public boolean isEmpty() {
		if (size == 0)
		    return true;
		else 
			return false;
	}
	@Override
	public void clear() {
		if (root==null)
			return;
		root.setLeftChild(null);
		root.setRightChild(null);
		root=null;
		return;
		
	}

	@Override
	public V search(T key) {
		INode<T,V> node = Find(key,root);
		if (node == null)
		return null;
		else return node.getValue();
	}

	@Override
	public boolean contains(T key) {
		INode<T,V> node= Find(key,root);
		if(node==null)
			return false;
		else return true;
		
	}

	@Override
	public void insert(T key, V value) {
		INode<T,V> node=new RBTNode<T,V>(key,value); 
		if (size==0) {
			node.setColor(INode.BLACK);
			root=node;
			size++;
			return;
		}
		else {
			addTo(node,root);
		}
	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub
		return false;
	}
	private void addTo(INode<T,V> nodeToAdd,INode<T,V> parentNode) {
		int check = nodeToAdd.getKey().compareTo(parentNode.getKey());
		if (check > 0) {
			if (parentNode.getLeftChild()==null) {
				parentNode.setLeftChild(nodeToAdd);
				nodeToAdd.setParent(parentNode);
			}
			else addTo(nodeToAdd,parentNode.getLeftChild());
		}
		else if(check < 0) {
			if (parentNode.getRightChild()==null) {
				parentNode.setRightChild(nodeToAdd);
				nodeToAdd.setParent(parentNode);
			}
			else addTo(nodeToAdd,parentNode.getRightChild());
		}
		else {
			parentNode.setValue(nodeToAdd.getValue());
		}
	}
	private INode<T,V> Find(T key,INode<T,V> node) {
		int check = node.getKey().compareTo(key);
		if (check==0)
			return node;
		else if (check < 0) {
			if (node.getRightChild()==null)
				return null;
			else return Find(key,node.getRightChild());
		}
		else {
			if (node.getLeftChild()==null)
				return null;
			else return Find(key,node.getLeftChild());
		}
	}
	
	private void fixTree() {
		
	}
	private void checkViolation() {}
	
	private void LeftRotation(INode<T,V> node) {
		if (node==null || node.getLeftChild()==null)
			return;
		INode<T,V> dummy1 = node.getLeftChild();
		INode<T,V> dummy2 = dummy1.getRightChild();
		node.setLeftChild(dummy2);
		dummy1.setRightChild(node);
		if (node.getParent()!=null)
			dummy1.setParent(node.getParent());
		else {
			dummy1.setParent(null);
			root = dummy1;
		}
		dummy2.setParent(node);
		return;
	}
	private void RightRotation(INode<T,V> node) {
		if (node==null || node.getRightChild()==null)
			return;
		INode<T,V> dummy1 = node.getRightChild();
		INode<T,V> dummy2 = dummy1.getLeftChild();
		node.setRightChild(dummy2);
		dummy1.setLeftChild(node);
		if (node.getParent()!=null)
			dummy1.setParent(node.getParent());
		else {
			dummy1.setParent(null);
			root = dummy1;
		}
		dummy2.setParent(node);
		return;
	}
    
}

import java.util.ArrayList;
import java.util.Hashtable;

public class BTreeNode {
	BTreeNode root;
	public String data;
	public BTreeNode parent;
	public ArrayList<BTreeNode> children;
	public Hashtable<String, Integer> hash; // key = 8bit °ª, value = BTreeNode index ÀÇ¹Ì
	public ArrayList<Integer> outport;
	
	BTreeNode(String data){
		this.data = data;
		outport = new ArrayList<Integer>();
		children = new ArrayList<BTreeNode>();
	}

	public BTreeNode getRoot() {
		return root;
	}

	public void setRoot(BTreeNode root) {
		this.root = root;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BTreeNode getParent() {
		return parent;
	}

	public void setParent(BTreeNode parent) {
		this.parent = parent;
	}

	public ArrayList<BTreeNode> getChildren() {
		return children;
	}

	public void setChildren(BTreeNode bchildren) {
		this.children.add(bchildren);
	}

	public ArrayList<Integer> getOutport() {
		return outport;
	}

	public void setOutport(int boutport) {
		this.outport.add(boutport);
	}
	
	
}

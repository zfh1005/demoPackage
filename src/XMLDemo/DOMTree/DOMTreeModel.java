/**
 * 
 */
package XMLDemo.DOMTree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.xml.soap.Node;



import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


/**
 * @author zfh1005
 *
 */

/*
 *This tree model describe the tree structure of an XML document. 
 * */
public class DOMTreeModel implements TreeModel{

	/*
	 * Constructs a document tree model
	 * @param doc the document
	 * 
	 * */
	public DOMTreeModel(Document document){
		this.document = document;
	}
	/*
	 * @see javax.swing.tree.TreeModel#addTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		
	}
	
	/* get parent's child node
	 * @param parent the parent node
	 * @param index the child node index
	 * @see javax.swing.tree.TreeModel#getChild(java.lang.Object, int)
	 */
	@Override
	public Object getChild(Object parent, int index) {
		Node node = (Node)parent;
		NodeList list = node.getChildNodes();
		return list.item(index);
	}
	
	/* get node node count
	 * @param parent the node 
	 * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)
	 */
	@Override
	public int getChildCount(Object parent) {
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		return list.getLength();
	}
	
	/* get child node's index
	 * @param parent the parent node
	 * @param child the find child node
	 * @see javax.swing.tree.TreeModel#getIndexOfChild(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		Node node = (Node) parent;
		NodeList list = node.getChildNodes();
		for(int i = 0; i < list.getLength(); i++){
			if(getChild(node, i) == child){
				return i;
			}
		}
		return -1;
	}
	
	/* 
	 * get node's root
	 * @see javax.swing.tree.TreeModel#getRoot()
	 */
	@Override
	public Object getRoot() {
		document.getDocumentElement();
		return null;
	}
	
	/* 
	 * Returns true if node is a leaf.
	 * @see javax.swing.tree.TreeModel#isLeaf(java.lang.Object)
	 */
	@Override
	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}
	
	/*
	 * @see javax.swing.tree.TreeModel#removeTreeModelListener(javax.swing.event.TreeModelListener)
	 */
	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		
	}
	
	/* 
	 * @see javax.swing.tree.TreeModel#valueForPathChanged(javax.swing.tree.TreePath, java.lang.Object)
	 */
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		
	}
	
	private Document document;
}

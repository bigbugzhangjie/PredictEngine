package jack.structure.tree;

import java.util.List;



/**
 * 表示在Tree结构中的一个树结点，有父子的链接关系
 * @author bigdata
 *
 */
public class TreeNode {
	TreeNode parent;
	List<TreeNode> children;
	
	/**
	 * 是否是叶结点
	 * @return
	 */
	public boolean isLeaf(){
		return false;
	}
	/**
	 * 添加直接子结点
	 * @param node
	 */
	public void addChild(TreeNode node){
		
	}
	/**
	 * 添加直接子结点
	 * 
	 * @param nodes
	 */
	public void addChildren(List<TreeNode> nodes){
		
	}
	
	/**
	 * 返回直接的子结点
	 * @return
	 */
	public List<TreeNode> getChildren(){
		return null;
	}
	
	public boolean containsChild(TreeNode node){
		return false;
	}
	public void deleteChild(TreeNode node){
		
	}
	public int getLeafSize(){
		return 0;
	}
	public int getNodeSize(){
		return 0;
	}
	public void traverse(){
		
	}
	public void buildDOM(){
		
	}
	public String toString(){
		return null;
	}
}

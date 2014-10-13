package jack.structure.tree;

import java.util.List;

/**
 * 表示一种树状的链接关系
 * @author bigdata
 *
 */
public class Tree {
	Tree root;
	List<TreeNode> tnlist;
	
	boolean isValid;
	
	/**
	 * 从root到node的路径
	 * @return
	 */
	public List<TreeNode> getPath(TreeNode node){
		return null;
	}
	/**
	 * 返回node的所有祖先结点
	 * @return
	 */
	public List<TreeNode> getAncestor(TreeNode node){
		return null;
	}
	/**
	 * 返回node的所有子子孙孙结点
	 * @return
	 */
	public List<TreeNode> getJuniors(TreeNode node){
		return null;
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public List<TreeNode> getTreeNodeByName(String name){
		return null;
	} 
	private void generateTree(){
		
	}
	private TreeNode findRoot(){
		return null;
	}
	/**
	 * 删除结点，将其子孙给父结点
	 */
	public void deleteNode(TreeNode node){
		
	}
	
	public void deleteSubtree(TreeNode node){
		
	}
//	public getDOM(){}
	
}

//iterative implementation of AVL tree
import java.lang.Math; 
public class AVL{
    TreeNode root = null; 
    int counter; 

    public AVL(){
        counter = 0; 
    }

    class TreeNode{
        int value, height; 
        TreeNode right, left, parent;

        public TreeNode(int v){
            value = v; 
            left = right = parent = null; 
            height = 0; 
        }
        
        public TreeNode findMax(){ 
            TreeNode current = this; 
            while(current.right != null)
                current = current.right;
            return current; 
        }
        
        public TreeNode findMin(){
            TreeNode current = this; 
            while  (current.left != null)
                current = current.left;
            return current; 
        }

        //these should only be called by insert and delete
        private int get_height(){
            int l_height, r_height; 
            if(this.left == null)
                l_height = -1; 
            else
                l_height = left.height; 
            if(this.right == null)
                r_height = -1; 
            else
                r_height = right.height; 
            return Math.max(l_height, r_height) + 1;
        }
        
        private int get_balance(){
            int l_height, r_height;
            if(this.left == null)
                l_height = -1; 
            else
                l_height = left.height; 
            if(this.right == null)
                r_height = -1; 
            else
                r_height = right.height; 
            return l_height - r_height; 
        }
        
        private void balance(){
            int bf = this.get_balance(); 
            //nothing should happen if the subtree is balanced
            if(bf > 1){
                if(this.left.get_balance() < 0)
                    this.left.rotate_left(); 
                this.rotate_right(); 
        
            }
            else if(bf < -1){
                if(this.right.get_balance() > 0)
                    this.right.rotate_right();
                rotate_left();
            }
        }
        
        private void rotate_right(){
            TreeNode pivot = this.left; 
            TreeNode parent = this.parent; 
        
            this.left = pivot.right; 
            pivot.right = this; 
            this.parent = pivot; 
            pivot.parent = parent; 
            if(parent != null){
                if(parent.value < pivot.value)
                    parent.right = pivot; 
                else 
                    parent.left = pivot; 
            }
            if(parent == null)
                root = pivot;
            this.height = this.get_height();
            pivot.height = pivot.get_height();
        }
        
        private void rotate_left(){
            TreeNode pivot = this.right; 
            TreeNode parent = this.parent; 
            this.right = pivot.left; 
            pivot.left = this; 
            this.parent = pivot; 
            pivot.parent = parent; 
            if(parent != null){
                if(parent.value < pivot.value)
                    parent.right = pivot; 
                else 
                    parent.left = pivot; 
            }
            if(parent == null){
                root = pivot;
            }
            this.height = this.get_height();
            pivot.height = pivot.get_height();
        }
    }

    public void insert(int v){
        //normal BST insert 
        TreeNode current = root; 
        if(current == null){ //in the case of the empty tree 
            current = new TreeNode(v);
            current.parent = null; 
            root = current; 
            return; 
        } 

        TreeNode trace = null; 
        while(current != null){
            trace = current; 
            if( v < current.value)
                current = current.left; 
            else   
                current = current.right; 
            counter ++; 
        }
        if (v < trace.value){
            trace.left = new TreeNode(v); 
            trace.left.parent = trace; 
            current = trace.left; 
        }
        else{
            trace.right = new TreeNode(v); 
            trace.right.parent = trace; 
            current = trace.right; 
        }
        //update the heights of the parents along the tree
        while(current != null){
            current.height = current.get_height(); 
            current.balance(); 
            current = current.parent; 
        }
    
    }
    
    void delete(int v){
        TreeNode current = find_node(v); 
        TreeNode successor = current; //will only be reassigned in the case of 2 children
        if(current.left != null && current.right != null){ //in the case of 2 children 
            successor = findNext(current);
            current.value = successor.value;
        }
        if(successor.left == null && successor.right == null){ //in the case of no children
            current = successor.parent;
            if(successor.value == successor.parent.left.value)
                successor.parent.left = null; 
            else   
                successor.parent.right = null; 
        }
        else if(successor.left != null && successor.right == null){ //in the case of 1 child on the left
            current = successor;
            successor.value = successor.left.value; 
            successor.left= null; 
        }
        else if(successor.left == null && successor.right != null){ //in the case of 1 child on the right
            current=successor; 
            successor.value = successor.right.value; 
            successor.right= null; 
        }
        while(current != null){
            current.height = current.get_height(); 
            current = current.parent; 
            current.balance(); 
        }
    }
    
    public TreeNode findNext(int v){
        return findNext(find_node(v));
    }
    
    public TreeNode findNext(TreeNode node){
        if(node.right != null)
            return node.right.findMin();
        TreeNode current = root; 
        TreeNode trace = null; 
        while(current != null){
            if( node.value < current.value){
                trace = current; 
                current = current.left;
            }
            else
                current = current.right;
        }        
        return trace;
    }
    
    public TreeNode findPrev(int v){
        return findPrev(find_node(v));
    }
    
    public TreeNode findPrev(TreeNode node){
        if(node.left != null)
            return node.left.findMin();
        TreeNode current = root; 
        TreeNode trace = null; 
        while(current != null){
            if( node.value > current.value){
                trace = current; 
                current = current.right;
            }
            else
                current = current.left;
        }        
        return trace;
    }
    
    //return the node with value v
    private TreeNode find_node(int v){ 
        TreeNode current = root;
        while(current.value != v){
            if (v > current.value)
                current = current.right; 
            else 
                current = current.left; 
        }
        return current; 
    }
}
public class iBST{
    
    TreeNode root;
    int counter;
    public iBST(){
        counter = 0; 
    }

    class TreeNode{
        int value;
        TreeNode right, left, parent;

        public TreeNode(int v){
            value = v; 
            left = right = parent = null; 
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
    }

     //insert a number v into the graph 
     public void insert(int v){
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
            counter++; 
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
    }

    void delete(int v){
        TreeNode current = find_node(v); 
        TreeNode successor = current; //will only be reassigned in the case of 2 children
        if(current.left != null && current.right != null){ //in the case of 2 children 
            successor = findNext(current);
            current.value = successor.value;
        }
        if(successor.left == null && successor.right == null){ //in the case of no children
            if(successor.value == successor.parent.left.value)
                successor.parent.left = null; 
            else   
                successor.parent.right = null; 
        return;
        }
        if(successor.left != null && successor.right == null){ //in the case of 1 child on the left
            successor.value = successor.left.value; 
            successor.left= null; 
            return;
        }
        if(successor.left == null && successor.right != null){ //in the case of 1 child on the right
            successor.value = successor.right.value; 
            successor.right= null; 
            return;
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


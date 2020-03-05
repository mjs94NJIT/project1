public class rBST{
    TreeNode root = null;
    rBST(){} 
    
    class TreeNode{
        int value; 
        TreeNode left, right, parent; 
        
        public TreeNode(int v){
            value = v; 
            left = right = parent = null; 
        }
        void insertRec(int n){
            if( this.left == null && this.value > n){
                if(this.left == null){
                    this.left = new TreeNode(n);
                    this.left.parent = this; 
                }
                else
                     this.left.insertRec(n);
            }
            if(this.value < n){
                if(this.right == null){
                    this.right = new TreeNode(n);
                    this.right.parent = this; 
                }
                else
                    this.right.insertRec(n);
            }
        }

        void deleteRec(int n){ //
            deleteRec(findNode(n));
        }
    
        void deleteRec(TreeNode node){
            if(node.left == null && node.right == null){
                if(node.left==null && node.right == null){
                    if(node.value == node.parent.left.value)
                        node.parent.left = null;
                    else
                        node.parent.right = null; 
                    return; 
                }
    
                if(node.left != null && node.right == null){
                    node.value = node.left.value; 
                    node.deleteRec(node.left);
                    return;
                }
                if(node.left == null && node.right != null){
                    node.value = node.right.value; 
                    node.deleteRec(node.right);
                    return;
                }
                if(node.left != null && node.right != null){
                //if the node has 2 children 
                TreeNode successor = node.findNextRec(node.value);
                node.value = successor.value; 
                deleteRec(successor);
                }
            }
        }
    
        TreeNode findNextRec(int n){
            if(this.value == n)
                return this.right.findMinRec(); 
            if(this.value > n)
                return this.left.findNextRec(n); 
            if(this.value < n)
                return this.right.findNextRec(n);
            return null; 
        }
    
        TreeNode findPrevRec(int n){
            if(this.value == n)
                return this.left.findMaxRec(); 
            if(this.value > n)
                return this.left.findNextRec(n); 
            if(this.value < n)
                return this.right.findNextRec(n);
            return null; 
        }
    
        TreeNode findMinRec(){
            if(this.left != null)
                return this.left.findMinRec(); 
            return this; 
        }
        TreeNode findMaxRec(){
            if(this.right != null)
                return this.right.findMaxRec();
            return this; 
        }
        
        TreeNode findNode(int n){ //helper function to find a value in the TreeNode
           
            if(this.value == n)
                return this; 
            if(this.left == null && this.right == null)
                return null; 
            
            TreeNode lsearch, rsearch; 
            lsearch = rsearch = null; 
    
            if(this.left != null)
                if((lsearch = this.left.findNode(n)) != null)
                    return lsearch; 
            if(this.right != null)
                if((rsearch = this.left.findNode(n)) != null)
                    return rsearch;  
            
            return null; 
        }
    }

    public void insert(int v){
        if(root == null)
            root = new TreeNode(v);
        else
            root.insertRec(v);
    }

    public void delete(int v){
        root.deleteRec(v);
    }
}
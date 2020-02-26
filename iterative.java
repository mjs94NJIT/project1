class Tree{
   
    public int value; 
    public Tree left, right, parent; 
    
    public Tree(int v){
        value= v; 
        left = right = parent = null; 
        //keeping track of the parent makes iterative functions easier by trading a little space for the time that would spend looking up the parent every time
    }

    void insertIter(int n){
        Tree current = this; 
        Tree trace = null; 
        while( current != null ){
            trace = current; 
            if( n < current.value )
                current = current.left;
            else
                current = current.right; 
        }
        if(trace == null) //in case of an empty tree
            trace = new Tree(n); 
        else if ( n < trace.value ){
            trace.left = new Tree(n);
            trace.left.parent = trace; 
        } 
        else{ 
            trace.right = new Tree(n); 
            trace.right.parent = trace; 
        }
    }
    void deleteIter(int n){
        Tree current = findNode(n); 

        //in the case that the node to be deleted is a leaf 
        if(current.left==null && current.right == null){
            if(current.parent == null){ //if it's the root and te only node in the tree
                current = null; 
                return;
            }
            if(current.value == current.parent.left.value)
                current.parent.left = null; 
            else   
                current.parent.right = null; 
            return;
        }
        //if the node has only one child 
        if(current.left != null && current.right == null){
            current.value = current.left.value; 
            current.left= null; 
            return;
        }
        if(current.left == null && current.right != null){
            current.value = current.right.value; 
            current.right= null; 
            return;
        }
       //if the node has two children
       if(current.left != null && current.right != null){
            Tree successor = this.findNextIter(current);
            current.value = successor.value; 
            if(successor.left==null && successor.right == null){
                if(successor.value == successor.parent.left.value)
                    successor.parent.left = null; 
                else   
                    successor.parent.right = null; 
                return;
            }
            //if the node has only one child 
            if(successor.left != null && successor.right == null){
                successor.value = successor.left.value; 
                successor.left= null; 
                return;
            }
            if(successor.left == null && successor.right != null){
                successor.value = successor.right.value; 
                successor.right= null; 
                return;
            }
        }
    }

    //these functions return a tree node 
    Tree findNextIter(int n){
        Tree current = findNode(n); 
        if(current.right != null)
            return current.findMinIter();
        current = this; 
        Tree trace =null; 
        while(current != null){
            if( n < current.value){
                trace = current; 
                current = current.left;
            }
            else
                current = current.right;
        }        
        return trace;
    }

    //overloaded function to help delete() without having to lookup a node twice
    Tree findNextIter(Tree node){
        if(node.right != null)
            return node.findMinIter();
        Tree current = this; 
        Tree trace =null; 
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

    Tree findPrevIter(int n){
        Tree current = findNode(n); 
        if(current.left != null)
            return current.findMaxIter();
        Tree trace = null; 
        current = this; 
        while(current != null){
            if(n>current.value){
                trace= current; 
                current = current.right; 
            }
            else
                current = current.left; 
        }
        return trace; 
    }

    Tree findMinIter(){
        Tree current = this; 
        while  (current.left != null)
            current = current.left;
        return current; 
    }

    Tree findMaxIter(){
        Tree current = this; 
        while  (current.right != null)
            current = current.right;
        return current; 
    }

    Tree findNode(int n){ //helper function to find a value in the tree
        Tree current = this; //not technically part of assignment
        while(current.value != n){
            if (n > current.value)
                current = current.right; 
            else 
                current = current.left; 
        }
        return current; 
    }
}


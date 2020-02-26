class SortIt{

    class Stack{
        Tree value; 
        Stack next; 
        Stack root; 

        public Stack(){
            next = root = null; 
        }
        public Stack(int n){
            value = n;
            next = root = null; 
        }
        public void push(Tree n){
            Stack temp = new Stack(n); 
            temp.next = root; 
            root = temp; 
        } 

        public Tree pop(){
            Stack temp = root; 
            root = root.next; 
            return temp.value; 
        }
    }
    void treeSort(int[] list){
        Stack s = new Stack(); 
        Tree bst = new Tree(); 
        for(int i=0; i< list.length; i++)
            bst.insert(list[i]);
        Tree current = bst.root; 
        while( current != null || s != null){
            while(current != null){
                s.push(current); 
                current = current.left; 
            }
            current = s.root; 
            list[0] = s.pop().value; 
            current = current.right; 
        }
    }    
}
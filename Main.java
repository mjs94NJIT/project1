import java.lang.Math; 
public class Main{
    
    public static int[] getRandomArray(int n){
        int random_array[] = getSortedArray(n);
        for(int i = 0; i < n; i++){ //shuffle the array n times
            int rand = (int) (Math.random() * n); 
            int temp = random_array[rand]; 
            random_array[rand] = random_array[i];
            random_array[i] = temp; 
        }
        return random_array; 
    }

    public static int[] getSortedArray(int n){
        int sorted_array[] = new int[n]; 
        for(int i = 0; i < n; i++){
            sorted_array[i] = i+1; 
        }
        return sorted_array; 
    }

    public static void main(String args[]){
        int[] nums = getRandomArray(10000);
        iBST BSTIter = new iBST();
        AVL AVLTree = new AVL(); 
        //rBST BSTRec = new rBST(); 
        for(int i = 0; i< nums.length; i++){
            System.out.println("Inserting "+ nums[i]);
            //BSTRec.insert(nums[i]);
            BSTIter.insert(nums[i]);
            AVLTree.insert(nums[i]);
        }
        System.out.println("BST Steps: " + BSTIter.counter);
        System.out.println("AVL Steps: " + AVLTree.counter);
    }

}
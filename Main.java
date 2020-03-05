import java.lang.Math; 
import java.util.*;

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
        int[] nums = getSortedArray(10);
        iBST BSTIter = new iBST();
        AVL AVLTree = new AVL(); 
        
        long start = System.nanoTime(); 
        for(int i = 0; i< nums.length; i++){
            BSTIter.insert(nums[i]);
        }
       for(int i = 0; i< nums.length; i++){
            BSTIter.delete(nums[i]);
        }
        long end = System.nanoTime(); 
        System.out.println("Time to insert and delete 10000 ints in a BST "+(end-start)+"ns");
        
        
        start = System.nanoTime(); 
        for(int i = 0; i< nums.length; i++){
           AVLTree.insert(nums[i]);
        }
        for(int i = 0; i< nums.length; i++){
            AVLTree.delete(nums[i]);
        }
        end = System.nanoTime(); 
        System.out.println("Time to insert and delete 10000 ints in a AVL "+(end-start)+"ns");
        
    }

}
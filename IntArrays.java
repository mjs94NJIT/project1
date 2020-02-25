import java.lang.Math; 
public class IntArrays{

    public static int[] getRandomArray(int n){
        int random_array[] = getSortedArray(n);
        for(int i = 0; i < n; i++){ //shuffle the array n times
            int rand1, rand2; 
            rand1 = (int) Math.random() * n; 
            rand2 = (int) Math.random() * n;
            int temp = random_array[rand1]; 
            random_array[rand1] = random_array[rand2];
            random_array[rand2] = temp; 
        }
        return random_array; 
    }

    public static int[] getSortedArray(int n){
        int sorted_array[] = new int[n]; 
        for(int i = 0; i < n; i++){
            sorted_array[i] = i; 
        }
        return sorted_array; 
    }
}
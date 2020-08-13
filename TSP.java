import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TSP
{
    public static char[] orderOfCities = new char[100];
    public static int[][] distance = new int[100][100];
    public static int minPath = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        System.out.println("Enter the distances of roads between cities");

        for (int i = 0; i < n; i++) {
            System.out.println("Next city");
            for (int j = 0; j < n; j++) {
                distance[i][j] = Integer.parseInt(reader.readLine());
            }
        }
        find_shortest_path(distance, n);
        System.out.println(minPath + " is min path");
    }

    public static void find_shortest_path(int[][] distance, int n){
        for (int i = 0; i < n; i++) {
            orderOfCities[i] = (char) i;
        }
        permute(String.valueOf(orderOfCities),0,n);
    }

    public static void permute(String str, int l, int r)
    {
        int currentPathDistance = 0;
        if (l == r){
            for (int i = 1; i < r; i++) {
                currentPathDistance += distance[i-1][i];
                if(i == r -1){
                    currentPathDistance += distance[i][0];
                }
            }
            if (currentPathDistance < minPath){
                minPath = currentPathDistance;
                currentPathDistance = 0;
            }
            else currentPathDistance = 0;
        }

        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }


    public static String swap(String a, int i, int j)
    {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

} 
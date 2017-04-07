
import java.util.*;

class Point{
	int x,y;
}

public class GiftWrapping{
	
	private boolean CWW(Point a,Point b,Point c){
		int val = (b.y-a.y)*(c.x-b.x) - (b.x-a.x)*(c.y-b.y);

		if(val >= 0)
			return false;
		return true;
	}

	public void convexHull(Point[] points){
		int n = points.length;

		if(n < 3)
			return;

		int []next = new int[n];

		for(int i = 0; i<n; i++) next[i] = -1;

		int leftMost = 0;

		for(int i = 1; i < n; i++){
			if(points[i].x < points[leftMost].x)
				leftMost = i;			
		}

		int p = leftMost,q;

		do{
			q = (p+1)%n;

			for(int i=0;i<n;i++){
				if(CWW(points[p],points[i],points[q]))
					q = i;
			}

			next[p] = q;
			p = q;
		} while(p != leftMost);

		display(points,next);
	}

	public void display(Point[] points, int[] next)
    {
        System.out.println("\nConvex Hull points : ");
        for (int i = 0; i < next.length; i++)
            if (next[i] != -1)
               System.out.println("("+ points[i].x +", "+ points[i].y +")");
    }

	public static void main (String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("GiftWrapping Algorithm Test\n");
        /** Make an object of Jarvis class **/
        GiftWrapping j = new GiftWrapping();        
 
        System.out.println("Enter number of points n :");
        int n = scan.nextInt();
        Point[] points = new Point[n];
        System.out.println("Enter "+ n +" x, y cordinates");
        for (int i = 0; i < n; i++)
        {
            points[i] = new Point();
            points[i].x = scan.nextInt();
            points[i].y = scan.nextInt();
        }        
        j.convexHull(points);        
    }
}
import java.io.*; 
public class ZigZag 
{ 
	private static boolean isOpposite(int a,int b)
	{ 
		return (a<0 && b>0)||(a>0 && b<0);
	} 
	public static void main(String [] args)throws IOException
	{ 
		BufferedReader buff=new BufferedReader(new InputStreamReader(System.in));
		String [] in=buff.readLine().split(" ");
		int n=in.length;
		int []a=new int[n];
		for(int i=0;i<n;i++)a[i]=Integer.parseInt(in[i]);
		int dp[][]=new int[n][2];
		for(int i=0;i<n;i++)dp[i][0]=1;	
		for(int i=1;i<n;i++)
		{ 
			for(int j=0;j<i;j++)	
			{ 
				int diff=a[i]-a[j];
				if(dp[j][1]==0)
				{	 
					dp[i][0]+=1;dp[i][1]=diff;
				} 
				else 
				{ 
					if(isOpposite(dp[j][1],diff) && dp[j][0]+1>dp[i][0])	
					{ 
						dp[i][0]=dp[j][0]+1;
						dp[i][1]=diff;
					} 
				} 
			} 
		} 
		System.out.println(dp[n-1][0]);
	} 
} 
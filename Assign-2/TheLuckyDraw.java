import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
class TheLuckyDraw{
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder("");
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		while(t-->0)
		{
			int n = Integer.parseInt(br.readLine());
			int[] sequence = new int[2*n];
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++)
				sequence[i] = sequence[i+n] = Integer.parseInt(st.nextToken());
			int max = -1;
			for(int i=0;i<n;i++)
			{
				int temp = solve(sequence,i,n+i);
				if(temp>max)
					max = temp;
			}
			sb.append(max).append("\n");
		}
		pw.print(sb);
		pw.flush();
		pw.close();
	}
	
	public static int solve(int[] sequence,int start,int end)
	{
		int max = 1;
		int[] listEnd = new int[end-start+1];
		listEnd[0] = sequence[start];
		for(int i=start+1;i<end;i++)
		{
			if(sequence[i]<listEnd[0])
			{
				listEnd[0] = sequence[i];
				//System.out.println("first "+sequence[i]);
			}
			else if(sequence[i]>listEnd[max-1])
			{
				listEnd[max++] = sequence[i];
				//System.out.println("last "+sequence[i]);
			}
			else
			{
				int index = binarySearch(listEnd,0,max,sequence[i]);
				listEnd[index] = sequence[i];
				//System.out.println(index+" "+sequence[i]);
			}
		}
		//System.out.println(max);
		return max;
	}
	
	public static int binarySearch(int[] listEnd,int l,int r,int lookingForThis)
	{
		while(r-l>1)
		{
			int m = l+(r-l)/2;
			if(listEnd[m]>=lookingForThis)
				r = m;
			else
				l = m;
		}
		return r;
	}
} 
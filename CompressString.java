package com.gitpackage;


public class CompressString {

	public  String encrypt(String input)
	{
		
		
			String out="";
			char[] c=input.toCharArray();
			int count;
			
			for(int i=0;i<c.length;i++)
			{
				count=1;
				for(int j=i+1;j<c.length;j++)
				{
					if(c[i]==c[j])
					{
						count++;
						i=j;
					}
					else
						break;
				}
				if(count==1){
					out+=""+c[i];
				}else
					if(isPrime(count))
						out+=""+count+c[i];
					else
						out+=""+c[i]+count;
							
				
				
			}
			
			if(out.length()>=input.length())
			{
				return input;
			}
			else
				return out;
		}
		
	

	//find count is a prime or not
	private  boolean isPrime(int num) {
		for(int i=3;i<num;i++)
		{
			if(num%i==0)
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		
		CompressString cps=new CompressString();
		String original="AAAAAABBCDDDggggg";
		System.out.println("Original String:  "+original);
		String en=cps.encrypt(original);
		System.out.println("Encrypted String : "+en);
		
		

	}

}

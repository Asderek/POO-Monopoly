package monopoly;

import java.awt.Point;

import monopoly.posicoes.Casa;

public class MovementChecker {
	
	private Casa[] array;
	public MovementChecker(Casa[] a){
	 this.array=a;	
	}
	
	public int CheckMovement (Peca p, int casa)
	{
		
		if ((casa > 0 && casa < 8) )
		{
			
			if (p.getPosition().y > array[casa].LowerBound.y || p.getPosition().y < array[casa].UpperBound.y)
				return 9;
			
			else if (p.getPosition().x < array[casa].UpperBound.x)
			{
				if (p.getPosition().x < array[casa-1].UpperBound.x)
				{
					return 9;
				}
				else
						return -1;
			}
			else if (p.getPosition().x > array[casa].LowerBound.x)
			{
				if (p.getPosition().x > array[casa+1].LowerBound.x)
					return 9;
				else
					return 1;
							
			}
			else
				return 0;
		}
		else if ((casa >16 && casa <24))
		{

			
			if (p.getPosition().y > array[casa].LowerBound.y || p.getPosition().y < array[casa].UpperBound.y)
				return 9;
			
			else if (p.getPosition().x < array[casa].UpperBound.x)
			{
				if (p.getPosition().x < array[casa+1].UpperBound.x)
					return 9;
				else
					return 1;
			}
			else if (p.getPosition().x > array[casa].LowerBound.x)
			{
				if (p.getPosition().x > array[casa-1].LowerBound.x)
					return 9;
				else
					return -1;
			}
			else
				return 0;
		}
		else if ((casa > 8 && casa < 16))
		{

			
			
			
			if (p.getPosition().x > array[casa].LowerBound.x || p.getPosition().x < array[casa].UpperBound.x)
				return 9;

			else if (p.getPosition().y < array[casa].UpperBound.y)
			{
				if (p.getPosition().y < array[casa-1].UpperBound.y)
				{
					return 9;
				}
				else
					return -1;
			}
			else if (p.getPosition().y > array[casa].LowerBound.y)
			{
				if (p.getPosition().y > array[casa+1].LowerBound.y)
					return 9;
				else 
					return 1;
			}
			else return 0;
		} 
		else if (casa > 24 && casa < 32)
		{
			if (p.getPosition().x > array[casa].LowerBound.x || p.getPosition().x < array[casa].UpperBound.x)
				return 9;
			
			else if(p.getPosition().y < array[casa].UpperBound.y)
			{
				if(casa>=31)
					casa=-1;
				if (p.getPosition().y < array[casa+1].UpperBound.y)
					return 9;
				else
					return 1;
			}
			else if(p.getPosition().y > array[casa].LowerBound.y)
			{
				if (p.getPosition().y > array[casa-1].LowerBound.y)
					return 9;
				else
					return -1;
			}
			else return 0;
				
		}
		
		else if (casa==0)
		{
			if (p.getPosition().x > array[casa].LowerBound.x )
			{
				if (p.getPosition().y>=array[casa+1].UpperBound.y && p.getPosition().y<=array[casa+1].LowerBound.y)
				{
					if(p.getPosition().x > array[casa+1].LowerBound.x)
					{
						return 9;
					}
					else 
						return 1;
				}
				else
					return 9;
			}
			else if (p.getPosition().y > array[casa].LowerBound.y )
			{
				if (p.getPosition().x >= array[31].UpperBound.x && p.getPosition().x <= array[31].LowerBound.x)
				{
					if (p.getPosition().y > array[31].LowerBound.y)
					{
						return 9;
					}
					else 
						return -1;
				}
				else
					return 9;
			}
			else return 0;
		}
		else if (casa==8)
		{
			if (p.getPosition().x < array[casa].UpperBound.x)
			{
				if (p.getPosition().y>=array[casa-1].UpperBound.y && p.getPosition().y<=array[casa-1].LowerBound.y)
				{
					if(p.getPosition().x < array[casa-1].UpperBound.x)
					{
						return 9;
					}
					else 
						return -1;
				}
				else
					return 9;
			}
			else if (p.getPosition().y > array[casa].LowerBound.y)
			{
				if (p.getPosition().x >= array[casa+1].UpperBound.x && p.getPosition().x <= array[casa+1].LowerBound.x)
				{
					if (p.getPosition().y > array[casa+1].LowerBound.y)
					{
						return 9;
					}
					else 
						return 1;
				}
				else
					return 9;
			}			
			else return 0;
		}
		else if (casa==16)
		{
			if (p.getPosition().x < array[casa].UpperBound.x)
			{
				if (p.getPosition().y>=array[casa+1].UpperBound.y && p.getPosition().y<=array[casa+1].LowerBound.y)
				{
					if(p.getPosition().x < array[casa+1].UpperBound.x)
					{
						return 9;
					}
					else 
						return 1;
				}
				else
					return 9;
			}
			else if (p.getPosition().y < array[casa].UpperBound.y)
			{
				if (p.getPosition().x >= array[casa-1].UpperBound.x && p.getPosition().x <= array[casa-1].LowerBound.x)
				{
					if (p.getPosition().y < array[casa-1].UpperBound.y)
					{
						return 9;
					}
					else
						return -1;
				}
				else
					return 9;
			}
			else return 0;
			
		}
		else if (casa==24)
		{
			if (p.getPosition().x > array[casa].LowerBound.x )
			{
				if (p.getPosition().y>=array[casa-1].UpperBound.y && p.getPosition().y<=array[casa-1].LowerBound.y)
				{
					if(p.getPosition().x > array[casa-1].LowerBound.x)
					{
						return 9;
					}
					else 
						return -1;
				}
				else
					return 9;
			}
			else if (p.getPosition().y < array[casa].UpperBound.y)
			{
				if (p.getPosition().x >= array[casa+1].UpperBound.x && p.getPosition().x <= array[casa+1].LowerBound.x)
				{
					if (p.getPosition().y < array[casa+1].UpperBound.y)
					{
						return 9;
					}
					else
						return 1;
				}
				else
					return 9;
			}
			else return 0;
		}
		return 0;
	}
	
	public Point returnUpper(int i)
	{
		return array[i].UpperBound;
	}
	public Point returnLower(int i)
	{
		return array[i].LowerBound;
	}
}
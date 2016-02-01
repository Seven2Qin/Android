package com.mendong.travel.ui;

import java.util.Random;

import android.graphics.Rect;

public class CircleRect {
	
    private int niDirection;
	
	private Rect rect;
	
	private int niSpeed=1;
	
	private int NI_WIDTH,NI_HEIGHT;
	
	private String niText;
	
	public CircleRect(int niLeft,int niTop,int niRight,int niBottom)
	{
		NI_WIDTH=niRight-niLeft;
		NI_HEIGHT=niBottom-niTop;
		rect = new Rect(niLeft,niTop,niRight,niBottom);
	}
	
	public Rect getInstance()
	{
		return rect;
	}
	
	
	//标签
	public void setText(String niText)
	{
		this.niText = niText;
	}
	
	public String getText()
	{
		return niText;
	}
	
	public void setSpeed(int niSpeed)
	{
		this.niSpeed=niSpeed;
	}
	
	public int getSpeed()
	{
		return niSpeed;
	}
	
	
	public void setDirection(int niDirection)
	{
		this.niDirection=niDirection;
	}
	
	public int getDirection()
	{
		return niDirection;
	}
	
	public int getLeft(){return rect.left;}
	public int getTop(){return rect.top;}
	public int getRight(){return rect.right;}
	public int getBottom(){return rect.bottom;}
	
	public void moveUp()
	{
		rect.top -=niSpeed;
		rect.bottom=rect.top+NI_HEIGHT;
		if(rect.top<=0)
		{
			resetDirection();
		}
	}
	public void moveDown()
	{
		rect.top +=niSpeed;
		rect.bottom=rect.top+NI_HEIGHT;
		if(rect.bottom>=1230)
		{
			resetDirection();
		}
	}
	public void moveLeft()
	{
		rect.left -=niSpeed;
		rect.right=rect.left+NI_WIDTH;
		if(rect.left<=0)
		{
			resetDirection();
		}
	}
	public void moveRight()
	{
		rect.left +=niSpeed;
		rect.right=rect.left+NI_WIDTH;
		if(rect.right>=720)
		{
			resetDirection();
		}
	}
	
	public void resetDirection()
	{
		int niRandomValue = getRandomInt(0,3);
		switch(niDirection)
		{
		case CircleInfo.NI_DIRECTION_UP :
			switch(niRandomValue)
			{
			case 0 :	niDirection = CircleInfo.NI_DIRECTION_LD;		break;
			case 1 :	niDirection = CircleInfo.NI_DIRECTION_RD;		break;
			default :	niDirection = CircleInfo.NI_DIRECTION_DOWN;	break;
			}
			break;
		case CircleInfo.NI_DIRECTION_DOWN :
			switch(niRandomValue)
			{
			case 0 :
				niDirection = CircleInfo.NI_DIRECTION_LU;
				break;
			case 1 :
				niDirection = CircleInfo.NI_DIRECTION_RU;
				break;
			default :
				niDirection = CircleInfo.NI_DIRECTION_UP;
				break;
			}
			break;
		case CircleInfo.NI_DIRECTION_LEFT :
			switch(niRandomValue)
			{
			case 0 :
				niDirection = CircleInfo.NI_DIRECTION_RU;
				break;
			case 1 :
				niDirection = CircleInfo.NI_DIRECTION_RD;
				break;
			default :
				niDirection = CircleInfo.NI_DIRECTION_RIGHT;
				break;
			}
			break;
		case CircleInfo.NI_DIRECTION_RIGHT :
			switch(niRandomValue)
			{
			case 0 :
				niDirection = CircleInfo.NI_DIRECTION_LU;
				break;
			case 1 :
				niDirection = CircleInfo.NI_DIRECTION_LD;
				break;
			default :
				niDirection = CircleInfo.NI_DIRECTION_LEFT;
				break;
			}
			break;
		case CircleInfo.NI_DIRECTION_LU :
			switch(niRandomValue)
			{
			case 0 :
				niDirection = CircleInfo.NI_DIRECTION_RIGHT;
				break;
			case 1 :
				niDirection = CircleInfo.NI_DIRECTION_DOWN;
				break;
			default :
				niDirection = CircleInfo.NI_DIRECTION_RD;
				break;
			}
			break;
		case CircleInfo.NI_DIRECTION_RU :
			switch(niRandomValue)
			{
			case 0 :
				niDirection = CircleInfo.NI_DIRECTION_LEFT;
				break;
			case 1 :
				niDirection = CircleInfo.NI_DIRECTION_DOWN;
				break;
			default :
				niDirection = CircleInfo.NI_DIRECTION_LD;
				break;
			}
			break;
		case CircleInfo.NI_DIRECTION_LD :
			switch(niRandomValue)
			{
			case 0 :
				niDirection = CircleInfo.NI_DIRECTION_RIGHT;
				break;
			case 1 :
				niDirection = CircleInfo.NI_DIRECTION_UP;
				break;
			default :
				niDirection = CircleInfo.NI_DIRECTION_RU;
				break;
			}
			break;
		case CircleInfo.NI_DIRECTION_RD :
			switch(niRandomValue)
			{
			case 0 :
				niDirection = CircleInfo.NI_DIRECTION_LEFT;
				break;
			case 1 :
				niDirection = CircleInfo.NI_DIRECTION_UP;
				break;
			default :
				niDirection = CircleInfo.NI_DIRECTION_LU;
				break;
			}
			break;
		}
	}
	
	
	public void resetIntersectDirection(int niDirection)
	{
		switch(niDirection)
		{
		case CircleInfo.NI_DIRECTION_UP :
		 	 this.niDirection = CircleInfo.NI_DIRECTION_DOWN;
			 break;
		case CircleInfo.NI_DIRECTION_DOWN :
			this.niDirection = CircleInfo.NI_DIRECTION_UP;
			 break;
		case CircleInfo.NI_DIRECTION_LEFT :
			this.niDirection = CircleInfo.NI_DIRECTION_RIGHT;
			 break;
		case CircleInfo.NI_DIRECTION_RIGHT :
			this.niDirection = CircleInfo.NI_DIRECTION_LEFT;
			 break;
		case CircleInfo.NI_DIRECTION_LU :
			this.niDirection = CircleInfo.NI_DIRECTION_RD;
			 break;
		case CircleInfo.NI_DIRECTION_RU :
			this.niDirection = CircleInfo.NI_DIRECTION_LD;
			 break;
		case CircleInfo.NI_DIRECTION_LD :
			this.niDirection = CircleInfo.NI_DIRECTION_RU;
			 break;
		case CircleInfo.NI_DIRECTION_RD :
			this.niDirection = CircleInfo.NI_DIRECTION_LU;
			 break;
		}
	}
	
	public void move()
	{

		switch(niDirection)
		{
		case CircleInfo.NI_DIRECTION_UP:
			 moveUp();
			 break;
		case CircleInfo.NI_DIRECTION_DOWN:
			moveDown();
			break;
		case CircleInfo.NI_DIRECTION_LEFT:
			moveLeft();
			break;
		case CircleInfo.NI_DIRECTION_RIGHT:
			moveRight();
			break;
		case CircleInfo.NI_DIRECTION_LU:
			moveLeft();
			moveUp();
			break;
		case CircleInfo.NI_DIRECTION_LD:
			moveLeft();
			moveDown();
			break;
		case CircleInfo.NI_DIRECTION_RU:
			moveRight();
			moveUp();
			break;
		case CircleInfo.NI_DIRECTION_RD:
			moveRight();
			moveDown();
			break;
		}
		
	}
	
	
	
	private int getRandomInt(int min, int max)
	{
		return Math.abs(new Random().nextInt()) % (max - min) + min;
	}
	

}

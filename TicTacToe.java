package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	//設位置為全域
	static ArrayList<Integer> playerpos = new ArrayList<Integer>();
	static ArrayList<Integer> cpupos = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//首先印出遊戲板(一個井字，3行3列)
		char [][] gameboard = {
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}};

		printgameboard(gameboard);
		
		while(true) {
			Scanner scan = new Scanner(System.in);//輸入要畫圈叉的位置
			System.out.println("enter the placement:(1-9)");
			int playerposition = scan.nextInt();
			
			while(playerpos.contains(playerposition) || cpupos.contains(playerposition)) {
				System.out.println("player's error.pls enter a correct position");
				playerposition = scan.nextInt();
			}
			
			placement(gameboard, playerposition, "player");
			
			String result = checkwin();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
			Random rand = new Random();	
			int cpuposition = rand.nextInt(9)+1;//在1-9之間隨機畫o
			while(playerpos.contains(cpuposition) || cpupos.contains(cpuposition)) {
				cpuposition = rand.nextInt(9)+1;
			}
			placement(gameboard, cpuposition, "cpu");
			
			printgameboard(gameboard);
			
			result = checkwin();
			if(result.length()>0) {
				System.out.println(result);
				break;
			}
			
			System.out.println(result);
		}
	
	}
	public static void printgameboard(char[][] gameboard) {
		for(char[] row:gameboard) {
			for(char c:row) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	public static void placement(char[][] gameboard,int pos, String user) {
		
		char symbol =' ';
				
		if(user=="player") {
			symbol = 'x';
			playerpos.add(pos);
		}
		else if(user=="cpu") {
			symbol = 'o';
			cpupos.add(pos);
		}
		
		switch(pos) {
			case 1:
				gameboard[0][0] = symbol;
				break;
			case 2:
				gameboard[0][2] = symbol;
				break;
			case 3:
				gameboard[0][4] = symbol;
				break;
			case 4:
				gameboard[2][0] = symbol;
				break;
			case 5:
				gameboard[2][2] = symbol;
				break;
			case 6:
				gameboard[2][4] = symbol;
				break;
			case 7:
				gameboard[4][0] = symbol;
				break;
			case 8:
				gameboard[4][2] = symbol;
				break;
			case 9:
				gameboard[4][4] = symbol;
				break;
			default:
				break;
	}
	}

	public static String checkwin() {
		
		//列出贏的條件(情境)
		List win1 = Arrays.asList(1,2,3);
		List win2 = Arrays.asList(4,5,6);
		List win3 = Arrays.asList(7,8,9);
		List win4 = Arrays.asList(1,4,7);
		List win5 = Arrays.asList(2,5,8);
		List win6 = Arrays.asList(3,6,9);
		List win7 = Arrays.asList(1,5,9);
		List win8 = Arrays.asList(7,5,3);
		
		List<List> win = new ArrayList<List>();
		win.add(win1);
		win.add(win2);
		win.add(win3);
		win.add(win4);
		win.add(win5);
		win.add(win6);
		win.add(win7);
		win.add(win8);
		
		for(List a: win) {
			if(playerpos.containsAll(a)) {
				return "player win";
			}
			else if(cpupos.containsAll(a)) {
				return "cpu win";
			}else if(playerpos.size()+cpupos.size()== 9) {
				return "tie";
			}
		}
		
		return "";
	
	}
}

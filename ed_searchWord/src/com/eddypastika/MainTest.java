package com.eddypastika;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

	public static void main(String[] args){
		
		String input = "C:\\Users\\ig.eddy.p.putra\\Documents\\EDDY\\input.in";
		//Read file to array
		String line = "";
		BufferedReader abc;
		try {
			abc = new BufferedReader(new FileReader(input));
			List<String> lines = new ArrayList<String>();

			while((line = abc.readLine()) != null) {
			    lines.add(line);
			}
			abc.close();
			
			String[] in = lines.toArray(new String[]{});
			
			List<String> result = new ArrayList<String>();
			
			result = findWord(in);
			
			//Print list result
			for (int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//String[] in = {"3","3","4","catt","aata","tatc","cat","5","5","gogog","ooooo","godog","ooooo","gogog","dog","2","8","bananana","kalibrrr","nana"};
		

	}
	
	public static List<String> findWord(String[] in){
		List<String> result = new ArrayList<String>();
		
		int totalCase = Integer.parseInt(in[0]);
		int n = 0;
		int m, i = 0;
		String w ="";
		int lineNumber = 1;
		int start, end = 0;
		int countWord = 0;
		String x = "";
		
		while (i < totalCase) {
			
			//Line
			n = Integer.parseInt(in[lineNumber]);
			//System.out.println("M "+ (i+1) +" = "+n);

			//column
			m = Integer.parseInt(in[lineNumber+1]);
			//System.out.println("N "+ (i+1) +" = "+m);
			
			//Word
			w = in[lineNumber+2+n];
			//System.out.println("W "+ (i+1) +" = "+w);
			
			
			//index firstWord
			start = lineNumber+2;
			//index lastWord
			end = start+(n-1);
			//System.out.println();
			
			//method for count word
			countWord = checkWord(in, start, end, w);
			x = "Case "+(i+1)+": "+countWord;
			//System.out.println(x);
			result.add(x);

			lineNumber = lineNumber+n+3;
			i = i+1;
		}
		return result;
	}
	
	public static int checkWord(String[] in, int start, int end, String w) {
		
		int countWord = 0;
		//Create GRID
		char[][] grid = new char[end-start+1][in[start].length()];
		int idx = 0;
		for (int i = start; i <= end; i++) {
			
			for (int j = 0; j < in[i].length(); j++) {
				grid[idx][j] =  in[i].charAt(j);
				//System.out.print(grid[idx][j]);
			}
			//System.out.println();
			idx = idx+1;
		}
		
		//Find Right
		countWord += findRight(grid, w, countWord);
		//Find Left
		countWord += findLeft(grid, w, countWord);
		//Find Top
		countWord += findTop(grid, w, countWord);
		//Find Bottom
		countWord += findBottom(grid, w, countWord);
		//Find Top Right
		countWord += findTopRight(grid, w, countWord);
		//Find Top Left
		countWord += findTopLeft(grid, w, countWord);
		//Find Bottom Right
		countWord += findBottomRight(grid, w, countWord);
		//Find Bottom Left
		countWord += findBottomLeft(grid, w, countWord);
		
		
		return countWord;
	}
	
	//RIGHT
	public static int findRight(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == firstLetter) {
					startIndex = j;
					endIndex = startIndex + (w.length() - 1);
					
					x = "";
					
					if (endIndex <= grid[0].length-1) { //validate number endIndex
						for (int k = startIndex; k <= endIndex; k++) {
							x = x.concat(""+grid[i][k]);
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword right: "+x);
					//System.out.println("Count right: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
	
	//LEFT
	public static int findLeft(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == firstLetter) {
					startIndex = j;
					endIndex = startIndex - (w.length() - 1);
					
					x = "";
					
					if (endIndex >= 0) { //validate number endIndex
						for (int k = startIndex; k >= endIndex; k--) {
							x = x.concat(""+grid[i][k]);
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword left: "+x);
					//System.out.println("Count left: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
	
	//TOP
	public static int findTop(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";

		for (int j = 0; j < grid[0].length; j++) {
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j] == firstLetter) {
					startIndex = i;
					endIndex = i + (w.length() - 1);
					
					x = "";
					
					if (endIndex <= grid.length - 1) { //validate number endIndex
						for (int k = startIndex; k <= endIndex; k++) {
							x = x.concat(""+grid[k][j]);
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword top: "+x);
					//System.out.println("Count top: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
	
	//BOTTOM
	public static int findBottom(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";

		for (int j = 0; j < grid[0].length; j++) {
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j] == firstLetter) {
					startIndex = i;
					endIndex = i - (w.length() - 1);
					
					x = "";
					
					if (endIndex >= 0) { //validate number endIndex
						for (int k = startIndex; k >= endIndex; k--) {
							x = x.concat(""+grid[k][j]);
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword bottom: "+x);
					//System.out.println("Count bottom: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
	
	//TOP-RIGHT
	public static int findTopRight(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";

		for (int j = 0; j < grid[0].length; j++) {
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j] == firstLetter) {
					startIndex = i;
					endIndex = i + (w.length() - 1);
					
					x = "";
					
					int l = j;
					if ((endIndex <= grid.length - 1) && (j <= (grid[0].length - w.length()))) { //validate number endIndex
						for (int k = startIndex; k <= endIndex; k++) {
							x = x.concat(""+grid[k][l]);
							l++;
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword tr: "+x);
					//System.out.println("Count tr: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
	
	//TOP-LEFT
	public static int findTopLeft(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";

		for (int j = 0; j < grid[0].length; j++) {
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j] == firstLetter) {
					startIndex = i;
					endIndex = i + (w.length() - 1);
					
					x = "";
					
					int l = j;
					if ((endIndex <= grid.length-1)  && j >= w.length()-1) { //validate number endIndex
						for (int k = startIndex; k <= endIndex; k++) {
							x = x.concat(""+grid[k][l]);
							l--;
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword tl: "+x);
					//System.out.println("Count tl: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
		
	//BOTTOM-RIGHT
	public static int findBottomRight(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";

		for (int j = 0; j < grid[0].length; j++) {
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j] == firstLetter) {
					startIndex = i;
					endIndex = i - (w.length() - 1);
					
					x = "";
					
					int l = j;
					if ((endIndex >= 0) && (j <= (grid[0].length - w.length()))) { //validate number endIndex
						for (int k = startIndex; k >= endIndex; k--) {
							x = x.concat(""+grid[k][l]);
							l++;
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword br: "+x);
					//System.out.println("Count br: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
	
	//TOP-LEFT
	public static int findBottomLeft(char[][] grid, String w, int countWord) {
		
		countWord = 0;
		char firstLetter = w.charAt(0);
		int startIndex = 0;
		int endIndex = 0;
		String x = "";

		for (int j = 0; j < grid[0].length; j++) {
			for (int i = 0; i < grid.length; i++) {
				if (grid[i][j] == firstLetter) {
					startIndex = i;
					endIndex = i - (w.length() - 1);
					
					x = "";
					
					int l = j;
					if ((endIndex >= 0)  && j >= w.length()-1) { //validate number endIndex
						for (int k = startIndex; k >= endIndex; k--) {
							x = x.concat(""+grid[k][l]);
							l--;
						}
					}
					
					//Count match word
					if(x.equalsIgnoreCase(w)) {
						countWord = countWord + 1;
					}
					//System.out.println("Xword bl: "+x);
					//System.out.println("Count bl: "+countWord);
				}
				
			}
			
		}
		
		return countWord;

	}
		


}

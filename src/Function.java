package src;

import java.util.Arrays;

public class Function {
	
	private int[][] m;
	private int w,h;
	
	public Function(int w , int h) {
		this.w = w;
		this.h = h;
		m = new int[h][w];
	}
	
	public void playWith(int player, int x, int y) {
		m[x][y] = player;
	}
	
	public boolean isGameOver() {
		return winWithLine() || winWithColumn() || winWithDiagonalLine();
	}
	
	private boolean winWithLine() {
		for(int i=0;i<m.length;i++) {
			String s = Arrays.toString(m[i]).replace(" ", "").replace(",", "");
//			System.out.println(s);
			if(s.contains("11111") || s.contains("22222")) return true;
		}
		return false;
	}
	
	private boolean winWithColumn() {
		String[] s = new String[w];
		for(int i=0;i<w;i++)
			for(int j=0;j<h;j++)
				s[j] += m[i][j];
		return isWin(s);
	}
	
	private boolean winWithDiagonalLine() {
		for(int i=0;i<h-4;i++) {
			for(int j=0;j<w-4;j++) {
				String s = "";
				for(int k=0;k<5;k++)
					s += m[i+k][j+k];
				if(s.contains("11111") || s.contains("22222")) return true;
			}
		}
		for(int i=0;i<h-4;i++) {
			for(int j=4;j<w;j++) {
				String s = "";
				for(int k=0; k<5;k++)
					s += m[i+k][j-k];
				if(s.contains("11111") || s.contains("22222")) return true;
			}
		}
		return false;
	}
	
	private boolean isWin(String[] s) {
		for(String str:s)
			if(str.contains("11111") || str.contains("22222")) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Function f = new Function(5,5);
		for(int i=0;i<f.h;i++)
			for(int j=0;j<f.w;j++)
				if(i==f.w-j-1)
					f.m[i][j] = 1;
		
		for(int[] x : f.m)
			System.out.println(Arrays.toString(x));
		System.out.println(f.isGameOver());
	}

}

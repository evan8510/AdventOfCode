package day8;

public class XmasTree {
	
	private int height;
	private boolean vis[] = new boolean[4];
	/*
	 * 0 -> leftvis
	 * 1 -> rightvis
	 * 2 -> upvis
	 * 3 -> downvis
	 */
	
	private int scens[] = new int[4];
	/*
	 * 0 -> left score
	 * 1 -> right score
	 * 2 -> up score
	 * 3 -> down score
	 */
	public XmasTree(int h, boolean v) {
		height = h;
		vis[0] = v;
		vis[1] = false;
		vis[2] = false;
		vis[3] = false;
		for(int i=0; i<4; i++) {
			scens[i] = 1;
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean getVis() {
		return vis[0] || vis[1] || vis[2] || vis[3];
	}
	
	public void setLeft(boolean b) {
		vis[0] = b;
	}
	public void setRight(boolean b) {
		vis[1] = b;
	}
	public void setTop(boolean b) {
		vis[2] = b;
	}
	public void setBot(boolean b) {
		vis[3] = b;
	}
	
	public void setScrLeft(int x) {
		scens[0] = x;
	}
	public void setScrRight(int x) {
		scens[1] = x;
	}
	public void setScrTop(int x) {
		scens[2] = x;
	}
	public void setScrBottom(int x) {
		scens[3] = x;
	}
	
	public int getLeftScore() {
		return scens[0];
	}
	
	public int getRightScore() {
		return scens[1];
	}
	
	public int getTopScore() {
		return scens[2];
	}
	
	public int getBotScore() {
		return scens[3];
	}
	
	public int getScore() {
		return (scens[0] * scens[1] * scens[2] * scens[3]);
	}
}

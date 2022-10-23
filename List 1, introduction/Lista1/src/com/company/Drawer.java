package com.company;

import java.util.ArrayList;

public class Drawer {

public static void drawTriangle(int size) {
		if (size <= 0)
				error();
		for (String hash : fillArray(size)) {
				System.out.print(hash + "\n");
		}
}


public static void drawSquare(int size) {
		if (size <= 0) {
				error();
		} else if (size == 1) {
				System.out.print("#" + "\n");
		} else {
				String roof = "#".repeat(size)+ "\n";
				StringBuilder hole = new StringBuilder("#" + " ".repeat(Math.max(0, size - 2)) + "#");  // "\\s" nie działa

				System.out.print(roof);
				for (int high = 0; high < size - 2; high++)
						System.out.print(hole + "\n");
				System.out.print(roof);
		}
}

public static void drawPyramid(int size) {
		if (size <= 0) {
				error();
		} else {
				piramid(size, 0);
		}
}


public static void drawChristmasTree(int size) {
		if (size <= 0) {
				error();
		} else {
				for (int row = 1; row < size + 1; row++) {
						piramid(row, (size * 2 - 1) / 2);
				}
		}
}

public static void drawRectangle(int width, int height){
		if (width<=0 || height <=0 ) {
				error();
		} else if(width==1){
				for(int row = 0; row <height; row++){
						System.out.print("#"+"\n");
				}
		}else if(height==1){
				for(int col = 0; col <width; col++){
						System.out.print("#");
				}
		} else {

				String roof ="#".repeat(Math.max(0, width)) +"\n";
				System.out.print(roof);
				for(int row = 0; row <height-2; row++)
						System.out.print("#"+" ".repeat(Math.max(0, width-2))+"#" +"\n");
				System.out.print(roof);

		}
}


private static ArrayList<String> fillArray(int size) {
		ArrayList<String> hashes = new ArrayList<>();
		StringBuilder hash = new StringBuilder("#");
		for (int i = 0; i < size; i++) {
				hashes.add(hash.toString());
				hash.append("#");
		}
		return hashes;
}

private static void error(){
		System.out.print("fail" + "\n");
}

private static void piramid(int size, int boost) {
		int cutter;
		if (boost == 0) {
				cutter = size-1;
		} else {
				cutter = boost;
		}
		StringBuilder hole;

		for (int i = 1; i < size + 1; i++) {
				hole = new StringBuilder(
								" ".repeat(Math.max(0, cutter))
												+ "#".repeat(i * 2 - 1));

				System.out.print(hole + "\n");
				cutter--;
		}
}

}

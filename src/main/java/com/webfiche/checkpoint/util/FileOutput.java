package com.webfiche.checkpoint.util;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutput {
	protected FileWriter fW;

	public FileOutput(String fileName) {
		String moduleName = "> FileOutput: ";
		try {
			fW = new FileWriter(fileName);
		} catch (IOException e) {
			System.out.println(java.util.Calendar.getInstance().getTime()
								+ moduleName + e.getMessage());
		}
	}
	//
	public void writeLine(String lineIn) {
		String moduleName = "> FileOutput.writeLine: ";
		try {
			fW.write(lineIn + "\n");
		} catch (IOException e) {
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ moduleName + e.getMessage());
		}
	}
	//
	public void close() {
		String moduleName = "> FileOutput.close: ";
		try {
			fW.close();
		} catch (IOException e) {
			System.out.println(java.util.Calendar.getInstance().getTime()
					+ moduleName + e.getMessage());
		}
	}
}

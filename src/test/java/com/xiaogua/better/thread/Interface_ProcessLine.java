package com.xiaogua.better.thread;

public interface Interface_ProcessLine {
	public boolean isProcessLine(String content);

	public String getProcessContent(String content);

	public String getFilePart(String content);

	public String getDynamicFileName(String filePath);
	
	public String getFileExtension();

}

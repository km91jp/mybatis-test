package com.example.interceptor;

public class PrintLogIntercepor {
	
	public void printStartTransaction(){
		System.out.println("[Transaction]Start");
	}
	public void printEndTransaction(){
		System.out.println("[Transaction]Commit Or Rollback");
	}
	
	public void printStartExecute(){
		System.out.println("Execute!");
	}

}

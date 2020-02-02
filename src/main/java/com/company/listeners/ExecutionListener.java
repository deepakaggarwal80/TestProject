//package com.company.listeners;
//
//import org.testng.IExecutionListener;
///*IExecutionListener is a listener that monitors the beginning and end of a TestNG run. 
// * It has two methods, onExecutionStart() and onExecutionFinish(). 
// * Method onExecutionStart() is called before the TestNG starts running the suites and 
// * onExecutionFinish() is called after TestNG is done running all the test suites*/
//public class ExecutionListener implements IExecutionListener{
//	private long startTime;
//	public void onExecutionStart() {
//		startTime = System.currentTimeMillis();
//        System.out.println("TestNG is going to start: " +startTime); 
//		
//	}
//
//	public void onExecutionFinish() {
//		System.out.println("TestNG has finished, took around " + (System.currentTimeMillis() - startTime) + "ms");
//		
//	}
//
//}

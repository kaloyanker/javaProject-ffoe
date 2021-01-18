package reallifetaskmanager;
import java.io.*;

public class Tasks implements Serializable {
	    String taskName; 
	    int priority;
	    
	    Tasks(String taskName, int priority){
	    	this.taskName = taskName;
	    	this.priority = priority;
	    }
	    
	    public String toString(){
	        return taskName+"   "+priority;
	    }
}

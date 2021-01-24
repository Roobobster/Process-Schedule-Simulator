import java.util.Comparator;

    
class SortByArrival implements Comparator<Process> 
{ 
    public int compare(Process a, Process b) 
    { 
        double difference = a.getArrival() - b.getArrival();
        if (difference > 0)
            return 1;
        else if (difference < 0)
            return -1;
        
        return 0; //Equal
    } 
} 


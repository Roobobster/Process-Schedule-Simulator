import java.util.ArrayList;
import java.util.Collections;

interface ScheduleAlgorithm {
    public void sort(ArrayList<Process> processes);
    public Computation compute(ArrayList<Process> processing, ArrayList<Process> notArrived, ArrayList<Process> completed, Clock clock );
    public void sortArrivals(ArrayList<Process> notArrived);
    

}

class Algorithm{
    protected void moveArrivals(ArrayList<Process> processing, ArrayList<Process> notArrived, Clock clock){
        while (notArrived.size() > 0  && notArrived.get(0).getArrival() <= clock.getValue()) {
            Process arrived = notArrived.get(0);
            processing.add(arrived);
            notArrived.remove(arrived);
        }

        
    }

    public void sortArrivals(ArrayList<Process> notArrived){
        Collections.sort(notArrived, new SortByArrival());
    }

    protected Computation sortAndStallCompute(ArrayList<Process> processing, ArrayList<Process> notArrived, Clock clock, ScheduleAlgorithm scheduleAlgorithm)
    {
        moveArrivals(processing, notArrived, clock);

        scheduleAlgorithm.sort(processing);

        if(processing.size() == 0){
            Process nextToArrive = notArrived.get(0);
            double stallDuration = nextToArrive.getArrival() - clock.getValue();
            Computation computation = new Computation("NULL", stallDuration);
            clock.addTime(stallDuration); 
            return computation;
            
        }

        return null;
    }
}


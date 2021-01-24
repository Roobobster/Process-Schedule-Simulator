import java.util.ArrayList;
import java.util.Collections;



class FirstComeFirstServe extends Algorithm implements ScheduleAlgorithm {
    public void sort(ArrayList<Process> processes) {
        Collections.sort(processes, new SortByArrival());
    }

    public Computation compute(ArrayList<Process> processing, ArrayList<Process> notArrived, ArrayList<Process> completed, Clock clock) {
        
        Computation stallComputation = sortAndStallCompute(processing, notArrived, clock, this);
        if(stallComputation != null){
            return stallComputation;
        }

        Process first = processing.get(0);
        Computation computation = new Computation(first.getName(), first.getDuration());

        first.runForDuration(first.getDuration(), clock);
        completed.add(first);
        processing.remove(first);

        return computation;
    }
}
    
import java.util.ArrayList;
import java.util.Collections;

class PreEmptivePriority extends Algorithm implements ScheduleAlgorithm
{
    public void sort(ArrayList<Process> processes) {
        Collections.sort(processes, new SortByPriority());
    }

    public Computation compute(ArrayList<Process> processing, ArrayList<Process> notArrived, ArrayList<Process> completed, Clock clock) {

        Computation stallComputation = sortAndStallCompute(processing, notArrived, clock, this);
        if(stallComputation != null){
            return stallComputation;
        }

        Process first = processing.get(0);

        double duration = first.getDuration();
        for (Process process : notArrived) {
            if (process.getPriority() < first.getPriority() && process.getArrival() < clock.getValue() + first.getDuration()) {
                duration = process.getArrival() - clock.getValue();
                break;
            }
        }
        
        Computation computation = new Computation(first.getName(), duration);

        first.runForDuration(duration, clock);
        if(first.getDuration() == 0){
            completed.add(first);
            processing.remove(first);
        }
        

        return computation;
    }
}

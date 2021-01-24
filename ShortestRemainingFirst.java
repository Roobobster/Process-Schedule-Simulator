import java.util.ArrayList;
import java.util.Collections;


class ShortestRemainingFirst extends Algorithm implements ScheduleAlgorithm
{
    public void sort(ArrayList<Process> processes) {
        Collections.sort(processes, new SortByDuration());
    }

    public Computation compute(ArrayList<Process> processing, ArrayList<Process> notArrived, ArrayList<Process> completed, Clock clock) {

        Computation stallComputation = sortAndStallCompute(processing, notArrived, clock, this);
        if(stallComputation != null){
            return stallComputation;
        }

        Process first = processing.get(0);

        double duration = first.getDuration();
        for (Process process : notArrived) {
            double timePass = process.getArrival() - clock.getValue();
            if (process.getDuration() < first.getDuration() - timePass) {
                duration = timePass;
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

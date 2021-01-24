import java.util.ArrayList;

class RoundRobin extends Algorithm implements ScheduleAlgorithm {

    private double quantum;

    public RoundRobin(double quantum){
        this.quantum = quantum;
    }

    public void sort(ArrayList<Process> processes) {
        //No sort
    }

    public Computation compute(ArrayList<Process> processing, ArrayList<Process> notArrived, ArrayList<Process> completed, Clock clock) {
        
        Computation stallComputation = sortAndStallCompute(processing, notArrived, clock, this);
        if(stallComputation != null){
            return stallComputation;
        }

        Process first = processing.get(0);
        double duration = quantum;
        if(duration > first.getDuration()){
            duration = first.getDuration();
        }
        Computation computation = new Computation(first.getName(),duration);

        first.runForDuration(duration, clock);

        if(first.getDuration() == 0){
            completed.add(first);
            processing.remove(first);
        }
        else{ // Moves from front to back
            moveArrivals(processing, notArrived, clock);
            processing.remove(0);
            processing.add(first);
      


        }

        return computation;
    }
}

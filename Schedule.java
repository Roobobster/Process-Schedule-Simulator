import java.util.ArrayList;

class Schedule{
    private ArrayList<Process> notArrived;
    private ArrayList<Process> processing;
    private ArrayList<Process> completed;
    private Clock clock;
    
    public Schedule(){
        notArrived = new ArrayList<Process>();
        processing = new ArrayList<Process>();
        completed = new ArrayList<Process>();
        clock = new Clock();
    }

    public void addProcess(double duration, double arrival, String name){
        Process process = new Process(duration, arrival, name);
        notArrived.add(process);
        
    }

    public void addProcess(double duration, double arrival, String name, int priority){
        Process process = new Process(duration, arrival, name, priority);
        notArrived.add(process);
        
    }

    public void run(ScheduleAlgorithm scheduleAlgorithm){
        scheduleAlgorithm.sortArrivals(notArrived);
        System.out.println("\n\n\n--------------------------------");

        System.out.println("Initial Process");
        System.out.println("----------------\n");
        printProcesses();
        System.out.println("--------------------------------\n\n\n");
        System.out.print("0");
        while(!hasFinished()){
            Computation computation = scheduleAlgorithm.compute(processing, notArrived , completed, clock);
            System.out.print("|" + computation.getName()  + "  " + clock.getValue());
        }
        System.out.print("|");
        System.out.println("\n\n\n--------------------------------");
        System.out.println("Completed");
        System.out.println("----------\n");
        printCompleted();

        System.out.println("\n\n\n--------------------------------");

        printWaitTimeData();

        System.out.println("\n\n\n--------------------------------");

        printTourAroundTimeData();

        printThrougput();
        
    }

    private void printThrougput(){
        double total = completed.get(completed.size()-1).getEndTime();
        double throughput = completed.size() / total;
        System.out.println("Throughput: " + throughput);
    }

    private void printWaitTimeData(){
        ArrayList<ProcessCalculation> processCalculations = calculateWaitingTimes();

        System.out.println("Wait Times");
        System.out.println("----------\n");
        printCalculations(processCalculations);

        double waitTimeTotal = calculateProccessCalculationsTotal(processCalculations);
        double waitTimeAverage = calculateProccessCalculationsAverage(waitTimeTotal, processCalculations.size());
        
        System.out.println("-------------------------");

        System.out.println("Wait time total: " + waitTimeTotal);
        System.out.println("Wait time Average: " + waitTimeAverage);

        System.out.println("-------------------------");

    

    }


    private void printTourAroundTimeData(){
        ArrayList<ProcessCalculation> processCalculations = calculateTourAroundTimes();

        System.out.println("Turn Around Times");
        System.out.println("----------\n");
        printCalculations(processCalculations);

        double tourAroundTimeTotal = calculateProccessCalculationsTotal(processCalculations);
        double tourAroundTimeAverage = calculateProccessCalculationsAverage(tourAroundTimeTotal, processCalculations.size());
        
        System.out.println("-------------------------");

        System.out.println("Turn around time total: " + tourAroundTimeTotal);
        System.out.println("Turn around time Average: " + tourAroundTimeAverage);

        System.out.println("-------------------------");


    }

    private void printProcesses(){
        for (int i = 0; i < notArrived.size(); i++) {
            Process process = notArrived.get(i);
            System.out.println(process.getName() +  "     Arrival: " + process.getArrival() + "     Duration: " + process.getDuration());
        }
    }

    private double calculateProccessCalculationsTotal(ArrayList<ProcessCalculation> processCalculations){
        double total = 0;
        for (ProcessCalculation processCalculation : processCalculations) {
            total += processCalculation.getValue();
        }

        return total;
    }

    private double calculateProccessCalculationsAverage( double total , double processCount){
        return total / processCount;
    }

    private void printCompleted(){
        for (int i = 0; i < completed.size(); i++) {
            Process process = completed.get(i);
            System.out.println(process.getName() +  "     Arrival: " + process.getArrival() );
        }
    }

    private void printCalculations(ArrayList<ProcessCalculation> processCalculations){
        for (ProcessCalculation processCalculation : processCalculations) {
            System.out.println(processCalculation.getProcessname() + "    Value: " +  processCalculation.getValue());
        }  
    }

    private ArrayList<ProcessCalculation> calculateWaitingTimes(){
        ArrayList<ProcessCalculation> processCalculations = new ArrayList<>();       
        for (Process process : completed) {
            double waitTime = process.getEndTime() - (process.getArrival() +process.getFullDuration());
            ProcessCalculation calculation = new ProcessCalculation(process.getName(), waitTime);
            processCalculations.add(calculation); 
            
        }
        return processCalculations;
    }

    private ArrayList<ProcessCalculation> calculateTourAroundTimes(){
        ArrayList<ProcessCalculation> processCalculations = new ArrayList<>();       
        for (Process process : completed) {
            double turnAroundTime = process.getEndTime() - process.getArrival();
            ProcessCalculation calculation = new ProcessCalculation(process.getName(), turnAroundTime);
            processCalculations.add(calculation); 
            
        }
        return processCalculations;
    }

    private boolean hasFinished(){
        return notArrived.size() == 0 && processing.size() == 0;
    }



}

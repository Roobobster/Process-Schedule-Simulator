class Process{

    private double duration;
    private double arrival;
    private String name; 
    private int priority;

    private double fullDuration;

    private double endTime;
    private double startTime;

    public Process(double duration, double arrival, String name){
        this.duration = duration;
        this.arrival = arrival;
        this.name = name;

        fullDuration = duration;

        endTime = -1; // Implies not completed
        startTime = -1; // Implies not started
        priority = -1; // No priority selected
    }

    public Process(double duration, double arrival, String name, int priority){
        this.duration = duration;
        this.arrival = arrival;
        this.name = name;

        fullDuration = duration;

        endTime = -1; // Implies not completed
        startTime = -1; // Implies not started
        this.priority = priority;
    }

    public double getDuration() {return duration;}
    public double getArrival() {return arrival;}
    public String getName() {return name;}
    public double getEndTime() {return endTime;}
    public double getStartTime() {return startTime;}
    public int getPriority() {return priority;}
    public double getFullDuration() {return fullDuration;}




    private void setEndTime(double endTime){
        this.endTime = endTime;
    }

    private void setStartTime(double startTime){
        this.startTime = startTime;
    }

    public void runForDuration(double runDuration, Clock clock){
        if(startTime == -1){
            setStartTime(clock.getValue());}

        duration -= runDuration;
        clock.addTime(runDuration);

        if(duration == 0){
            setEndTime(clock.getValue());}
        
    }

}

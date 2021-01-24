class ProcessCalculation{
    private String processName;
    private double value;

    public ProcessCalculation(String processName, double value){
        this.processName = processName;
        this.value = value;
    }

    public String getProcessname(){return processName;}
    public double getValue(){return value;}
}
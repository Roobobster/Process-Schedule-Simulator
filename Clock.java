class Clock{
    private double value;
    public Clock(){
        value = 0;
    }

    public double getValue(){
        return value;
    }

    public void addTime(double time){
        value += time;
    }
}

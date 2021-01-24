
class Computation{
    private String name;
    private Double duration;

    public Computation(String name, double duration){
        this.name = name;
        this.duration = duration;
    }

    public String getName(){ return name;}
    public double getDuration(){return duration;}
}

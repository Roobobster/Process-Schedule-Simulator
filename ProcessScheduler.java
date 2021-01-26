public class ProcessScheduler {

    public static void main(String[] args) {
        Schedule schedule = new Schedule();
        // duration, arrival, name, priority(optional)
        schedule.addProcess(278, 0, "P0");
        schedule.addProcess(254,489, "P1");
        schedule.addProcess(59, 392, "P2");
        schedule.addProcess(381, 371, "P3");
        schedule.addProcess(317, 14, "P4");

        
   
        schedule.run(new ShortestRemainingFirst());

    }
}

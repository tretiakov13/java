package ua.khpi.oop.tretiakov13;

public class SecondThread implements Runnable {

    LinkedContainer<Event> linkedContainer;

    public SecondThread(LinkedContainer<Event> linkedContainer)
    {
        this.linkedContainer = linkedContainer;
    }


    @Override
    public void run() {
        int sum = 0;
        int avg = 0;
        for (int i = 0; i < linkedContainer.size(); i++) {
            sum += linkedContainer.getElementByIndex(i).getDuration();
        }
        avg = sum / linkedContainer.size();
        System.out.println("Sum duration = " + sum + " minute");
        System.out.println("Avg duration = " + avg + " minute");
    }

}

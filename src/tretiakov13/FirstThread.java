package ua.khpi.oop.tretiakov13;

public class FirstThread implements Runnable {

    LinkedContainer<Event> linkedContainer;

    public FirstThread(LinkedContainer<Event> linkedContainer)
    {
        this.linkedContainer = linkedContainer;
    }


    @Override
    public void run() {

        int max = linkedContainer.getElementByIndex(0).getDuration();
        int min = linkedContainer.getElementByIndex(0).getDuration();
        for (int i = 0; i < linkedContainer.size(); i++) {
            if (linkedContainer.getElementByIndex(i).getDuration() < min) {
                min = linkedContainer.getElementByIndex(i).getDuration();
            }
            if (linkedContainer.getElementByIndex(i).getDuration() > max) {
                max = linkedContainer.getElementByIndex(i).getDuration();
            }
        }
        System.out.println("Max duration = " + max+" minute");
        System.out.println("Min duration = " + min+" minute");
    }

}

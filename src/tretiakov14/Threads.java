package ua.khpi.oop.tretiakov14;

import java.util.concurrent.Callable;

public class Threads {

    public static class MyThread3 implements Callable<Boolean> {
        public static final int HIGHER_DURATION = 1000;
        LinkedContainer<Event> linkedContainer;

        @Override
        public Boolean call() throws Exception {
            countHigherDuration();
            return true;
        }

        private void countHigherDuration() throws InterruptedException {
            int count = 0;
            for (int i = 0; i < linkedContainer.size(); i++) {
                Thread.sleep(0);
                if (linkedContainer.getElementByIndex(i).getDuration() > HIGHER_DURATION) {
                    count++;
                }
            }
            //System.out.println("Number of events with higher duration: " + count);
        }

        public void set(LinkedContainer<Event> linkedContainer) {
            this.linkedContainer = linkedContainer;
        }
    }

    public static class MyThread2 implements Callable<Boolean> {
        LinkedContainer<Event> linkedContainer;


        private void sumAvgDuration() throws InterruptedException {
            long sum = 0;
            long avg = 0;
            for (int i = 0; i < linkedContainer.size(); i++) {
                // Thread.sleep(2);
                sum += linkedContainer.getElementByIndex(i).getDuration();
            }
            avg = sum / linkedContainer.size();
            System.out.println("Sum duration = " + sum +" minute");
            System.out.println("Avg duration = " + avg +" minute");
        }

        public void set(LinkedContainer<Event> linkedContainer) {
            this.linkedContainer = linkedContainer;
        }

        @Override
        public Boolean call() throws Exception {
            sumAvgDuration();
            return true;
        }
    }


    public static class MyTread1 implements Callable<Boolean> {

        LinkedContainer<Event> linkedContainer;

        public void set(LinkedContainer<Event> linkedContainer) {
            this.linkedContainer = linkedContainer;
            //System.out.println(linkedContainer.size());
        }

        @Override
        public Boolean call() throws Exception {
            run();
            return true;
        }


        public void run() throws InterruptedException {

            int max = linkedContainer.getElementByIndex(0).getDuration();
            int min = linkedContainer.getElementByIndex(0).getDuration();
            for (int i = 0; i < linkedContainer.size(); i++) {
                //Thread.sleep(10);
                if (linkedContainer.getElementByIndex(i).getDuration() < min) {
                    min = linkedContainer.getElementByIndex(i).getDuration();
                }
                if (linkedContainer.getElementByIndex(i).getDuration() > max) {
                    max = linkedContainer.getElementByIndex(i).getDuration();
                }
            }
            System.out.println("Max duration = " + max + " minute");
            System.out.println("Min duration = " + min + " minute");
        }




    }

}

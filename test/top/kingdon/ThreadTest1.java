package top.kingdon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest1 {
    public static void main(String[] args) {
        Count count = new Count();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count.getCount()<100)
                    count.getCountOdd();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(count.getCount()<100)
                    count.getCountEven();
            }
        }).start();

    }
}
class Count{
    static int count = 1;
    Lock lock = new ReentrantLock();
    Condition condiction = lock.newCondition();
//    Condition condiction2 = lock.newCondition();

    public int getCountOdd()  {
        lock.lock();
        try {
            while(count%2 == 0)
                condiction.await();    //调用await（）会释放锁


            System.out.println(Thread.currentThread().getName()+"-->"+count++);
            condiction.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return count;




    }

    public int getCountEven(){
        lock.lock();
        try {
            while(count%2 == 1)
                condiction.await();
            System.out.println(Thread.currentThread().getName()+"-->"+count++);
            condiction.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return count;

    }

    int getCount(){
        return count;
    }


}

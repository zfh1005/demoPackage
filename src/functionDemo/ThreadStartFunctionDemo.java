package functionDemo;

import java.lang.Thread;


/**
 *
 * @author ZFH
 */
public class ThreadStartFunctionDemo extends Thread {
	
	private String name;

    public ThreadStartFunctionDemo(String name){
    	super();
    	this.name = name;  
    }
    @Override
    public void run() {
    	int timer = 0;
        while (timer < 3) {
            try {
                Thread.sleep(1000);
                timer++;
                System.out.println("线程:" + name + "; 逝去了:" + timer + "	 秒");
                } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }     
   

	public static void main(String[] args) {
		ThreadStartFunctionDemo tfd1 = new ThreadStartFunctionDemo("AAAA");
		ThreadStartFunctionDemo tfd2 = new ThreadStartFunctionDemo("BBBB");
		/*
		 * tfd1.start();
		 * tfd2.start();
		 * 对应的输出结果是：
		 * 线程:AAAA; 逝去了:1	 秒
		 * 线程:BBBB; 逝去了:1	 秒
		 * 线程:AAAA; 逝去了:2	 秒
		 * 线程:BBBB; 逝去了:2	 秒
		 * 线程:AAAA; 逝去了:3	 秒
		 * 线程:BBBB; 逝去了:3	 秒
		 * */
		
		tfd1.start();
		tfd2.start();
		/*
		 * tfd1.run();
		 * tfd2.run();
		 * 对应的输出结果是：
		 * 线程:AAAA; 逝去了:1	 秒
		 * 线程:AAAA; 逝去了:2	 秒
		 * 线程:AAAA; 逝去了:3	 秒
		 * 线程:BBBB; 逝去了:1	 秒
		 * 线程:BBBB; 逝去了:2	 秒
		 * 线程:BBBB; 逝去了:3	 秒
		 * */
		
		//tfd1.run();
		//tfd2.run();
	}

}

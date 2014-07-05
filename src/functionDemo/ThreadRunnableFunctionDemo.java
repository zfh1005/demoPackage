package functionDemo;

public class ThreadRunnableFunctionDemo implements Runnable{
	private String name;
	public ThreadRunnableFunctionDemo(String name){
		this.name = name;
	}
	

	int iTimer = 3;
	private int iRetryNumber = iTimer;
	@Override
	public void run() {
		for(int i = 1; i <= iRetryNumber; i++)
		{
            if(iTimer > 0){            	
				try {
	                //Thread.sleep(1000);                
	                System.out.println("线程:" + name + "; 逝去了:" + (iTimer--) + "	 秒");
	                } 
	            catch (Exception e){
	                e.printStackTrace();
	            }
            }
			
        }		
	}
	public static void main(String[] args) {
		ThreadRunnableFunctionDemo tfd1 = new ThreadRunnableFunctionDemo("AAAA");
		//ThreadRunnableFunctionDemo tfd2 = new ThreadRunnableFunctionDemo("BBBB");
		Thread aaa = new Thread(tfd1);
		Thread bbb = new Thread(tfd1);
		/*
		 * aaa.start();
		 * bbb.start();
		 * 对应的输出是：
		 * 线程:AAAA; 逝去了:3	 秒
		 * 线程:AAAA; 逝去了:2	 秒
		 * 线程:AAAA; 逝去了:1	 秒
		 * 
		 * */
		aaa.start();
		bbb.start();
	}
}

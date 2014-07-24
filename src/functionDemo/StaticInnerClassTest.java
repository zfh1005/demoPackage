/**
 * 
 */
package functionDemo;

/**
 * @author zfh1005
 *
 */
public class StaticInnerClassTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double[] d = new double[20];
		for(int i = 0 ; i < d.length; i++){
			d[i] = 100 * Math.random();
		}
		for(int i = 0 ; i < d.length; i++){
			System.out.print(d[i] + ",");
		}
		System.out.println();
		ArrayAlg.Pair p = ArrayAlg.Pair.minmax(d);
		System.out.println("min = " + p.getFirst());
		System.out.println("max = " + p.getSecond());
	}
	
	
	static class ArrayAlg{
		public static class Pair{
			public Pair(double f, double s){
				first = f;
				second = s;
			}
			
			public double getFirst() {
				return first;
			}
		
			public double getSecond() {
				return second;
			}
			
			public static Pair minmax(double[] values){
				double min = Double.MAX_VALUE;
				double max = Double.MIN_VALUE;
				for(double v : values){
					if(min > v){
						min = v;
					}
					if(max < v){
						max = v;
					}
				}
				return new Pair(min, max);
			}
			
			private double first;
			private double second;			
			
		}
	}

}

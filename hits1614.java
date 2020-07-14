
//Created by CHANDNI PATEL CS610 1614 PRP NJIT

import java.io.*;
import java.text.DecimalFormat;

public class hits1614 {

	private static BufferedReader br;
	private static float[] hub_value, authority_value;
	private static int num_itrs, AT[][], adj_mat[][], len, err_rate;
	static boolean isLarge = true;
	private static DecimalFormat df;
	public static final float _10M = 10000000.0f;


	public static void initializeAdjacentMatrixAndValues1614(int initVal) {

		float first_value = 0.0f;
		if (len <= 10) {
			if (initVal == 0) {
				first_value = 0.0f;
			} else if (initVal == 1) {
				first_value = 1.0f;
			} else if (initVal == -1) {
				float value = 1 / ((float) len);
				first_value = value;
			} else if (initVal == -2) {
				first_value = 1 / (float)Math.sqrt((float) len);
			}
		} else {
			first_value = 1 / ((float) len);
			num_itrs = 0;
			isLarge = false;
		}
		System.out.printf("\nInitialized value is " + first_value + "\n");
		int i = 0;
		while (i < len) {
			hub_value[i] = first_value;
			authority_value[i] = first_value;
			i++;
		}
	}

	public static void buildMatrix1614(int initVal) throws IOException {
		String line = "";
		while ((line = br.readLine()) != null) {
			adj_mat[Integer.valueOf(line.split(" ")[0])][Integer.valueOf(line.split(" ")[1])] = 1;
		}
	}

	public static void updateAHon1614(int iter) {
		if (getErrorRate1614() == 1) {
			updateWithErrorRate1614();
		} else {
			updateWithIters1614(iter);
		}
	}

	public static void updateWithErrorRate1614() {
		float[] pA, pH, a = authority_value, h = hub_value;
		float errVal = (float)Math.pow(10, num_itrs);
		int i = 0;
		do {
			pA = a;
			pH = h;
			a = matMul1614(h, AT);
			h = matMul1614(a, adj_mat);
			a = normalize1614(a);
			h = normalize1614(h);
			if (!isLarge)
				printItrs1614("Iter", ++i, a, h);
		} while (!isConverged1614(a, h, pA, pH, errVal));
		if (isLarge) {
			printItrs1614("Iteration", ++i, a, h);
		}
	}

	public static void updateWithIters1614(int iter) {
		float[] a, h = hub_value;
		for(int i=1;i<iter;i++){
			a = matMul1614(h, AT);
			h = matMul1614(a, adj_mat);
			a = normalize1614(a);
			h = normalize1614(h);
			printItrs1614("Iter",i, a, h);
		}
	}
	
	public static float[] matMul1614(float[] v, int[][] a) {
		float[] res = new float[len];
		int i = 0, j = 0;
		while (i < len) {
			j = 0;
			while (j < len) {
				res[i] = res[i] + (a[i][j] * v[j++]);
			}
			i++;
		}
		return res;
	}
	
	public static float[] normalize1614(float[] v) {
		float[] res = new float[len];
		float sum = 0;
		int i = 0;
		while (i < len) {
			sum += Math.pow(v[i++], 2.0);

		}
		i = 0;
		while (i < len) {
			res[i] = v[i++] / (float)Math.sqrt(sum);

		}
		return res;
	}
	
	public static void printItrs1614(String cased, int i, float[] a, float[] b) {
		System.out.printf("\n" + cased + " : " + (i) + " ");
		if ((cased == "Base") || !isLarge) {
			int j = 0;
			while (j < len) {
				System.out.printf("A/H[" + j + "] " + df.format(Math.round(a[j] * _10M) / _10M) + "/"
						+ df.format(Math.round(b[j] * _10M) / _10M) + " ");
				j++;
			}
		}
		if (isLarge && !(cased == "Base")) {
			int j = 0;
			while (j < len) {
				System.out.printf("A/H[" + j + "] " + df.format(a[j]) + "/" + df.format(b[j]) + " ");
				j++;
			}
		}
	}

	public static int[][] transpose1614() {
		int[][] a = new int[len][len];
		int i = 0, j = 0;
		while (i < len) {
			j = 0;
			while (j < len) {
				a[i][j] = adj_mat[j][i];
				j++;
			}
			i++;
		}
		return a;
	}
	
	public static boolean isConverged1614(float[] a, float[] h, float[] pA, float[] pH, float err) {
		boolean check = true;
		boolean diff[] = new boolean[len];
		int i = 0;
		while (i < len) {
			diff[i] = Math.abs(a[i] - pA[i]) < err && Math.abs(h[i] - pH[i]) < err ? true : false;
			i++;
		}

		for (boolean b : diff) {
			check = b && check;
		}

		return check;
	}

	public static int getErrorRate1614() {
		return err_rate;
	}

	public static void setErrorRate1614(int err_rate) {
		hits1614.err_rate = err_rate;
	}
	
	public static void main(String[] args) throws Exception {
		df = new DecimalFormat("#0.0000000");

		num_itrs = Integer.valueOf(args[0]);
		int initVal = Integer.valueOf(args[1]);
		String filename = args[2];

		br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		len = Integer.valueOf(line.split(" ")[0]);
		adj_mat = new int[len][len];
		AT = new int[len][len];
		hub_value = new float[len];
		authority_value = new float[len];

		initializeAdjacentMatrixAndValues1614(initVal);
		if (num_itrs == 0) {
			setErrorRate1614(1);
			num_itrs = -5;

		} else if (num_itrs < 0) {
			setErrorRate1614(1);
		}
		buildMatrix1614(initVal);
		AT = transpose1614();
		printItrs1614("Base", 0, authority_value, hub_value);
		updateAHon1614(num_itrs);
	}
}

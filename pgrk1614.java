//Created by CHANDNI PATEL CS610 1614 PRP NJIT

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

class Vertex1614 {
	List<Integer> inputs, outputs;
	float pgrk = 0.0f;

//creates arraylist for incoming and outgoing edges
	public Vertex1614(float initVal) {
		pgrk = initVal;
		inputs = new ArrayList<Integer>();
		outputs = new ArrayList<Integer>();
	}
//getter method
	public float getPgrk1614() {
		return pgrk;
	}
//setter method
	public void setVertex1614(float initVal) {
		pgrk = initVal;
	}

//insert incoming nodes into list
	public void addVertexinputs1614(Integer p) {
		if (!inputs.contains(p)) {
			inputs.add(p);
		}
	}

//insert outgoing nodes into list
	public void addVertexoutputs1614(Integer p) {
		if (!outputs.contains(p)) {
			outputs.add(p);
		}
	}

}

public class pgrk1614 {
	static BufferedReader br;
	static DecimalFormat decimalFormat = new DecimalFormat("#.0000000");
	static int numb_itr, len; 
	static boolean isLarge = false;
	static float d = 0.85f,err_rate = 0;

	public static void main(String[] args) throws Exception {
		List<Vertex1614> vertices;
		numb_itr = Integer.valueOf(args[0]);
		len = Integer.valueOf(args[1]);
		String fn = args[2];

		br = new BufferedReader(new FileReader(fn));
		String line = br.readLine();
		len = Integer.valueOf(line.split(" ")[0]);
		vertices = new ArrayList<Vertex1614>(len);
		float initVal = getinitVal1614(Integer.valueOf(args[1]));

		if (numb_itr == 0) {
			err_rate = 1;
			numb_itr = -5;
		} else if (numb_itr < 0) {
			err_rate = 1;
		}
		vertices = intializevertices1614(initVal, vertices);
		vertices = buildGraph1614(vertices);
		printItrs1614("Base", vertices, 0);
		updatePageRanks1614(vertices);
	}
	public static List<Vertex1614> intializevertices1614(float initVal, List<Vertex1614> vertices) {
		vertices = new ArrayList<Vertex1614>(len);
		for(int i = 0; i < len; i++) {
			vertices.add(new Vertex1614(initVal));
		}
		return vertices;
	}
	
	//generates graph
	public static List<Vertex1614> buildGraph1614(List<Vertex1614> vertices) throws Exception {
		String line = "";
		while ((line = br.readLine()) != null) {
			int m, n;
			m = Integer.valueOf(line.split(" ")[0]);
			n = Integer.valueOf(line.split(" ")[1]);
			vertices.get(n).addVertexinputs1614(m);
			vertices.get(m).addVertexoutputs1614(n);
		}
		return vertices;
	}
	
	//display method
	public static void printItrs1614(String cased, List<Vertex1614> p, int iter) {
		System.out.printf('\n' + cased + " : " + iter);
		if ((cased == "Base") || !isLarge) {
			for (Vertex1614 Vertex1614 : p) {
				System.out.printf(" P[" + p.indexOf(Vertex1614) + "] = %.7f", +(Vertex1614.getPgrk1614()));
			}
			return;
		}
		if (isLarge && !(cased == "Base")) {
			for (Vertex1614 Vertex1614 : p) {
				System.out.printf("\n P[" + p.indexOf(Vertex1614) + "] = %.7f", +(Vertex1614.getPgrk1614())+"\n");
			}
			return;
		}
	}

	public static float getinitVal1614(int init) {
		float initVal = 0.0f;
		if (len > 10) {
			initVal = 1.0f / ((float) len);
			numb_itr = 0;
			isLarge = true;
		} else {

			if (init == 0) {
				initVal = 0.0f;
			} else if (init == 1) {
				initVal = 1.0f;
			} else if (init == -1) {
				float val = 1.0f / ((float) len);
				initVal = val;
			} else if (init == -2) {
				initVal = 1.0f / (float) Math.sqrt((float) len);
			}

		}
		return initVal;
	}

	//update pagerank according to error rate value
	public static void updatePageRanks1614(List<Vertex1614> vertices) {
		if (err_rate!=1) {
			updatePGRKIters1614(vertices);
		} else {
			updatePGRKerrorRate1614(vertices);
		}
	}
	public static void updatePGRKIters1614(List<Vertex1614> vertices) {
		float sum = 0.0f, restCals = (1 - d) / (float) len;
		int i = 0;
		while (i < numb_itr) {
			vertices = update1614(sum, restCals, vertices);
			printItrs1614("Iteration", vertices, i + 1);
			i++;
		}
	}
	public static void updatePGRKerrorRate1614(List<Vertex1614> vertices) {
		float sum = 0.0f, restCals = (1 - d) / (float) len, errRate = (float) Math.pow(10.0, numb_itr);

		List<Vertex1614> prev_vertices = new ArrayList<Vertex1614>(len);
		int precision = (int) (1 / errRate), i = 0;
		String format = ("" + precision).substring(1);
		DecimalFormat dF = new DecimalFormat("#." + format);
		do {
			prev_vertices = getValuesFromVertex1614(vertices);
			vertices = update1614(sum, restCals, vertices);
			if (!isLarge)
				printItrs1614("Iteration", vertices, i + 1);
			i++;
		} while (!isConverged1614(prev_vertices, errRate, vertices, dF));

		if (isLarge) {
			printItrs1614("Iteration", vertices, i + 1);
		}
	}
	public static List<Vertex1614> update1614(float sum, float restCals, List<Vertex1614> vertices) {
		List<Vertex1614> t1 = new ArrayList<Vertex1614>(len);
		for (Vertex1614 Vertex1614 : vertices) {
			sum = 0.0f;
			for (Integer inc : Vertex1614.inputs) {
				sum += ((vertices.get(inc).getPgrk1614()) / (float) (vertices.get(inc).outputs.size()));
			}
			float fin_Cal = restCals + (d * sum);
			t1.add(new Vertex1614(fin_Cal));
		}
		for (Vertex1614 n : t1) {
			vertices.get(t1.indexOf(n)).setVertex1614(n.getPgrk1614());
		}
		return vertices;
	}
	

	public static List<Vertex1614> getValuesFromVertex1614(List<Vertex1614> vertices) {
		List<Vertex1614> node = new ArrayList<Vertex1614>(len);
		int i = 0;
		while (i < vertices.size()) {
			node.add(new Vertex1614(vertices.get(i++).getPgrk1614()));
		}
		return node;
	}

	public static Boolean isConverged1614(List<Vertex1614> prev, float errRate, List<Vertex1614> vertices,
			DecimalFormat dF) {
		//boolean diff[] = new boolean[len];
		boolean diff;
		int i = 0;
		while (i < len) {
			diff = Math.abs(vertices.get(i).getPgrk1614() - prev.get(i++).getPgrk1614()) >= errRate ? false : true;
			//check = check && diff[i];
			if (!diff) {
				return false;
				//System.out.println("mera false :" + false);
			}
		}
		return true;
	}
	
}

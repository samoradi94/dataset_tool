package dataset_tools;

import java.io.File;


import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Resample;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Instances reduced ;
		// data
		
		ArffLoader arffLoader = new ArffLoader();
		arffLoader.setSource(new File("/home/saeideh/iris.arff"));
		Instances data = arffLoader.getDataSet();

//		String Fliteroptions="-S 1";
		Resample sampler = new Resample();
		sampler.setInputFormat(data);
		sampler.setSampleSizePercent(90);
		sampler.setRandomSeed(2);
		sampler.setNoReplacement(true);
//		reduced = Resample.useFilter(data, sampler);
		reduced =  Filter.useFilter(data, sampler);
		
//		resampleFilter.setRandomSeed(1);
//		resampleFilter.setBiasToUniformClass(1);
//		resampleFilter.setInputFormat(data);
//		Instances reduced = Filter.useFilter(data, resampleFilter);
		for (int i = 0; i < reduced.numInstances(); i++) {
			System.out.println(reduced.instance(i));
		}
		

		ArffSaver saver = new ArffSaver();
		 saver.setInstances(reduced);
		 saver.setFile(new File("./data/test.arff"));
		 saver.setDestination(new File("./data/test.arff"));   // **not** necessary in 3.5.4 and later
		 saver.writeBatch();


	}
}
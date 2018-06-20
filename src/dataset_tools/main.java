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
		int percent = 90;
		int seedNumber = 1;
		String dataset_name = "iris";
		// data

		ArffLoader arffLoader = new ArffLoader();
		arffLoader.setSource(new File("/home/saeideh/"+ dataset_name+ ".arff"));
		Instances data = arffLoader.getDataSet();

		Resample sampler = new Resample();
		sampler.setInputFormat(data);
		sampler.setSampleSizePercent(percent);
		sampler.setRandomSeed(seedNumber);
		sampler.setNoReplacement(true);
		reduced =  Filter.useFilter(data, sampler);
		for (int i = 0; i < reduced.numInstances(); i++) {
			System.out.println(reduced.instance(i));
		}


		ArffSaver saver = new ArffSaver();
		saver.setInstances(reduced);
//		saver.setFile(new File("./data/test.arff"));
		saver.setFile(new File("data/"+ dataset_name + "- " + seedNumber + "-" + percent + ".arff"));
		saver.writeBatch();


	}
}
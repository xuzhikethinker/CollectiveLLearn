package edu.metu.lngamesml;

import edu.metu.lngamesml.agents.LearnerTypes;
import edu.metu.lngamesml.agents.beliefs.SigmoidFunctionTypes;
import edu.metu.lngamesml.alt.MajorityVoting;
import edu.metu.lngamesml.alt.WeightedMajorityVoting;
import edu.metu.lngamesml.collective.games.langgame.CategorizationGame;
import edu.metu.lngamesml.collective.games.langgame.conflanggame.CategorizationGameCRBU;
import edu.metu.lngamesml.utils.log.Logging;

import java.util.logging.Level;



/**
 * Created by IntelliJ IDEA.
 * User: caglar
 * Date: 1/26/11
 * Time: 1:58 AM
 * To change this template use File | Settings | File Templates.
 */

public class ThesisTests {

     public static void startMajorityVotingSim(String trainingDataset, String testDataset, LearnerTypes lType, int noOfAgents, int samplingRatio) {
        System.out.println("=============MajorityVoting Simulation have started=================");
        System.out.println("Agents creation is started");
        System.out.println("Game Props are: AgNo: " + noOfAgents + " test dataset" + testDataset + " sampling " + samplingRatio + "training " + trainingDataset );
        MajorityVoting mVoting = new MajorityVoting(noOfAgents, samplingRatio);
        //mVoting.setLearningType(LearnerTypes.NBAYES);
        mVoting.setLearningType(lType);
        mVoting.createAgentsWSampled(trainingDataset);
        System.out.println("Agents are created");
        System.out.println("The simulation has started");
        mVoting.startSimulation(testDataset);
        System.out.println("Accuracy is: " + mVoting.getAccuracy());
        System.out.println("The simulation has ended");
    }

    public static void startWeightedMajorityVotingSim(String trainingDataset, String testDataset, LearnerTypes lType, int noOfAgents, int samplingRatio) {
        System.out.println("=============Weighted Majority Voting Simulation have started=================");
        System.out.println("Agents creation is started");
        System.out.println("Game Props are: AgNo: " + noOfAgents + " test dataset" + testDataset + " sampling " + samplingRatio + "training " + trainingDataset );
        MajorityVoting mVoting = new WeightedMajorityVoting(noOfAgents, samplingRatio);
        //mVoting.setLearningType(LearnerTypes.NBAYES);
        mVoting.setLearningType(lType);
        mVoting.createAgentsWSampled(trainingDataset);
        System.out.println("Agents are created");
        System.out.println("The simulation has started");
        mVoting.startSimulation(testDataset);
        System.out.println("Accuracy is: " + mVoting.getAccuracy());
        System.out.println("The simulation has ended");
    }

    public static void startBasicLanguageGame(String trainingDataset, String testDataset, LearnerTypes lType, int noOfAgents, int samplingRatio, boolean useBeliefUpdates) {

        System.out.println("=============CategorizationGame Simulation have started================");
        System.out.println("Agents are being created");
        System.out.println("Game Props are: AgNo: " + noOfAgents + " test dataset" + testDataset + " sampling " + samplingRatio + "training " + trainingDataset );
        CategorizationGame bLangGame = new CategorizationGame(noOfAgents, samplingRatio);
        bLangGame.setUseBeliefUpdates(true);
        bLangGame.setLearningType(lType);
        //bLangGame.setLearningType(LearnerTypes.C45);
        bLangGame.createAgents(trainingDataset);
        System.out.println("Agents are created");
        try {
            System.out.println("The game has started");
            bLangGame.playGames(testDataset, SigmoidFunctionTypes.NONE);
            System.out.println("The game has ended");
            System.out.println("Accuracy is: " + bLangGame.getAccuracy());
        } catch (Exception e) {
            Logging.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        }
    }

    public static void startConfidenceLangGame(String trainingDataset, String testDataset, LearnerTypes lType, int noOfAgents, int samplingRatio, boolean useBeliefUpdates) {

        System.out.println("=============ConfidenceLanguageGame Simulation have started================");
        System.out.println("Agents are being created");
        System.out.println("Game Props are: AgNo: " + noOfAgents + " test dataset" + testDataset + " sampling " + samplingRatio + "training " + trainingDataset );
        CategorizationGameCRBU cLangGameCRBU = new CategorizationGameCRBU(noOfAgents, samplingRatio);
        cLangGameCRBU.setUseBeliefUpdates(useBeliefUpdates);
        cLangGameCRBU.setNoOfAgents(noOfAgents);
        cLangGameCRBU.setUseConfidences(true);
        cLangGameCRBU.setLearningType(lType);
        //cLangGameCRBU.setLearningType(LearnerTypes.C45);
        cLangGameCRBU.createAgents(trainingDataset);
        System.out.println("Agents are created");
        try {
            System.out.println("The game has started");
            cLangGameCRBU.playGames(testDataset, SigmoidFunctionTypes.LOGISTIC);
            System.out.println("The game has ended");
            System.out.println("Accuracy is: " + cLangGameCRBU.getAccuracy());
        } catch (Exception e) {
            Logging.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public static void startMajorityVotingSimNTimes(String trainingDataset, String testDataset, LearnerTypes lType, int noOfAgents, int samplingRatio) {
       int noOfIterations = 10;
       System.out.println("=============MajorityVoting Simulation have started=================");
       System.out.println("Agents creation is started");
       System.out.println("Game Props are: AgNo: " + noOfAgents + " test dataset" + testDataset + " sampling " + samplingRatio + "training " + trainingDataset );
       MajorityVoting mVoting = new MajorityVoting(noOfAgents, samplingRatio);
       //mVoting.setLearningType(LearnerTypes.NBAYES);
       mVoting.setLearningType(lType);
       mVoting.createAgentsWSampled(trainingDataset);
       System.out.println("Agents are created");
       System.out.println("The simulation has started");
       double accuracy = 0.0;
       for (int i = 0; i < noOfIterations; i++){
           mVoting.startSimulation(testDataset);
           accuracy += mVoting.getAccuracy();
       }
       accuracy /= noOfIterations;
       System.out.println("Accuracy percentage is :" + accuracy);
       System.out.println("The simulation has ended");
   }

    public static void startBasicLanguageGameNTimes(String trainingDataset, String testDataset, LearnerTypes lType, int noOfAgents, int samplingRatio, boolean useBeliefUpdates) {
        int noOfIterations = 10;
        System.out.println("=============CategorizationGame Simulation have started================");
        System.out.println("Agents are being created");
        System.out.println("Game Props are: AgNo: " + noOfAgents + " test dataset" + testDataset + " sampling " + samplingRatio + "training " + trainingDataset );
        CategorizationGame bLangGame = new CategorizationGame(noOfAgents, samplingRatio);
        bLangGame.setUseBeliefUpdates(true);
        //bLangGame.setLearningType(LearnerTypes.NBAYES);
        bLangGame.setLearningType(lType);
        bLangGame.createAgents(trainingDataset);
        System.out.println("Agents are created");
        double accuracy = 0.0;
        try {
            //System.out.println("The game has started");
            for(int i = 0; i < noOfIterations; i++){
                bLangGame.playGames(testDataset, SigmoidFunctionTypes.NONE);
                accuracy += bLangGame.getAccuracy();
            }
            //System.out.println("The game has ended");
        } catch (Exception e) {
            Logging.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        }
        accuracy /= noOfIterations;
        System.out.println("Averaged Accuracy is: " + accuracy);
    }

    public static void startConfidenceLangGameNTimes(String trainingDataset, String testDataset, LearnerTypes lType, int noOfAgents, int samplingRatio, boolean useBeliefUpdates) {
        int noOfIterations = 10;
        System.out.println("=============ConfidenceLanguageGame Simulation have started================");
        System.out.println("Agents are being created");
        System.out.println("Game Props are: AgNo: " + noOfAgents + " test dataset" + testDataset + " sampling " + samplingRatio + "training " + trainingDataset );
        CategorizationGameCRBU cLangGameCRBU = new CategorizationGameCRBU(noOfAgents, samplingRatio);
        cLangGameCRBU.setUseBeliefUpdates(useBeliefUpdates);
        cLangGameCRBU.setNoOfAgents(noOfAgents);
        cLangGameCRBU.setUseConfidences(true);
        //cLangGameCRBU.setLearningType(LearnerTypes.NBAYES);
        cLangGameCRBU.setLearningType(lType);
        cLangGameCRBU.createAgents(trainingDataset);
        System.out.println("Agents are created");
        double accuracy = 0.0;
        try {
            //System.out.println("The game has started");
            for(int i = 0; i < noOfIterations; i++){
                cLangGameCRBU.playGames(testDataset, SigmoidFunctionTypes.NONE);
                accuracy += cLangGameCRBU.getAccuracy();
            }
            //System.out.println("The game has ended");
        } catch (Exception e) {
            Logging.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        accuracy /= noOfIterations;
        System.out.println("Averaged Accuracy is: " + accuracy);
    }

    public static void mnistTests(LearnerTypes lType){
        String trainingDataset = "/home/caglar/Datasets/mnist/mnist_train_40_sampled.arff";
        String testDataset = "/home/caglar/Datasets/mnist/mnist_test_40_sampled.arff";
        int noOfAgents = 10;
        int samplingRatio = 20;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
    }

    public static void gtvsTests(LearnerTypes lType){
        String trainingDataset = "/home/caglar/Datasets/gtvs/Day1_50.TCP.arff";
        String testDataset = "/home/caglar/Datasets/gtvs/Day2_50.TCP.arff";
        int noOfAgents = 10;
        int samplingRatio = 20;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
    }

    public static void segmentationTests(LearnerTypes lType){
        String trainingDataset = "/home/caglar/Datasets/segment/segment-challenge.arff";
        String testDataset = "/home/caglar/Datasets/segment/segment-test.arff";
        int noOfAgents = 10;
        int samplingRatio = 40;
        //startMajorityVotingSimNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        //startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        //startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);

        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startWeightedMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        //startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
    }

    public static void segmentationIncSamplingTests(LearnerTypes lType){
        String trainingDataset = "/home/caglar/Datasets/segment/segment-challenge.arff";
        String testDataset = "/home/caglar/Datasets/segment/segment-test.arff";
        int noOfAgents = 10;
        int samplingRatio = 5;

        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);

        //10 percent:
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 10);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, 10, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, 10, true);

        //15 percent
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 15);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, 15, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, 15, true);

        //20 percent
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 20);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, 20, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, 20, true);

        //25 percent
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 25);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, 25, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, 25, true);
    }

    public static void segmentationIncAgentTests(LearnerTypes lType){
        String trainingDataset = "/home/caglar/Datasets/segment/segment-challenge.arff";
        String testDataset = "/home/caglar/Datasets/segment/segment-test.arff";
        int noOfAgents = 5;
        int samplingRatio = 10;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);

        noOfAgents +=5;
        startMajorityVotingSim(trainingDataset, testDataset, lType,noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);

        noOfAgents +=5;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        noOfAgents +=5;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        noOfAgents +=5;
        startMajorityVotingSim(trainingDataset, testDataset,lType, noOfAgents, samplingRatio);
        startBasicLanguageGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGame(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
    }

    public static void segmentationIncAgentTests10Times(LearnerTypes lType){
        String trainingDataset = "/home/caglar/Datasets/segment/segment-challenge.arff";
        String testDataset = "/home/caglar/Datasets/segment/segment-test.arff";
        int noOfAgents = 5;
        int samplingRatio = 10;
        startMajorityVotingSimNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        noOfAgents +=5;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        noOfAgents +=5;
        startMajorityVotingSimNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        noOfAgents +=5;
        startMajorityVotingSimNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        noOfAgents +=5;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
    }

    public static void segmentationIncSamplingTests10Times(LearnerTypes lType){
        String trainingDataset = "/home/caglar/Datasets/segment/segment-challenge.arff";
        String testDataset = "/home/caglar/Datasets/segment/segment-test.arff";
        int noOfAgents = 10;
        int samplingRatio = 5;
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, samplingRatio);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, samplingRatio, true);
        //10 percent:
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 10);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 10, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 10, true);
        //15 percent
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 15);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 15, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 15, true);
        //20 percent
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 20);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 20, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 20, true);
        //25 percent
        startMajorityVotingSim(trainingDataset, testDataset, lType, noOfAgents, 25);
        startBasicLanguageGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 25, true);
        startConfidenceLangGameNTimes(trainingDataset, testDataset, lType, noOfAgents, 25, true);
    }

    public static void main(String args[]){
        mnistTests(LearnerTypes.NBAYES);
        //gtvsTests(LearnerTypes.C45);
        //segmentationTests(LearnerTypes.C45);
        //segmentationIncSamplingTests();
        //segmentationIncAgentTests();
        //segmentationIncAgentTests10Times();
        //segmentationIncSamplingTests10Times();
    }
}

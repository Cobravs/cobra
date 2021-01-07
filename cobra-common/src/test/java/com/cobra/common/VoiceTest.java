package com.cobra.common;

public class VoiceTest {
    static int channel = 1;

    public static void main(String[] args) throws Exception {
        convertOne();
//        convertThree();
        
        convertThree();
        convertTwo();
        convertF();
        convertG();
        converth();
    }

    private static void convertOne() throws Exception {
        String source_one = "D:\\项目\\杭州分行外呼项目\\贷后回访_电话抽查.wav";
        String target_one = "D:\\项目\\杭州分行外呼项目\\贷后回访_电话抽查.mp3";
        Voice.convertToMp3(source_one, target_one, channel);
    }

    private static void convertTwo() throws Exception {
        String source_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+7.wav";
        String target_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+7.mp3";
        Voice.convertToMp3(source_one, target_one, channel);
    }

    private static void convertThree() throws Exception {
        String source_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+29.wav";
        String target_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+29.mp3";
        Voice.convertToMp3(source_one, target_one, channel);
    }
    private static void convertF() throws Exception {
        String source_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+50.wav";
        String target_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+50.mp3";
        Voice.convertToMp3(source_one, target_one, channel);
    }
    private static void convertG() throws Exception {
        String source_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+70.wav";
        String target_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+70.mp3";
        Voice.convertToMp3(source_one, target_one, channel);
    }
    private static void converth() throws Exception {
        String source_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+90.wav";
        String target_one = "D:\\项目\\杭州分行外呼项目\\催收电话T+90.mp3";
        Voice.convertToMp3(source_one, target_one, channel);
    }
}

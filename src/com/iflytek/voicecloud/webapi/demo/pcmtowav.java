package com.iflytek.voicecloud.webapi.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * ��.pcm�ļ�תΪWAV�ļ�
 * @author Administrator
 *
 */
public class pcmtowav {
    public static void convertAudioFiles(String[] src) throws Exception {
        FileInputStream fis = new FileInputStream(src[0]);

        //���㳤��
        byte[] buf = new byte[1024 * 4];
        int size = fis.read(buf);
        int PCMSize = 0;
        while (size != -1) {
            PCMSize += size;
            size = fis.read(buf);
        }
        System.out.println(size);
        fis.close();


        //��������������ʵȵȡ������õ���16λ������ 8000 hz
        WaveHeader header = new WaveHeader(PCMSize);
        //�����ֶ� = ���ݵĴ�С��PCMSize) + ͷ���ֶεĴ�С(������ǰ��4�ֽڵı�ʶ��RIFF�Լ�fileLength�����4�ֽ�)
        header.fileLength = PCMSize + (44 - 8);
        header.FmtHdrLeth = 16;
        header.BitsPerSample = 16;
        header.Channels = 1;
        header.FormatTag = 0x0001;
        header.SamplesPerSec = 16000;
        header.BlockAlign = (short)(header.Channels * header.BitsPerSample / 8);
        header.AvgBytesPerSec = header.BlockAlign * header.SamplesPerSec;
        header.DataHdrLeth = PCMSize;

        byte[] h = header.getHeader();

        assert h.length == 44; //WAV��׼��ͷ��Ӧ����44�ֽ�

//		   auline.write(h, 0, h.length);

        byte[] b = new byte[10];

        FileOutputStream fs = new FileOutputStream(src[1]);
        fs.write(h);
        FileInputStream fiss = new FileInputStream(src[0]);
        byte[] bb = new byte[10];
        int len = -1;
        while((len = fiss.read(bb))>0) {
//            System.out.println(len+" qwq");
            fs.write(bb, 0, len);
        }

    }
}



/**
 * WavHeader�����ࡣ��������ͷ����Ϣ��
 * @author Administrator
 *
 */
class WaveHeader {
    public final char fileID[] = {'R', 'I', 'F', 'F'};
    public int fileLength;
    public short FormatTag;
    public short Channels;
    public int SamplesPerSec;
    public int AvgBytesPerSec;
    public short BlockAlign;
    public short BitsPerSample;
    public char DataHdrID[] = {'d','a','t','a'};
    public int DataHdrLeth;
    public char wavTag[] = {'W', 'A', 'V', 'E'};;
    public char FmtHdrID[] = {'f', 'm', 't', ' '};
    public int FmtHdrLeth;

    public WaveHeader() {}//�޲ι��췽��
    /**
     *
     * @param a
     */
    public WaveHeader(int a) {

    }

    public byte[] getHeader() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        WriteChar(bos, fileID);
        WriteInt(bos, fileLength);
        WriteChar(bos, wavTag);
        WriteChar(bos, FmtHdrID);
        WriteInt(bos,FmtHdrLeth);
        WriteShort(bos,FormatTag);
        WriteShort(bos,Channels);
        WriteInt(bos,SamplesPerSec);
        WriteInt(bos,AvgBytesPerSec);
        WriteShort(bos,BlockAlign);
        WriteShort(bos,BitsPerSample);
        WriteChar(bos,DataHdrID);
        WriteInt(bos,DataHdrLeth);
        bos.flush();
        byte[] r = bos.toByteArray();
        bos.close();
        return r;
    }

    private void WriteShort(ByteArrayOutputStream bos, int s) throws IOException {
        byte[] mybyte = new byte[2];
        mybyte[1] =(byte)( (s << 16) >> 24 );
        mybyte[0] =(byte)( (s << 24) >> 24 );
        bos.write(mybyte);
    }


    private void WriteInt(ByteArrayOutputStream bos, int n) throws IOException {
        byte[] buf = new byte[4];
        buf[3] =(byte)( n >> 24 );
        buf[2] =(byte)( (n << 8) >> 24 );
        buf[1] =(byte)( (n << 16) >> 24 );
        buf[0] =(byte)( (n << 24) >> 24 );
        bos.write(buf);
    }

    private void WriteChar(ByteArrayOutputStream bos, char[] id) {
        for (int i=0; i<id.length; i++) {
            char c = id[i];
            bos.write(c);
        }
    }
}

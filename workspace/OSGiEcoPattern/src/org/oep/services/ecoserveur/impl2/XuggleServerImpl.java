package org.oep.services.ecoserveur.impl2;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.oep.services.ecoclient.api.xuggle.XuggleView;
import org.oep.services.ecoserveur.api.xuggle.XuggleServer;

import com.xuggle.xuggler.IAudioSamples;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

public class XuggleServerImpl implements XuggleServer {
	private IStreamCoder audioCoder;
	private int audioStreamId;
	private IContainer container;
	private String videoPath;
	private SourceDataLine mLine;
	private boolean play = false;
	private EcoServerImpl ecoServerImpl;
	
	

	public XuggleServerImpl(EcoServerImpl ecoServerImpl) {
		this.ecoServerImpl = ecoServerImpl;
	}
	@Override
	public void play(){
		Thread t = new Thread( new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				play = true;
				ecoServerImpl.consumption += 4.2;
				
				if (audioCoder != null) {
					if (audioCoder.open() < 0)
						throw new RuntimeException("could not open audio decoder for container: ");

					try {
						openJavaSound(audioCoder);
					}
					catch (LineUnavailableException ex) {
						throw new RuntimeException("unable to open sound device on your system when playing back container: ");
					}
				}

				IPacket packet = IPacket.make();
				
				while(container.readNextPacket(packet) >= 0 && play) {
					if (packet.getStreamIndex() == audioStreamId) {
						IAudioSamples samples = IAudioSamples.make(1024, audioCoder.getChannels());
						int offset = 0;

						while(offset < packet.getSize()) {
							int bytesDecoded = audioCoder.decodeAudio(samples, packet, offset);

							if (bytesDecoded < 0) 
								throw new RuntimeException("got error decoding audio in: ");

							offset += bytesDecoded;

							if (samples.isComplete()) {
								playJavaSound(samples);
							}
						}
					}
					else 
						do {} while(false);
				}
				
				ecoServerImpl.consumption -= 4.2;
				play = false;
			}
		});

		t.start();
	}
	@Override
	public void stop() {
		play = false;
	}
	@Override
	public void setmScreen(XuggleView mScreen) {
		
	}
	@Override
	public void setVideo(String path) {
		videoPath = path;

		load();
	}
	@Override
	public String getVideo() {
		return videoPath;
	}
	@Override
	public boolean isStarted() {
		return play;
	}
	

	private void load() {
		container = IContainer.make();

		if (container.open(videoPath, IContainer.Type.READ, null) < 0)
			throw new IllegalArgumentException("could not open file: " + videoPath);

		int numStreams = container.getNumStreams();

		audioStreamId = -1;
		audioCoder = null;
		for(int i = 0; i < numStreams; i++) {
			IStream stream = container.getStream(i);
			IStreamCoder coder = stream.getStreamCoder();

			if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
				audioStreamId = i;
				audioCoder = coder;
				break;
			}
		}
		if (audioStreamId == -1)
			throw new RuntimeException("could not find audio stream in container: "+videoPath);

	}
	private void openJavaSound(IStreamCoder aAudioCoder) throws LineUnavailableException {
		AudioFormat audioFormat = new AudioFormat(aAudioCoder.getSampleRate(), (int)IAudioSamples.findSampleBitDepth(aAudioCoder.getSampleFormat()), aAudioCoder.getChannels(), true, false);
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		mLine = (SourceDataLine) AudioSystem.getLine(info);
		mLine.open(audioFormat);
		mLine.start();


	}
	private void playJavaSound(IAudioSamples aSamples) {
		byte[] rawBytes = aSamples.getData().getByteArray(0, aSamples.getSize());
		mLine.write(rawBytes, 0, aSamples.getSize());
	}
}

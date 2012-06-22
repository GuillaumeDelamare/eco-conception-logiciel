package org.oep.services.ecoserveur.impl1;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.oep.services.ecoclient.api.xuggle.XuggleView;
import org.oep.services.ecoserveur.api.xuggle.XuggleServer;

import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.IAudioSamples;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.IVideoResampler;
import com.xuggle.xuggler.Utils;

public class XuggleServerImpl implements XuggleServer{
	private IStreamCoder videoCoder;
	private IStreamCoder audioCoder;
	private int videoStreamId;
	private int audioStreamId;
	private XuggleView mScreen;
	private SourceDataLine mLine;
	private long mSystemVideoClockStartTime;
	private long mFirstVideoTimestampInStream;
	private IContainer container;

	private String videoPath;
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
				//ecoServerImpl.consumption += 11.5;
				
				IVideoResampler resampler = null;
				if (videoCoder!= null) {
					if(videoCoder.open() < 0)
						throw new RuntimeException("could not open audio decoder for container: ");

					if (videoCoder.getPixelType() != IPixelFormat.Type.BGR24) {
						resampler = IVideoResampler.make(videoCoder.getWidth(), videoCoder.getHeight(), IPixelFormat.Type.BGR24, videoCoder.getWidth(), videoCoder.getHeight(), videoCoder.getPixelType());
						if (resampler == null)
							throw new RuntimeException("could not create color space resampler for: ");
					}
					if(mScreen == null)
						throw new IllegalStateException("mScreen == null");
				}

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
				mFirstVideoTimestampInStream = Global.NO_PTS;
				mSystemVideoClockStartTime = 0;

				while(container.readNextPacket(packet) >= 0 && play) {
					if (packet.getStreamIndex() == videoStreamId) {
						IVideoPicture picture = IVideoPicture.make(videoCoder.getPixelType(), videoCoder.getWidth(), videoCoder.getHeight());

						int bytesDecoded = videoCoder.decodeVideo(picture, packet, 0);
						if (bytesDecoded < 0)
							throw new RuntimeException("got error decoding audio in: ");

						if (picture.isComplete()) {
							IVideoPicture newPic = picture;
							if (resampler != null) {
								newPic = IVideoPicture.make(resampler.getOutputPixelFormat(), picture.getWidth(), picture.getHeight());
								if (resampler.resample(newPic, picture) < 0)
									throw new RuntimeException("could not resample video from: ");
							}
							if (newPic.getPixelType() != IPixelFormat.Type.BGR24)
								throw new RuntimeException("could not decode video as BGR 24 bit data in: ");

							long delay = millisecondsUntilTimeToDisplay(newPic);
							try {
								if (delay > 0)
									Thread.sleep(delay);
							}
							catch (InterruptedException e) {
								return;
							}

							mScreen.setImage(Utils.videoPictureToImage(newPic));
						}
					}
					else if (packet.getStreamIndex() == audioStreamId) {
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
				
				//ecoServerImpl.consumption -= 11.5;
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
		this.mScreen = mScreen;
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
		if (!IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION))
			throw new RuntimeException("you must install the GPL version of Xuggler (with IVideoResampler support) for this demo to work");

		container = IContainer.make();

		if (container.open(videoPath, IContainer.Type.READ, null) < 0)
			throw new IllegalArgumentException("could not open file: " + videoPath);

		int numStreams = container.getNumStreams();

		videoStreamId = -1;
		videoCoder = null;
		audioStreamId = -1;
		audioCoder = null;

		for(int i = 0; i < numStreams; i++) {
			IStream stream = container.getStream(i);
			IStreamCoder coder = stream.getStreamCoder();

			if (videoStreamId == -1 && coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
				videoStreamId = i;
				videoCoder = coder;
			}
			else if (audioStreamId == -1 && coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
				audioStreamId = i;
				audioCoder = coder;
			}
		}
		if (videoStreamId == -1 && audioStreamId == -1)
			throw new RuntimeException("could not find audio or video stream in container: "+videoPath);
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
	private long millisecondsUntilTimeToDisplay(IVideoPicture picture) {
		long millisecondsToSleep = 0;
		if (mFirstVideoTimestampInStream == Global.NO_PTS) {
			mFirstVideoTimestampInStream = picture.getTimeStamp();
			mSystemVideoClockStartTime = System.currentTimeMillis();
			millisecondsToSleep = 0;
		} else {
			long systemClockCurrentTime = System.currentTimeMillis();
			long millisecondsClockTimeSinceStartofVideo = systemClockCurrentTime - mSystemVideoClockStartTime;
			long millisecondsStreamTimeSinceStartOfVideo = (picture.getTimeStamp() - mFirstVideoTimestampInStream)/1000;
			final long millisecondsTolerance = 50; // and we give ourselfs 50 ms of tolerance
			millisecondsToSleep = (millisecondsStreamTimeSinceStartOfVideo - (millisecondsClockTimeSinceStartofVideo + millisecondsTolerance));
		}
		return millisecondsToSleep;
	}
}

package org.oep.services.ecoclient.impl1;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.oep.services.ecoserveur.api.xuggle.XuggleServer;

import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.IAudioSamples;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.IVideoResampler;
import com.xuggle.xuggler.Utils;

public class XuggleReader{
	private XuggleServer server;
	private XuggleView mScreen;

	private SourceDataLine mLine;
	private long mSystemVideoClockStartTime;
	private long mFirstVideoTimestampInStream;

	public XuggleReader() {
		server = null;
		mScreen = null;
	}

	public void play(){
		Thread t = new Thread( new Runnable() {
			@Override
			public void run() {
				if(server != null) {
					IVideoResampler resampler = null;
					if (server.getVideoCoder() != null) {
						if(server.getVideoCoder().open() < 0)
							throw new RuntimeException("could not open audio decoder for container: ");

						if (server.getVideoCoder().getPixelType() != IPixelFormat.Type.BGR24) {
							resampler = IVideoResampler.make(server.getVideoCoder().getWidth(), server.getVideoCoder().getHeight(), IPixelFormat.Type.BGR24,
									server.getVideoCoder().getWidth(), server.getVideoCoder().getHeight(), server.getVideoCoder().getPixelType());
							if (resampler == null)
								throw new RuntimeException("could not create color space resampler for: ");
						}
						if(mScreen == null)
							throw new IllegalStateException("mScreen == null");
					}

					if (server.getAudioCoder() != null) {
						if (server.getAudioCoder().open() < 0)
							throw new RuntimeException("could not open audio decoder for container: ");

						try {
							openJavaSound(server.getAudioCoder());
						}
						catch (LineUnavailableException ex) {
							throw new RuntimeException("unable to open sound device on your system when playing back container: ");
						}
					}

					IPacket packet = IPacket.make();
					mFirstVideoTimestampInStream = Global.NO_PTS;
					mSystemVideoClockStartTime = 0;

					while(server.getContainer().readNextPacket(packet) >= 0) {
						if (packet.getStreamIndex() == server.getVideoStreamId()) {
							IVideoPicture picture = IVideoPicture.make(server.getVideoCoder().getPixelType(), server.getVideoCoder().getWidth(), server.getVideoCoder().getHeight());

							int bytesDecoded = server.getVideoCoder().decodeVideo(picture, packet, 0);
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
						else if (packet.getStreamIndex() == server.getAudioStreamId()) {
							IAudioSamples samples = IAudioSamples.make(1024, server.getAudioCoder().getChannels());
							int offset = 0;

							while(offset < packet.getSize()) {
								int bytesDecoded = server.getAudioCoder().decodeAudio(samples, packet, offset);

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
				}
			}
		});

		t.start();
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

	public void setmScreen(XuggleView mScreen) {
		this.mScreen = mScreen;
	}
	public void setServer(XuggleServer server) {
		this.server = server;
	}
}

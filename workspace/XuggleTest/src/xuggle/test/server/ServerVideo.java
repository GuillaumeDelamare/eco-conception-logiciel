package xuggle.test.server;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoResampler;

public class ServerVideo implements Server{
	private IStreamCoder videoCoder;
	private IStreamCoder audioCoder;
	private int videoStreamId;
	private int audioStreamId;
	
	private IContainer container;
	
	public ServerVideo(){
		String filename = "file:/home/guillaume/Vidéos/big_buck_bunny_480p_surround-fix.avi";

		// Let's make sure that we can actually convert video pixel formats.
		if (!IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION))
			throw new RuntimeException("you must install the GPL version of Xuggler (with IVideoResampler support) for this demo to work");

		// Create a Xuggler container object
		container = IContainer.make();

		// Open up the container
		if (container.open(filename, IContainer.Type.READ, null) < 0)
			throw new IllegalArgumentException("could not open file: " + filename);

		// query how many streams the call to open found
		int numStreams = container.getNumStreams();

		// and iterate through the streams to find the first audio stream
		videoStreamId = -1;
		videoCoder = null;
		audioStreamId = -1;
		audioCoder = null;
		
		for(int i = 0; i < numStreams; i++) {
			// Find the stream object
			IStream stream = container.getStream(i);
			// Get the pre-configured decoder that can decode this stream;
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
			throw new RuntimeException("could not find audio or video stream in container: "+filename);
	}

	public IStreamCoder getVideoCoder() {
		return videoCoder;
	}

	public IStreamCoder getAudioCoder() {
		return audioCoder;
	}
	public IContainer getContainer() {
		return container;
	}
	public int getAudioStreamId() {
		return audioStreamId;
	}
	public int getVideoStreamId() {
		return videoStreamId;
	}
}

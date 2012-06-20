package xuggle.test.server;

import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;

public class ServerAudio implements Server {
	private IStreamCoder audioCoder;
	private int audioStreamId;
	
	private IContainer container;
	
	public ServerAudio() {
		String filename = "file:/home/guillaume/Vidéos/big_buck_bunny_480p_surround-fix.avi";

		// Create a Xuggler container object
		container = IContainer.make();

		// Open up the container
		if (container.open(filename, IContainer.Type.READ, null) < 0)
			throw new IllegalArgumentException("could not open file: " + filename);

		// query how many streams the call to open found
		int numStreams = container.getNumStreams();

		// and iterate through the streams to find the first audio stream
		audioStreamId = -1;
		audioCoder = null;
		for(int i = 0; i < numStreams; i++) {
			// Find the stream object
			IStream stream = container.getStream(i);
			// Get the pre-configured decoder that can decode this stream;
			IStreamCoder coder = stream.getStreamCoder();

			if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
				audioStreamId = i;
				audioCoder = coder;
				break;
			}
		}
		if (audioStreamId == -1)
			throw new RuntimeException("could not find audio stream in container: "+filename);

	}
	
	@Override
	public IStreamCoder getVideoCoder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IStreamCoder getAudioCoder() {
		// TODO Auto-generated method stub
		return audioCoder;
	}
	@Override
	public IContainer getContainer() {
		return container;
	}
	@Override
	public int getAudioStreamId() {
		// TODO Auto-generated method stub
		return audioStreamId;
	}
	@Override
	public int getVideoStreamId() {
		// TODO Auto-generated method stub
		return -1;
	}
}

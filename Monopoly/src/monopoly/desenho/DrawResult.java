package monopoly.desenho;

import java.net.URL;

@SuppressWarnings("serial")
public class DrawResult extends Draw {
	
	private int i;
	public DrawResult(int i) {
		this.i = i;
		init();
	}

	@Override
	public URL getURL() {
		return  getClass().getClassLoader().getResource("d"+i+".png");
	}
	
}

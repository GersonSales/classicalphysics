package javafx.physics;

import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.asset.Texture;

public class Positive implements Charge {

	@Override
	public Texture getTexture() {
		AssetManager assetManager = new AssetManager();
		try {
			return assetManager.loadTexture("plusBall.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

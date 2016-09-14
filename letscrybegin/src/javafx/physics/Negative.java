package javafx.physics;

import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.asset.Texture;

public class Negative implements Charge {

	@Override
	public Texture getTexture() {
		AssetManager assetManager = new AssetManager();
		try {
			return assetManager.loadTexture("inferiorPlate.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

package javafx.physics;

import com.almasb.fxgl.asset.AssetManager;
import com.almasb.fxgl.asset.Texture;

public class PositivePlate implements PlateCharge {

	private AssetManager assetManager;

	public PositivePlate() {
		this.assetManager = new AssetManager();
	}

	@Override
	public Texture getTexture() {
		try {
			return assetManager.loadTexture("upperPlate.png");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public Texture getField() {
		try {
			return assetManager.loadTexture("positiveArrow.png");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}

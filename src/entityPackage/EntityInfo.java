package entityPackage;

import entityPackage.*;

public class EntityInfo {

	GameEntity entity;
	int numOfThisEntity;
	
	public EntityInfo(GameEntity entity, int numOfThisEntity) {
		this.entity = entity;
		this.numOfThisEntity = numOfThisEntity;
	}
	
	public GameEntity getEntity() {
		return entity;
	}

	public void setEntity(GameEntity entity) {
		this.entity = entity;
	}

	public int getNumOfThisEntity() {
		return numOfThisEntity;
	}

	public void setNumOfThisEntity(int numOfThisEntity) {
		this.numOfThisEntity = numOfThisEntity;
	}
	

}

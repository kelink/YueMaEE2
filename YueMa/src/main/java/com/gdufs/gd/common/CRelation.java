package com.gdufs.gd.common;

public class CRelation {

	public enum FriendRelation {
		STRANGER(0), FRIEND(1), FRIEND_FRINED(2);

		private final int value;

		public int getValue() {
			return value;
		}

		FriendRelation(int value) {
			this.value = value;
		}
	}

	public enum RelationFrom {
		FROM_CONTACT(0), FROM_SYSTEM(1);
		private final int value;

		public int getValue() {
			return value;
		}

		RelationFrom(int value) {
			this.value = value;
		}
	}
}

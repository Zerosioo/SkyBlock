package me.zerosio.npcs.build.dialogue;

public enum VoiceType {
    MALE(1.0f),
    FEMALE(2.3f);

    private final float pitch;

    VoiceType(float pitch) {
        this.pitch = pitch;
    }

    public float getPitch() {
        return pitch;
    }
}

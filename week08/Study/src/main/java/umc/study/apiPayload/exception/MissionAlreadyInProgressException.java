package umc.study.apiPayload.exception;

public class MissionAlreadyInProgressException extends RuntimeException {
    private final String missionId;

    public MissionAlreadyInProgressException(String missionId) {
        super("이미 진행 중인 미션입니다.");
        this.missionId = missionId;
    }

    public String getMissionId() {
        return missionId;
    }
}

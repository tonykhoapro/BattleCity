package com.mygdx.battlecity;

public abstract class Tickable {

    // Cờ lưu trạng thái của đối tượng.
    public enum EState {
        UNIDENTIFIED, // Object chỉ được cấp phát trong bộ nhớ và chưa được TickableManager sử dụng.
        ACTIVATED, // Object vừa chạy xong hàm OnActivate.
        TICKING,  // Object vừa chạy xong hàm OnTick.
        DEACTIVATED // Object vừa chạy xong hàm OnDeactivate.
    }

    public final boolean isActive() {
        return state == EState.TICKING || state == EState.ACTIVATED;
    }

    // Được gọi khi bắt đầu nhận tick mỗi frame.
    public void OnActivate() {
        assert (state == EState.UNIDENTIFIED || state == EState.DEACTIVATED);
        state = EState.ACTIVATED;
    }

    // Gọi mỗi frame một lần.
    public void OnTick(float dt) {
        assert (state == EState.TICKING || state == EState.ACTIVATED);
        state = EState.TICKING;
    }

    // Hủy tick.
    public void OnDeactivate() {
        assert (state == EState.TICKING);
        state = EState.DEACTIVATED;
    }

    public static void Activate(Tickable tickable) {
        TickManager.getInstance().AddTickable(tickable);
    }

    public static void Deactivate(Tickable tickable) {
        TickManager.getInstance().RemoveTickable(tickable);
    }

    public EState getState() {
        return state;
    }


    private EState state = EState.UNIDENTIFIED;
}

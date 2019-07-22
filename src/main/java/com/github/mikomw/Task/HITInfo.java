package com.github.mikomw.Task;

/**
 * This class is information about a HIT,
 *
 * @version 1.0
 *
 * @author Songbo
 *
 */

public final class HITInfo {
    private final String hitId;
    private final String hitTypeId;

    public HITInfo(final String hitId, final String hitTypeId) {
        this.hitId = hitId;
        this.hitTypeId = hitTypeId;
    }

    public String getHITId() {
        return this.hitId;
    }

    public String getHITTypeId() {
        return this.hitTypeId;
    }
}
package com.ahmadhamwi.keepclone.presentation.ui.util;

public class Event<T> {

    private T data;
    private boolean eventHandled;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isEventHandled() {
        return eventHandled;
    }

    public void setEventHandled(boolean eventHandled) {
        this.eventHandled = eventHandled;
    }
}

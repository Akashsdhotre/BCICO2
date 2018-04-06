package com.example.akashsdhotre.bcico2.Interfaces;

/**
 * @interface HTTPCallback
 * @desc Interface for implementing HTTpTask Server Response Call back in activity.
 */

public interface HTTPCallback {
    /**
     *Callback method for Success HTTPTask from server.
     * @param statusCode Success response code from sever.
     * @param statusMessage Success message from server.
     * @param data JSON string data from server
     */
    public void onSuccess(int statusCode, String statusMessage, String data, int version);

    /**
     * method to handle failure httptask from server.
     * @param statusCode failure status code from server.
     * @param statusMessage failure status message from server.
     */
    public void onFailure(int statusCode, String statusMessage);

    /**
     * method to handle error on httptask from server
     * @param statusCode error status code from server
     * @param statusMessage error status message from server
     */
    public void onError(int statusCode, String statusMessage);
}

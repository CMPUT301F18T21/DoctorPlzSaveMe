package com.erikligai.doctorplzsaveme.Models;

import com.erikligai.doctorplzsaveme.backend.Backend;

/**
 * RecordBuffer class which essentially builds up a Record to then add to a Problem
 * We need this because our various activities piece by piece build a Record and we need a
 * data class we can access across those activities, easiest way is a singleton.
 */
public class RecordBuffer {
    private static final RecordBuffer ourInstance = new RecordBuffer();

    private Record m_record;

    private Boolean flag = false;

    /**
     * Singleton method to return the instance (buffer)
     * @return RecordBuffer
     */
    public static RecordBuffer getInstance() {
        return ourInstance;
    }

    /**
     * Returns the actual record being stored
     * @return : Record
     */
    public Record getRecord() {
        return m_record;
    }

    /**
     * Constructor for the RecordBuffer which makes a new empty Record and stores it to itself
     */
    private RecordBuffer() {
        m_record = new Record();
    }

    /**
     * Clears the buffer to prep it for next use
     */
    public void ClearBuffer() {
        m_record = new Record();
        flag = false;
    }

    /**
     * add a record add a given problem [index]
     * @param problem_index : int
     */
    public void addRecord(int problem_index){
        Backend.getInstance().addPatientRecord(problem_index, m_record);
        ClearBuffer();
    }

    /**
     * override an existing record at a given index and problem index
     * @param problemIndex : int
     * @param recordIndex : int
     */
    public void setExistingRecord(int problemIndex, int recordIndex)
    {
        if (flag) { return; }
        flag = true;
        Record r = Backend.getInstance().getPatientRecords(problemIndex).get(recordIndex);
        m_record = new Record();
        m_record.setComment(r.getComment());
        m_record.setTitle(r.getTitle());
        m_record.setDate(r.getDate());
        m_record.setGeolocation(r.getGeolocation());
        m_record.setPhotoid(r.getPhotoid());
        m_record.setXpos(r.getXpos());
        m_record.setYpos(r.getYpos());
        m_record.setPhotos(r.getPhotos());
    }

    /**
     * Populates the buffer with the record fetched with given indexes from backend
     * @param problemIndex : int
     * @param recordIndex : int
     */
    public void editRecord(int problemIndex, int recordIndex){
        Record r = Backend.getInstance().getPatientRecords(problemIndex).get(recordIndex);

        r.setTitle(m_record.getTitle());
        r.setComment(m_record.getComment());
        r.setDate(m_record.getDate());
        r.setGeolocation(m_record.getGeolocation());
        r.setPhotoid(m_record.getPhotoid());
        r.setXpos(m_record.getXpos());
        r.setYpos(m_record.getYpos());
        r.setPhotos(m_record.getPhotos());

        Backend.getInstance().UpdatePatient();
        ClearBuffer();
    }
    public void setImageID(String imageID){
        m_record.setPhotoid(imageID);

    }
}

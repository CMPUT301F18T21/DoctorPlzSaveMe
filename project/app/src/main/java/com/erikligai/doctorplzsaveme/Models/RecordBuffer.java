package com.erikligai.doctorplzsaveme.Models;

import com.erikligai.doctorplzsaveme.backend.Backend;

public class RecordBuffer {
    private static final RecordBuffer ourInstance = new RecordBuffer();

    public static RecordBuffer getInstance() {
        return ourInstance;
    }

    public Record getRecord() {
        return m_record;
    }

    private Record m_record;

    private RecordBuffer() {
        m_record = new Record();
    }

    public void ClearBuffer() {
        m_record = new Record();
    }

    public void addRecordBuffer(int problem_index){
        Backend.getInstance().addPatientRecord(problem_index, m_record);
        ClearBuffer();
    }
    // RecordBuffer.getInstance().getRecord().get...
    // RecordBuffer.getInstance().ClearBuffer();
}

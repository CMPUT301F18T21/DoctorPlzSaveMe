package com.erikligai.doctorplzsaveme.Models;

import android.util.Log;

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

    private Boolean flag = false;

    private RecordBuffer() {
        m_record = new Record();
    }

    public void ClearBuffer() {
        m_record = new Record();
        flag = false;
    }

    public void addRecordBuffer(int problem_index){
        Backend.getInstance().addPatientRecord(problem_index, m_record);
        ClearBuffer();
    }

    public void setExistingRecord(int problemIndex, int recordIndex)
    {
        if (flag) { return; }
        flag = true;
        Record r = Backend.getInstance().getPatientRecords(problemIndex).get(recordIndex);
        m_record = new Record();
        m_record.setComment(r.getComment());
        m_record.setTitle(r.getTitle());
        m_record.setDate(r.getDate());
        Log.d("abc", m_record.getTitle());
        // ...
    }

    public void editRecord(int problemIndex, int recordIndex){
        Record r = Backend.getInstance().getPatientRecords(problemIndex).get(recordIndex);
        r.setTitle(m_record.getTitle());
        r.setComment(m_record.getComment());
        Backend.getInstance().UpdatePatient();
        ClearBuffer();
    }
}

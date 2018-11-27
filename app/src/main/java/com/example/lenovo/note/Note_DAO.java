package com.example.lenovo.note;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

//Data Access Object
@Dao
public interface Note_DAO {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void ClearDataBase();

    @Query("SELECT * FROM note_table ORDER BY periority DESC")
    LiveData<List<Note>> getAllData();

}

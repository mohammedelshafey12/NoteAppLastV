package com.example.lenovo.note;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class NoteRepository {
    private Note_DAO noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDataBase dataBase = NoteDataBase.getInstance(application);
        noteDao = dataBase.noteDao();
        allNotes = noteDao.getAllData();
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }
    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }
    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }
    public void deleteAll(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }
    public LiveData<List<Note>> getAllData(){
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note , Void , Void>{
        public Note_DAO noteDao;
        public InsertNoteAsyncTask(Note_DAO noteDao) {
            this.noteDao =  noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    private static class UpdateNoteAsyncTask extends AsyncTask<Note , Void , Void>{
        public Note_DAO noteDao;
        public UpdateNoteAsyncTask(Note_DAO noteDao) {
            this.noteDao =  noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    private static class DeleteNoteAsyncTask extends AsyncTask<Note , Void , Void>{
        public Note_DAO noteDao;
        public DeleteNoteAsyncTask(Note_DAO noteDao) {
            this.noteDao =  noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void , Void , Void>{
        public Note_DAO noteDao;
        public DeleteAllNotesAsyncTask(Note_DAO noteDao) {
            this.noteDao =  noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.ClearDataBase();
            return null;
        }
    }


}

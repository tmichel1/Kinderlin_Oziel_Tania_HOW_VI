####lvm/app/src/main/java/br/com/dlweb/lvm/database/DatabaseHelper.java
package br.com.dlweb.lvm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import br.com.dlweb.lvm.R;
import br.com.dlweb.lvm.objeto.objeto;
import br.com.dlweb.lvm.unidade.unidade;
import br.com.dlweb.lvm.conteudo.conteudo;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "lvm";
    private static final String TABLE_unidade = "unidade";
    private static final String TABLE_objeto = "objeto";
    private static final String TABLE_conteudo = "conteudo";


    private static final String CREATE_TABLE_unidade = "CREATE TABLE " + TABLE_unidade + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100)) ";
            
  private static final String CREATE_TABLE_objeto = "CREATE TABLE " + TABLE_objeto + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
             "id_conteudo INTEGER, " +
            "nome VARCHAR(100), " +
            "nivel VARCHAR(10), " +
            "url VARCHAR(60))";
            "CONSTRAINT fk_objeto_conteudo FOREIGN KEY (id_conteudo) REFERENCES conteudo (id))";

  private static final String CREATE_TABLE_conteudo = "CREATE TABLE " + TABLE_conteudo + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_unidade INTEGER, " +
            "nome VARCHAR(100), " +
            "CONSTRAINT fk_conteudo_unidade FOREIGN KEY (id_unidade) REFERENCES unidade (id))";
     
    private static final String DROP_TABLE_unidade = "DROP TABLE IF EXISTS " + TABLE_unidade;
    private static final String DROP_TABLE_objeto = "DROP TABLE IF EXISTS " + TABLE_objeto;
    private static final String DROP_TABLE_conteudo = "DROP TABLE IF EXISTS " + TABLE_conteudo;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_unidade);
        db.execSQL(CREATE_TABLE_objeto);
        db.execSQL(CREATE_TABLE_conteudo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_unidade);
        db.execSQL(DROP_TABLE_objeto);
        db.execSQL(DROP_TABLE_conteudo);
        onCreate(db);
    }

    /* Início CRUD Objeto*/
    public long createobjeto (objeto m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("nivel", m.getnivel());
        cv.put("URL", m.getURL());
        long id = db.insert(TABLE_objeto, null, cv);
        db.close();
        return id;
    }
    public long updateobjeto (objeto m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("nivel", m.getnivel());
        cv.put("URL", m.getURL());
        long id = db.update(TABLE_objeto, cv,
                "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }
    public long deleteobjeto (objeto m) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_objeto, "_id = ?",
                new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }
    public void getAllobjeto (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "URL"};
        Cursor data = db.query(TABLE_objeto, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarobjeto, R.id.textViewNomeListarobjeto,
                R.id.textViewURLListarobjeto};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.objeto_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
    public objeto getByIdobjeto (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "nivel", "URL"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_objeto, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        objeto m = new objeto();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setnivel(data.getString(2));
        m.setURL(data.getString(3));
        data.close();
        db.close();
        return m;
    }
    public void getAllNameobjeto (ArrayList<Integer> listobjetoId, ArrayList<String> listobjetoName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        Cursor data = db.query(TABLE_objeto, columns, null, null, null,
                null, "nome");
        while (data.moveToNext()) {
            int idColumnIndex = data.getColumnIndex("_id");
            listobjetoId.add(Integer.parseInt(data.getString(idColumnIndex)));
            int nameColumnIndex = data.getColumnIndex("nome");
            listobjetoName.add(data.getString(nameColumnIndex));
        }
        db.close();
    }
    /* Fim CRUD objeto */
   
    /* Início CRUD unidade */
    public long createunidade (unidade m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        
        long id = db.insert(TABLE_unidade, null, cv);
        db.close();
        return id;
    }

    public long updateUnidade (unidade m) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        long id = db.update(TABLE_unidade, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public long deleteunidade (unidade m) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_unidade, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public unidade getByIdUnidade (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome"};
        Cursor data = db.query(TABLE_unidade, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        unidade m = new unidade();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
         data.close();
        db.close();
        return m;
    }

    public void getAllunidade (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "URL"};
        Cursor data = db.query(TABLE_unidade, columns, null, null, null, null, null);
        int[] to = {R.id.textViewIdListunidade, R.id.textViewNomeListunidade, R.id.textViewURLListunidade};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context, R.layout.unidade_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public void getAllNameunidade (ArrayList<Integer> listunidadeId, ArrayList<String> listunidadeName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        Cursor data = db.query(TABLE_unidade, columns, null, null, null,
                null, "nome");
        while (data.moveToNext()) {
            int idColumnIndex = data.getColumnIndex("_id");
            listunidadeId.add(Integer.parseInt(data.getString(idColumnIndex)));
            int nameColumnIndex = data.getColumnIndex("nome");
            listunidadeName.add(data.getString(nameColumnIndex));
        }
        db.close();
    }

    /* Fim CRUD Unidade temática */

    /* Início CRUD conteudo */
    public long createconteudo(conteudo b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_unidade", b.getId_unidade());
         cv.put("nome", b.getNome());
        long id = db.insert(TABLE_conteudo, null, cv);
        db.close();
        return id;
    }

    public long updateconteudo(conteudo b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id_unidade", b.getId_unidade());
        cv.put("nome", b.getNome());
        long rows = db.update(TABLE_conteudo, cv, "_id = ?", new String[]{String.valueOf(b.getId())});
        db.close();
        return rows;
    }

    public long deleteconteudo(conteudo b) {
        SQLiteDatabase db = this.getWritableDatabase();
        long rows = db.delete(TABLE_conteudo, "_id = ?", new String[]{String.valueOf(b.getId())});
        db.close();
        return rows;
    }

    public void getAllconteudo (Context context, ListView lv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome"};
        Cursor data = db.query(TABLE_conteudo, columns, null, null, null,
                null, "nome");
        int[] to = {R.id.textViewIdListarconteudo, R.id.textViewNomeListarconteudo};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.conteudo_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public conteudo getByIdconteudo (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "id_unidade", "nome"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_conteudo, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        conteudo b = new conteudo();
        b.setId(data.getInt(0));
        b.setId_unidade(data.getInt(1));
         b.setNome(data.getString(3));
       
        data.close();
        db.close();
        return b;
    }
    /* Fim CRUD conteudo*/
}

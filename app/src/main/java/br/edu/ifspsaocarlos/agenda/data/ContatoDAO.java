package br.edu.ifspsaocarlos.agenda.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import java.util.ArrayList;
import java.util.List;


public class ContatoDAO {
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public ContatoDAO(Context context) {
        this.dbHelper=new SQLiteHelper(context);
    }

    public  List<Contato> buscaTodosContatos()
    {
        database=dbHelper.getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        Cursor cursor;

        String[] cols=new String[] {
                SQLiteHelper.KEY_ID,SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_FONE, SQLiteHelper.KEY_FONED,
                SQLiteHelper.KEY_EMAIL, SQLiteHelper.KEY_DATANIVERSARIO};

        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, null , null,
                null, null, SQLiteHelper.KEY_NAME);

        while (cursor.moveToNext())
        {
            Contato contato = new Contato();
            contato.setId(cursor.getInt(0));
            contato.setNome(cursor.getString(1));
            contato.setFone(cursor.getString(2));
            contato.setFoneD(cursor.getString(3));
            contato.setEmail(cursor.getString(4));
            contato.setData(cursor.getString(5));

            contatos.add(contato);
        }
        cursor.close();

        database.close();
        return contatos;
    }

    public  List<Contato> buscaContato(String nome)
    {
        database=dbHelper.getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        Cursor cursor;

        String[] cols=new String[] {SQLiteHelper.KEY_ID,SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_FONE, SQLiteHelper.KEY_FONED, SQLiteHelper.KEY_EMAIL, SQLiteHelper.KEY_DATANIVERSARIO};
        String where=SQLiteHelper.KEY_NAME + " like ? OR " + SQLiteHelper.KEY_FONE + " like ? OR " + SQLiteHelper.KEY_FONED + " like ? OR " + SQLiteHelper.KEY_DATANIVERSARIO + " like ? OR " + SQLiteHelper.KEY_EMAIL + " like ?";
        String [] argWhere=new String[]{nome, nome, nome, nome, nome + "%"};

        cursor = database.query(SQLiteHelper.DATABASE_TABLE, cols, where , argWhere,
                null, null, SQLiteHelper.KEY_NAME);

        while (cursor.moveToNext())
        {
            Contato contato = new Contato();
            contato.setId(cursor.getInt(0));
            contato.setNome(cursor.getString(1));
            contato.setFone(cursor.getString(2));
            contato.setFoneD(cursor.getString(3));
            contato.setEmail(cursor.getString(4));
            contato.setData(cursor.getString(5));
            contatos.add(contato);
        }
        cursor.close();

        database.close();
        return contatos;
    }

    public void salvaContato(Contato c) {
        database=dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.KEY_NAME, c.getNome());
        values.put(SQLiteHelper.KEY_FONE, c.getFone());
        values.put(SQLiteHelper.KEY_FONED, c.getFoneD());
        values.put(SQLiteHelper.KEY_EMAIL, c.getEmail());
        values.put(SQLiteHelper.KEY_DATANIVERSARIO, c.getData());

        if (c.getId()>0)
            database.update(SQLiteHelper.DATABASE_TABLE, values, SQLiteHelper.KEY_ID + "="
                    + c.getId(), null);
        else
            database.insert(SQLiteHelper.DATABASE_TABLE, null, values);

        database.close();
    }

    public void apagaContato(Contato c)
    {
        database=dbHelper.getWritableDatabase();
        database.delete(SQLiteHelper.DATABASE_TABLE, SQLiteHelper.KEY_ID + "="
                + c.getId(), null);
        database.close();
    }

    //Adicionando os favoritos
    public void addToFavorites (Contato c)
    {
        database=dbHelper.getReadableDatabase();
        String query = String.format("INSERT INTO contatos(favorites) VALUE ('%s');", c );

    }
    public void removeFromFavorites (Contato c)
    {
        database=dbHelper.getReadableDatabase();
        String query = String.format("DELETE FROM contatos WHERE favorites='%s';", c);
    }


    public boolean BuscaFavoritos (String Favorites)
    {
        database=dbHelper.getReadableDatabase();

        String query = String.format("SELECT * FROM contatos WHERE favorites='%s';", BuscaFavoritos(Favorites));
        Cursor cursor = database.rawQuery(query, null);

        if(cursor.getCount() <= 0)
        {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

}
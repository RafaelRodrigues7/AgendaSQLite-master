package br.edu.ifspsaocarlos.agenda.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.ifspsaocarlos.agenda.data.SQLiteHelper;
import br.edu.ifspsaocarlos.agenda.model.Contato;
import br.edu.ifspsaocarlos.agenda.R;

import java.util.List;




public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private SQLiteHelper dbHelper;
    private List<Contato> contatos;
    private Context context;
    public ImageView  fav_image;
    private SQLiteDatabase database;

    public ContatoAdapter(Context favoritos) {
        this.dbHelper=new SQLiteHelper(favoritos);}

    private static ItemClickListener clickListener;

    public ContatoAdapter(List<Contato> contatos, Context context) {
        this.contatos = contatos;
        this.context = context;
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contato_celula, parent, false);
        return new ContatoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContatoViewHolder holder, int position) {
        Contato contato  = contatos.get(position) ;
        holder.nome.setText(contato.getNome());
        contato.getFavorites();

        if(contato.getFavorites() == "1") {
            holder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else

            holder.fav_image.setImageResource(R.drawable.ic_favorite_border_black_24dp);
    }


    @Override
    public int getItemCount() {
        return contatos.size();
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        clickListener = itemClickListener;
    }


    public  class ContatoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView nome;
        final ImageView fav_image;

        ContatoViewHolder(final View view) {
            super(view);
            nome = (TextView)view.findViewById(R.id.nome);
            fav_image = (ImageView)view.findViewById(R.id.favoriteView);
            view.setOnClickListener(this);
            fav_image.setOnClickListener(this);
            fav_image.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Contato c = contatos.get(getAdapterPosition());


                }
            });
        }

        //Add favorites

        /*if(isFavorite(adapter.getRef(position).getKey()))
            viewHolder.fav_image.setImageResource(R.drawable.ic_favorite_black_24dp);

        viewHolder.fav_image.setOnClickListener (new View.OnClickListener() {
            public void Onclick(View view) {
                if(!databas)
            }
       })*/
        @Override
        public void onClick(View view) {
            Contato c = contatos.get(getAdapterPosition());
            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition());
        }
    }


    public interface ItemClickListener {
        void onItemClick(int position);
    }

}
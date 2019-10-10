package pe.edu.idat.appfragmentsavanzado1.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pe.edu.idat.appfragmentsavanzado1.Modelo.Flores;
import pe.edu.idat.appfragmentsavanzado1.R;

public class FloresAdapter extends RecyclerView.Adapter<FloresAdapter.FloresViewHolder> {

    private Context context;
    private ArrayList<Flores> lista;

    public FloresAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public FloresAdapter.FloresViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context)
                .inflate(R.layout.item_detalle,
                        viewGroup,false);
        return new FloresViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FloresAdapter.FloresViewHolder floresViewHolder, int i) {
        Flores item = lista.get(i);
        floresViewHolder.tvNombre.setText("Creador: "+ item.getNombre());
        Picasso.with(context).load(item.getUrlImagen())
                .fit().centerInside()
                .into(floresViewHolder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class FloresViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvNombre;
        public FloresViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvNombre = itemView.findViewById(R.id.tvNombre);
        }
    }
    public void agregarElemento(ArrayList<Flores> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}

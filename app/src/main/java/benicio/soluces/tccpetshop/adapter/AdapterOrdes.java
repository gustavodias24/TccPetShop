package benicio.soluces.tccpetshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.soluces.tccpetshop.R;
import benicio.soluces.tccpetshop.model.OrderModel;

public class AdapterOrdes extends RecyclerView.Adapter<AdapterOrdes.MyViewHolder> {

    List<OrderModel> orders;
    Context c;


    public AdapterOrdes(List<OrderModel> orders, Context c) {
        this.orders = orders;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_sub_radio_button, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OrderModel orderModel = orders.get(position);

        holder.data.setText(orderModel.getData());
        holder.nomeLoja.setText(orderModel.getStoreName());
        holder.status.setText(orderModel.getStatus());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView data, nomeLoja, status;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.history_data);
            nomeLoja = itemView.findViewById(R.id.store_name);
            status = itemView.findViewById(R.id.order_finished);
        }
    }
}

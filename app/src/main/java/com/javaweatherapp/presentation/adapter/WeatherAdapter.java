package com.javaweatherapp.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.javaweatherapp.R;
import com.javaweatherapp.domain.model.WeatherInfoItem;
import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
  private final List<WeatherInfoItem> items = new ArrayList<>();

  @NonNull
  @Override
  public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
    return new WeatherViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.bind(items.get(position));
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void setItems(@NonNull List<WeatherInfoItem> newItems) {
    items.clear();
    items.addAll(newItems);
    notifyDataSetChanged();
  }

  static class WeatherViewHolder extends RecyclerView.ViewHolder {
    TextView textTemp, textTempMin, textTempMax, textDescription, textDate;

    WeatherViewHolder(@NonNull View itemView) {
      super(itemView);
      textDate = itemView.findViewById(R.id.textDate);
      textTemp = itemView.findViewById(R.id.textTemp);
      textTempMin = itemView.findViewById(R.id.textTempMin);
      textTempMax = itemView.findViewById(R.id.textTempMax);
      textDescription = itemView.findViewById(R.id.textDescription);

    }

    void bind(WeatherInfoItem item) {
      textDate.setText(item.getDay());
      textTemp.setText("Temperature: " + String.format("%.2f", item.getTemp()) + "°F");
      textTempMin.setText("Minimun temperature: " + String.format("%.2f",item.getTempMin())+ "°F");
      textTempMax.setText("Maximum temperature: " + String.format("%.2f",item.getTempMax())+ "°F");
    }
  }
}

package benicio.soluces.tccpetshop.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.session.MediaSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import benicio.soluces.tccpetshop.model.ProductModel;

public class CarrinhoUtils {

    public static final String prefs_carrinho = "Carrinho";
    public static final String name_carrinho = "lista";

    public static void saveCarrinho(List<ProductModel> lista, Context c){
        SharedPreferences preferences = c.getSharedPreferences(prefs_carrinho, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        Gson gson = new Gson();

        editor.putString(name_carrinho, gson.toJson(lista));
        editor.apply();
    }

    public static List<ProductModel> returnCarrinho(Context c){
        SharedPreferences preferences = c.getSharedPreferences(prefs_carrinho, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<List<ProductModel>>(){}.getType();

        if ( gson.fromJson(preferences.getString(name_carrinho, ""), type) != null){
            return gson.fromJson(preferences.getString(name_carrinho, ""), type);
        }else {
            return  new ArrayList<>();
        }
    }
}

package com.example.webpos.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();


    public boolean modify(Item item1,Item item2){
        int sum = item1.getQuantity()+ item2.getQuantity();
        if(sum <= 0)
            return deleteItem(item1);
        item1.setQuantity(sum);
        return true;
    }


    public void clear(){
        items.clear();
    }

    public boolean addItem(Item item) {
        Item i = items.stream().filter(t -> t.getProduct().getId().equals(item.getProduct().getId())).findAny().orElse(null);
        if(i!=null){
            return modify(i,item);
        }
        return items.add(item);
    }

    public boolean deleteItem(Item item){
        return this.items.remove(item);
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getQuantity() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}

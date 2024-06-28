package com.anxstra.gson.adapters;

import com.anxstra.entities.Discount;
import com.anxstra.entities.enums.DiscountType;
import com.anxstra.exceptions.WrongPropertyException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DiscountAdapter extends TypeAdapter<Discount> {

    private static final String ID_PROPERTY_NAME = "id";

    private static final String TYPE_PROPERTY_NAME = "type";

    private static final String DATE_PROPERTY_NAME = "date";

    private static final String DATE_FROM_PROPERTY_NAME = "dateFrom";

    private static final String DATE_TO_PROPERTY_NAME = "dateTo";

    private static final String DISCOUNT_PROPERTY_NAME = "discount";

    @Override
    public void write(JsonWriter jsonWriter, Discount discount) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name(ID_PROPERTY_NAME);
        jsonWriter.value(discount.getId());
        jsonWriter.name(TYPE_PROPERTY_NAME);
        jsonWriter.value(discount.getType()
                                 .name());
        if (discount.getType() == DiscountType.ONE) {
            jsonWriter.name(DATE_PROPERTY_NAME);
            jsonWriter.value(discount.getDateFrom()
                                     .toString());
        } else {
            jsonWriter.name(DATE_FROM_PROPERTY_NAME);
            jsonWriter.value(discount.getDateFrom()
                                     .toString());
            jsonWriter.name(DATE_TO_PROPERTY_NAME);
            jsonWriter.value(discount.getDateTo()
                                     .toString());
        }
        jsonWriter.name(DISCOUNT_PROPERTY_NAME);
        jsonWriter.value(discount.getDiscountAmount());
        jsonWriter.endObject();
    }

    @Override
    public Discount read(JsonReader jsonReader) throws IOException {
        Discount discount = new Discount();
        jsonReader.beginObject();
        String fieldName = "";
        while (jsonReader.hasNext()) {
            JsonToken jsonToken = jsonReader.peek();
            if (jsonToken.equals(JsonToken.NAME)) {
                fieldName = jsonReader.nextName();
                jsonReader.peek();
            }
            switch (fieldName) {
                case ID_PROPERTY_NAME -> discount.setId(jsonReader.nextInt());
                case TYPE_PROPERTY_NAME -> discount.setType(DiscountType.valueOf(jsonReader.nextString()));
                case DATE_PROPERTY_NAME -> {
                    LocalDate date = LocalDate.parse(jsonReader.nextString());
                    discount.setDateFrom(date);
                    discount.setDateTo(date);
                }
                case DATE_FROM_PROPERTY_NAME -> discount.setDateFrom(LocalDate.parse(jsonReader.nextString()));
                case DATE_TO_PROPERTY_NAME -> discount.setDateTo(LocalDate.parse(jsonReader.nextString()));
                case DISCOUNT_PROPERTY_NAME -> discount.setDiscountAmount(new BigDecimal(jsonReader.nextString()));
                default ->
                        throw new WrongPropertyException("Property with name " + fieldName + " is not a part of Discount");
            }
        }
        jsonReader.endObject();
        return discount;
    }
}

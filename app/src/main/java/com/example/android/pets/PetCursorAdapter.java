package com.example.android.pets;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;

/**
 * Created by the_insider on 13/03/2017.
 */
public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter (Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find individual views that we are going to use in the list item
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView breedTextView = (TextView) view.findViewById(R.id.breed);
        TextView genderTextView = (TextView) view.findViewById(R.id.gender);
        TextView weightTextView = (TextView) view.findViewById(R.id.weight);

        // Find the column index in the cursor for each field of pet attributes
        int nameColumnIndex =  cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int breedColumnIndex =  cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
        int genderColumnIndex =  cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
        int weightColumnIndex =  cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);

        // Read the pet attributes from the cursor
        String petName = cursor.getString(nameColumnIndex);
        String petBreed = cursor.getString(breedColumnIndex);
        int petGender = cursor.getInt(genderColumnIndex); // We need this as an int to replace it with string
        String petWeight = cursor.getString(weightColumnIndex);

        // Update TextViews with the attributes of the pets
        nameTextView.setText(petName);
        if (TextUtils.isEmpty(petBreed)) {
            petBreed = context.getString(R.string.breed_unknown);

        }
        breedTextView.setText(petBreed);

        // Use petGender value to decide the text on genderTextxView
        switch (petGender) {
            case 1:
                genderTextView.setText("Male");
                break;
            case 2:
                genderTextView.setText("Female");
                break;
            default:
                genderTextView.setText("Unknown");
        }
        weightTextView.setText(petWeight + "kg");
    }
}

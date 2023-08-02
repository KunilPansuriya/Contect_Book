package com.example.contact_book;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.contact_book.databinding.ActivityAddContactBinding;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class Add_Contact_Activity extends AppCompatActivity {
    ActivityAddContactBinding binding;

    Uri resultUri;
    MydataBase1 mydataBase1 = new MydataBase1(Add_Contact_Activity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.nametextview.getText().toString();
                String email = binding.emailtextview.getText().toString();
                String number = binding.numbertextview.getText().toString();

                mydataBase1.addData(name,email,number, String.valueOf(resultUri));
                binding.nametextview.setText("");
                binding.emailtextview.setText("");
                binding.numbertextview.setText("");
                Intent intent = new Intent(Add_Contact_Activity.this,MainActivity.class);
                startActivity(intent);

            }
        });
        binding.cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Contact_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        binding.addContantImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(Add_Contact_Activity.this);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                binding.addContantImg.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }


    }
}
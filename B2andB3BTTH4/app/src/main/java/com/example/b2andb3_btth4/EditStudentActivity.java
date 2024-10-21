package com.example.b2andb3_btth4;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class EditStudentActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference("student_profiles");

    private EditText editName, editClass, editGpa;
    private ImageView imageProfile;
    private Uri imageUri;

    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        editName = findViewById(R.id.edit_name);
        editClass = findViewById(R.id.edit_class);
        editGpa = findViewById(R.id.edit_gpa);
        imageProfile = findViewById(R.id.image_profile);
        Button btnSave = findViewById(R.id.btn_save);
        Button btnSelectImage = findViewById(R.id.btn_select_image);

        btnSelectImage.setOnClickListener(v -> openFileChooser());
        btnSave.setOnClickListener(v -> saveStudent());

        studentId = getIntent().getStringExtra("studentId");
        if (studentId != null) {
            loadStudentData(studentId);
        }
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imageProfile.setImageURI(imageUri);
        }
    }

    private void saveStudent() {
        String hoten = editName.getText().toString();
        String lop = editClass.getText().toString();
        double diem = Double.parseDouble(editGpa.getText().toString());

        if (imageUri != null) {
            StorageReference fileRef = storageRef.child(System.currentTimeMillis() + ".jpg");
            fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();
                saveStudentToFirestore(hoten, lop, diem, imageUrl);
            })).addOnFailureListener(e -> {
                Toast.makeText(EditStudentActivity.this, "Upload failed", Toast.LENGTH_SHORT).show();
            });
        } else {
            saveStudentToFirestore(hoten, lop, diem, null);
        }
    }

    private void saveStudentToFirestore(String hoten, String lop, double diem, String imageUrl) {
        DocumentReference docRef = db.collection("sinhvien").document(studentId != null ? studentId : db.collection("sinhvien").document().getId());
        Student student = new Student(docRef.getId(), hoten, lop, diem, imageUrl);
        docRef.set(student)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(EditStudentActivity.this, "Student saved", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(EditStudentActivity.this, "Error saving student", Toast.LENGTH_SHORT).show());
    }

    private void loadStudentData(String studentId) {
        // Load student data from Firestore and display it (including image)
    }
}
